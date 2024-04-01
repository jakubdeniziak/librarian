FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp

ENV SERVER_PORT=8081

EXPOSE 8081
COPY target/*.jar librarian.jar
ENTRYPOINT ["java","-jar","/librarian.jar"]
