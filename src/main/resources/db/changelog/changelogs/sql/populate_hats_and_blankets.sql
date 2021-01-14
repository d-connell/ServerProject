/* ENTITY TYPES */
INSERT INTO types
VALUES (DEFAULT, 'hats', '/images/Hat.png',
        (SELECT categories.id FROM categories WHERE categories.name = 'Accessories'));
INSERT INTO types
VALUES (DEFAULT, 'blankets', '/images/Blanket.png',
        (SELECT categories.id FROM categories WHERE categories.name = 'Covers'));

/* HAT SIZES */
INSERT INTO hat_sizes
VALUES (DEFAULT, 'SMALL', '(circumference: 54cm)');
INSERT INTO hat_sizes
VALUES (DEFAULT, 'MEDIUM', '(circumference: 57cm)');
INSERT INTO hat_sizes
VALUES (DEFAULT, 'LARGE', '(circumference: 59cm)');

/* HATS */
INSERT INTO hats
VALUES (DEFAULT, 'Summer Hat', 30.00,
        (SELECT types.id FROM types WHERE types.name = 'hats'),
        (SELECT hat_sizes.id FROM hat_sizes WHERE hat_sizes.size = 'SMALL'),
        (SELECT makers.id FROM makers WHERE makers.name = 'Dawn'),
        '/images/Summer Hat.png');
INSERT INTO hats
VALUES (DEFAULT, 'Straw Hat', 20.00,
        (SELECT types.id FROM types WHERE types.name = 'hats'),
        (SELECT hat_sizes.id FROM hat_sizes WHERE hat_sizes.size = 'LARGE'),
        (SELECT makers.id FROM makers WHERE makers.name = 'Alexis'),
        '/images/Straw Hat.png');

/* BLANKETS */
INSERT INTO blankets
VALUES (DEFAULT, 'Wedding Cables', 800.00,
        (SELECT types.id FROM types WHERE types.name = 'blankets'),
        (SELECT cover_sizes.id FROM cover_sizes WHERE cover_sizes.size = 'SINGLE'),
        (SELECT makers.id FROM makers WHERE makers.name = 'Alexis'),
        '/images/Wedding Cables.png');
INSERT INTO blankets
VALUES (DEFAULT, 'Ocean Blues', 500.00,
        (SELECT types.id FROM types WHERE types.name = 'blankets'),
        (SELECT cover_sizes.id FROM cover_sizes WHERE cover_sizes.size = 'LAP'),
        (SELECT makers.id FROM makers WHERE makers.name = 'Alexis'),
        '/images/Ocean Blues.png');