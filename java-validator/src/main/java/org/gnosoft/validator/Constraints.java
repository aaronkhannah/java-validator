package org.gnosoft.validator;

import java.util.Objects;

/**
 * Utilities for constructing and working with {@link Constraint}s.
 */
public final class Constraints {

  /*
   * Thou shalt not construct this utility class.
   */
  private Constraints() {
  }

  /**
   * Creates a {@link Constraint} that will throw a {@link ValidationException} if the subject is null.
   * @param message The message that will be included in the {@link ValidationException} when thrown.
   * @param <S> The type of the subject.
   * @param <C> The type of the context.
   * @return An empty {@link Violations} on success.
   */
  public static <S, C> Constraint<S, C> requireNotNull(String message) {
    return (s, c) -> {
      if (Objects.isNull(s)) {
        throw new ValidationException(message);
      }

      return Violations.none();
    };
  }
}
