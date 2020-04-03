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

public class ProductFitnessConfiguration {
  private BigDecimal lengthMax;
  private BigDecimal lengthMin;

  private BigDecimal widthMax;
  private BigDecimal widthMin;

  private BigDecimal heightMax;
  private BigDecimal heightMin;

  private int descriptionLengthMax;
  private int descriptionLengthMin;

  private int nameLengthMax;
  private int nameLengthMin;

  public BigDecimal getLengthMax() {
    return lengthMax;
  }

  public void setLengthMax(BigDecimal lengthMax) {
    this.lengthMax = lengthMax;
  }

  public BigDecimal getLengthMin() {
    return lengthMin;
  }

  public void setLengthMin(BigDecimal lengthMin) {
    this.lengthMin = lengthMin;
  }

  public BigDecimal getWidthMax() {
    return widthMax;
  }

  public void setWidthMax(BigDecimal widthMax) {
    this.widthMax = widthMax;
  }

  public BigDecimal getWidthMin() {
    return widthMin;
  }

  public void setWidthMin(BigDecimal widthMin) {
    this.widthMin = widthMin;
  }

  public BigDecimal getHeightMax() {
    return heightMax;
  }

  public void setHeightMax(BigDecimal heightMax) {
    this.heightMax = heightMax;
  }

  public BigDecimal getHeightMin() {
    return heightMin;
  }

  public void setHeightMin(BigDecimal heightMin) {
    this.heightMin = heightMin;
  }

  public long getDescriptionLengthMax() {
    return descriptionLengthMax;
  }

  public void setDescriptionLengthMax(int descriptionLengthMax) {
    this.descriptionLengthMax = descriptionLengthMax;
  }

  public int getDescriptionLengthMin() {
    return descriptionLengthMin;
  }

  public void setDescriptionLengthMin(int descriptionLengthMin) {
    this.descriptionLengthMin = descriptionLengthMin;
  }

  public int getNameLengthMax() {
    return nameLengthMax;
  }

  public void setNameLengthMax(int nameLengthMax) {
    this.nameLengthMax = nameLengthMax;
  }

  public int getNameLengthMin() {
    return nameLengthMin;
  }

  public void setNameLengthMin(int nameLengthMin) {
    this.nameLengthMin = nameLengthMin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductFitnessConfiguration that = (ProductFitnessConfiguration) o;
    return descriptionLengthMax == that.descriptionLengthMax &&
      descriptionLengthMin == that.descriptionLengthMin &&
      nameLengthMax == that.nameLengthMax &&
      nameLengthMin == that.nameLengthMin &&
      Objects.equals(lengthMax, that.lengthMax) &&
      Objects.equals(lengthMin, that.lengthMin) &&
      Objects.equals(widthMax, that.widthMax) &&
      Objects.equals(widthMin, that.widthMin) &&
      Objects.equals(heightMax, that.heightMax) &&
      Objects.equals(heightMin, that.heightMin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lengthMax, lengthMin, widthMax, widthMin, heightMax, heightMin, descriptionLengthMax, descriptionLengthMin, nameLengthMax, nameLengthMin);
  }
}
