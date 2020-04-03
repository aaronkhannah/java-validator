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

/**
 * A {@link ValidationException} represents an exceptional condition that occurred while validation was being processed
 * that could not be expressed as a {@link Violation} and requires further validation cease.  This exception is intended
 * to be thrown from within instances of {@link Constraint} in lieu of the {@link Constraint} returning normally with
 * a {@link Violations}.
 */
public class ValidationException extends RuntimeException {
  /**
   * Constructs a {@link ValidationException} using the supplied String as its message.
   * @param message Description of the violation that occurred.
   */
  public ValidationException(String message) {
    super(message);
  }

  /**
   * Constructs a {@link ValidationException} using the supplied String as its message and
   * supplied Throwable as its cause.
   * @param message Description of the violation that occurred.
   * @param cause The underlying cause of the violation.
   */
  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
