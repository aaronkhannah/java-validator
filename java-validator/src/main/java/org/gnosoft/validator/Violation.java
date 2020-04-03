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

import java.util.Objects;

/**
 * Represents a constraint failure and its description.  A {@link Violation} is a condition which will allow further
 * validation to continue.  If a validation failure should stop further validation processing, a {@link Constraint}
 * should throw a {@link ValidationException} during it's execution.
 */
public class Violation {
  private final String text;

  Violation(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Violation violation = (Violation) o;
    return Objects.equals(text, violation.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  @Override
  public String toString() {
    return text;
  }


  public static Violation of(String text) {
    return new Violation(text);
  }
}
