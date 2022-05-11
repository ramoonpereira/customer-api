package com.ramon.pereira.customerapi.adapter.output.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.ramon.pereira.customerapi.adapter.output.persistence.entity.CustomerEntity;
import com.ramon.pereira.customerapi.adapter.output.persistence.repository.CustomerRepository;
import com.ramon.pereira.customerapi.core.domain.Customer;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersistenceJpaAdapterTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private PersistenceJpaAdapter persistenceJpaAdapter;

  @Test
  void shouldReturnCustomerWhenCallGetByCpfThenReturnEntityNotFoundException() {
    final var cpf = "12345678910";
    when(customerRepository.findByCpf(cpf))
        .thenThrow(new EntityNotFoundException());

    assertThatThrownBy(() -> persistenceJpaAdapter.getByCpf(cpf))
        .isExactlyInstanceOf(EntityNotFoundException.class);
  }

  @Test
  void shouldReturnCustomerWhenCallGetByCpfThenReturnEntity() {
    final var customerEntity = buildCustomerEntity();

    when(customerRepository.findByCpf(customerEntity.getCpf()))
        .thenReturn(customerEntity);

    final var customer = persistenceJpaAdapter.getByCpf(customerEntity.getCpf());

    assertThat(customer)
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);

    assertThat(customer.getCpf())
        .isNotNull()
        .isExactlyInstanceOf(String.class);

    assertThat(customer.getCpf())
        .isNotNull()
        .isEqualTo("12345678910");
  }

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnEntityNotFoundException() {
    final var uuid = UUID.randomUUID();
    when(customerRepository.getById(uuid))
        .thenThrow(new EntityNotFoundException());

    assertThatThrownBy(() -> persistenceJpaAdapter.getByUuid(uuid))
        .isExactlyInstanceOf(EntityNotFoundException.class);
  }

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnCustomer() {
    final var customerEntity = buildCustomerEntity();

    when(customerRepository.getById(customerEntity.getUuid()))
        .thenReturn(customerEntity);

    final var customer = persistenceJpaAdapter.getByUuid(customerEntity.getUuid());

    assertThat(customer)
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);

    assertThat(customer.getUuid())
        .isNotNull()
        .isExactlyInstanceOf(UUID.class);
  }

  @Test
  void shouldReturnCustomerWhenCallGetCustomersThenReturnResults() {
    final var customersEntity = List.of(buildCustomerEntity());

    when(customerRepository.findAll())
        .thenReturn(customersEntity);

    final var customers = persistenceJpaAdapter.getAll();

    assertThat(customers)
        .isNotNull()
        .isNotEmpty();

    assertThat(customers.size())
        .isNotNull()
        .isEqualTo(1);

    assertThat(customers.get(0))
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);
  }

  @Test
  void shouldReturnCustomerWhenCallGetCustomersThenReturnEmptyResults() {
    when(customerRepository.findAll())
        .thenReturn(Collections.emptyList());

    final var customers = persistenceJpaAdapter.getAll();

    assertThat(customers)
        .isNotNull()
        .isEmpty();
  }

  @Test
  void shouldReturnCustomerWhenCallCreateCustomerThenReturnSuccess() {
    final var customer = Customer.builder()
        .name("name")
        .cpf("12345678910")
        .build();
    final var customerEntity = buildCustomerEntity();

    when(customerRepository.saveAndFlush(any(CustomerEntity.class)))
        .thenReturn(customerEntity);

    final var customerCreated = persistenceJpaAdapter.insert(customer);

    assertThat(customerCreated)
        .isNotNull()
        .isExactlyInstanceOf(Customer.class);

    assertThat(customerCreated.getUuid())
        .isNotNull()
        .isExactlyInstanceOf(UUID.class);

    assertThat(customerCreated.getCpf())
        .isNotNull()
        .isEqualTo("12345678910");
  }

  private CustomerEntity buildCustomerEntity() {
    return CustomerEntity.builder()
        .name("name")
        .cpf("12345678910")
        .createdAt(ZonedDateTime.now())
        .updatedAt(ZonedDateTime.now())
        .uuid(UUID.randomUUID())
        .build();
  }
}
