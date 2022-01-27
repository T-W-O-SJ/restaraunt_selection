# Restaurant Selection Api

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/69cb536992a44cc0b2107586e73cfd88)](https://app.codacy.com/gh/T-W-O-SJ/restaraunt_selection?utm_source=github.com&utm_medium=referral&utm_content=T-W-O-SJ/restaraunt_selection&utm_campaign=Badge_Grade_Settings)

RESTful web-service  REST API with the voting system on stack: Maven, Spring Boot, Spring Security, REST
(Jackson), JDK17, Stream API, H2DB (in memory), JPA (Hibernate), JUnit5, Swagger (API).
### Software requirements specification
The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we assume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides a new menu each day.

## Installation
* Make sure you are using JDK 17 and Maven 3.x
* Clone this repository
```bash
$ git clone https://github.com/T-W-O-SJ/restaraunt_selection.git
```

* Run RestaurantSelectionRestApplication using the IDE 
* Or you can run without IDE using for example :
```bash
$ mvn spring-boot:run
```
* You can connect to H2 database in the IDE to see changes with parameters :
```bash
jdbc:h2:tcp://localhost:9092/mem:selection
```
## Usage
### REST API documentation
The application supports OpenAPI 3.0 Swagger UI.  
After starting APP you need to go to url :
http://localhost:8080/swagger-ui/index.html#/

### Test credentials

* <b>User - </b> user@yandex.ru / password
* <b>Admin - </b>  admin@gmail.com / admin


