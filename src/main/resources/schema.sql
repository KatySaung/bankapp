-- Drop existing tables if they exist
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS account_user;

-- Create the account_user table
CREATE TABLE account_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Create the accounts table
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    account_user_id BIGINT NOT NULL,
    account_number BIGINT NOT NULL UNIQUE,
    account_type VARCHAR(50) NOT NULL,
    balance NUMERIC(15, 2) NOT NULL,
    CONSTRAINT fk_customer FOREIGN KEY (account_user_id) REFERENCES account_user(id)
);

-- Create the transactions table
CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    from_account_number BIGINT NOT NULL,
    to_account_number BIGINT,
    amount NUMERIC(15, 2) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    CONSTRAINT fk_from_account FOREIGN KEY (from_account_number) REFERENCES accounts(id),
    CONSTRAINT fk_to_account FOREIGN KEY (to_account_number) REFERENCES accounts(id)
);
