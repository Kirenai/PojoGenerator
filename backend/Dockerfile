# Utiliza una imagen base de JDK
FROM eclipse-temurin:21-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado en el contenedor
COPY target/pojo_generator-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto (opcional, Render lo asignará automáticamente)
EXPOSE 8080

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "app.jar"]