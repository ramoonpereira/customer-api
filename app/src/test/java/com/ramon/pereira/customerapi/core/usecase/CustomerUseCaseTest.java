package com.ramon.pereira.customerapi.core.usecase;

import com.ramon.pereira.customerapi.core.usecase.port.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerUseCaseTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerUseCase customerUseCase;

  @Test
  void shouldReturnCustomerWhenCallGetByUuidThenReturnCustomer() {

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
}
