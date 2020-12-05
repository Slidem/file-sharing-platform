#!/bin/bash

# text colors
# 1 - red
# 2 - green
# 7 - white

tput setaf 2
echo 
echo "#####################################################################"
echo "#  Initializing local dev environment for file-sharing application  #"
echo "#####################################################################"
echo 
tput setaf 7

## prereq
brew install jq

## add docker localhost DNS
# map in etc/hosts -> 127.0.0.1: host.docker.internal
# sudo dscacheutil -flushcache


### create storage folder
mkdir -p ~/dev/file-sharing-storage/

#
# ACTIVEMQ SETUP
#

tput setaf 2
echo
echo "SETTING UP ACTIVE MQ"
echo
tput setaf 7

# run docker container if it's not running already
if [ ! "$(docker ps -q -f name=activemq)" ]; then
    echo "Pulling activemq docker image..."
    docker pull webcenter/activemq
    echo "Running activemq docker image..."
    docker run -d --name activemq -p 61616:61616 -p 8161:8161 webcenter/activemq
fi
echo

## check activemq is alive
STATUS="$(curl -u admin:admin -s http://localhost:8161/api/jolokia/exec/org.apache.activemq:type=Broker,brokerName=localhost,service=Health/healthStatus | jq -r '.status')"

if [ "$STATUS" != "200" ]; then
   tput setaf 1
   echo -e "\nERROR: ActiveMQ failed to start.\n\n"
else
   tput setaf 2
   echo -e "\nINFO: ActiveMQ is up and running.\n\n"
fi
tput setaf 2

#
# POSTGRES SETUP
#

tput setaf 2
echo
echo "SETTING UP POSTGRES DATABASE"
echo
tput setaf 7

if [ ! "$(docker ps -q -f name=file-sharing-backend-database)" ]; then
    echo "Run postgres DB docker image"
    docker run -d --name file-sharing-backend-database -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
fi

echo "Update postgres DB schema"
cd file-sharing-back/file-sharing-back-db/
mvn clean install -q
mvn liquibase:update -q
cd ../..
echo
echo

#
# BUILD COMMON PACKAGES
#

tput setaf 2
echo
echo "Build common dependencies..."
echo
tput setaf 7
cd file-sharing-common
mvn clean install -q
cd ..

#
# AUTH APP SETUP 
#

tput setaf 2
echo
echo "SETTING UP AUTH APP"
echo
tput setaf 7

if [ ! "$(docker ps -q -f name=file-sharing-auth-app)" ]; then
    echo "Run auth app docker image..."
    cd file-sharing-auth/file-sharing-auth-app
    mvn clean install -q
    docker build -t file-sharing-auth-app:latest .
    docker run -d --name file-sharing-auth-app -p 9081:9081 file-sharing-auth-app
    cd ../..
fi

STATUS="$(curl -s http://localhost:9081/file-sharing-auth/health | jq -r '.status')"

if [ "$STATUS" != "UP" ]; then
   tput setaf 1
   echo -e "\nERROR: file-sharing-auth app failed to start.\n\n"
else
   tput setaf 2
   echo -e "\nINFO: file-sharing-auth app is up and running.\n\n"
fi
tput setaf 7

#
# FRONTEND APP SETUP
#

tput setaf 2
echo
echo "SETTING UP FRONTEND APP"
echo
tput setaf 7


if [ ! "$(docker ps -q -f name=file-sharing-front-webapp)" ]; then
    echo "Run frontend app docker image..."
    cd file-sharing-front/file-sharing-front-webapp/
    mvn clean install -q
    docker build -t file-sharing-front-webapp:latest .
    docker run -d --name file-sharing-front-webapp -p 8084:8084 file-sharing-front-webapp
    cd ../..
fi

status_code=$(curl --write-out %{http_code} --silent --output /dev/null http://localhost:8084/file-sharing/index)
if [ "$status_code" != "302" ]; then
   tput setaf 1
   echo -e "\nERROR: file-sharing-fronted app failed to start.\n\n"
else
   tput setaf 2
   echo -e "\nINFO: file-sharing-fronted app is up and running.\n\n"
fi
tput setaf 7


# echo "Run backend app docker image"
# cd file-sharing-backend/file-sharing-backend-app
# mvn clean install
# docker run -d -p 8090:8080 file-sharing-back-app
# cd ../..

tput setaf 2
echo
echo "SETTING UP FINISHED"
echo
tput setaf 7

read -p "Press enter to continue..."