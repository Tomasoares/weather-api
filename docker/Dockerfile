FROM openjdk:11

ARG JAR_FILE=./weather-api.jar
COPY ${JAR_FILE} app.jar

ARG CFG_FILE=./application.yml
COPY ${CFG_FILE} application.yml

ENTRYPOINT ["java","-jar","/app.jar"]