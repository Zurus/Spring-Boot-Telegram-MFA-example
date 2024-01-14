DROP TABLE users_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE users_seq IF EXISTS;

create sequence users_seq;

CREATE TABLE users
(
    id                      BIGINT DEFAULT users_seq.nextval PRIMARY KEY,
    name                    VARCHAR(255)            NOT NULL,
    username                VARCHAR(255)            NOT NULL,
    password                VARCHAR(255)            NOT NULL,
    telegram_chat_id        BIGINT,
    UNIQUE (username),
    UNIQUE (telegram_chat_id)
);

CREATE TABLE users_roles
(
    id               BIGINT DEFAULT users_seq.nextval PRIMARY KEY,
    user_id          INTEGER                 NOT NULL,
    role             VARCHAR                 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    UNIQUE (user_id, role)
);

CREATE TABLE settings
(
    id    SERIAL       NOT NULL,
    key VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    primary key (id)
);

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
