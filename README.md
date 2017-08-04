# This project is a shopping list service, 
 
### Tecnology
This project is built in java 8 with spring boot; 
- Mongodb as data repository and 
- Redis as distributed lock.
- RabbitMQ as message broker

The project comes ready with an instance of Gradle wrapper. 

## Building
To run the build for the project execute the following command from the project's root directory

```
./gradlew build
```

## Testing 
To run the tests for the project execute the following command from the project's root directory:

```
./gradlew clean test integrationTest testAcceptanceLocal -i
```
* `test` - executes unit tests
* `integrationTest` - executes spring-boot tests
* `testAcceptanceLocal` - executes cucumber tests 

## Running

### Docker 
 It is possible to launch the application as a container, in this case you need to have docker installed in your server,
  and then you just need to execute the next command in the project's root:
  
```
docker-compose up.
```
 

### Terminal 
This is a Spring Boot application, so it is packed as a jar. 
To start the app, execute:
```
java -jar <pathToJar>.jar

```
- after run the command "./gradlew build", you can find the jar on "/build/libs/statistc-service-0.0.1.jar" from the project's root

You need to set environment variables for the mongo host (SHOPPINGLIST_MONGO_1_PORT_27017_TCP_ADDR) and port (MONGO_PORT_27017_TCP_PORT).

## Used design Patterns: ##
1. Builder
2. Strategy
3. Singleton

## Next steps:
1. Insert documentation for the API e.g. swagger.
2. Improve the docker image.
3. Create the new endpoints

## Diagram

### Solution design

![Alt text](diagram/solution.png?raw=true "Sequence diagram - list access")

### Sequence diagrams

![Alt text](diagram/list-acess.png?raw=true "Sequence diagram - list access")

![Alt text](diagram/update-acess.png?raw=true "Sequence diagram - list access")

![Alt text](diagram/share-acess.png?raw=true "Sequence diagram - list access")
