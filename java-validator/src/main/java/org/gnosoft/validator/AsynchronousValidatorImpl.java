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
