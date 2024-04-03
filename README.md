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