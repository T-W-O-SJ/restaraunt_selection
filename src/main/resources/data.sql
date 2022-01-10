INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User2', 'user2@yandex.ru', '{noop}password2'),
       ('User3', 'user3@yandex.ru', '{noop}password3');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4);

INSERT INTO RESTAURANT (name, email, description, phone)
VALUES ('Pushkin', 'pushkin@yandex.ru', 'элитный ресторан', '8917111222'),
       ('Baikal', 'baikal@yandex.ru', 'ресторан с хорошим видом', '8917111223'),
       ('DodoPizza', 'dodo@gmail.com', 'ресторан быстрого питания', '8917111241'),
       ('VeganOnly', 'vegi@yandex.ru', 'ресторан для веганов', '8914444423');

INSERT INTO DISH (local_date, price, description, restaurant_id)
VALUES ('2020-01-30', 1000, 'Мясо по строгановски', 1),
       ('2020-01-30', 2000, 'Торт мечта Зайки', 1),
       ('2020-01-30', 200, 'Кофе', 1),
       ('2020-01-31', 200, 'Тёплый салат', 2),
       ('2020-01-31', 300, 'Борщ', 2),
       (TODAY, 400, 'Мясо', 1),
       (TODAY, 200, 'Почки', 1),
       (TODAY, 200, 'Шашлык', 2),
       (TODAY, 300, 'Торт', 2),
       (TODAY, 400, 'Суп Харчо', 2),
       (TODAY, 200, 'Салат Цезарь', 3),
       (TODAY, 300, 'Пицца Милано', 3),
       (TODAY, 400, 'Пицца 3 сыра', 3);

INSERT INTO VOTE(user_id, restaurant_id, local_date)
VALUES (1, 1, '2020-01-31'),
       (2, 1, '2020-01-31'),
       (1, 1, TODAY),
       (3, 1, TODAY),
       (4, 3, TODAY);