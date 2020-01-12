# SpringBootRestService
Graduation project Topjava
## Выпускной проект
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

## Используемые инструменты и технологии
* Maven
* SpringBoot
* Spring Fata JPA
* HSQLDB
* Hibernate
* Java 8

## Примеры cURL команд:
> For windows use `Git Bash`

**Admin**
#### GET all restaurants:
`curl -s http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:admin`
#### GET restaurant with id 100003:
`curl -s http://localhost:8080/rest/admin/restaurants/100003 --user admin@gmail.com:admin`
#### DELETE restaurant with id 100003:
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100003 --user admin@gmail.com:admin`
#### CREATE restaurant:
`curl -s -X POST -d '{"name":"Gerkules"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:admin`
#### UPDATE restaurant:
`curl -s -X PUT -d '{"id": 100007, "name": "Zevs"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/100007 --user admin@gmail.com:admin`
#### GET all dishes:
`curl -s http://localhost:8080/rest/admin/dishes --user admin@gmail.com:admin`
#### GET dish with id 100010:
`curl -s http://localhost:8080/rest/admin/dishes/100010 --user admin@gmail.com:admin`
#### DELETE dish with id 100009:
`curl -s -X DELETE http://localhost:8080/rest/admin/dishes/100009 --user admin@gmail.com:admin`
#### CREATE dish:
`curl -s -X POST -d '{"date":"2020-01-12", "name": "Octopus", "price": 200, "restaurant" : {"id": 100005, "name": "Bufet"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/dishes --user admin@gmail.com:admin`
#### UPDATE dish:
`curl -s -X PUT -d '{"id": 100013, "date":"2020-01-12", "name":"Fish", "price": 100, "restaurant" : {"id": 100005, "name": "Stolovaya"}}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/dishes/100013 --user admin@gmail.com:admin`

**User**
#### CREATE vote:
`curl -s -X POST -d ' {"restaurant_id": 100007"} ' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/vote/100007 --user user@yandex.ru:12345678`
`curl -s -X POST -d ' {"restaurant_id": 100004"} ' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/vote/100004 --user user1@yandex.ru:12345678`
#### GET vote for user on date:
`curl -s http://localhost:8080/rest/vote?date=2020-01-12 --user user@yandex.ru:12345678`
#### GET vote history for user with id 100001:
`curl -s http://localhost:8080/rest/vote/history --user user@yandex.ru:12345678`
`curl -s http://localhost:8080/rest/vote/history?endDate=2020-01-13 --user user@yandex.ru:12345678`
