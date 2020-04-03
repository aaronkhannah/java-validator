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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Specialized {@link Iterable} of {@link Violation} objects.
 */
public class Violations implements Iterable<Violation> {
  final Collection<Violation> delegate;

  Violations(Collection<Violation> violations) {
    this.delegate = new LinkedList<>(Objects.requireNonNull(violations));
  }

  /**
   * Provides a {@link Stream} of this {@link Violations} instance.
   * @return A {@link Stream} of the {@link Violation}
   */
  public Stream<Violation> stream() {
    return delegate.stream();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<Violation> iterator() {
    return delegate.iterator();
  }

  /**
   * Static factory method for creating {@link Violations} containing a {@link Violation} for each {@link String}
   * provided.
   * @param violations An array of {@link String}s, each element representing a separate {@link Violation}
   * @return An instance of {@link Violations} containing {@link Violation} elements derived from each given
   * {@link String}
   */
  public static Violations of(String... violations) {
    return new Violations(Arrays.stream(violations).map(Violation::new).collect(Collectors.toList()));
  }

  /**
   * Static factory method for creating an empty {@link Violations}, which should be considered the result of
   * a {@link Constraint} successfully executing (i.e. not producing any {@link Violation}s).
   * @return An instance of {@link Violations} containing no {@link Violation}.
   */
  public static Violations none() {
    return new Violations(Collections.emptyList());
  }
}
