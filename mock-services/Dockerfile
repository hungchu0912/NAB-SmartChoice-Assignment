FROM openjdk:8-jdk
EXPOSE 9000
WORKDIR /jar
ADD jar /jar
ENTRYPOINT [ "sh", "-c", "java -jar wiremock-standalone-2.27.2.jar --port 9000" ]