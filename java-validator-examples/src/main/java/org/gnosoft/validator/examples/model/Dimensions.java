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
