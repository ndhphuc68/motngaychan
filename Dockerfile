FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /target/motngaythu6-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
