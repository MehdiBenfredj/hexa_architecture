package com.mehdi.bankaccount.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.mehdi.bankaccount")
public class DomainArchitectureTest {

    @ArchTest
    static final ArchRule domainModel_shouldOnlyHave_DependenciesWithBasicLibraries_andItsOwnProject =
            classes().that()
                    .resideInAnyPackage("..domain..")
                    .should()
                    .onlyHaveDependentClassesThat()
                    .resideInAnyPackage("java..", "javax..",
                            "com.mehdi.bankaccount..");
}
