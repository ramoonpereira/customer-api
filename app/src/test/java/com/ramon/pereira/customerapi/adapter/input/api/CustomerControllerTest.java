package com.ramon.pereira.customerapi.adapter.input.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.ramon.pereira.customerapi.adapter.input.api.dto.request.CreateCustomerRequestDto;
import com.ramon.pereira.customerapi.adapter.input.api.dto.response.CustomerResponseDto;
import com.ramon.pereira.customerapi.core.domain.Customer;
import com.ramon.pereira.customerapi.core.exception.ConflictCustomerException;
import com.ramon.pereira.customerapi.core.usecase.CustomerUseCase;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

  @Mock
  private CustomerUseCase customerUseCase;

  @InjectMocks
  private CustomerController customerController;

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnCustomer() {

    final var customer = buildCustomer();

    when(customerUseCase.getByUuid(customer.getUuid()))
        .thenReturn(customer);

    final var response = customerController.getCustomerByUuid(customer.getUuid());

    assertThat(response)
        .isNotNull();

    assertThat(response.getBody())
        .isNotNull()
        .isExactlyInstanceOf(CustomerResponseDto.class);

    assertThat(response.getBody().getUuid())
        .isNotNull()
        .isEqualTo(customer.getUuid());
  }

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnEntityNotFoundException() {
    final var uuid = UUID.randomUUID();

    when(customerUseCase.getByUuid(uuid))
        .thenThrow(new EntityNotFoundException());

    assertThatThrownBy(() -> customerController.getCustomerByUuid(uuid))
        .isExactlyInstanceOf(EntityNotFoundException.class);
  }

  @Test
  void shouldReturnCustomerWhenCallGetCustomersThenReturnResults() {
    final var customers = List.of(buildCustomer());

    when(customerUseCase.getCustomers())
        .thenReturn(customers);

    assertThat(customerController.getCustomers())
        .isNotNull();

    assertThat(customerController.getCustomers().getBody())
        .isNotNull()
        .isNotEmpty();
  }

  @Test
  void shouldReturnCustomerWhenCallGetCustomersThenReturnEmptyResults() {
    when(customerUseCase.getCustomers())
        .thenReturn(Collections.emptyList());

    assertThat(customerController.getCustomers())
        .isNotNull();

    assertThat(customerController.getCustomers().getBody())
        .isNotNull()
        .isEmpty();
  }

  @Test
  @SneakyThrows
  void shouldReturnCustomerWhenCallCreateCustomerThenReturnSuccess() {
    final var customerCreate = CreateCustomerRequestDto.builder()
        .name("name")
        .cpf("12345678910")
        .build();

    final var customerCreated = buildCustomer();

    when(customerUseCase.createCustomer(any()))
        .thenReturn(customerCreated);

    final var response = customerController.createCustomer(customerCreate);

    assertThat(response)
        .isNotNull();

    assertThat(response.getBody())
        .isNotNull()
        .isExactlyInstanceOf(CustomerResponseDto.class);

    assertThat(response.getBody().getUuid())
        .isNotNull()
        .isEqualTo(customerCreated.getUuid());
  }

  @Test
  @SneakyThrows
  void shouldReturnCustomerWhenCallCreateCustomerUsingCpfThenReturnConflictCustomerException() {
    final var customerCreate = CreateCustomerRequestDto.builder()
        .name("name")
        .cpf("12345678910")
        .build();

    when(customerUseCase.createCustomer(any()))
        .thenThrow(new ConflictCustomerException());

    assertThatThrownBy(() -> customerController.createCustomer(customerCreate))
        .isExactlyInstanceOf(ConflictCustomerException.class)
        .hasMessageContaining("Conflict customer already exist");
  }

  private Customer buildCustomer() {
    return Customer.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build();
  }
}
