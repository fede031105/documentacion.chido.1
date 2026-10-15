# ==========================================
# Etapa 1: Compilación del proyecto (Build)
# ==========================================
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar pom.xml y descargar dependencias para aprovechar la caché de Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar omitiendo las pruebas unitarias
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# Etapa 2: Imagen final de ejecución (Runtime)
# ==========================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar el archivo .jar generado desde la etapa de compilació
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto 8081
EXPOSE 8081

# Ejecutar la aplicación permitiendo adaptar el puerto según la variable PORT de Render
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8081} -jar app.jar"]