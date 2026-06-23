FROM eclipse-temurin:21-jre-alpine-3.23
WORKDIR /app

COPY target/cowork-webservice-api-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
