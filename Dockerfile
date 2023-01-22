FROM amazoncorretto:11-alpine-jdk
ENV BOT_TOKEN=${BOT_TOKEN}
ENV BOT_NAME=${BOT_NAME}
ENV BOT_CHECKTIME=${BOT_CHECKTIME}
ENV DB_NAME=${DB_NAME}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
COPY target/*.jar useless_bot_app.jar
ENTRYPOINT ["java", "-Dbot.token=${BOT_TOKEN}", "-Dbot.username=${BOT_NAME}", "-Dbot.checktime=${BOT_CHECKTIME}", "-Dspring.datasource.username=${DB_USERNAME}", "-Dspring.datasource.password=${DB_PASSWORD}", "-jar", "/useless_bot_app.jar"]