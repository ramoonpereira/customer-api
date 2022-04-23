package com.ramon.pereira.customerapi.adapter.output.persistence;

import com.ramon.pereira.customerapi.adapter.output.persistence.mapper.CustomerEntityMapper;
import com.ramon.pereira.customerapi.adapter.output.persistence.repository.CustomerRepository;
import com.ramon.pereira.customerapi.core.domain.Customer;
import com.ramon.pereira.customerapi.core.usecase.port.CustomerPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersistenceJpaAdapter implements CustomerPersistencePort {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getByCpf(String cpf) {
        final var customer = customerRepository.findByCpf(cpf);
        return CustomerEntityMapper.toDomain(customer);
    }

    @Override
    public Customer getByUuid(UUID uuid) {
        final var customer = customerRepository.getById(uuid);
        return CustomerEntityMapper.toDomain(customer);
    }

    @Override
    public List<Customer> getAll() {
        final var customers = customerRepository.findAll();
        return CustomerEntityMapper.toDomainList(customers);
    }

    @Override
    public Customer insert(Customer customer) {
        final var customerCreated =
                customerRepository.saveAndFlush(CustomerEntityMapper.toEntity(customer));
        return CustomerEntityMapper.toDomain(customerCreated);
    }
}
