FROM maven:3.9.4-eclipse-temurin-21 AS build
ARG VERSION=1.0.0
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn -Drevision=${VERSION} package

FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /app/target/*.jar librarian.jar
ENTRYPOINT ["java", "-jar", "/librarian.jar"]
