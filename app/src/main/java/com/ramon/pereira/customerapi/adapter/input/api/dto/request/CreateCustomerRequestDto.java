package com.ramon.pereira.customerapi.adapter.input.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCustomerRequestDto {

  private String name;

  private String cpf;
}
