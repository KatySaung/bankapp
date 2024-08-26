INSERT INTO banking.account_user (username) VALUES
('kats')
ON CONFLICT (username) DO NOTHING;

INSERT INTO banking.account_user (username) VALUES
('tchico1er')
ON CONFLICT (username) DO NOTHING;

INSERT INTO banking.accounts (account_user_id, account_number, account_type, balance) VALUES
(1, 1234567890, 'Savings', 1000.00)
ON CONFLICT (account_number) DO NOTHING;

INSERT INTO banking.accounts (account_user_id, account_number, account_type, balance) VALUES
(2, 987654321, 'Checking', 500.00)
ON CONFLICT (account_number) DO NOTHING;

INSERT INTO banking.accounts (account_user_id, account_number, account_type, balance) VALUES
(1, 1233455678, 'Checking', 10543400.00)
ON CONFLICT (account_number) DO NOTHING;

INSERT INTO banking.accounts (account_user_id, account_number, account_type, balance) VALUES
(2, 1233450956, 'Savings', 45367500.00)
ON CONFLICT (account_number) DO NOTHING;

INSERT INTO banking.transactions (from_account_number, to_account_number, amount, transaction_type, timestamp) VALUES
(1233455678, 1233450956, 100.00, 'TRANSFER', NOW())
ON CONFLICT DO NOTHING;

INSERT INTO banking.transactions (from_account_number, to_account_number, amount, transaction_type, timestamp) VALUES
(987654321, NULL, 50.00, 'WITHDRAWAL', NOW())
ON CONFLICT DO NOTHING;
