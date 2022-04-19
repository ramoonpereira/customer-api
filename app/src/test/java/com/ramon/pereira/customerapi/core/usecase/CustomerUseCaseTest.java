package com.ramon.pereira.customerapi.core.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import com.ramon.pereira.customerapi.core.domain.Customer;
import com.ramon.pereira.customerapi.core.exception.ConflictCustomerException;
import com.ramon.pereira.customerapi.core.usecase.port.CustomerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class CustomerUseCaseTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerUseCase customerUseCase;

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnCustomer() {
    final var customer = buildCustomer();

    when(customerRepository.getById(customer.getUuid()))
        .thenReturn(customer);

    assertThat(customerUseCase.getByUuid(customer.getUuid()))
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);
  }

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnEntityNotFoundException() {
    final var uuid = UUID.randomUUID();

    when(customerRepository.getById(uuid))
        .thenThrow(new EntityNotFoundException());

    assertThatThrownBy(() -> customerUseCase.getByUuid(uuid))
        .isExactlyInstanceOf(EntityNotFoundException.class);
  }

  @Test
  void shouldReturnCustomerWhenCallGetCustomersThenReturnResults() {
    final var customers = List.of(buildCustomer());

    when(customerRepository.findAll())
        .thenReturn(customers);

    assertThat(customerUseCase.getCustomers())
        .isNotNull()
        .isNotEmpty();
  }

  @Test
  void shouldReturnCustomerWhenCallGetCustomersThenReturnEmptyResults() {
    when(customerRepository.findAll())
        .thenReturn(Collections.emptyList());

    assertThat(customerUseCase.getCustomers())
        .isNotNull()
        .isEmpty();
  }

  @Test
  @SneakyThrows
  void shouldReturnCustomerWhenCallCreateCustomerThenReturnSuccess() {
    final var customerCreate = Customer.builder()
        .name("name")
        .cpf("12345678910")
        .build();
    final var customerCreated = buildCustomer();

    when(customerRepository.findByCpf(customerCreate.getCpf()))
        .thenReturn(Optional.empty());

    when(customerRepository.saveAndFlush(customerCreate))
        .thenReturn(customerCreated);

    final var entity = customerUseCase.createCustomer(customerCreate);

    assertThat(entity)
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);

    assertThat(entity.getUuid())
        .isNotNull()
        .isExactlyInstanceOf(UUID.class);
  }

  @Test
  @SneakyThrows
  void shouldReturnCustomerWhenCallCreateCustomerUsingCpfThenReturnConflictCustomerException() {
    final var customerCreate = Customer.builder()
        .name("name")
        .cpf("12345678910")
        .build();

    final var customerExist = buildCustomer();

    when(customerRepository.findByCpf(customerCreate.getCpf()))
        .thenReturn(Optional.of(customerExist));

    assertThatThrownBy(() -> customerUseCase.createCustomer(customerCreate))
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
