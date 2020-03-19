package org.gnosoft.validator;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gnosoft.validator.ConstraintBuilder.violation;
import static org.gnosoft.validator.Constraints.requireNotNull;

public class AsynchronousValidatorImplTest {
  @Test
  public void validate() throws ExecutionException, InterruptedException {
    AsynchronousValidator<String, String> validator = ValidatorBuilder.<String, String>builder()
      .add(requireNotNull("subject.cannot.be.null"))
      .add(violation("subject.cannot.be.blank").forConstraint((subject, context) -> !subject.isBlank()))
      .buildAsynchronous();

    CompletableFuture<Violations> future = validator.validate("non null subject");

    Violations result = future.get();

    assertThat(result)
      .isNotNull()
      .isEmpty();
  }
}