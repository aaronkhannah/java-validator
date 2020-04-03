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

class SynchronousValidatorImpl<S, C> extends AbstractValidator<S, C> implements SynchronousValidator<S, C> {

  SynchronousValidatorImpl(ValidatorBuilder<S, C> builder) {
    super(builder);
  }

  @Override
  public Violations validate(S subject, C context) {
    return getConstraints().stream()
      .map(c -> c.verify(subject, context))
      .map(MutableViolations::new)
      .reduce(MutableViolations::add)
      .orElseGet(MutableViolations::none);
  }
}
