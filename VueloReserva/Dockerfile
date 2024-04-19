FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} VueloReserva.jar
ENTRYPOINT ["java", "-jar", "/VueloReserva.jar"]