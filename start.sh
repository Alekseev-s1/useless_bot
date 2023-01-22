#!/bin/bash

git pull

mvn clean
mvn package

docker-compose stop

export BOT_NAME=$1
export BOT_TOKEN=$2
export BOT_CHECKTIME=$3
export DB_NAME=$4
export DB_USERNAME=$5
export DB_PASSWORD=$6

docker-compose up --build -d