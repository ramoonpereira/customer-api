package com.ramon.pereira.customerapi.archunit;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import org.junit.jupiter.api.Test;

class ArchitectureTest {

  @Test
  void shouldRespectedLayersDependencies() {
    final var importedClasses = new ClassFileImporter()
        .importPackages("com.ramon.pereira.customerapi");

    final var rule = layeredArchitecture()
        .layer("Domain").definedBy("com.ramon.pereira.customerapi.core.domain..")
        .layer("UseCase").definedBy("com.ramon.pereira.customerapi.core.usecase..")
        .layer("Input").definedBy("com.ramon.pereira.customerapi.adapter.input..")

        .whereLayer("Domain").mayOnlyBeAccessedByLayers("Input", "UseCase")
        .whereLayer("UseCase").mayOnlyBeAccessedByLayers("Input");

    rule.check(importedClasses);
  }

}
