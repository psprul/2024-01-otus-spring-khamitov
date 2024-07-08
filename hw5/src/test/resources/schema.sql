CREATE TABLE IF NOT EXISTS authors (
    id bigserial,
    author_name varchar(255),
    primary key (id)
);

CREATE TABLE IF NOT EXISTS genres (
    id bigserial,
    name varchar(255),
    primary key (id)
);

create table if not exists books (
    id bigserial,
    name varchar(255),
    author_id bigint references authors(id) on delete cascade,
    genre_id bigint references genres(id) on delete cascade,
    primary key (id)
);