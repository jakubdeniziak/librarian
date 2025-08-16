FROM eclipse-temurin:21-jdk-alpine-3.22 AS dependencies
WORKDIR /build
COPY gradlew gradlew
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .
RUN --mount=type=cache,target=/root/.gradle ./gradlew dependencies --no-daemon

FROM dependencies AS build
COPY src/ src/
RUN --mount=type=cache,target=/root/.gradle ./gradlew bootJar --no-daemon

FROM build AS extract
WORKDIR /build
RUN java -Djarmode=layertools -jar build/libs/*.jar extract --destination build/extracted

FROM eclipse-temurin:21-jdk-alpine-3.22 AS final
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser
COPY --from=extract /build/build/extracted/dependencies/ ./
COPY --from=extract /build/build/extracted/spring-boot-loader/ ./
COPY --from=extract /build/build/extracted/snapshot-dependencies/ ./
COPY --from=extract /build/build/extracted/application/ ./
EXPOSE 443
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
