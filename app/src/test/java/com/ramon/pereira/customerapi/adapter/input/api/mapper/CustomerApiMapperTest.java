package com.ramon.pereira.customerapi.adapter.input.api.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import com.ramon.pereira.customerapi.adapter.input.api.dto.request.CreateCustomerRequestDto;
import com.ramon.pereira.customerapi.adapter.input.api.dto.response.CustomerResponseDto;
import com.ramon.pereira.customerapi.core.domain.Customer;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CustomerApiMapperTest {


  @Test
  void customerApiMapper_ToDomain() {
    final var customerRequest = CreateCustomerRequestDto.builder()
        .name("name")
        .cpf("12345678910")
        .build();

    assertThat(CustomerApiMapper.toDomain(customerRequest))
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);
  }

  @Test
  void customerApiMapper_ToListResponseDto() {
    final var customers = List.of(Customer.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build());

    final var listResponseDto = CustomerApiMapper.toListResponseDto(customers);

    assertThat(listResponseDto)
        .isNotNull();

    assertThat(listResponseDto.size())
        .isNotNull()
        .isEqualTo(1);

    assertThat(listResponseDto.get(0))
        .isNotNull()
        .isExactlyInstanceOf(CustomerResponseDto.class);

    assertThat(listResponseDto.get(0).getCpf())
        .isNotNull()
        .hasToString("****5678910");

    assertThat(listResponseDto.get(0).getUuid())
        .isNotNull()
        .isExactlyInstanceOf(UUID.class);

    assertThat(listResponseDto.get(0).getName())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(listResponseDto.get(0).getCreatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);

    assertThat(listResponseDto.get(0).getUpdatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);
  }

  @Test
  void customerApiMapper_ToResponseDto() {
    final var customer = Customer.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build();

    final var responseDto = CustomerApiMapper.toResponseDto(customer);

    assertThat(responseDto)
        .isNotNull()
        .isExactlyInstanceOf(CustomerResponseDto.class);

    assertThat(responseDto.getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(responseDto.getCpf())
        .isNotNull()
        .hasToString("****5678910");

    assertThat(responseDto.getUuid())
        .isNotNull()
        .isExactlyInstanceOf(UUID.class);

    assertThat(responseDto.getName())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(responseDto.getCreatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);

    assertThat(responseDto.getUpdatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);
  }

}
