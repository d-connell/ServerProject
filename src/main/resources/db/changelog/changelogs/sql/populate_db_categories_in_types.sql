/* UPDATE TYPES */
UPDATE types
SET category_id = (SELECT categories.id FROM categories WHERE categories.name = 'Covers')
WHERE name = "quilts";
UPDATE types
SET category_id = (SELECT categories.id FROM categories WHERE categories.name = 'Accessories')
WHERE name = "bags";