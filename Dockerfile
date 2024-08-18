FROM openjdk:17-jdk-slim
WORKDIR /usr/app
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 9001
ENTRYPOINT exec java ${JAVA_OPTS} -jar app.jar
