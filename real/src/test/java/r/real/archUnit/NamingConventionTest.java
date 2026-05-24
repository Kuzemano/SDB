package r.real.archUnit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "r.real")
public class NamingConventionTest {

    @ArchTest
    static final ArchRule service_classes_should_end_with_service_impl =
            classes().that().resideInAPackage("..service..")
                    .and().areNotInterfaces()
                    .and().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("ServiceImpl");

    @ArchTest
    static final ArchRule service_interfaces_should_end_with_service =
            classes().that().resideInAPackage("..service..")
                    .and().areInterfaces()
                    .should().haveSimpleNameEndingWith("Service");
}