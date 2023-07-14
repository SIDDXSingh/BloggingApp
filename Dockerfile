FROM openjdk:17.0.2-jdk

COPY build/libs/bloggingApp-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]