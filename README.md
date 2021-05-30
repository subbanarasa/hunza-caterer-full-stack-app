# hunza-caterer-full-stack-app
Caterer full stack app with spring-boot, Angular, Mongo, Redis and Kafka

Steps to run locally . 

================================================================
Mongo, Redis and Kafka should run before testing it locally
================================================================
1. Make sure docker desktop is running locally. 
2. #docker-compose -f caterer-service/docker-compose.yml
3. #docker-compose -f caterer-service/docker-compose-kafka.yml
================================================================

================================================================
Run spring boot application
================================================================
mvn clean package -DskipTests
mvn spring-boot:run

================================================================
Run angular application
================================================================
npm run start


Swagger URL: 
=================================================================
http://localhost:8080/swagger-ui/#/caterer-controller

=================================================================
URL's
=================================================================

http://localhost:8080/api/caterers 

http://localhost:8080/api/caterers/{id}

http://localhost:8080/api/caterers/name/{name}

http://localhost:8080/api/caterers/location/{cityName}
 
=================================================================== 