DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100001);

INSERT INTO restaurants (name, email, phone)
VALUES ('Pushkin', 'pushkin@yandex.ru', '8917111222'),
       ('Baikal', 'baikal@yandex.ru', '8917111223'),
       ('DodoPizza', 'dodo@gmail.com', '8917111241');

INSERT INTO dishes (date_time,price, description, restaurant_id)
VALUES ('2020-01-30 10:00:00',1000, 'Завтрак', 100002),
       ('2020-01-30 10:00:00',2000 ,'Обед',  100002),
       ('2020-01-30 10:00:00',3000, 'Ужин', 100002),
       ('2020-01-31 0:00:00',200,'Еда на граничное значение', 100003),
       ('2020-01-31 10:00:00',300, 'Завтрак', 100003),
       ('2020-01-31 13:00:00',400, 'Обед', 100003),
       (NOW(), 20,'Ужин', 100004),
       (NOW(), 30, 'Админ ланч',  100004),
       (NOW(),40, 'Админ ужин',  100004);