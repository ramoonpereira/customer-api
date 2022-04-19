package com.ramon.pereira.customerapi.adapter.input.api.mapper;

import com.ramon.pereira.customerapi.adapter.input.api.dto.request.CreateCustomerRequestDto;
import com.ramon.pereira.customerapi.adapter.input.api.dto.response.CustomerResponseDto;
import com.ramon.pereira.customerapi.core.domain.Customer;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerApiMapper {

  public Customer toDomain(@NonNull final CreateCustomerRequestDto createCustomerRequestDto) {
    return Customer.builder()
        .cpf(createCustomerRequestDto.getCpf())
        .name(createCustomerRequestDto.getName())
        .build();
  }

  public List<CustomerResponseDto> toListResponseDto(@NonNull final List<Customer> customer) {
    return customer.stream()
        .map(CustomerApiMapper::toResponseDto)
        .collect(Collectors.toList());
  }

  public CustomerResponseDto toResponseDto(@NonNull final Customer customer) {
    return CustomerResponseDto.builder()
        .uuid(customer.getUuid())
        .name(customer.getName())
        .cpf(hideCpf(customer.getCpf()))
        .createdAt(customer.getCreatedAt())
        .updatedAt(customer.getUpdatedAt())
        .build();
  }

  private String hideCpf(final String cpf) {
    StringBuilder hideCpf = new StringBuilder(cpf);
    for (int i = 0; i < 4; i++) {
      hideCpf.setCharAt(i, '*');
    }
    return hideCpf.toString();
  }

}
