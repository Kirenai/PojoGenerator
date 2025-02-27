package me.kire.re.pojogenerator.domain.port.out;

import me.kire.re.pojogenerator.domain.model.Attribute;
import me.kire.re.pojogenerator.domain.model.Class;

import java.nio.file.Path;
import java.util.List;

public interface PojoGeneratorPort {
    void generateJavaFile(Class clazz, List<Attribute> attributes, Path outputDirectory, Boolean useLombok);
}
