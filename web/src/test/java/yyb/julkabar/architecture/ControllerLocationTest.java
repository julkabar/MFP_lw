package yyb.julkabar.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ControllerLocationTest {
    @Test
    void controllersShouldResideOnlyInWeb() {
        JavaClasses imported = new ClassFileImporter()
                .importPackages("yyb.julkabar");

        classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().resideInAPackage("..web..")
                .check(imported);
    }
}
