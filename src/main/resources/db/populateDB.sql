DELETE
FROM user_roles;
DELETE
FROM restaurants;
DELETE
FROM users;
DELETE
FROM dishes;
DELETE
FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('User1', 'user1@yandex.ru', 'password2'),
       ('User2', 'user2@yandex.ru', 'password3');


INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100001),
       ('USER', 100002),
       ('USER', 100003);

INSERT INTO restaurants (name, email, phone)
VALUES ('Pushkin', 'pushkin@yandex.ru', '8917111222'),
       ('Baikal', 'baikal@yandex.ru', '8917111223'),
       ('DodoPizza', 'dodo@gmail.com', '8917111241');

INSERT INTO dishes (local_date, price, description, restaurant_id)
VALUES ('2020-01-30', 1000, 'Завтрак', 100004),
       ('2020-01-30', 2000, 'Обед', 100004),
       ('2020-01-30', 3000, 'Ужин', 100004),
       ('2020-01-31', 200, 'Еда на граничное значение', 100005),
       ('2020-01-31', 300, 'Завтрак', 100005),
       ('2020-01-31', 400, 'Обед', 100005),
       (NOW(), 20, 'Ужин', 100006),
       (NOW(), 30, 'Админ ланч', 100006),
       (NOW(), 40, 'Админ ужин', 100006);

INSERT INTO votes (user_id, restaurant_id, local_date)
VALUES (100000, 100004, now()),
       (100003, 100004, now()),
       (100002, 100005, now());