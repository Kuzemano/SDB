package r.real.archUnit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "r.real")
public class InterfaceRulesTest {

    @ArchTest
    static final ArchRule interfaces_must_not_be_in_impl =
            noClasses().that().resideInAPackage("..impl..").should().beInterfaces();
}