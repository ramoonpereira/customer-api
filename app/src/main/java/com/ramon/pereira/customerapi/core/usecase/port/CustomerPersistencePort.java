package com.ramon.pereira.customerapi.core.usecase.port;

import com.ramon.pereira.customerapi.core.domain.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerPersistencePort {

  Customer getByCpf(final String cpf);

  Customer getByUuid(final UUID uuid);

  List<Customer> getAll();

  Customer insert(final Customer customer);

}
