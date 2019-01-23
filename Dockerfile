FROM openjdk:8-jre-alpine

RUN apk add --update curl

COPY build/libs/motiva-datalinkbi-platform-api-0.0.2.jar /application.jar

CMD [ "java", "-jar", "/application.jar" ]
