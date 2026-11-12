
INSERT INTO rewards
(name, description, category, points_cost, stock_quantity,
 image_url, reward_type, active)
VALUES
    ('10% Eco Store Discount',
     'Discount voucher for partner eco-friendly shops',
     'Shopping', 200, 100, NULL, 'DISCOUNT', TRUE),

    ('Reusable Water Bottle',
     'Stainless steel bottle made from recycled material',
     'Shopping', 500, 40, NULL, 'PRODUCT', TRUE),

    ('Guided Recycling Plant Visit',
     'Visit a local recycling facility with a guide',
     'Experiences', 800, 15, NULL, 'EXPERIENCE', TRUE),

    ('Free Home Collection Pickup',
     'One free pickup of recyclable materials at home',
     'Services', 300, 50, NULL, 'SERVICE', TRUE),

    ('Plant a Tree',
     'Fund the planting of one tree in your name',
     'Environment', 400, 1000, NULL, 'ENVIRONMENTAL_CONTRIBUTION', TRUE);
