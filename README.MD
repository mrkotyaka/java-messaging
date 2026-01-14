# Task 

## Tools and materials that will be useful for completing the task
### Tools:
- IntellijIDEA
- Kafka
- RabbitMQ
- RDBMS of your choice
- Docker
- HTTP client

### Materials:
- https://www.baeldung.com/rabbitmq
- https://www.baeldung.com/spring-kafka

## Task description
You need to create two microservices that will exchange data with each other using message brokers: RabbitMQ and Kafka. For example, the first service sends a message to the second service using Kafka, and the second service sends a response to this message to the first service using RabbitMQ. A REST request to the controller will be used as a trigger for sending a message. To deploy Kafka and RabbitMQ, you must use Docker and docker-compose.

### Instructions

1. Create a microservice – an API for submitting a loan application. The loan application must contain the loan amount, loan term, user income, current credit load, and current credit rating.
- Use RestController to receive and process requests from spring-boot-starter-web
- When receiving a loan application, the service must save this application in the database, send it to the application processing service using Kafka, and return the application ID in the database to the user 
- Additionally, a field must be created in the database to store the application status (in progress/approved/declined). When adding an application to the database, the default status value will be “in progress.”
- Add a second API to obtain the application status by application ID.
- Receive a response from the application processing service using RabbitMQ. 
- When processing the response from the application processing service, update its status in the database.
- Provide a migration mechanism for project deployment.
- Use separate classes for interaction (usually with the suffix Event in the name). There is no need to forward the DTO received from the user to another service.

2. Create a microservice that processes loan applications
- The microservice should receive loan applications using Kafka
- Make a decision whether to approve the loan or not (loan payments should not exceed 50% of income)
- Send information about the decision made using RabbitMQ

3. Add a docker-compose file to deploy the application

4. Add an http file containing requests for a loan application and obtaining the status of a loan application
