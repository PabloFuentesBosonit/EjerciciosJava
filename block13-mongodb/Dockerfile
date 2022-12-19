FROM openjdk:17-jdk-alpine
COPY "./target/block13-mongodb-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.data.mongodb.host=mongodb", "app.jar"]