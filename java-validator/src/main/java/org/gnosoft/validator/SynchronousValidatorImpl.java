package org.gnosoft.validator;

class SynchronousValidatorImpl<S, C> extends AbstractValidator<S, C> implements SynchronousValidator<S, C> {

  SynchronousValidatorImpl(ValidatorBuilder<S, C> builder) {
    super(builder);
  }

  @Override
  public Violations validate(S subject, C context) {
    return getConstraints().stream()
      .map(c -> c.verify(subject, context))
      .map(MutableViolations::new)
      .reduce(MutableViolations::add)
      .orElseGet(MutableViolations::none);
  }
}
