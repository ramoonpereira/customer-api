package com.ramon.pereira.customerapi.core.usecase;

import com.ramon.pereira.customerapi.core.domain.Customer;
import com.ramon.pereira.customerapi.core.exception.ConflictCustomerException;
import com.ramon.pereira.customerapi.core.usecase.port.CustomerPersistencePort;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerUseCase {

  private final CustomerPersistencePort customerPersistencePort;

  public Customer createCustomer(@NonNull final Customer customer) throws ConflictCustomerException {
    validCustomerNotExist(customer.getCpf());
    log.info("Creating customer : {}", customer);
    return customerPersistencePort.insert(customer);
  }

  public Customer getByUuid(@NonNull final UUID uuid) {
    log.info("Getting customer uuid: {}", uuid);
    return customerPersistencePort.getByUuid(uuid);
  }

  public List<Customer> getCustomers() {
    log.info("Getting all customers");
    return customerPersistencePort.getAll();
  }

  private void validCustomerNotExist(final String cpf) throws ConflictCustomerException {
      try{
          if(Objects.nonNull(customerPersistencePort.getByCpf(cpf))){
              throw new ConflictCustomerException();
          }
      }catch (final  ConflictCustomerException ex){
          log.warn("Customer already exist: {}", cpf);
          throw ex;
      }catch (final EntityNotFoundException entityNotFoundException){
          log.warn("Customer not exist: {}", cpf);
      }
  }
}
