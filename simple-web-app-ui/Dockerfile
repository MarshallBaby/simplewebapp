FROM openjdk:11

WORKDIR ./app
COPY . .

RUN chmod +x mvnw
RUN ./mvnw package

ENTRYPOINT ["/usr/local/openjdk-11/bin/java", "-jar", "/app/target/simple-web-app-ui-0.0.1-SNAPSHOT.jar"]