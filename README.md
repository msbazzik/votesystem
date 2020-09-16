
Restaurant voting system
===============================

This is a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant, and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
1) If it is before 11:00 we assume that he changed his mind.
2) If it is after 11:00 then it is too late, vote can't be changed.

Each restaurant provides a new menu each day.

REST API
===============================

###curl samples
> For windows use `Git Bash`

###REST Admin API:

- #### get All Users
`curl -s http://localhost:8080/topjava/rest/admin/users --user admin@gmail.com:admin`

- #### get Users 100001
`curl -s -X GET http://localhost:8080/votesystem/admin/users/100001`

- #### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votesystem/rest/profile/register`

- #### get Profile
`curl -s http://localhost:8080/votesystem/rest/profile --user test@mail.ru:test-password`


- #### delete User
`curl -s -X DELETE http://localhost:8080/votesystem/admin/users/100001`

- #### update User


- #### get By Email
`curl -s -X GET http://localhost:8080/votesystem/admin/users/by?email=user1@yandex.ru`

- #### Enable/disable




###REST User API:

#### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json' http://localhost:8080/votesystem/profile/register`

- #### get own profile
`curl -s -X GET http://localhost:8080/votesystem/profile`

- #### delete own profile
`curl -s -X DELETE http://localhost:8080/votesystem/profile`

- #### update own profile
 `curl -s -X PUT http://localhost:8080/votesystem/profile' -H 'Content-Type: application/json' --data-raw '{"name":"UpdatedUser", "email":"userUpdated@gmail.com", "password":"updatedPass"}'`
 
 
 ###REST Restaurant API:
 
- #### create restaurant
 `curl -s -X POST http://localhost:8080/votesystem/restaurants -H 'Content-Type: application/json' --data-raw '{"name":"NewRestaurant"}`

- #### delete restaurant
 `curl -s -X DELETE http://localhost:8080/votesystem/restaurants/100004`
 
- #### update restaurant
`curl -s -X PUT http://localhost:8080/votesystem/restaurants/100005 -H 'Content-Type: application/json' --data-raw '{"name":"UpdatedRestaurant"}'`
 
- #### get restaurant
`curl -s -X GET http://localhost:8080/votesystem/restaurants/100005`
 
- #### get by date restaurant
`curl -s -X GET http://localhost:8080/votesystem/restaurants/100005?date=2020-08-20`
 
- #### get all with menu by date restaurant
`curl -s -X GET http://localhost:8080/votesystem/restaurants/dishes?date=2020-08-20`
 
- #### get all with votes by date restaurant
 
 
 
- #### create a dish for restaurant
`curl -s -X POST http://localhost:8080/votesystem/restaurants/100004/dishes -H 'Content-Type: application/json' --data-raw '{"name":"newMeal", "date":"2020-09-01","price":12 }'`
  
- #### update dish for restaurant
`curl -s -X PUT http://localhost:8080/votesystem/restaurants/100004/dishes/100007 -H 'Content-Type: application/json' --data-raw '{"name":"UpdatedMeal","date":"2020-09-01", "price":"123"}'`
    
- #### delete a dish for restaurant
`curl -s -X DELETE http://localhost:8080/votesystem/restaurants/100004/dishes/100007`

###REST Vote API:
 
- #### vote for a restaurant
`curl -s -X PUT http://localhost:8080/votesystem/votes?restaurantId=100004&date=2020-12-15`

- #### delete a vote
`curl -s -X DELETE http://localhost:8080/votesystem/votes/100017?date=2020-08-20`

- #### get a vote
`curl -s -X GET http://localhost:8080/votesystem/votes/100017`

- #### get all by date a vote
`curl -s -X GET http://localhost:8080/votesystem/votes/?date=2020-08-20`