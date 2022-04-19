package com.ramon.pereira.customerapi.core.usecase;

import com.ramon.pereira.customerapi.core.domain.Customer;
import com.ramon.pereira.customerapi.core.exception.ConflictCustomerException;
import com.ramon.pereira.customerapi.core.usecase.port.CustomerRepository;

import java.util.List;
import java.util.UUID;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerUseCase {

  private final CustomerRepository customerRepository;

  public Customer createCustomer(@NonNull final Customer customer) throws ConflictCustomerException {
    if (customerRepository.findByCpf(customer.getCpf()).isPresent()) {
      log.warn("Customer already exist: {}", customer);
      throw new ConflictCustomerException();
    }
    log.info("Creating customer : {}", customer);
    return customerRepository.saveAndFlush(customer);
  }

  public Customer getByUuid(@NonNull final UUID uuid) {
    log.info("Getting customer uuid: {}", uuid);
    return customerRepository.getById(uuid);
  }

  public List<Customer> getCustomers() {
    log.info("Getting all customers");
    return customerRepository.findAll();
  }
}
