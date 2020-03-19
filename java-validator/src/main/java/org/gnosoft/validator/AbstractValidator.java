package org.gnosoft.validator;

import java.util.Collection;
import java.util.LinkedList;

/*
 * Provides common state to child validators.
 */
abstract class AbstractValidator<S, C> {
  private final Collection<Constraint<S, C>> constraints;

  AbstractValidator(ValidatorBuilder<S, C> builder) {
    this.constraints = new LinkedList<>(builder.criteria);
  }

  protected Collection<Constraint<S, C>> getConstraints() {
    return constraints;
  }
}
