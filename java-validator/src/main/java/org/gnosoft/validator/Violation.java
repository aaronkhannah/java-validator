package org.gnosoft.validator;

import java.util.Objects;

/**
 * Represents a constraint failure and its description.  A {@link Violation} is a condition which will allow further
 * validation to continue.  If a validation failure should stop further validation processing, a {@link Constraint}
 * should throw a {@link ValidationException} during it's execution.
 */
public class Violation {
  private final String text;

  Violation(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Violation violation = (Violation) o;
    return Objects.equals(text, violation.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  @Override
  public String toString() {
    return text;
  }


  public static Violation of(String text) {
    return new Violation(text);
  }
}
