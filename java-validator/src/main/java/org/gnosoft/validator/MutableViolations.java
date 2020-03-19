package org.gnosoft.validator;

class MutableViolations extends Violations {
  MutableViolations(Violations violations) {
    super(violations.delegate);
  }

  MutableViolations add(Violations violations) {
    this.delegate.addAll(violations.delegate);
    return this;
  }

  public static MutableViolations none() {
    return new MutableViolations(Violations.none());
  }
}
