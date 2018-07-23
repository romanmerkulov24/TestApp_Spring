
CREATE TABLE customer
(
  id         SERIAL       NOT NULL
    CONSTRAINT customer_pkey
    PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL
    CONSTRAINT customer_email_key
    UNIQUE,
  password   VARCHAR(255) NOT NULL,
  phone      VARCHAR(13)  NOT NULL
);

CREATE TABLE field
(
  id       SERIAL       NOT NULL
    CONSTRAINT field_pkey
    PRIMARY KEY,
  label    VARCHAR(255) NOT NULL,
  type     VARCHAR(255) NOT NULL,
  required BOOLEAN      NOT NULL,
  active   BOOLEAN      NOT NULL
);

create  table option(
  id SERIAL PRIMARY KEY ,
  value VARCHAR(255) NOT NULL,
  field_id INTEGER NOT NULL,
  FOREIGN KEY (field_id) REFERENCES field(id)
)

create table response(
  id serial primary key,
  field_id integer,
  response_uuid varchar(255),
  received_on timestamp,
  value varchar(255) not null,
  foreign key (field_id) references field(id) on delete cascade
);