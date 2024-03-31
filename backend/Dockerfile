FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar librarian.jar
ENTRYPOINT ["java","-jar","/librarian.jar"]
