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
