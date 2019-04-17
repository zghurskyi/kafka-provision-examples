FROM openjdk:8-jdk-slim

COPY build/libs/*.jar /opt/app.jar
COPY init /opt/
WORKDIR /opt

EXPOSE 9001

ENTRYPOINT ["/opt/init"]