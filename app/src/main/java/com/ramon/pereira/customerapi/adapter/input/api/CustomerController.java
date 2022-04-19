package com.ramon.pereira.customerapi.adapter.input.api;

import com.ramon.pereira.customerapi.adapter.input.api.dto.request.CreateCustomerRequestDto;
import com.ramon.pereira.customerapi.adapter.input.api.dto.response.CustomerResponseDto;
import com.ramon.pereira.customerapi.adapter.input.api.mapper.CustomerApiMapper;
import com.ramon.pereira.customerapi.core.exception.ConflictCustomerException;
import com.ramon.pereira.customerapi.core.usecase.CustomerUseCase;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/customers")
@AllArgsConstructor
public class CustomerController {

  private final CustomerUseCase customerUseCase;

  @PostMapping
  public ResponseEntity<CustomerResponseDto> createCustomer(
      @RequestBody final CreateCustomerRequestDto createCustomerRequestDto) throws ConflictCustomerException {
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{uuid}")
            .buildAndExpand(customerUseCase
                .createCustomer(CustomerApiMapper.toDomain(createCustomerRequestDto)).getUuid())
            .toUri())
        .build();
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<CustomerResponseDto> getCustomerByUuid(@PathVariable final UUID uuid) {
    return ResponseEntity.ok(CustomerApiMapper.toResponseDto(customerUseCase.getByUuid(uuid)));
  }

  @GetMapping()
  public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
    return ResponseEntity.ok(CustomerApiMapper.toListResponseDto(customerUseCase.getCustomers()));
  }
}
