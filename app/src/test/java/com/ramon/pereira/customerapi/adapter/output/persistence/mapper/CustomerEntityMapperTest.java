package com.ramon.pereira.customerapi.adapter.output.persistence.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ramon.pereira.customerapi.adapter.output.persistence.entity.CustomerEntity;
import com.ramon.pereira.customerapi.core.domain.Customer;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CustomerEntityMapperTest {

  @Test
  void customerEntityMapper_ToDomain() {
    final var customerEntity = CustomerEntity.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build();

    final var customer = CustomerEntityMapper.toDomain(customerEntity);

    assertThat(customer)
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);

    assertThat(customer.getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

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

  @Test
  void customerEntityMapper_ToEntity() {
    final var customer = Customer.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build();

    final var customerEntity = CustomerEntityMapper.toEntity(customer);

    assertThat(customerEntity)
        .isNotNull()
        .isExactlyInstanceOf(CustomerEntity.class);

    assertThat(customerEntity.getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customerEntity.getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customerEntity.getUuid())
        .isNotNull()
        .isExactlyInstanceOf(UUID.class);

    assertThat(customerEntity.getName())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customerEntity.getCreatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);

    assertThat(customerEntity.getUpdatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);
  }

  @Test
  void customerEntityMapper_ToDomainList() {
    final var customersEntity = List.of(CustomerEntity.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build());

    final var customers = CustomerEntityMapper.toDomainList(customersEntity);

    assertThat(customers)
        .isNotNull();

    assertThat(customers.size())
        .isNotNull()
        .isEqualTo(1);

    assertThat(customers.get(0))
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);

    assertThat(customers.get(0).getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customers.get(0).getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customers.get(0).getUuid())
        .isNotNull()
        .isExactlyInstanceOf(UUID.class);

    assertThat(customers.get(0).getName())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customers.get(0).getCreatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);

    assertThat(customers.get(0).getUpdatedAt())
        .isNotNull()
        .isExactlyInstanceOf(ZonedDateTime.class);
  }
}
