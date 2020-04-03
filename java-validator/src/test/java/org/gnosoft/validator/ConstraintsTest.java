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