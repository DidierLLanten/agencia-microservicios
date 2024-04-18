FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Persona.jar
ENTRYPOINT ["java", "-jar", "/Persona.jar"]