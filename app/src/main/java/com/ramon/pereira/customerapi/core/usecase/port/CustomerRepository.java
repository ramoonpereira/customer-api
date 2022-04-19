package com.ramon.pereira.customerapi.core.usecase.port;

import com.ramon.pereira.customerapi.core.domain.Customer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

  Optional<Customer> findByCpf(final String cpf);

}
