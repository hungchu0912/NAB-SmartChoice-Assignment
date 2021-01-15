# NAB-SmartChoice-Assignment
Demo for Smart choice backend services

# Getting Started
## Prerequisites
To make it easier for launch the project, i have set up database / queue / mock services that ready for use using docker and docker-compose. Therefore, docker and docker-compose is needed.

Also, backend services written in java and using maven build tool. So java 8 and maven 3 is required.

## Installing
Navigate to root folder NAB-SmartChoice-Assignment and run (This might take up a minute to start all the container)

>. docker-compose up

After all container started successfully, navigate to data-collector folder and start the service either by maven or your favourite idea.

Example running by maven:

>. cd data-collector

>. mvn clean install

>. java -jar target/data-collector-0.0.1-SNAPSHOT.jar

After data-collector start successfully, it will fetch the data from mock-service to database. Now start product-api and audit service (using same steps as data-collector above)

## Running the tests
#### CURL
Product API serve 2 basic API for searching and get detail of products.

Example:

>. curl --location --request GET 'localhost:8002/api/product?name=D%C3%A9p'

>. curl --location --request GET 'localhost:8002/api/product/2'

#### Unit Test
Unit test can run either by maven or your favourite idea

Example running by maven for data-collector:
>. cd data-collector

>. mvn clean test

## Architecture

![Screenshot](smart-choice-architecture.png)

For ease of developement, i'm using wiremock to set up mock service that acts as Tiki / Shopee / Lazada api (The response json is copied from real api)

### Data Collector
Service to collect data from publisher (Tiki / Shopee / Lazada). When start service, it will consume api from publisher, do some transformation and save it to mysql database.

This service using Spring Boot and Spring Batch framework.

### Product DB
Store detail of the product from publisher.

ER schema:
![Screenshot](er-schema.png)

- publisher table: store information of publisher

- publisher product mapping: Mainly used to store product id in publisher site

- publisher product detail: Store product detail

### Product API
Service to serve api for product

### Audit API
Service to store audit action

### Active MQ
Message queue to transfer audit event. Product API publish audit event and Audit API consume audit event.

### Limitation and Enhancement
The above architecture has some limitation and the following should be concerned to fix / investigate:

- Database: Since Data-Collector and Product-Api using same database, for now it wouldn't be a problem because Data-Collector is write side and Product-Api is read side. But seperate them would be a better approach and does not break Database per service pattern. We can consider either using database's replication feature - product api would use slave database for reading purpose, or seperate it using completely different database and synchronising between these database by kafka connect (or any other data synchronisation method)

- ActiveMQ: Consider to using kafka instead of Active MQ for storing audit events. As Kafka would be more fit in this case both in storing and scaling capability.

- Authentication and Authorization: Due to short time of the implementation, Authentication and Authorization cannot be done. The whole appliocation is assuming these services is behind an api gateway that take care of authentication and authorization.

- Data Collector: Horizontal scaling for this service should be put under consideration as for now it does not have any mechanism to seperate the collect job between scaling services. A better approach should be consider is seperate this service to 2 services: Collector-Generator and Data-Collector. The Collector-Generator would produce the job/task information and publish to Kafka, and Data-Collector would consume job/task from kafka and process those messages to collect data from publisher.
