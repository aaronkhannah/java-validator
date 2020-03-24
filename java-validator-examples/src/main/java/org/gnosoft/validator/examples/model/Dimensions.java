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


  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;

    private Builder() {
    }

    public static Builder aDimensions() {
      return new Builder();
    }

    public Builder length(BigDecimal length) {
      this.length = length;
      return this;
    }

    public Builder width(BigDecimal width) {
      this.width = width;
      return this;
    }

    public Builder height(BigDecimal height) {
      this.height = height;
      return this;
    }

    public Dimensions build() {
      Dimensions dimensions = new Dimensions();
      dimensions.setLength(length);
      dimensions.setWidth(width);
      dimensions.setHeight(height);
      return dimensions;
    }
  }
}
