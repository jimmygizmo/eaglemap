FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/eaglemap-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


# EARLIER:
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY . .
#RUN ./mvnw clean package -DskipTests
#CMD ["java", "-jar", "target/eaglemap-0.0.1-SNAPSHOT.jar"]

# TODO: To address absence of mvnw in the openjdk image (one of many ways) we might use a multi-step build and just
# copy what we need out of the first build container.
# OR check out this image which might have the JDK and the Maven tools:
#   eclipse-temurin:21-jdk

