package com.ramon.pereira.customerapi.adapter.input.api.dto.response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CustomerResponseDtoTest {

  @Test
  void buildCustomerResponseDtoTest() {
    final var customer = CustomerResponseDto.builder()
        .name("name")
        .cpf("****5678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build();

    assertThat(customer)
        .isNotNull()
        .isExactlyInstanceOf(CustomerResponseDto.class);

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
