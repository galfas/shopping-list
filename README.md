# This project is a shopping list service, 
 
### Tecnology
This project is built in java 8 with spring boot and the following dependencies 
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

### IDE
Run the main class(Application.java) from any IDE.

Don't forget to run with dev profile if you want to use a local mongo or set the environment variables described in the previous step

### Health check and system information

In order to monitor the application and make sure it is working or at least what is not working, I created endpoints the
following endpoints:

***{applicationPort}/status***
    It answer ok in case the application is running.
    
***{managementPort}/health***
    It describe the system dependencies and its status, so far we are monitoring the following items:
    - Mongo
    - Disk space
    
***{managementPort}/info***
    It shows the version of the code that is running.
    
***{managementPort}/health***
 We also can find more information about the application on: 
    

### Trying the API

To Create a new Shopping list
 POST to localhost:8080/shopping/list
 
 payload 
    ```
    {
    		"owner": "1234",
    		"users": ["1111", "2222"],
    		"listItemList": [
    			{
    				"item": {
    					"name": "name",
    					"description": "description1"
    				},
    				"quantity": 10,
    				"version": 1,
    				"isChecked": "false"
    			},
    			{
    				"item": {	
    					"name": "name name",
    					"description": "description2"
    				},
    				"quantity": 20,
    				"version": 1,
    				"isChecked": "true"
    			}
    		]
    	}
    ```
    
  It will return an ID, this id should be use to retrieve the list
  
  ```
  localhost:8080/shopping/list/{listId}
  ```
    
##TODO
- The strategy to handle concurrency will be at start an optimistic lock, I picked redis at first but I want to test it direct in Mongo,
 I believe I can have the same result using only Mongo. So I want to investigate it before.

- Externalize application configuration with environment variable i.e. port and application context  

- Insert a live documentation i.e. Swagger

## Diagram

### Solution design

![Solution](diagram/design.png?raw=true "Solution design")


***I inserted Redis as a cache in the design, but I would test the application's performance before make optimizations.***
 
### Sequence diagrams

![Retrieve list](diagram/retrieve-list.png?raw=true "Sequence diagram - retrieve list")

![Update list](diagram/update-list.png?raw=true "Sequence diagram - update list")

![Share list](diagram/share-list.png?raw=true "Sequence diagram - share list")


There are more Sequence diagrams missing, I started for the ones that I believe help me to explain some of my design decisions.