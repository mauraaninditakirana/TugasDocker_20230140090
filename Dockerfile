FROM ubuntu:latest
RUN apt-get update && apt-get install -y openjdk-25-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080