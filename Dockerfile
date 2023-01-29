FROM openjdk:17
WORKDIR /app
COPY build/libs/ModsenTask-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ModsenTask-0.0.1-SNAPSHOT.jar"]

