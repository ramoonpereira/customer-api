package com.ramon.pereira.customerapi.core.domain;


import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Builder
@Entity
@Table(name = "customer", schema = "customerdb")
public class Customer {

  @Id
  @Column(name = "uuid")
  @Type(type = "uuid-char")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column(name = "name")
  private String name;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @PrePersist
  protected void prePersist() {
    updatedAt = createdAt = ZonedDateTime.now();
  }

  @PreUpdate
  protected void preUpdate() {
    updatedAt = ZonedDateTime.now();
  }
}
