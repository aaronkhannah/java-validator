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
 * An interface for defining a single unit of validation against the validation subject.
 * @param <S> The type of the validation subject.
 * @param <C> The type of the validation context.
 */
@FunctionalInterface
public interface Constraint<S, C> {
  /**
   * <p>The method providing a unit of validation against the supplied subject, using the given context.  I.e. this is
   * where the business validation logic is defined.</p>
   *
   * <p>A call to verify is typically expected to produce an instance of {@link org.gnosoft.validator.Violations}; on
   * success, the {@link org.gnosoft.validator.Violations} instance should contain no
   * {@link org.gnosoft.validator.Violation} objects, and on a failure, {@link org.gnosoft.validator.Violation} objects
   * detailing the failure should be available.</p>
   *
   * <p>There may be times when a particular verification needs to terminate the remainder of the verification process.
   * In this case, the verification should throw some type of exception (e.g. {@link java.lang.IllegalStateException}).
   * </p>
   *
   * @param subject The subject of which some aspect is being evaluated for validity.
   * @param context The context instance that may provide some useful information necessary for calculating
   *                subject validity.
   * @return An instance of {@link org.gnosoft.validator.Violations} containing the result of the validation.  The idea
   * is to return a Messages instance containing no Message objects when verification passes.  On failure, the
   * {@link org.gnosoft.validator.Violations} instance will contain {@link org.gnosoft.validator.Violation} objects
   * describing how the verification failed.
   */
  Violations verify(S subject, C context);
}
