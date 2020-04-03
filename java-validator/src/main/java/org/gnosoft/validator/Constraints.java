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
 * Utilities for constructing and working with {@link Constraint}s.
 */
public final class Constraints {

  /*
   * Thou shalt not construct this utility class.
   */
  private Constraints() {
  }

  /**
   * Creates a {@link Constraint} that will throw a {@link ValidationException} if the subject is null.
   * @param message The message that will be included in the {@link ValidationException} when thrown.
   * @param <S> The type of the subject.
   * @param <C> The type of the context.
   * @return An empty {@link Violations} on success.
   */
  public static <S, C> Constraint<S, C> requireNotNull(String message) {
    return (s, c) -> {
      if (Objects.isNull(s)) {
        throw new ValidationException(message);
      }

      return Violations.none();
    };
  }
}
