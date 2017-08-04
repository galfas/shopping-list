# fetch basic image
FROM openjdk:8u131-jdk-slim

# application placed into /opt/app
RUN mkdir -p /opt/app
WORKDIR /opt/app

# rest of the project
COPY . /opt/app/
RUN ./gradlew build

# local application port
EXPOSE 8080:8080

RUN ./gradlew run
# execute it
CMD ["java", "-jar", "build/libs/shopping-list-service-0.0.1.jar"]