DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM dishes;
DELETE
FROM restaurants;
DELETE
FROM votes;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$nkIkAuiTPDwI1apyvvkALuGjLzlH4z6drw.P4.kRTzWnV7c5eai9K'),
       ('User', 'user@yandex.ru', '$2a$10$d1nYOalJluNlt5K/BPUnlOKT5A3LZ7irNzR2..vuorxd3SXkOwsSm'),
       ('User1', 'user1@yandex.ru', '$2a$10$d1nYOalJluNlt5K/BPUnlOKT5A3LZ7irNzR2..vuorxd3SXkOwsSm');


INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_ADMIN', 100000),
       ('ROLE_USER', 100001),
       ('ROLE_USER', 100002);

INSERT INTO restaurants (name)
VALUES ('Petrovich'),
       ('Italianec'),
       ('Stolovaya'),
       ('Bufet'),
       ('Dietka'),
       ('Pelmennaya'),
       ('Kartoshka');

INSERT INTO dishes (name, price, restaurant_id)
VALUES ('Borsch', 100, 100006),
       ('Kotleta', 150, 100003),
       ('Scramble', 120, 100009),
       ('Fish', 130, 100004),
       ('Meat', 160, 100007),
       ('Cake', 140, 100008),
       ('Salad', 110, 100005),
       ('Noodles', 90, 100003),
       ('Shrimp', 140, 100004),
       ('Fish and Chips', 160, 100009),
       ('Beer', 130, 100007),
       ('Vine', 190, 100008),
       ('Apple juice', 100, 100006),
       ('Mango juice', 140, 100005),
       ('Pineapple juice', 120, 100004),
       ('Lacie', 170, 100005);
INSERT INTO votes (user_id, restaurant_id)
VALUES (100001, 100005),
       (100002, 100006);
