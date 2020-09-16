
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
`curl -s -X GET http://localhost:8080/votesystem/admin/users --user admin@gmail.com:admin`

- #### get Users 100001
`curl -s -X GET http://localhost:8080/votesystem/admin/users/100001 --user admin@gmail.com:admin`

- #### create User
`curl -s -X POST http://localhost:8080/votesystem/admin/users/ -H 'Content-Type: application/json' --data-raw '{"name":"New user","email":"new@gmail.com","password":"newpass","enabled":"true","roles":["USER"]}' --user admin@gmail.com:admin`

- #### delete User
`curl -s -X DELETE http://localhost:8080/votesystem/admin/users/100002 --user admin@gmail.com:admin`

- #### update User
`curl -s -X PUT 'http://localhost:8080/votesystem/admin/users/100001' -H 'Content-Type: application/json' --data-raw '{"name":"New user","email":"user1@yandex","password":"nwpass","roles":["USER"]}' --user admin@gmail.com:admin`

- #### get By Email
`curl -s -X GET http://localhost:8080/votesystem/admin/users/by?email=user1@yandex.ru --user admin@gmail.com:admin`


###REST User API:

- #### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json' http://localhost:8080/votesystem/profile/register`

- #### get own profile
`curl -s -X GET http://localhost:8080/votesystem/profile --user user1@yandex.ru:password1`

- #### delete own profile
`curl -s -X DELETE http://localhost:8080/votesystem/profile --user user3@yandex.ru:password3`

- #### update own profile
 `curl -s -X PUT http://localhost:8080/votesystem/profile' -H 'Content-Type: application/json' --data-raw '{"name":"UpdatedUser", "email":"userUpdated@gmail.com", "password":"updatedPass"}' --user user1@yandex.ru:password1`
 
 
 ###REST Restaurant API:
 
- #### create restaurant
 `curl -s -X POST http://localhost:8080/votesystem/admin/restaurants -H 'Content-Type: application/json' --data-raw '{"name":"NewRestaurant"} --user admin@gmail.com:admin`

- #### delete restaurant
 `curl -s -X DELETE http://localhost:8080/admin/votesystem/restaurants/100006 --user admin@gmail.com:admin`
 
- #### update restaurant
`curl -s -X PUT http://localhost:8080/votesystem/admin/restaurants/100005 -H 'Content-Type: application/json' --data-raw '{"name":"UpdatedRestaurant"}'--user admin@gmail.com:admin`
 
- #### get restaurant
`curl -s -X GET http://localhost:8080/votesystem/admin/restaurants/100005 --user admin@gmail.com:admin`
 
- #### get by date restaurant
`curl -s -X GET http://localhost:8080/votesystem/admin/restaurants/100005?date=2020-08-20 --user admin@gmail.com:admin`
 
- #### get all with menu by date restaurant
`curl -s -X GET http://localhost:8080/votesystem/profile/restaurants/dishes?date=2020-08-20 --user user1@yandex.ru:password1`
 
- #### get all with votes by date restaurant
 `curl -s -X GET http://localhost:8080/votesystem/admin/restaurants/votes?date=2020-08-20 --user admin@gmail.com:admin`
 
 
- #### create a dish for restaurant
`curl -s -X POST http://localhost:8080/votesystem/admin/restaurants/100004/dishes -H 'Content-Type: application/json' --data-raw '{"name":"newMeal", "date":"2020-09-30","price":12 }' --user admin@gmail.com:admin`
  
- #### update dish for restaurant
`curl -s -X PUT http://localhost:8080/votesystem/admin/restaurants/100004/dishes/100007 -H 'Content-Type: application/json' --data-raw '{"name":"UpdatedMeal","date":"2020-09-01", "price":"123"}' --user admin@gmail.com:admin`
    
- #### delete a dish for restaurant
`curl -s -X DELETE http://localhost:8080/votesystem/admin/restaurants/100004/dishes/100007 --user admin@gmail.com:admin`

###REST Vote API:
 
- #### vote for a restaurant
`curl -s -X PUT http://localhost:8080/votesystem/profile/votes?restaurantId=100004&date=2020-09-30 --user user1@yandex.ru:password1`

- #### get a vote
`curl -s -X GET http://localhost:8080/votesystem/profile/votes/100017 --user user1@yandex.ru:password1`

- #### delete a vote
`curl -s -X DELETE http://localhost:8080/votesystem/profile/votes/100017?date=2020-08-20 --user user1@yandex.ru:password1`

- #### get all by date a vote
`curl -s -X GET http://localhost:8080/votesystem/admin/votes/?date=2020-08-20 --user admin@gmail.com:admin`