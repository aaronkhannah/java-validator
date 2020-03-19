package org.gnosoft.validator;

import java.util.concurrent.CompletableFuture;

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
