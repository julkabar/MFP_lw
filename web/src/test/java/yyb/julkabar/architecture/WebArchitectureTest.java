package yyb.julkabar.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class WebArchitectureTest {
    @Test
    public void webShouldNotDependOnPersistence() {
        JavaClasses imported = new ClassFileImporter()
                .importPackages("yyb.julkabar.web");

        noClasses()
                .that().resideInAPackage("..web..")
                .should().dependOnClassesThat()
                .resideInAPackage("..persistence..")
                .check(imported);
    }
}
