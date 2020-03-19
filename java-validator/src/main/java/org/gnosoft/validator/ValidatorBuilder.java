package org.gnosoft.validator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides a user with facilities for building instances of either {@link SynchronousValidator} or
 * {@link AsynchronousValidator}.
 * @param <S> The type of the subject of the validation.
 * @param <C> The type of the context for performing validation.
 */
public class ValidatorBuilder<S, C> {
  final List<Constraint<S, C>> criteria = new LinkedList<Constraint<S, C>>();

  /**
   * Adds or registers a {@link Constraint} that the validator built from this builder will apply to instances of
   * the subject (using the context, if one is available).
   * @param constraint The {@link Constraint} to be registered to the validator ultimately being built.
   * @param constraints Additional {@link Constraint}s.
   * @return The {@link ValidatorBuilder}.
   */
  @SafeVarargs
  public final ValidatorBuilder<S, C> add(Constraint<S, C> constraint, Constraint<S, C>... constraints) {
    criteria.add(constraint);
    criteria.addAll(Arrays.asList(constraints));
    return this;
  }

  /**
   * Produces an instance of {@link SynchronousValidator} with all of this builder's registered constraints added to it.
   * @return A new instance of {@link SynchronousValidator}
   */
  public SynchronousValidator<S, C> buildSynchronous() {
    return new SynchronousValidatorImpl<>(this);
  }

  /**
   * Produces an instance of {@link AsynchronousValidator} with all of this builder's registered constraints added to it.
   * @return A new instance of {@link AsynchronousValidator}
   */
  public AsynchronousValidator<S, C> buildAsynchronous() {
    return new AsynchronousValidatorImpl<>(this);
  }


  /**
   * A static factory method for producing an instance of {@link ValidatorBuilder}.
   * @param <S> The type of the subject of validation.
   * @param <C> The type of the context of validation.
   * @return A new instance of {@link ValidatorBuilder}
   */
  public static <S, C> ValidatorBuilder<S, C> builder() {
    return new ValidatorBuilder<>();
  }
}
