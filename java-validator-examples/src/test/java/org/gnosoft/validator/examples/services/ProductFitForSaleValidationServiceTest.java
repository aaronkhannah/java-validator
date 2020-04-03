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

import org.gnosoft.validator.examples.model.Dimensions;
import org.gnosoft.validator.examples.model.Product;
import org.gnosoft.validator.examples.store.ProductFitnessConfigurationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductFitForSaleValidationServiceTest {
  @Mock
  private TranslationService translationService;

  @Mock
  private ProductFitnessConfigurationRepository productFitnessConfigurationRepository;


  private ProductFitForSaleValidationService unitUnderTest;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    unitUnderTest = new ProductFitForSaleValidationService(translationService, productFitnessConfigurationRepository);
  }

  @Test
  public void test() {
    when(translationService.translate(anyString(), any(Locale.class)))
      .thenCallRealMethod();

    when(productFitnessConfigurationRepository.find())
      .thenCallRealMethod();

    Collection<Product> products = products();

    Map<Long, Collection<String>> results = unitUnderTest.validate(products);

    assertThat(results).isNotNull().isNotEmpty();

    assertThat(results.get(1L)).isNotNull().isEmpty();

    assertThat(results.get(2L)).isNotNull().isNotEmpty().contains("label.product.exceeds.length.limit");

    assertThat(results.get(3L)).isNotNull().isNotEmpty().contains("label.product.list.greater.than.msrp");
  }

  private Collection<Product> products() {
    Product.Builder builder = Product.builder()
      .createdBy("some_buyer")
      .createdDate(Instant.parse("2020-07-04T00:00:00.00Z"))
      .updatedBy("some_buyer")
      .updatedDate(Instant.parse("2020-07-04T00:00:00.00Z"));

    return List.of(
      builder
        .id(1L)
        .name("Christopher Squire Recliner")
        .description("A really comfortable and stylish recliner.")
        .dimensions(Dimensions.builder()
          .length(new BigDecimal("50.0"))
          .width(new BigDecimal("50.0"))
          .height(new BigDecimal("50.0"))
          .build())
        .manufacturerSuggestedRetailPrice(new BigDecimal("600.00"))
        .listPrice(new BigDecimal("525.00"))
        .build(),
      builder
        .id(2L)
        .name("SoftCo Bean Bag")
        .description("A really comfortable bean bag.")
        .dimensions(Dimensions.builder()
          .length(null) // OOPS!  Not valid!
          .width(new BigDecimal("40.0"))
          .height(new BigDecimal("40.0"))
          .build())
        .manufacturerSuggestedRetailPrice(new BigDecimal("300.00"))
        .listPrice(new BigDecimal("225.00"))
        .build(),
      builder
        .id(3L)
        .name("Stumpy's Foot Stool")
        .description("A squat, but cool, ottoman.")
        .dimensions(Dimensions.builder()
          .length(new BigDecimal("10.0"))
          .width(new BigDecimal("10.0"))
          .height(new BigDecimal("5.0"))
          .build())
        .manufacturerSuggestedRetailPrice(new BigDecimal("50.00"))
        .listPrice(new BigDecimal("55.00")) // OOPS! Not valid!
        .build()
    );
  }
}