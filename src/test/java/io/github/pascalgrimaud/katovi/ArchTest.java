package io.github.pascalgrimaud.katovi;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("io.github.pascalgrimaud.katovi");

        noClasses()
            .that()
            .resideInAnyPackage("io.github.pascalgrimaud.katovi.service..")
            .or()
            .resideInAnyPackage("io.github.pascalgrimaud.katovi.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..io.github.pascalgrimaud.katovi.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
