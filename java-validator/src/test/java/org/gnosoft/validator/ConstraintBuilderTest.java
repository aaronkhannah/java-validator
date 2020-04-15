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