package yyb.julkabar.architecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.domain.JavaClasses;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class CoreArchitectureTest {
    @Test
    public void coreShouldNotDependOnServletOrJdbc() {
        JavaClasses imported = new ClassFileImporter().importPackages("yyb.julkabar.core");

        noClasses()
                .that().resideInAnyPackage("..core..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("jakarta.servlet..", "java.sql..")
                .check(imported);
    }
}
