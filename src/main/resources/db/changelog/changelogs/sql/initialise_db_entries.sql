/* MAKERS */
INSERT INTO makers
VALUES (DEFAULT, 'Dawn');
INSERT INTO makers
VALUES (DEFAULT, 'Alexis');

/* ENTITY TYPES */
INSERT INTO types
VALUES (DEFAULT, 'bags', '/images/Bag.png');
INSERT INTO types
VALUES (DEFAULT, 'quilts', '/images/Quilt.png');

/* COVER SIZES */
INSERT INTO cover_sizes
VALUES (DEFAULT, 'COT', '(width: 71cm, height: 132cm)');
INSERT INTO cover_sizes
VALUES (DEFAULT, 'LAP', '(width: 90cm, height: 90cm)');
INSERT INTO cover_sizes
VALUES (DEFAULT, 'SINGLE', '(width: 92cm, height: 188cm)');
INSERT INTO cover_sizes
VALUES (DEFAULT, 'DOUBLE', '(width: 137cm, height: 188cm)');
INSERT INTO cover_sizes
VALUES (DEFAULT, 'QUEEN', '(width: 153cm, height: 203cm)');
INSERT INTO cover_sizes
VALUES (DEFAULT, 'KING', '(width: 183cm, height: 203cm)');

/* QUILTS */
INSERT INTO quilts
VALUES (DEFAULT, 'Pinwheels', 200.00,
        (SELECT types.id FROM types WHERE types.name = 'quilts'),
        (SELECT cover_sizes.id FROM cover_sizes WHERE cover_sizes.size = 'COT'),
        (SELECT makers.id FROM makers WHERE makers.name = 'Dawn'),
        '/images/Pinwheels.png');
INSERT INTO quilts
VALUES (DEFAULT, 'Friendship', 600.00,
        (SELECT types.id FROM types WHERE types.name = 'quilts'),
        (SELECT cover_sizes.id FROM cover_sizes WHERE cover_sizes.size = 'DOUBLE'),
        (SELECT makers.id FROM makers WHERE makers.name = 'Alexis'),
        '/images/Friendship.png');

/* BAG SIZES */
INSERT INTO bag_sizes
VALUES (DEFAULT, 'SMALL', '(width: 40cm, height: 40cm, depth: 10cm)');
INSERT INTO bag_sizes
VALUES (DEFAULT, 'SHOPPER', '(width: 23cm, height: 23cm, depth: 2cm)');

/* BAGS */
INSERT INTO bags
VALUES (DEFAULT, 'Purple Shopper', 10.00,
        (SELECT types.id FROM types WHERE types.name = 'bags'),
        (SELECT bag_sizes.id FROM bag_sizes WHERE bag_sizes.size = 'SHOPPER'),
        (SELECT makers.id FROM makers WHERE makers.name = 'Dawn'),
        '/images/Purple Shopper.png');
INSERT INTO bags
VALUES (DEFAULT, 'Croatia', 20.00,
        (SELECT types.id FROM types WHERE types.name = 'bags'),
        (SELECT bag_sizes.id FROM bag_sizes WHERE bag_sizes.size = 'SMALL'),
        (SELECT makers.id FROM makers WHERE makers.name = 'Alexis'),
        '/images/Croatia.png');