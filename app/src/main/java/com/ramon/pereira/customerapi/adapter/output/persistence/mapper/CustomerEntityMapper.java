package com.ramon.pereira.customerapi.adapter.output.persistence.mapper;

import com.ramon.pereira.customerapi.adapter.output.persistence.entity.CustomerEntity;
import com.ramon.pereira.customerapi.core.domain.Customer;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CustomerEntityMapper {

    public Customer toDomain(@NonNull final CustomerEntity customer) {
        return Customer.builder()
                .cpf(customer.getCpf())
                .name(customer.getName())
                .uuid(customer.getUuid())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }

    public List<Customer> toDomainList(@NonNull final List<CustomerEntity> customers) {
        return customers.stream()
                .map(CustomerEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public CustomerEntity toEntity(@NonNull final Customer customer) {
        return CustomerEntity.builder()
                .cpf(customer.getCpf())
                .name(customer.getName())
                .uuid(customer.getUuid())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }
}
