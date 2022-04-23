package com.ramon.pereira.customerapi.core.domain;


import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class Customer {

  private UUID uuid;

  private String name;

  private String cpf;

  private ZonedDateTime updatedAt;

  private ZonedDateTime createdAt;

}
