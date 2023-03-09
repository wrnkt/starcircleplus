#! /bin/zsh

cd service/starcircleplus/
mvn clean #remove old version

#package as Jar and skip running MySQL connection tests that will end build
mvn install -DskipTests

docker compose up -d #recompose docker project

