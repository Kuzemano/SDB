package r.real.archUnit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "r.real")
public class LayerDependencyRulesTest {

    @ArchTest
    static final ArchRule entities_must_reside_in_domain =
            classes().that().areAnnotatedWith(Entity.class)
                    .should().resideInAPackage("..domain..");

    @ArchTest
    static final ArchRule services_should_not_depend_on_web =
            noClasses().that().resideInAPackage("..service..")
                    .should().dependOnClassesThat().resideInAPackage("..web..");

    @ArchTest
    static final ArchRule repositories_should_not_depend_on_services =
            noClasses().that().resideInAPackage("..repository..")
                    .should().dependOnClassesThat().resideInAPackage("..service..");

    @ArchTest
    static final ArchRule services_should_only_be_depended_on_by_web_service_or_config =
            classes().that().resideInAPackage("..service..")
                    .should().onlyHaveDependentClassesThat()
                    .resideInAnyPackage("..web..", "..service..", "..config..");

    @ArchTest
    static final ArchRule services_should_only_depend_on_allowed_packages =
            classes().that().resideInAPackage("..service..")
                    .should().onlyDependOnClassesThat().resideInAnyPackage(
                            "..service..",
                            "..repository..",
                            "..domain..",
                            "java..",
                            "org.springframework.stereotype.."
                    );
}