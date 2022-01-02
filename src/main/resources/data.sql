-- DELETE
-- FROM user_roles;
-- DELETE
-- FROM restaurants;
-- DELETE
-- FROM users;
-- DELETE
-- FROM dishes;
-- DELETE
-- FROM votes;
-- ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User1', 'user1@yandex.ru', '{noop}password2'),
       ('User2', 'user2@yandex.ru', '{noop}password3');


INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 2),
       ('USER', 3),
       ('USER', 4);

INSERT INTO restaurants (name, email, phone)
VALUES ('Pushkin', 'pushkin@yandex.ru', '8917111222'),
       ('Baikal', 'baikal@yandex.ru', '8917111223'),
       ('DodoPizza', 'dodo@gmail.com', '8917111241');

INSERT INTO dishes (local_date, price, description, restaurant_id)
VALUES ('2020-01-30', 1000, 'Завтрак',1),
       ('2020-01-30', 2000, 'Обед',1),
       ('2020-01-30', 200, 'Ужин', 1),
       ('2020-01-31', 200, 'Еда на граничное значение', 2),
       ('2020-01-31', 300, 'Завтрак', 2),
       ('2020-01-31', 400, 'Обед', 2),
       (TODAY, 200, 'Ужин',3),
       (TODAY, 300, 'Админ ланч',3),
       (TODAY, 400, 'Админ ужин', 3);

INSERT INTO votes (user_id, restaurant_id, local_date)
VALUES (1, 1, TODAY),
       (2, 1, TODAY),
       (3, 3, TODAY);