package com.book.cleanarchitecture.buckpal;

import com.book.cleanarchitecture.buckpal.archunit.HexagonalArchitecture;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

    // @formatter:off
    @Test
    void validateRegistrationContextArchitecture() {
        HexagonalArchitecture.boundedContext("com.book.cleanarchitecture.buckpal.account")
                .withDomainLayer("domain")
                .withAdaptersLayer("adapter")
                .incoming("in.web")
                .outgoing("out.persistence")
                .and()

                .withApplicationLayer("application")
                .services("service")
                .incomingPorts("port.in")
                .outgoingPorts("port.out")
                .and()

                .withConfiguration("configuration")
                .check(new ClassFileImporter().importPackages("io.reflectoring.buckpal.."));
    }

    @Test
    void testPackageDependencies() {
        noClasses()
                .that()
                .resideInAPackage("io.reflectoring.reviewapp.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("io.reflectoring.reviewapp.application..")
                .check(new ClassFileImporter()
                        .importPackages("io.reflectoring.reviewapp.."));
    }
}
