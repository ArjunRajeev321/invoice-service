create schema if NOT EXISTS test_schema;


CREATE TABLE test_schema.payment_information (
    id INT PRIMARY KEY,
    payment_method VARCHAR(255) NOT NULL  
);

CREATE TABLE test_schema.billing_header (
    id INT PRIMARY KEY,
    billing_id VARCHAR(255) NOT NULL,
    document_type VARCHAR(255) NOT NULL,
    currency_code VARCHAR(255) NOT NULL,
    company_id VARCHAR(255) NOT NULL,
    payment_id INT,
    FOREIGN KEY (payment_id) REFERENCES payment_information(id)
);


