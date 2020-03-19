package org.gnosoft.validator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorBuilderTest {
  @Test
  public void buildSynchronousValidator() {
    SynchronousValidator<String, Object> validator = ValidatorBuilder.<String, Object>builder()
      .add(Constraints.requireNotNull("subject.cannot.be.null"))
      .buildSynchronous();

    assertThat(validator).isNotNull();
  }

  @Test
  public void buildAsynchronousValidator() {
    AsynchronousValidator<String, Object> validator = ValidatorBuilder.<String, Object>builder()
      .add(Constraints.requireNotNull("subject.cannot.be.null"))
      .buildAsynchronous();

    assertThat(validator).isNotNull();
  }
}