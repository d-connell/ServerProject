/* USERS */
INSERT INTO users
VALUES (DEFAULT, 'Alan', '$2a$10$Ko/FAtEZs9IrDl2DZYxHwuTPesDchwlzdD.51dDEVUjj0BfHfTyG2', '1');

/* USERS_ROLES */
INSERT INTO users_roles
VALUES ((SELECT users.id FROM users WHERE users.username = 'Alan'), (SELECT roles.id FROM roles WHERE roles.name = 'ADMIN'));