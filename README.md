# Transaction verifier

<hr/>

## Description
The project is meant to analyze financial transactions and determine possible financial frauds based on multiple criteria:
 - Transactions initiated by a repeating account in a short span of time
 - Transactions between accounts owned by related persons, as per person's last name

## Tech stack
    Spring Boot
    Spring JPA
    Apache Kafka
    PostgreSQL
    Docker

## Architecture
Architecture is based on microservices which communicate through Eureka server. 
Microservices available:
 - Transactions:8081 - handles persistence of entities in DB, logic for CRUD operations, exposes API for manipulating entities
 - Analytics:8082 - performs analysis on transaction data, exposes API for getting suspicious transactions
 - Discovery:8761 - Enables inter service discovery with Eureka server







