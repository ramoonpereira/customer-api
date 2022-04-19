package com.ramon.pereira.customerapi.adapter.input.dto.request;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import com.ramon.pereira.customerapi.adapter.input.api.dto.request.CreateCustomerRequestDto;
import org.junit.jupiter.api.Test;

class CreateCustomerRequestDtoTest {

  @Test
  void buildCreateCustomerRequestDtoTest() {
    final var customer = CreateCustomerRequestDto.builder()
        .name("name")
        .cpf("12345678910")
        .build();

    assertThat(customer)
        .isNotNull()
        .isExactlyInstanceOf(CreateCustomerRequestDto.class);

    assertThat(customer.getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customer.getName())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

  }
}
