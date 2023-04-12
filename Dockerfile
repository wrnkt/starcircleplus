FROM amazoncorretto:17
MAINTAINER Niko Ware
ARG JAR_FILE=/service/starcircleplus/target/starcircleplus-0.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080

