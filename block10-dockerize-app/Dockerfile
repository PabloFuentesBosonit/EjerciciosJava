FROM openjdk:17-jdk-alpine
COPY "./target/block10-dockerize-app-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=Postgres", "app.jar"]