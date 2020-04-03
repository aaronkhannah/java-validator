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

import java.util.concurrent.CompletableFuture;

/**
 * A validator that attempts to asynchronously apply its registered {@link org.gnosoft.validator.Constraint}s to the
 * given subject, using the supplied context.
 * @param <S> The type of the subject to be validated.
 * @param <C> The type of the context (if it matters) to be read while performing validation.
 */
public interface AsynchronousValidator<S, C> {
  /**
   * Attempts to apply all registered {@link org.gnosoft.validator.Constraint}s asynchronously to the supplied subject,
   * using the context object (if applicable) and returning a {@link java.util.concurrent.CompletableFuture} that
   * represents this collection of work.
   *
   * NOTE:  Due to the nature of asynchronous processing, the order in which {@link org.gnosoft.validator.Constraint}s
   * are applied is not guaranteed.  If such behavior is required, please consider using the
   * {@link org.gnosoft.validator.SynchronousValidator} instead.
   *
   * @param subject The subject of the validation, to which registered constraints will be applied.
   * @param context The context (if any), used to assist registered constraints during validation.
   * @return A {@link java.util.concurrent.CompletableFuture} of {@link Violations}.  The idea is
   * that an empty {@link Violations} means validation completed without finding any constraint
   * violations.
   */
  CompletableFuture<Violations> validate(S subject, C context);

  /**
   * Convenience validation invocation if registered {@link Constraint}s do not require a non-null context
   * in order to perform their processing.
   * @param subject The subject of the validation.
   * @return A {@link java.util.concurrent.CompletableFuture} of {@link Violations}.  The idea is
   * that an empty {@link Violations} means validation completed without finding any constraint
   * violations.
   */
  default CompletableFuture<Violations> validate(S subject) {
    return validate(subject, null);
  }
}
