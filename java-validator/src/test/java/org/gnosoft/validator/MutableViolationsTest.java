package org.gnosoft.validator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MutableViolationsTest {
  @Test
  public void add() {
    String message = "a.violation.message";

    MutableViolations mutableViolations = MutableViolations.none();

    assertThat(mutableViolations).isNotNull().isEmpty();

    mutableViolations.add(Violations.of(message));

    assertThat(mutableViolations)
      .isNotEmpty()
      .contains(Violation.of(message));
  }
}