package me.kire.re.pojogenerator.util;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import lombok.Getter;
import lombok.Setter;
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

import static me.kire.re.pojogenerator.util.Constants.OUT_CONCAT;

@Slf4j
public class PojoUtils {

    private PojoUtils() {
    }

    public static void generateJavaFile(Class propertyKey, List<Attribute> attributes, Path outputDirectory,
                                        Boolean lombok) {
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
            List<AnnotationSpec> annotations = lombokAnnotations();
            classBuilder.addAnnotations(annotations);
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

    private static FieldSpec generateField(ClassName listType, Attribute attribute) {
        TypeName typeName;

        String propertyType = attribute.type();
        String propertyName = attribute.name();
        if (attribute.isArray() && attribute.isObject()) {
            typeName = ParameterizedTypeName.get(listType, ClassName.get("", propertyType));
        } else if (attribute.isObject()) {
            typeName = ClassName.get("", propertyType);
        } else {
            typeName = ClassName.get(String.class);
        }
        return field(typeName, propertyName);
    }

    private static FieldSpec field(TypeName typeName, String field) {
        return FieldSpec.builder(typeName, field)
                .addModifiers(Modifier.PRIVATE)
                .build();
    }

    private static List<MethodSpec> generateMethods(ClassName listType, Attribute attribute) {
        TypeName typeName;

        String propertyType = attribute.type();
        String propertyName = attribute.name();
        if (attribute.isArray() && attribute.isObject()) {
            typeName = ParameterizedTypeName.get(listType, ClassName.get("", propertyType));
        } else if (attribute.isObject()) {
            typeName = ClassName.get("", propertyType);
        } else {
            typeName = ClassName.get(String.class);
        }

        return methods(typeName, propertyName);
    }

    private static List<MethodSpec> methods(TypeName typeName, String field) {
        return Arrays.asList(getterMethod(field, typeName), setterMethod(field, typeName));
    }

    private static MethodSpec getterMethod(String fieldName, TypeName typeName) {
        String field = removeOutString(lowerCase(fieldName));
        return MethodSpec.methodBuilder("get" + StringUtils.capitalize(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(typeName)
                .addStatement("return this.$L", field)
                .build();
    }

    private static MethodSpec setterMethod(String fieldName, TypeName typeName) {
        String field = removeOutString(lowerCase(fieldName));
        return MethodSpec.methodBuilder("set" + StringUtils.capitalize(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(typeName, field)
                .addStatement("this.$L = $L", field, field)
                .build();
    }

    private static List<AnnotationSpec> lombokAnnotations() {
        return Arrays.asList(getterAnnotation(), setterAnnotation());
    }

    private static AnnotationSpec getterAnnotation() {
        return AnnotationSpec.builder(ClassName.get(Getter.class))
                .build();
    }

    private static AnnotationSpec setterAnnotation() {
        return AnnotationSpec.builder(ClassName.get(Setter.class))
                .build();
    }

    private static String upperCaseClassName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static String lowerCase(String field) {
        if (field.startsWith(field.substring(0, 1).toUpperCase())) {
            return field.substring(0, 1).toLowerCase() + field.substring(1);
        }
        return field;
    }

    private static String removeOutString(String field) {
        if (field.endsWith(OUT_CONCAT)) {
            return field.substring(0, field.length() - OUT_CONCAT.length());
        }
        return field;
    }
}
