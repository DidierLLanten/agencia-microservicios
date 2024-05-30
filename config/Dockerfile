FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} config.jar
ENTRYPOINT ["java", "-jar", "/config.jar"]