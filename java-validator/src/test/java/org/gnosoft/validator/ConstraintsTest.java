package org.gnosoft.validator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public class ConstraintsTest {
  @Test(expected = ValidationException.class)
  public void requireNotNull_NullSubject() {
    String message = "a.constraint.violation.message";

    Constraint<String, Object> constraint = Constraints.requireNotNull(message);

    assertThat(constraint).isNotNull();

    constraint.verify(null, null);

    shouldHaveThrown(ValidationException.class);
  }

  @Test
  public void requireNotNull_NotNullSubject() {
    String message = "a.constraint.violation.message";

    Constraint<String, Object> constraint = Constraints.requireNotNull(message);

    assertThat(constraint).isNotNull();

    assertThat(constraint.verify("not null", null))
      .isNotNull()
      .isEmpty();
  }
}