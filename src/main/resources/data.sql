-- Insert  data into account_user table
INSERT INTO account_user (username, password) VALUES
('kats', 'katspassword')
ON CONFLICT (username) DO NOTHING;

INSERT INTO account_user (username, password) VALUES
('tchico1er', 'tchico1erpassword')
ON CONFLICT (username) DO NOTHING;

-- Insert data into accounts table
INSERT INTO accounts (account_user_id, account_number, account_type, balance) VALUES
(1, '1234567890', 'Savings', 1000.00)
ON CONFLICT (account_number) DO NOTHING;

INSERT INTO accounts (account_user_id, account_number, account_type, balance) VALUES
(2, '0987654321', 'Checking', 500.00)
ON CONFLICT (account_number) DO NOTHING;

-- Insert data into transactions table
INSERT INTO transactions (from_account_id, to_account_id, amount, transaction_type, timestamp) VALUES
(1, 2, 100.00, 'TRANSFER', NOW())
ON CONFLICT DO NOTHING;

INSERT INTO transactions (from_account_id, to_account_id, amount, transaction_type, timestamp) VALUES
(2, NULL, 50.00, 'WITHDRAWAL', NOW())
ON CONFLICT DO NOTHING;
