FROM openjdk:8-jdk-alpine
ARG BUILD_VERSION

ENV DB_HOST localhost
ENV DB_PORT 3306

ADD spring-boot-webapp-${BUILD_VERSION}.jar spring-boot-webapp.jar

EXPOSE 19900

ENTRYPOINT exec java $JAVA_OPTS -Ddb.host=$DB_HOST -Ddb.port=$DB_PORT -jar /spring-boot-webapp.jar
