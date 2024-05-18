FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} autenticacion.jar
ENTRYPOINT ["java", "-jar", "/autenticacion.jar"]