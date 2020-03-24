package org.gnosoft.validator.examples.services;

import org.gnosoft.validator.AsynchronousValidator;
import org.gnosoft.validator.Constraint;
import org.gnosoft.validator.ConstraintBuilder;
import org.gnosoft.validator.ValidatorBuilder;
import org.gnosoft.validator.Violation;
import org.gnosoft.validator.examples.model.Entity;
import org.gnosoft.validator.examples.model.Product;
import org.gnosoft.validator.examples.model.ProductFitnessConfiguration;
import org.gnosoft.validator.examples.store.ProductFitnessConfigurationRepository;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ProductFitForSaleValidationService {
  private static class ProductValidationContext {
    ProductFitnessConfiguration productFitnessConfiguration;
  }

  private final AsynchronousValidator<Product, ProductValidationContext> validator;

  private final TranslationService translationService;

  private final ProductFitnessConfigurationRepository productFitnessConfigurationRepository;

  public ProductFitForSaleValidationService(TranslationService translationService, ProductFitnessConfigurationRepository productFitnessConfigurationRepository) {
    this.translationService = translationService;
    this.productFitnessConfigurationRepository = productFitnessConfigurationRepository;

    this.validator = ValidatorBuilder.<Product, ProductValidationContext>builder()
      .add(checkForMaximumLength())
      .add(checkForMinimumLength())
      .add(checkForMaximumWidth())
      .add(checkforMinimumWidth())
      .add(checkForMaximumHeight())
      .add(checkForMinimumHeight())
      .add(checkForValidDescription())
      .add(checkForValidName())
      .add(checkForListLessThanMsrp())
      .buildAsynchronous();
  }

  private Constraint<Product, ProductValidationContext> checkForMaximumLength() {
    return ConstraintBuilder.violation("label.product.exceeds.maximum.length")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getLength() != null
        && product.getDimensions().getLength().compareTo(context.productFitnessConfiguration.getLengthMax()) <= 0);
  }

  private Constraint<Product, ProductValidationContext> checkForMinimumLength() {
    return ConstraintBuilder.violation("label.product.exceeds.minimum.length")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getLength() != null
        && product.getDimensions().getLength().compareTo(context.productFitnessConfiguration.getLengthMin()) >= 0);
  }

  private Constraint<Product, ProductValidationContext> checkForMaximumWidth() {
    return ConstraintBuilder.violation("label.product.exceeds.maximum.width")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getWidth() != null
        && product.getDimensions().getWidth().compareTo(context.productFitnessConfiguration.getWidthMax()) <= 0);
  }

  private Constraint<Product, ProductValidationContext> checkforMinimumWidth() {
    return ConstraintBuilder.violation("label.product.exceeds.minimum.width")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getWidth() != null
        && product.getDimensions().getWidth().compareTo(context.productFitnessConfiguration.getWidthMin()) >= 0);
  }

  private Constraint<Product, ProductValidationContext> checkForMaximumHeight() {
    return ConstraintBuilder.violation("label.product.exceeds.maximum.height")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getHeight() != null
        && product.getDimensions().getHeight().compareTo(context.productFitnessConfiguration.getHeightMax()) <= 0);
  }

  private Constraint<Product, ProductValidationContext> checkForMinimumHeight() {
    return ConstraintBuilder.violation("label.product.exceeds.minimum.height")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getHeight() != null
        && product.getDimensions().getHeight().compareTo(context.productFitnessConfiguration.getHeightMin()) >= 0);
  }

  private Constraint<Product, ProductValidationContext> checkForValidDescription() {
    return ConstraintBuilder.violation("label.product.invalid.description").forConstraint((product, context) ->
      product.getDescription() != null
        && !product.getDescription().isBlank()
        && product.getDescription().length() >= context.productFitnessConfiguration.getDescriptionLengthMin()
        && product.getDescription().length() <= context.productFitnessConfiguration.getDescriptionLengthMax());
  }

  private Constraint<Product, ProductValidationContext> checkForValidName() {
    return ConstraintBuilder.violation("label.product.invalid.name").forConstraint((product, context) ->
      product.getName() != null
        && !product.getName().isBlank()
        && product.getName().length() >= context.productFitnessConfiguration.getNameLengthMin()
        && product.getName().length() <= context.productFitnessConfiguration.getNameLengthMax());
  }

  private Constraint<Product, ProductValidationContext> checkForListLessThanMsrp() {
    return ConstraintBuilder.violation("label.product.list.greater.than.msrp").forConstraint((product, context) ->
      product.getListPrice() != null
        && product.getListPrice().compareTo(product.getManufacturerSuggestedRetailPrice()) <= 0);
  }

  public Map<Long, Collection<String>> validate(Collection<Product> products) {
    return products.stream().collect(Collectors.toMap(Entity::getId, this::validate));
  }

  public Collection<String> validate(Product product) {
    Objects.requireNonNull(product, "Cannot validate an absent product");

    ProductValidationContext productValidationContext = buildProductValidationContext();

    return tryValidation(product, productValidationContext);
  }

  private ProductValidationContext buildProductValidationContext() {
    ProductValidationContext productValidationContext = new ProductValidationContext();
    productValidationContext.productFitnessConfiguration = productFitnessConfigurationRepository.find();
    return productValidationContext;
  }

  private Collection<String> tryValidation(Product product, ProductValidationContext productValidationContext) {
    try {
      return validator.validate(product, productValidationContext)
        .thenApply(violations -> violations.stream()
          .map(Violation::toString)
          .map(s -> translationService.translate(s, Locale.ENGLISH))
          .collect(Collectors.toList()))
        .get();
    } catch (InterruptedException | ExecutionException e) {
      throw new IllegalStateException(
        "Unexpected problem occurred while performing product fitness for sale validation.",
        e
      );
    }
  }
}
