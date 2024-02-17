#
# Build stage
#
FROM maven:3.8.5-openjdk-18 AS MAVEN_BUILD
COPY ./ ./
RUN mvn package -DskipTests
#
# Package stage
#
FROM openjdk:18-alpine
COPY --from=MAVEN_BUILD target/config-service-0.0.1-SNAPSHOT.jar config-service.jar
VOLUME /app/config
ENTRYPOINT ["java", "-jar", "/config-service.jar"]
EXPOSE 8888