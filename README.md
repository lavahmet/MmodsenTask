
## Deployment

To deploy this project run

**IntelliJ IDEA:** 
1. Chenage the values in  "application-local.properties" to your own values.
```java
jdbc_url=jdbc:postgresql://localhost:5432/modsen_task
jdbc_username=postgres
jdbc_password=postgres
hibernate_dialect=org.hibernate.dialect.PostgreSQLDialect
jdbc_driverCLassName=org.postgresql.Driver
hibernate_show_sql=true
hibernate_hbm2ddl_auto=validate


spring.flyway.url=jdbc:postgresql://localhost:5432/modsen_task
spring.flyway.user=postgres
spring.flyway.password=postgres
```
2. Chenage the property in @PropertySource to the "application-local.properties" and run the project.

**Docker:** 
1. Chenage the values in  "application-local.properties" to your own values.

```java
#Conatiner configuration
jdbc_url=jdbc:postgresql://db:5432/modsen_task
jdbc_username=postgres
jdbc_password=postgres
jdbc_driverCLassName=org.postgresql.Driver
hibernate_dialect=org.hibernate.dialect.PostgreSQLDialect
hibernate_show_sql=true
hibernate_hbm2ddl_auto=validate

spring.flyway.url=jdbc:postgresql://db:5432/modsen_task
spring.flyway.user=postgres
spring.flyway.password=postgres

```

2. Chenage the property in @PropertySource to the "application-container.properties" and run ```docker-compose up```

## Tech Stack

 - [Java 17](https://openjdk.org/projects/jdk/17/)
 - [Gradle 7.6](https://docs.gradle.org/7.6/userguide/userguide.html)
 - [Sprin-boot 3.0](https://spring.io/projects/spring-boot)
 - [Hibernate](https://hibernate.org/orm/documentation/6.1/)
 - [Postgresql](https://www.postgresql.org/)
 - [Dokcer](https://docs.docker.com/)
 - [Flyway](https://flywaydb.org/documentation/)
 
## API Reference

#### Get all events

```http
  GET /events
```
Returns all events

#### Get all events sorting by theme

```http
  GET /events/theme
```
Returns all events, sorted by theme

#### Get all events sorting by organizer

```http
  GET /events/organizer
```
Returns all events, sorted by organizer

#### Get all events sorting by time

```http
  GET /events/time
```
Returns all events, sorted by time

#### Get all events sorting by theme, organizer, time

```http
  GET /events/filter
```
Returns all events, sorted by theme, organizer, time

#### Get item
```http
  GET /events/${id}
```
Returns event with 'id'

#### Create event
```http
  POST /events/create
  
  {
    "theme" : "event1",
    "description" : "event1",
    "organizer" : "event1",
    "date" : "2023-03-13",
    "time" : "12:30",
    "place" : "event1"
  }
```
Takes parameters: theme, description, organizer, date, time, place in Json format and saves it in the database.

#### Update event
```http
  PUT /events/update/${id}

  {
    "theme" : "event2",
    "description" : "event2",
    "organizer" : "event2",
    "date" : "2023-03-13",
    "time" : "12:30",
    "place" : "event2"
  }
```
Takes parameters: theme, description, organizer, date, time, place in Json format and updates event with 'id' it in the database.


#### Delete event
```http
  GET /events/delete/${id}
```
Deletes event with 'id'
