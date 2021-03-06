/*
 * Copyright (c) 2020. Aaron Hannah
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
      .add(checkForLengthLimit())
      .add(checkForWidthLimit())
      .add(checkForHeightLimit())
      .add(checkForValidDescription())
      .add(checkForValidName())
      .add(checkForListLessThanMsrp())
      .buildAsynchronous();
  }

  private Constraint<Product, ProductValidationContext> checkForLengthLimit() {
    return ConstraintBuilder.violation("label.product.exceeds.length.limit")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getLength() != null
        && product.getDimensions().getLength().compareTo(context.productFitnessConfiguration.getLengthMax()) <= 0
        && product.getDimensions().getLength().compareTo(context.productFitnessConfiguration.getLengthMin()) >= 0);
  }

  private Constraint<Product, ProductValidationContext> checkForWidthLimit() {
    return ConstraintBuilder.violation("label.product.exceeds.width.limit")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getWidth() != null
        && product.getDimensions().getWidth().compareTo(context.productFitnessConfiguration.getWidthMax()) <= 0
        && product.getDimensions().getWidth().compareTo(context.productFitnessConfiguration.getWidthMin()) >= 0);
  }

  private Constraint<Product, ProductValidationContext> checkForHeightLimit() {
    return ConstraintBuilder.violation("label.product.exceeds.height.limit")
      .forConstraint((product, context) ->
        product.getDimensions() != null
        && product.getDimensions().getHeight() != null
        && product.getDimensions().getHeight().compareTo(context.productFitnessConfiguration.getHeightMax()) <= 0
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
