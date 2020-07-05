#Build
mvn clean install

#Start mongo
mongod

#Run the App
mvn spring-boot:run

curl --header Content-Type: application/json --request POST --data '{ id : null, name : eric, password : asdf, roles : [ADMIN] }' http://localhost:8080/authentications

http://localhost:8080/api/rest-ruleresults