FROM openjdk:11-jdk
VOLUME /tmp
ARG JAR_FILE=target/shop-api-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]