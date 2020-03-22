package org.gnosoft.validator.examples.store;

import org.gnosoft.validator.examples.model.ProductFitnessConfiguration;

import java.math.BigDecimal;

public class ProductFitnessConfigurationRepository {
  public ProductFitnessConfiguration find() {
    ProductFitnessConfiguration configuration = new ProductFitnessConfiguration();
    configuration.setDescriptionLengthMax(256);
    configuration.setDescriptionLengthMin(16);

    configuration.setHeightMax(new BigDecimal("100"));
    configuration.setHeightMin(new BigDecimal("1"));

    configuration.setLengthMax(new BigDecimal("100"));
    configuration.setLengthMin(new BigDecimal("1"));

    configuration.setNameLengthMax(1);
    configuration.setNameLengthMin(127);

    configuration.setWidthMax(new BigDecimal("100"));
    configuration.setWidthMin(new BigDecimal("1"));

    return configuration;
  }
}
