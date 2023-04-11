INSERT INTO db_pizzeria.pizzas (created_at, description, name, price, updated_at) VALUES('2023-03-29 17:33:50', 'Un grande classico, intramontabile.', 'Margherita', 5.00, '2023-03-29 17:33:50');
INSERT INTO db_pizzeria.pizzas (created_at, description, name, price, updated_at) VALUES('2023-03-29 17:34:50', 'Semplice, ma ricca di gusto.', 'Marinara', 4.00, '2023-03-29 17:34:50');
INSERT INTO db_pizzeria.pizzas (created_at, description, name, price, updated_at) VALUES ('2023-03-29 17:34:50', 'Un classico... piccantino!', 'Diavola', 6.00, '2023-03-29 17:46:50');
INSERT INTO db_pizzeria.pizzas (created_at, description, name, price, updated_at) VALUES ('2023-03-30 11:33:50', 'Per veri intenditori, un mix gustoso e raffinato.', 'Anna\'s favorite', 7.00, '2023-03-30 11:46:50');
INSERT INTO db_pizzeria.pizzas (created_at, description, name, price, updated_at) VALUES ('2023-03-30 13:33:50', 'Perché accontentarsi quando si può avere tutto?', '4 Stagioni', 7.50, '2023-03-30 13:46:50');
INSERT INTO db_pizzeria.pizzas (created_at, description, name, price, updated_at) VALUES ('2023-03-30 11:33:50', 'La tradizione napoletana.', 'Napoli', 6.00, '2023-03-30 11:46:50');
INSERT INTO db_pizzeria.pizzas (created_at, description, name, price, updated_at) VALUES ('2023-03-30 11:33:50', 'La preferita dei veri amanti del formaggio.', '4 formaggi', 7.00, '2023-03-30 11:46:50');
INSERT INTO db_pizzeria.offers (end_date, start_date, title, pizza_id) VALUES ('2023-04-11', '2023-03-11', 'Classic', 1);
INSERT INTO db_pizzeria.offers (end_date, start_date, title, pizza_id) VALUES ('2023-04-18', '2023-03-18', 'Base', 1);
INSERT INTO db_pizzeria.offers (end_date, start_date, title, pizza_id) VALUES ('2023-04-28', '2023-03-28', 'Simple', 2);
INSERT INTO db_pizzeria.offers (end_date, start_date, title, pizza_id) VALUES ('2023-02-20', '2023-03-20', 'Rossa', 2);
INSERT INTO db_pizzeria.offers (end_date, start_date, title, pizza_id) VALUES ('2023-04-18', '2023-03-18', 'Ricca', 3);
INSERT INTO db_pizzeria.offers (end_date, start_date, title, pizza_id) VALUES ('2023-04-18', '2023-03-18', 'Bianca', 7);
INSERT INTO db_pizzeria.ingredients (name) VALUES ('pomodoro');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('mozzarella');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('basilico');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('salame piccante');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('aglio');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('acciughe');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('prosciutto crudo');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('gorgonzola');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('grana');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('fontina');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('carciofini');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('olive');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('funghi');
INSERT INTO db_pizzeria.ingredients (name) VALUES ('prosciutto cotto');
INSERT INTO db_pizzeria.pizza_ingredient (pizza_id, ingredient_id) VALUES (1, 1);
INSERT INTO db_pizzeria.pizza_ingredient (pizza_id, ingredient_id) VALUES (1, 2);
INSERT INTO db_pizzeria.pizza_ingredient (pizza_id, ingredient_id) VALUES (1, 3);
INSERT INTO db_pizzeria.users (email, first_name, last_name, password) VALUES('cri@email.com', 'Cristina', 'Lietti', '{noop}cri');
INSERT INTO db_pizzeria.users (email, first_name, last_name, password) VALUES('anna@email.com', 'Anna', 'Wulfson', '{noop}anna');
INSERT INTO db_pizzeria.roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO db_pizzeria.roles (id, name) VALUES(2, 'USER');
INSERT into db_pizzeria.users_roles (user_id, roles_id) VALUES(1, 1);
INSERT into db_pizzeria.users_roles (user_id, roles_id) VALUES(2, 2);