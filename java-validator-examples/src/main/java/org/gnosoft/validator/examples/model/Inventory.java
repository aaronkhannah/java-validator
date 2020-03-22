package org.gnosoft.validator.examples.model;

import java.util.Objects;

public class Inventory extends Entity {
  private Product product;
  private Long available;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Long getAvailable() {
    return available;
  }

  public void setAvailable(Long available) {
    this.available = available;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Inventory inventory = (Inventory) o;
    return Objects.equals(product, inventory.product) &&
      Objects.equals(available, inventory.available);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), product, available);
  }
}
