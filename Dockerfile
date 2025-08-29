FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/country-app-0.0.1.jar /app/country-app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/country-app.jar"]
