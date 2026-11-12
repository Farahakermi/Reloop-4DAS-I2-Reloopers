
CREATE TABLE rewards (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(150) NOT NULL,
                         description TEXT,
                         category VARCHAR(80) NOT NULL,
                         points_cost BIGINT NOT NULL,
                         stock_quantity INTEGER NOT NULL,
                         image_url VARCHAR(500),
                         reward_type VARCHAR(40) NOT NULL,
                         active BOOLEAN NOT NULL DEFAULT TRUE,
                         expiration_date TIMESTAMP NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT ck_rewards_points_cost_positive
                             CHECK (points_cost > 0),

                         CONSTRAINT ck_rewards_stock_non_negative
                             CHECK (stock_quantity >= 0),

                         CONSTRAINT ck_rewards_type
                             CHECK (reward_type IN (
                                                    'DISCOUNT',
                                                    'PRODUCT',
                                                    'EXPERIENCE',
                                                    'SERVICE',
                                                    'ENVIRONMENTAL_CONTRIBUTION'
                                 ))
);

CREATE INDEX idx_rewards_active_category
    ON rewards (active, category);
