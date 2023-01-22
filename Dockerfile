FROM amazoncorretto:11-alpine-jdk
ENV BOT_TOKEN=${BOT_TOKEN}
ENV BOT_NAME=${BOT_NAME}
ENV BOT_CHECKTIME=${BOT_CHECKTIME}
ENV DB_NAME=${DB_NAME}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
COPY target/*.jar useless_bot_app.jar
ENTRYPOINT ["java", "-jar", "/useless_bot_app.jar"]