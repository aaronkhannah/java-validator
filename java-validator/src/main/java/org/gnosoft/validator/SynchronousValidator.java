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
 * A validator that attempts to synchronously apply all registered {@link org.gnosoft.validator.Constraint}s to the
 * supplied subject, using the supplied context.
 * @param <S> The type of the subject of the validation.
 * @param <C> The type of the context of the validation.
 */
public interface SynchronousValidator<S, C> {
  /**
   * Applies all registered {@link org.gnosoft.validator.Constraint}s to the supplied subject using the given context,
   * returning any validation constraint failures in a Messages object.  The order of constraint application is meant to
   * be the order of registration.
   * @param subject The target of the validation.
   * @param context The context (if any) used by {@link org.gnosoft.validator.Constraint}s during validation.
   * @return Messages object containing any validation constraint failures; empty indicates no failures.
   */
  Violations validate(S subject, C context);

  /**
   * Convenience validation invocation if registered {@link Constraint}s do not require a non-null context
   * in order to perform their processing.
   * @param subject The subject of the validation.
   * @return Messages object containing any validation constraint failures; empty indicates no failures.
   */
  default Violations validate(S subject) {
    return validate(subject, null);
  }
}
