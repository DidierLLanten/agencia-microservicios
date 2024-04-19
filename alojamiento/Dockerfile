FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} alojamiento.jar
ENTRYPOINT ["java", "-jar", "/alojamiento.jar"]