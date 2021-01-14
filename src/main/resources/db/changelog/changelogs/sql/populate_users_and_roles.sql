/* ROLES */
INSERT INTO roles
VALUES (DEFAULT, 'VIEWER');
INSERT INTO roles
VALUES (DEFAULT, 'MAKER');
INSERT INTO roles
VALUES (DEFAULT, 'ADMIN');

/* USERS */
INSERT INTO users
VALUES (DEFAULT, 'Dawn', '$2a$10$LiC0ugo8BzndblUPykVP5OBv46vP5c.hB3CeX1XHExuYMUODFDoyG', '1');
INSERT INTO users
VALUES (DEFAULT, 'Alexis', '$2a$10$KEOa4rERP1/uAjdY9G4LDOzQMjaq/l2Ag1ZHf1guS9GpGrfkrCtcO', '1');
INSERT INTO users
VALUES (DEFAULT, 'Andy', '$2a$10$LZEokkXQlzj3QSj617ccWeMR5qt79tYDARy0xmiQKzUCBMBO62p96', '1');

/* USERS_ROLES */
INSERT INTO users_roles
VALUES ((SELECT users.id FROM users WHERE users.username = 'Andy'), (SELECT roles.id FROM roles WHERE roles.name = 'VIEWER'));
INSERT INTO users_roles
VALUES ((SELECT users.id FROM users WHERE users.username = 'Alexis'), (SELECT roles.id FROM roles WHERE roles.name = 'MAKER'));
INSERT INTO users_roles
VALUES ((SELECT users.id FROM users WHERE users.username = 'Dawn'), (SELECT roles.id FROM roles WHERE roles.name = 'MAKER'));
INSERT INTO users_roles
VALUES ((SELECT users.id FROM users WHERE users.username = 'Dawn'), (SELECT roles.id FROM roles WHERE roles.name = 'ADMIN'));