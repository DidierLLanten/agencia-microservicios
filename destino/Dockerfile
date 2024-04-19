FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} destino.jar
ENTRYPOINT ["java", "-jar", "/destino.jar"]