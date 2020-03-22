package org.gnosoft.validator.examples.model;

import java.time.Instant;
import java.util.Objects;

public class Entity {
  private Long id;

  private String createdBy;
  private Instant createdDate;

  private String updatedBy;
  private Instant updatedDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Instant getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Instant updatedDate) {
    this.updatedDate = updatedDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Entity entity = (Entity) o;
    return Objects.equals(id, entity.id) &&
      Objects.equals(createdBy, entity.createdBy) &&
      Objects.equals(createdDate, entity.createdDate) &&
      Objects.equals(updatedBy, entity.updatedBy) &&
      Objects.equals(updatedDate, entity.updatedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdBy, createdDate, updatedBy, updatedDate);
  }
}
