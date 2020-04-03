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
