# Restaurant Selection Api

RESTful web-service  REST API с системой голосования на стеке: Maven, Spring Boot, Spring Security, REST
(Jackson), JDK8/17, Stream API, H2DB (in memory), JPA (Hibernate), JUnit5, Swagger (API).
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
* Make sure you are using JDK 1.8 and Maven 3.x
* Clone this repository
```bash
$ git clone https://github.com/T-W-O-SJ/restaraunt_selection.git
```

* Run RestaurantSelectionRestApplication using the IDE

* You can connect to H2 database in the IDE to see changes with parameters :
```java
jdbc:h2:tcp://localhost:9092/mem:selection
```
## Usage
### REST API documentation
The application supports OpenAPI 3.0 Swagger UI.  
After starting APP you need to go to url :
http://localhost:8080/swagger-ui/index.html#/

####Test credentials

* <b>User - </b> user@yandex.ru / password
* <b>Admin - </b>  admin@gmail.com / admin

###Test Curls
* GET All Restaurants:
```bash
  curl -s http://localhost:8080/api/profile/restaurants --user user@yandex.ru:password
```
* GET All Restaurants with menu for today:
```bash
  curl -s http://localhost:8080/api/profile/restaurants/with_dishes --user user@yandex.ru:password
```
* GET  menu for today for Pushkin Restaurant(id 1):
```bash
  curl -s http://localhost:8080/api/profile/dishes/1 --user user@yandex.ru:password
```
* DELETE dish with id 2:
```bash
  curl -s -X DELETE http://localhost:8080/api/admin/dishes/1/2 --user admin@gmail.com:admin
```

* CREATE a new Dish for restaurant with id 3:
```bash
  curl -s -x POST  -d 
  '{"description": "Пицца Маргарита","price": 350, "localDate": "2022-01-10"}'
  -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/admin/dishes/3 --user admin@gmail.com:admin
```
* CREATE Vote for restaurant with id 2:
```bash
  curl -s -x POST http://localhost:8080/api/profile/votes/2 --user admin@gmail.com:admin
```
* GET all User Votes:
```bash
  curl -s -x http://localhost:8080/api/profile/votes/filter --user user@yandex.ru:password
```

