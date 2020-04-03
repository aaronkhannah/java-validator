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
import java.time.Instant;
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


  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Long id;
    private String name;
    private String createdBy;
    private String description;
    private Instant createdDate;
    private Dimensions dimensions;
    private String updatedBy;
    private BigDecimal manufacturerSuggestedRetailPrice;
    private Instant updatedDate;
    private BigDecimal listPrice;

    private Builder() {
    }

    public static Builder aProduct() {
      return new Builder();
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder createdBy(String createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder createdDate(Instant createdDate) {
      this.createdDate = createdDate;
      return this;
    }

    public Builder dimensions(Dimensions dimensions) {
      this.dimensions = dimensions;
      return this;
    }

    public Builder updatedBy(String updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    public Builder manufacturerSuggestedRetailPrice(BigDecimal manufacturerSuggestedRetailPrice) {
      this.manufacturerSuggestedRetailPrice = manufacturerSuggestedRetailPrice;
      return this;
    }

    public Builder updatedDate(Instant updatedDate) {
      this.updatedDate = updatedDate;
      return this;
    }

    public Builder listPrice(BigDecimal listPrice) {
      this.listPrice = listPrice;
      return this;
    }

    public Product build() {
      Product product = new Product();
      product.setId(id);
      product.setName(name);
      product.setCreatedBy(createdBy);
      product.setDescription(description);
      product.setCreatedDate(createdDate);
      product.setDimensions(dimensions);
      product.setUpdatedBy(updatedBy);
      product.setManufacturerSuggestedRetailPrice(manufacturerSuggestedRetailPrice);
      product.setUpdatedDate(updatedDate);
      product.setListPrice(listPrice);
      return product;
    }
  }
}
