DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 1000;

INSERT INTO users(name, email, password) VALUES
  ('user', 'user@gmail.com', 'userpassword'),
  ('admin', 'admin@gmail.com', 'adminpassword');

INSERT INTO user_roles(role, user_id) VALUES
  ('ROLE_USER', 1000),
  ('ROLE_ADMIN', 1001);

INSERT INTO restaurants(name) VALUES
  ('Hard Rock Cafe'),
  ('Pizzamania'),
  ('Burger King');

INSERT INTO dishes(name, restaurant_id, price) VALUES
  ('Hard Rock Burger', 1002, 1000),
  ('Hard Rock Pasta', 1002, 700),
  ('Pizza Romeo', 1003, 800),
  ('Pizza Pepperoni', 1003, 600),
  ('Big King', 1004, 500),
  ('French Fries', 1004, 300);

INSERT INTO votes(user_id, restaurant_id, date_time) VALUES
  (1000, 1002, '2018-05-03 10:00:00'),
  (1001, 1002, '2018-05-03 09:00:00');

