FROM openjdk:17-jdk-alpine
COPY "./target/block17-spring-batch-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=Postgre", "app.jar"]