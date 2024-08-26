-- Create schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS banking;
SET search_path = banking, public;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS banking.transactions;
DROP TABLE IF EXISTS banking.accounts;
DROP TABLE IF EXISTS banking.account_user;

-- Create the account_user table without the password field
CREATE TABLE banking.account_user (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE
);

-- Create the accounts table
CREATE TABLE banking.accounts (
    id SERIAL PRIMARY KEY,
    account_user_id BIGINT NOT NULL,
    account_number INTEGER NOT NULL UNIQUE,
    account_type VARCHAR(50) NOT NULL,
    balance NUMERIC(15, 2) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (account_user_id)
    REFERENCES banking.account_user(user_id)
);

-- Create the transactions table
CREATE TABLE banking.transactions (
    id SERIAL PRIMARY KEY,
    from_account_number INTEGER NOT NULL,
    to_account_number INTEGER,
    amount NUMERIC(15, 2) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    CONSTRAINT fk_from_account FOREIGN KEY (from_account_number)
    REFERENCES banking.accounts(account_number),
    CONSTRAINT fk_to_account FOREIGN KEY (to_account_number)
    REFERENCES banking.accounts(account_number)
);
