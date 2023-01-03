FROM openjdk:17-jdk-alpine
COPY "./target/block16-trip-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=Postgre", "app.jar"]