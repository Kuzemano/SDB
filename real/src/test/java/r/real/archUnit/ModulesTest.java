package r.real.archUnit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.modules.syntax.ModuleDependencyScope;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.library.modules.syntax.ModuleRuleDefinition.modules;
import static com.tngtech.archunit.library.modules.syntax.AllowedModuleDependencies.allow;

@AnalyzeClasses(packages = "r.real")
public class ModulesTest {

    @ArchTest
    static final ArchRule modules_should_respect_dependencies =
            modules()
                    .definedByPackages("r.real.(*)..")
                    .should().respectTheirAllowedDependencies(
                            allow()
                                    .fromModule("rides").toModules("drivers", "common")
                                    .fromModule("drivers").toModules("common")
                                    .fromModule("common").toModules(),
                            ModuleDependencyScope.consideringOnlyDependenciesBetweenModules()
                    )
                    .ignoreDependency(resideInAnyPackage("r.real.config.."), alwaysTrue());
}