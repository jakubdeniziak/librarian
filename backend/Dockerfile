FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /app/target/*.jar librarian.jar
ENTRYPOINT ["java", "-jar", "/librarian.jar"]
