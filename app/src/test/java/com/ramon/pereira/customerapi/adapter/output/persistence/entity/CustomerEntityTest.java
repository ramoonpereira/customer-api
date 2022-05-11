package com.ramon.pereira.customerapi.adapter.output.persistence.entity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CustomerEntityTest {

  @Test
  void buildModelTest() {
    final var customer = CustomerEntity.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build();

    assertThat(customer)
        .isNotNull()
        .isExactlyInstanceOf(CustomerEntity.class);

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
