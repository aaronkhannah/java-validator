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
 * A utility class for facilitating a two step process that first defines the text of a {@link Violation} and then
 * the {@link Constraint} that will produce it if it (the {@link Constraint}) fails when applied to the subject.
 */
public class ConstraintBuilder {
  private final String textOnFailure;

  ConstraintBuilder(String textOnFailure) {
    this.textOnFailure = textOnFailure;
  }

  /**
   * Produces a {@link Constraint} that will apply the provided {@link ToBooleanBiFunction} to its subject.  If the
   * application produces a false, a {@link Violations} with a {@link Violation} containing the text originally
   * provided to this {@link ConstraintBuilder} will be returned.
   * @param constraintDefinition The heart of the {@link Constraint} being built.
   * @param <S> Type of the validation subject.
   * @param <C> Type of the validation context.
   * @return A {@link Constraint} that will applied the supplied {@link ToBooleanBiFunction} to its subject.  When
   * that application results in a false, the {@link Constraint} will produce a {@link Violations} containing a
   * single {@link Violation} with the text originally supplied to this {@link ConstraintBuilder} during
   * instantiation.
   */
  public <S, C> Constraint<S, C> forConstraint(ToBooleanBiFunction<S, C> constraintDefinition) {
    return (s, c) -> constraintDefinition.apply(s, c) ? Violations.none() : Violations.of(textOnFailure);
  }


  /**
   * <p>
   * Creates an instance of {@link ConstraintBuilder} which will use the supplied text to build a {@link Violation}
   * if the {@link ToBooleanBiFunction} given in the next step of the builder's construction returns false.
   * </p>
   *
   * <pre>Example: {@code
   *
   * Constraint<SubjectType, ContextType> constraint =  Constraints
   *   .violation("some violation text")
   *   .forConstraint((subject, context) -> subject.value == A_VALID_VALUE);
   *
   * }</pre>
   *
   * @param textOnFailure The text of the {@link Violation}.
   * @return A {@link ConstraintBuilder} that will use the provided text for the {@link Violation} the
   * {@link Constraint} ultimately built will produce when it fails when applied to its subject.
   */
  public static ConstraintBuilder violation(String textOnFailure) {
    return new ConstraintBuilder(textOnFailure);
  }
}
