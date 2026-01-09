package yyb.julkabar.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class RepositoryLocationTest {
    @Test
    public void coreShouldNotDependOnServletOrJdbc() {
        JavaClasses imported = new ClassFileImporter().importPackages("yyb.julkabar");

        classes()
                .that().haveSimpleNameEndingWith("Repository")
                .should().resideInAPackage("..persistence..")
                .check(imported);
    }
}
