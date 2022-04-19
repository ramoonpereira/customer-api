package com.ramon.pereira.customerapi.core.exception;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class ConflictCustomerExceptionTest {

  @Test
  void conflictCustomerExceptionTest() {
    final var exception = new ConflictCustomerException();

    assertThat(exception)
        .isNotNull()
        .isExactlyInstanceOf(ConflictCustomerException.class)
        .hasMessageContaining("Conflict customer already exist");
  }
}
