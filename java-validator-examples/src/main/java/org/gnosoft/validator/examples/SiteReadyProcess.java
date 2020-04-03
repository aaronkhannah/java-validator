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

package org.gnosoft.validator.examples;

import org.gnosoft.validator.examples.model.Entity;
import org.gnosoft.validator.examples.model.Product;
import org.gnosoft.validator.examples.services.ProductFitForSaleValidationService;
import org.gnosoft.validator.examples.services.SiteReadyReportService;
import org.gnosoft.validator.examples.services.SiteReadyService;
import org.gnosoft.validator.examples.services.TranslationService;
import org.gnosoft.validator.examples.store.ProductFitnessConfigurationRepository;
import org.gnosoft.validator.examples.store.ProductRepository;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SiteReadyProcess implements Runnable {

  private final TranslationService translationService;

  private final ProductFitnessConfigurationRepository productFitnessConfigurationRepository;

  private final ProductFitForSaleValidationService productFitForSaleValidationService;

  private final ProductRepository productRepository;

  private final SiteReadyReportService siteReadyReportService;

  private final SiteReadyService siteReadyService;

  public SiteReadyProcess() {
    translationService = new TranslationService();
    productFitnessConfigurationRepository = new ProductFitnessConfigurationRepository();
    productFitForSaleValidationService = new ProductFitForSaleValidationService(
      translationService,
      productFitnessConfigurationRepository
    );
    productRepository = new ProductRepository();
    siteReadyReportService = new SiteReadyReportService();
    siteReadyService = new SiteReadyService();
  }

  SiteReadyProcess(
    TranslationService translationService,
    ProductFitnessConfigurationRepository productFitnessConfigurationRepository,
    ProductFitForSaleValidationService productFitForSaleValidationService,
    ProductRepository productRepository,
    SiteReadyReportService siteReadyReportService,
    SiteReadyService siteReadyService
  ) {
    this.translationService = translationService;
    this.productFitnessConfigurationRepository = productFitnessConfigurationRepository;
    this.productFitForSaleValidationService = productFitForSaleValidationService;
    this.productRepository = productRepository;
    this.siteReadyReportService = siteReadyReportService;
    this.siteReadyService = siteReadyService;
  }

  @Override
  public void run() {
    Collection<Product> products = productRepository.loadProductsReadyToBeMovedOnSite();
    Map<Long, Product> productsMappedById = products.stream().collect(Collectors.toMap(Entity::getId, Function.identity()));

    Map<Long, Collection<String>> validationResults = productFitForSaleValidationService.validate(products);

    siteReadyReportService.generateReport(validationResults);

    Collection<Product> siteReadyProducts = validationResults.keySet().stream()
      .filter(key -> validationResults.get(key).isEmpty())
      .map(productsMappedById::get)
      .collect(Collectors.toList());

    siteReadyService.putProductsOnSite(siteReadyProducts);
  }

  public static void main(String[] args) {
    new SiteReadyProcess().run();
  }
}
