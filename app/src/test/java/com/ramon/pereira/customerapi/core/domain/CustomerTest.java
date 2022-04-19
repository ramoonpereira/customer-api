package com.ramon.pereira.customerapi.core.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.ZonedDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CustomerTest {

  @Test
  void buildModelTest() {
    final var customer = Customer.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build();

    assertThat(customer)
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);

    assertThat(customer.getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customer.getUuid())
        .isNotNull()
        .isExactlyInstanceOf(UUID.class);

    assertThat(customer.getName())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customer.getCreatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);

    assertThat(customer.getUpdatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);
  }
}
