package com.ramon.pereira.customerapi.adapter.input.api.dto.response;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto {

  private UUID uuid;

  private String name;

  private String cpf;

  private ZonedDateTime updatedAt;

  private ZonedDateTime createdAt;
}
