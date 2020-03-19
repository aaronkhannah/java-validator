package org.gnosoft.validator;

/**
 * A {@link ValidationException} represents an exceptional condition that occurred while validation was being processed
 * that could not be expressed as a {@link Violation} and requires further validation cease.  This exception is intended
 * to be thrown from within instances of {@link Constraint} in lieu of the {@link Constraint} returning normally with
 * a {@link Violations}.
 */
public class ValidationException extends RuntimeException {
  /**
   * Constructs a {@link ValidationException} using the supplied String as its message.
   * @param message Description of the violation that occurred.
   */
  public ValidationException(String message) {
    super(message);
  }

  /**
   * Constructs a {@link ValidationException} using the supplied String as its message and
   * supplied Throwable as its cause.
   * @param message Description of the violation that occurred.
   * @param cause The underlying cause of the violation.
   */
  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
