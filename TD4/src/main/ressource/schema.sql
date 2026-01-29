CREATE TYPE ingredient_category AS ENUM ('VIANDE', 'LEGUME', 'PRODUIT_LAITIER', 'EPICERIE');
CREATE TYPE movement_type AS ENUM ('IN', 'OUT');
CREATE TYPE unit_type AS ENUM ('KG', 'L', 'G', 'PIECE');
CREATE TYPE order_type AS ENUM ('EAT_IN', 'TAKE_AWAY');
CREATE TYPE order_status AS ENUM ('CREATED', 'READY', 'DELIVERED');

CREATE TABLE ingredient (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            price NUMERIC(10, 2),
                            category ingredient_category
);

CREATE TABLE stock_movement (
                                id SERIAL PRIMARY KEY,
                                id_ingredient INT REFERENCES ingredient(id),
                                quantity NUMERIC NOT NULL,
                                type movement_type NOT NULL,
                                unit unit_type NOT NULL,
                                creation_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "order" (
                         id SERIAL PRIMARY KEY,
                         reference VARCHAR(50) UNIQUE NOT NULL,
                         type order_type NOT NULL,
                         status order_status NOT NULL
);