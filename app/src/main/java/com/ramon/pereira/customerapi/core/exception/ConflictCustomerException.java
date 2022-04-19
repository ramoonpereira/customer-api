package com.ramon.pereira.customerapi.core.exception;

public class ConflictCustomerException extends Exception {
  private static final String DEFAULT_MESSAGE =
      "Conflict customer already exist";

  public ConflictCustomerException() {
    super(DEFAULT_MESSAGE);
  }
}
