/*
 * Copyright (c) 2020. Aaron Hannah
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gnosoft.validator;

import org.junit.jupiter.api.Test;

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