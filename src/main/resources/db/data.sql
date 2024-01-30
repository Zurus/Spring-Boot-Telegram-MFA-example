INSERT INTO users (name, username, password) VALUES
    ('User', 'admin', '{noop}admin'),
    ('User', 'user', '{noop}user');

INSERT INTO users_roles (user_id, role) VALUES
    (1, 'ROLE_USER'),
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');

INSERT INTO SETTINGS(KEY, VALUE)
VALUES ('bot.name', 'SecureEnvBot'),
       ('bot.token', '5596926556:AAFHZqdaMFrDZ1Dm7fxw404RodHM3p01dzc'),
       ('test.key', 'test.value');