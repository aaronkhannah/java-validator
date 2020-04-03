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

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;

class AsynchronousValidatorImpl<S, C> extends AbstractValidator<S, C> implements AsynchronousValidator<S, C> {

  AsynchronousValidatorImpl(ValidatorBuilder<S, C> builder) {
    super(builder);
  }

  @Override
  public CompletableFuture<Violations> validate(S subject, C context) {
    Collection<CompletableFuture<Violations>> futures = getConstraints().stream()
      .map(criterion -> supplyAsync(() -> criterion.verify(subject, context)))
      .collect(Collectors.toList());

    return allOf(futures.toArray(new CompletableFuture[0]))
      .thenApply(v -> futures.stream()
        .map(CompletableFuture::join)
        .map(MutableViolations::new)
        .reduce(MutableViolations::add)
        .orElseGet(MutableViolations::none));
  }
}
