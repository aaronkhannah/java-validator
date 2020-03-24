package org.gnosoft.validator.examples.store;

import org.gnosoft.validator.examples.model.Dimensions;
import org.gnosoft.validator.examples.model.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

public class ProductRepository {
  public Collection<Product> loadProductsReadyToBeMovedOnSite() {
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
