package com.codedifferently.lesson17.bank.exceptions;

/** Exception thrown when attempting to use a money order that has already been used. */
public class MoneyOrderVoidedException extends RuntimeException {

  /**
   * Creates a new MoneyOrderVoidedException.
   *
   * @param message The exception message.
   */
  public MoneyOrderVoidedException(String message) {
    super(message);
  }

  /**
   * Creates a new MoneyOrderVoidedException.
   *
   * @param message The exception message.
   * @param cause The cause of the exception.
   */
  public MoneyOrderVoidedException(String message, Throwable cause) {
    super(message, cause);
  }
}
