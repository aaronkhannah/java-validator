package org.gnosoft.validator.examples.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Dimensions {
  private BigDecimal length;
  private BigDecimal width;
  private BigDecimal height;

  public BigDecimal getLength() {
    return length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  public BigDecimal getWidth() {
    return width;
  }

  public void setWidth(BigDecimal width) {
    this.width = width;
  }

  public BigDecimal getHeight() {
    return height;
  }

  public void setHeight(BigDecimal height) {
    this.height = height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Dimensions that = (Dimensions) o;
    return Objects.equals(length, that.length) &&
      Objects.equals(width, that.width) &&
      Objects.equals(height, that.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(length, width, height);
  }
}
