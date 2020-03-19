package org.gnosoft.validator;

/**
 * A {@link FunctionalInterface} that allows a condition to be defined which a wrapping {@link Constraint} will apply
 * to a subject.  If the result of this application is true, the {@link Constraint} will produce an empty
 * {@link Violations}.  Otherwise, it will contain a single {@link Violation}.  This is meant to be used in
 * conjunction with the {@link ConstraintBuilder} to (hopefully) rapidly produce {@link Constraint}s.
 * @param <S> type of the validation subject.
 * @param <C> type of the validation context.
 */
@FunctionalInterface
public interface ToBooleanBiFunction<S, C> {
  boolean apply(S s, C c);
}
