DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS settings;
DROP
    SEQUENCE IF EXISTS users_seq;

create
    sequence users_seq;

CREATE TABLE users
(
    id               BIGINT       NOT NULL DEFAULT nextval('users_seq') PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    username         VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    telegram_chat_id BIGINT,
    UNIQUE (username),
    UNIQUE (telegram_chat_id)
);

CREATE TABLE users_roles
(
    id      BIGINT  NOT NULL DEFAULT nextval('users_seq') PRIMARY KEY,
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    UNIQUE (user_id, role)
);

CREATE TABLE settings
(
    id    SERIAL       NOT NULL,
    key   VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    primary key (id)
);
