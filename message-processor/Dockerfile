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
COPY --from=MAVEN_BUILD target/message-processor-0.0.1-SNAPSHOT.jar message-processor.jar
ENTRYPOINT ["java", "-jar", "/message-processor.jar"]
EXPOSE 8084