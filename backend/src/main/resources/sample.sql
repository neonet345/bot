CREATE TABLE city
(
    id bigint NOT NULL,
    city character varying(255)  NOT NULL,
    description character varying(255)  NOT NULL,
    CONSTRAINT city_pkey PRIMARY KEY (id)
)