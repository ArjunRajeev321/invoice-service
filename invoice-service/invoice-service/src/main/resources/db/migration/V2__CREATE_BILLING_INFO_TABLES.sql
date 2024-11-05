
CREATE TABLE test_schema.billing_line (
    id INT PRIMARY KEY,
    action_type VARCHAR(255) NOT NULL,
    product_type VARCHAR(255) NOT NULL,
    line_type VARCHAR(255) NOT NULL,
    total_price int NOT NULL,
    base_price int NOT NULL
);


