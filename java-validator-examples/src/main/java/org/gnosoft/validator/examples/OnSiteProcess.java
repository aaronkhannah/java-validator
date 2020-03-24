package org.gnosoft.validator.examples;

import org.gnosoft.validator.examples.model.Product;
import org.gnosoft.validator.examples.services.OnSiteReportService;
import org.gnosoft.validator.examples.services.ProductFitForSaleValidationService;
import org.gnosoft.validator.examples.services.TranslationService;
import org.gnosoft.validator.examples.store.ProductFitnessConfigurationRepository;
import org.gnosoft.validator.examples.store.ProductRepository;

import java.util.Collection;
import java.util.Map;

public class OnSiteProcess implements Runnable {

  private final TranslationService translationService;

  private final ProductFitnessConfigurationRepository productFitnessConfigurationRepository;

  private final ProductFitForSaleValidationService productFitForSaleValidationService;

  private final ProductRepository productRepository;

  private final OnSiteReportService onSiteReportService;

  public OnSiteProcess() {
    translationService = new TranslationService();
    productFitnessConfigurationRepository = new ProductFitnessConfigurationRepository();
    productFitForSaleValidationService = new ProductFitForSaleValidationService(
      translationService,
      productFitnessConfigurationRepository
    );
    productRepository = new ProductRepository();
    onSiteReportService = new OnSiteReportService();
  }

  @Override
  public void run() {
    Collection<Product> products = productRepository.loadProductsReadyToBeMovedOnSite();

    Map<Long, Collection<String>> validationResults = productFitForSaleValidationService.validate(products);

    onSiteReportService.generateReport(validationResults);
  }

  public static void main(String[] args) {
    new OnSiteProcess().run();
  }
}
