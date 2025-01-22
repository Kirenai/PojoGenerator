package me.kire.re.pojogenerator.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import lombok.extern.slf4j.Slf4j;
import me.kire.re.pojogenerator.model.Attribute;
import me.kire.re.pojogenerator.model.Class;
import org.springframework.util.StringUtils;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static me.kire.re.pojogenerator.util.Constants.OUT_CONCAT;

@Slf4j
public class PojoGenerator {
    private final Consumer<TypeSpec.Builder> lombokAnnotations = builder -> {
        List<AnnotationSpec> annotationSpecs = lombokAnnotations();
        builder.addAnnotations(annotationSpecs);
    };
    
    public void generateJavaFile(Class propertyKey, List<Attribute> attributes, Path outputDirectory, Boolean lombok) {
        // Class
        String className = upperCaseClassName(propertyKey.name());
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        // Fields, Getters and Setters
        ClassName listType = ClassName.get("java.util", "List");

        attributes.forEach(property -> {
            FieldSpec fieldSpec = generateField(listType, property);
            List<MethodSpec> methodSpecs = generateMethods(listType, property);
            classBuilder.addField(fieldSpec);
            if (!lombok) {
                classBuilder.addMethods(methodSpecs);
            }
        });

        if (lombok) {
            lombokAnnotations.accept(classBuilder);
        }

        String packageName = propertyKey.ioType().equals("I") ? "in" : "out";
        JavaFile javaFile = JavaFile.builder(packageName, classBuilder.build())
                .skipJavaLangImports(true)
                .indent("    ")
                .build();

        try {
            log.debug("Java file generated: {}{}{}.java", packageName.replace(".", File.separator), File.separator, className);
            javaFile.writeTo(outputDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FieldSpec generateField(ClassName listType, Attribute attribute) {
        String propertyType = attribute.type();
        String propertyName = attribute.name();

        if (attribute.isArray() && attribute.isObject()) {
            var parameterizedTypeName =
                    ParameterizedTypeName.get(listType, ClassName.get("", propertyType));
            return field(parameterizedTypeName, propertyName);
        } else if (attribute.isObject()) {
            var className = ClassName.get("", propertyType);
            return field(className, propertyName);
        } else {
            var className = ClassName.get(String.class);
            return field(className, propertyName);
        }
    }

    private FieldSpec field(TypeName typeName, String field) {
        return FieldSpec.builder(typeName, field)
                .addModifiers(Modifier.PRIVATE)
                .build();
    }

    private List<MethodSpec> generateMethods(ClassName listType, Attribute attribute) {
        String propertyType = attribute.type();
        String propertyName = attribute.name();

        if (attribute.isArray() && attribute.isObject()) {
            var parameterizedTypeName =
                    ParameterizedTypeName.get(listType, ClassName.get("", propertyType));
            return methods(parameterizedTypeName, propertyName);
        } else if (attribute.isObject()) {
            var className = ClassName.get("", propertyType);
            return methods(className, propertyName);
        } else {
            var className = ClassName.get(String.class);
            return methods(className, propertyName);
        }
    }

    private List<MethodSpec> methods(TypeName typeName, String field) {
        return Arrays.asList(getterMethod(field, typeName), setterMethod(field, typeName));
    }

    private MethodSpec getterMethod(String fieldName, TypeName typeName) {
        String field = removeOutString(lowerCase(fieldName));
        return MethodSpec.methodBuilder("get" + StringUtils.capitalize(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(typeName)
                .addStatement("return this.$L", field)
                .build();
    }

    private MethodSpec setterMethod(String fieldName, TypeName typeName) {
        String field = removeOutString(lowerCase(fieldName));
        return MethodSpec.methodBuilder("set" + StringUtils.capitalize(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(typeName, field)
                .addStatement("this.$L = $L", field, field)
                .build();
    }

    private List<AnnotationSpec> lombokAnnotations() {
        return Arrays.asList(getterAnnotation(), setterAnnotation());
    }

    private AnnotationSpec getterAnnotation() {
        ClassName getter = ClassName.get("lombok", "Getter");
        return AnnotationSpec.builder(getter)
                .build();
    }

    private AnnotationSpec setterAnnotation() {
        ClassName setter = ClassName.get("lombok", "Setter");
        return AnnotationSpec.builder(setter)
                .build();
    }

    private String upperCaseClassName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private String lowerCase(String field) {
        if (field.startsWith(field.substring(0, 1).toUpperCase())) {
            return field.substring(0, 1).toLowerCase() + field.substring(1);
        }
        return field;
    }

    private String removeOutString(String field) {
        if (field.endsWith(OUT_CONCAT)) {
            return field.substring(0, field.length() - OUT_CONCAT.length());
        }
        return field;
    }
}