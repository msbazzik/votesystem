DELETE
FROM user_roles;
DELETE
FROM votes;
DELETE
FROM meals;
DELETE
FROM users;
DELETE
FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@yandex.ru', 'password1'),
       ('User2', 'user2@yandex.ru', 'password2'),
       ('User3', 'user3@yandex.ru', 'password3'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('USER', 100002),
       ('ADMIN', 100003),
       ('USER', 100003);

INSERT INTO restaurants (name)
VALUES ('restaurant1'),
       ('restaurant2'),
       ('restaurant3');

INSERT INTO meals (name, date, price, restaurant_id)
VALUES ('meal1_1', '2020-08-20', 50, 100004),
       ('meal2_1', '2020-08-20', 15, 100004),
       ('meal3_1', '2020-08-20', 45, 100004),

       ('meal1_2', '2020-08-20', 19, 100005),
       ('meal2_2', '2020-08-20', 200, 100005),

       ('meal1_3', '2020-08-20', 70, 100006),

       ('meal1_1', '2020-08-21', 55, 100004),

       ('meal1_2', '2020-08-21', 35, 100005),

       ('meal1_3', '2020-08-21', 42, 100006),
       ('meal2_3', '2020-08-21', 4, 100006);

INSERT INTO votes(user_id, date, restaurant_id)
VALUES (100000, '2020-08-20', 100004),
       (100000, '2020-08-21', 100006),
       (100001, '2020-08-20', 100006),
       (100002, '2020-08-20', 100005),
       (100002, '2020-08-21', 100005),
       (100003, '2020-08-20', 100004);