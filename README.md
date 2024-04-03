# Spring Boot with Cassandra

### Download template project code from Spring Initializer
1. Go to [spring initializr](https://start.spring.io/)
2. Choose **Maven**, **Java**, **3.2.4** (by default), **Jar**, and **17**
3. Go to Dependencies, add **Spring Web**, **Spring Data for Apache Cassandra**
4. Click Generate, unzip and then the load the project from Intellij
5. Make sure Docker is running, create a container 
```
docker run -p 9042:9042 --rm --name CONTAINER_NAME -d cassandra:4.0.7
```
6. Go to Cassandra query language shell inside the Cassandra container
```
docker exec -it CONTAINER_NAME bash -c "cqlsh -u cassandra -p cassandra"
```
7. Create a keyspace for the application
```
CREATE KEYSPACE DATABASE_NAME WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
```
8. Add the following properties in your **application.properties**
```
spring.cassandra.schema-action=CREATE_IF_NOT_EXISTS
spring.cassandra.request.timeout=10s
spring.cassandra.connection.connect-timeout=10s
spring.cassandra.connection.init-query-timeout=10s

spring.cassandra.local-datacenter=datacenter1
spring.cassandra.keyspace-name=DATABASE_NAME
```

### Create a table in Cassandra
1. Use `@Table` to map a POJO to table in Cassandra
2. Use `@PrimaryKey` to denote primary key
3. Use following cmd to check table is created in Cassandra database running in container
```
docker exec -it CONTAINER_NAME bash -c "cqlsh -u cassandra -p cassandra"

describe keyspaces;
use KEYSPACE_CREATED_EARLIER;
describe tables;
```

### Create Repository that extends from CassandraRepository<T, ID>
This allows for basic CRUD operations
```
public interface PlayerRepository extends CassandraRepository<T, ID> {
}
```

### Create Service layer that has Repository layer injected to access data

### Create Controller layer that has Service layer injected to handle APIs

### Create different API handlers on top of these
The above is assumed to run from Intellij. Other option is to use Docker

### Dockerize the Spring boot app (Need to resolve issue running two containers as Cassandra is being used in container)
1. cd into the project directory, build the jar file by running `./mvnw install` with Maven (make sure java version is the same as the project code)
2. Create a `Dockerfile` with following cmd
```
FROM openjdk:17-oracle
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
3. Build an image using following Docker cmd
```
docker build -t CONTAINER_IMAGE_NAME .
```
4. Check the built image under Local Images in Docker Desktop
5. Run the image in a container using following cmd
```
docker run -p 8080:8080 CONTAINER_IMAGE_NAME
```
Reference: [Spring Boot Docker](https://spring.io/guides/topicals/spring-boot-docker)