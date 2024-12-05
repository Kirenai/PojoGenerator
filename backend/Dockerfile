# Etapa 1: Construir el proyecto
FROM maven:3.9.5-eclipse-temurin-21 AS builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copiar el resto de los archivos del proyecto
COPY src ./src

# Construir el proyecto y empaquetar el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final
FROM eclipse-temurin:21-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Configurar el puerto de la aplicación
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]