package org.gnosoft.validator.examples.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product extends Entity {
  private String name;
  private String description;
  private Dimensions dimensions;
  private BigDecimal manufacturerSuggestedRetailPrice;
  private BigDecimal listPrice;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Dimensions getDimensions() {
    return dimensions;
  }

  public void setDimensions(Dimensions dimensions) {
    this.dimensions = dimensions;
  }

  public BigDecimal getManufacturerSuggestedRetailPrice() {
    return manufacturerSuggestedRetailPrice;
  }

  public void setManufacturerSuggestedRetailPrice(BigDecimal manufacturerSuggestedRetailPrice) {
    this.manufacturerSuggestedRetailPrice = manufacturerSuggestedRetailPrice;
  }

  public BigDecimal getListPrice() {
    return listPrice;
  }

  public void setListPrice(BigDecimal listPrice) {
    this.listPrice = listPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Product product = (Product) o;
    return Objects.equals(name, product.name) &&
      Objects.equals(description, product.description) &&
      Objects.equals(dimensions, product.dimensions) &&
      Objects.equals(manufacturerSuggestedRetailPrice, product.manufacturerSuggestedRetailPrice) &&
      Objects.equals(listPrice, product.listPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description, dimensions, manufacturerSuggestedRetailPrice, listPrice);
  }
}
