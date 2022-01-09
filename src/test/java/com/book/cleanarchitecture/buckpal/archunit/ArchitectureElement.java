package com.book.cleanarchitecture.buckpal.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import java.util.List;

import static com.tngtech.archunit.base.DescribedPredicate.greaterThanOrEqualTo;
import static com.tngtech.archunit.lang.conditions.ArchConditions.containNumberOfElements;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureElement {
    protected final String basePackage;

    public ArchitectureElement(String basePackage) {
        this.basePackage = basePackage;
    }

    static void denyDependency(String fromPackageName, String toPackageName, JavaClasses classes) {
        noClasses()
                .that()
                .resideInAPackage("io.reflectoring.reviewapp.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("io.reflectoring.reviewapp.application..")
                .check(classes);
    }

    static void denyAnyDependency(
            List<String> fromPackages, List<String> toPackages, JavaClasses classes) {
        for (String fromPackage : fromPackages) {
            for (String toPackage : toPackages) {
                noClasses()
                        .that()
                        .resideInAPackage(matchAllClassesInPackage(fromPackage))
                        .should()
                        .dependOnClassesThat()
                        .resideInAnyPackage(matchAllClassesInPackage(toPackage))
                        .check(classes);
            }
        }
    }

    protected static String matchAllClassesInPackage(String packageName) {
        return packageName + "..";
    }

    String fullQualifiedPackage(String relativePackage) {
        StringBuilder fullQualifiedPackage = new StringBuilder();
        fullQualifiedPackage.append(this.basePackage)
                .append(".")
                .append(relativePackage);

        return fullQualifiedPackage.toString();
    }

    void denyEmptyPackages(List<String> packages) {
        for (String packageName : packages) {
            denyEmptyPackage(packageName);
        }
    }

    private void denyEmptyPackage(String packageName) {
        classes()
                .that()
                .resideInAPackage(matchAllClassesInPackage(packageName))
                .should(containNumberOfElements(greaterThanOrEqualTo(1)))
                .check(classesInPackage(packageName));
    }

    private JavaClasses classesInPackage(String packageName) {
        ClassFileImporter classFileImporter = new ClassFileImporter();

        return classFileImporter.importPackages(packageName);
    }
}
