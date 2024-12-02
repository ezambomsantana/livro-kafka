FROM openjdk:21-jdk
VOLUME /tmp
ARG JAR_FILE=target/shop-report-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]