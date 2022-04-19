package com.ramon.pereira.customerapi.core.usecase;

import com.ramon.pereira.customerapi.core.domain.Customer;
import com.ramon.pereira.customerapi.core.usecase.port.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CustomerUseCaseTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerUseCase customerUseCase;

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnCustomer() {
    final var customer = buildCustomer();

  }

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnEntityNotFoundException() {

  }

  @Test
  void shouldReturnCustomerWhenCallGetCustomersThenReturnResults() {

  }

  @Test
  void shouldReturnCustomerWhenCallGetCustomersThenReturnEmptyResults() {

  }

  @Test
  void shouldReturnCustomerWhenCallCreateCustomerThenReturnSuccess() {

  }

  @Test
  void shouldReturnCustomerWhenCallCreateCustomerUsingCpfThenReturnConflictCustomerException() {

  }

  private Customer buildCustomer(){
    return Customer.builder()
            .name("name")
            .cpf("12345678910")
            .createdAt(ZonedDateTime.now())
            .updatedAt(ZonedDateTime.now())
            .uuid(UUID.randomUUID())
            .build();
  }
}
