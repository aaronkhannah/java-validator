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

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ViolationsTest {
  @Test
  public void buildAndIterate() {
    Collection<Violation> collection = Arrays.asList(
      Violation.of("a.violation.message"),
      Violation.of("another.violation.message")
    );

    Violations violations = Violations.of(collection.stream()
      .map(Violation::toString)
      .toArray(String[]::new));

    assertThat(violations)
      .isNotNull()
      .isNotEmpty();

    for (Violation violation : violations) {
      assertThat(collection).contains(violation);
    }
  }

  @Test
  public void buildAndStream() {
    Collection<Violation> collection = Arrays.asList(
      Violation.of("a.violation.message"),
      Violation.of("another.violation.message")
    );

    Violations violations = Violations.of(collection.stream()
      .map(Violation::toString)
      .toArray(String[]::new));

    assertThat(violations)
      .isNotNull()
      .isNotEmpty();

    assertThat(violations.stream().map(Violation::toString).collect(Collectors.toList()))
      .containsAll(collection.stream().map(Violation::toString).collect(Collectors.toList()));
  }
}
