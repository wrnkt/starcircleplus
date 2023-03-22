#! /bin/bash

cd service/starcircleplus/
mvn clean #remove old version

#package as Jar and skip running MySQL connection tests that will end build
mvn clean package install -DskipTests

docker compose up -d --force-recreate --build  #recompose docker project

