-- Create schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS banking;
SET search_path = banking, public;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS bank_info;

-- Create the customers table with both full_name and username columns
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE  -- Ensure this column exists and is defined correctly
);

-- Create the accounts table
CREATE TABLE accounts (
    account_id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    account_number INTEGER NOT NULL UNIQUE,
    routing_number INTEGER NOT NULL,
    account_name VARCHAR(50) NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    balance NUMERIC(15, 2) NOT NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id)
    REFERENCES customers(customer_id)
);

-- Create the transactions table
CREATE TABLE transactions (
    transaction_id SERIAL PRIMARY KEY,
    from_account_number INTEGER,  -- Now allows NULL
    to_account_number INTEGER,
    amount NUMERIC(15, 2) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    transaction_timestamp TIMESTAMP NOT NULL,
    CONSTRAINT fk_from_account FOREIGN KEY (from_account_number) REFERENCES accounts(account_number),
    CONSTRAINT fk_to_account FOREIGN KEY (to_account_number) REFERENCES accounts(account_number)
);

CREATE TABLE banking.bank_info (
    id SERIAL PRIMARY KEY,
    sort_code VARCHAR(20) NOT NULL
);
