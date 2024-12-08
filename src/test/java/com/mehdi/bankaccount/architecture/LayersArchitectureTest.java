package com.mehdi.bankaccount.architecture;


import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameContaining;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameEndingWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.mehdi.bankaccount")
public class LayersArchitectureTest {

    @ArchTest
    static final ArchRule controllers_areTaggedAndLocatedOnAdapterApi =
            classes().that()
                    .areAnnotatedWith(RestController.class)
                    .should()
                    .bePackagePrivate()
                    .andShould()
                    .haveSimpleNameEndingWith("Controller")
                    .andShould()
                    .resideInAPackage("..adapter.entrypoint.api..");

    @ArchTest
    static final ArchRule repositories_shouldBeLocatedOnAdapterPersistence_AndFollowingStructure =
            classes().that()
                    .areAnnotatedWith(Repository.class)
                    .should()
                    .bePublic()
                    .andShould()
                    .beInterfaces()
                    .andShould()
                    .beAssignableTo(JpaRepository.class)
                    .andShould()
                    .haveSimpleNameEndingWith("Repository")
                    .andShould()
                    .resideInAPackage("..adapter.persistence..");

    @ArchTest
    static final ArchRule adapters_shouldBeOnItsPackage_AndExtendingPort =
            classes().that()
                    .haveSimpleNameEndingWith("Adapter")
                    .should()
                    .bePackagePrivate()
                    .andShould()
                    .haveSimpleNameEndingWith("Adapter")
                    .andShould()
                    .implement(simpleNameEndingWith("Port"))
                    .andShould()
                    .onlyAccessClassesThat()
                    .resideInAnyPackage(
                            "..infrastructure..", "..adapter..", "..application.usecase..", "..domain..",
                            "java.lang..", "java.util.."/*, "reactor.core.."*/);

    @ArchTest
    static final ArchRule mappers_should_bePackageScope_AndOnlyBeAccessedByAdapters =
            classes().that()
                    .haveSimpleNameEndingWith("Mapper")
                    .should()
                    .onlyBeAccessed()
                    .byClassesThat(simpleNameContaining("Adapter")
                            .or(simpleNameContaining("Mapper")));

    @ArchTest
    static final ArchRule services_shouldBeOnApplicationPackage_ImplementUseCase_AndCallPorts =
            classes().that()
                    .haveSimpleNameEndingWith("Service")
                    .should()
                    .bePackagePrivate()
                    .andShould()
                    .resideInAnyPackage("..application.service..")
                    .andShould()
                    .implement(simpleNameEndingWith("UseCase"))
                    .andShould()
                    .accessClassesThat().haveSimpleNameEndingWith("Port");

    @ArchTest
    static final ArchRule useCases_shouldBeInProperPackage_AndBeInterfaces =
            classes().that()
                    .haveSimpleNameEndingWith("UseCase")
                    .should()
                    .resideInAPackage("..application.usecase..")
                    .andShould()
                    .beInterfaces();

    @ArchTest
    static final ArchRule ports_shouldBeInProperPackage_AndBeInterfaces =
            classes().that()
                    .haveSimpleNameEndingWith("Port")
                    .should()
                    .resideInAPackage("..application.port..")
                    .andShould()
                    .beInterfaces();
}
