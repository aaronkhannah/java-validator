package org.gnosoft.validator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConstraintBuilderTest {
  @Test
  public void buildSimpleConstraint() {
    Constraint<String, Object> constraint = ConstraintBuilder
      .violation("a.constraint.violation.message")
      .forConstraint((String subject, Object context) -> subject != null && !subject.isBlank());

    assertThat(constraint)
      .isNotNull();

    assertThat(constraint.verify(null, null))
      .isNotNull()
      .isNotEmpty()
      .contains(Violation.of("a.constraint.violation.message"));

    assertThat(constraint.verify("not null and not blank", null))
      .isNotNull()
      .isEmpty();
  }
}