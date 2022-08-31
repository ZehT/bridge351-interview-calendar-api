FROM maven:3.6.3-jdk-11-slim AS build
MAINTAINER Trein
COPY .. /home/interview-calendar-api/
RUN mvn -f /home/interview-calendar-api/pom.xml clean install -DskipTests

FROM adoptopenjdk/openjdk11:latest
COPY --from=build /home/interview-calendar-api/target/*.jar /usr/local/lib/interview-calendar-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT java -jar /usr/local/lib/interview-calendar-api-0.0.1-SNAPSHOT.jar