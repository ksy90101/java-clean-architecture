INSERT INTO accounts (id)
VALUES (1);
INSERT INTO accounts (id)
VALUES (2);

INSERT INTO activities (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1001, '2018-08-08 08:00:00.0', 1, 1, 2, 500);

INSERT INTO activities (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1002, '2018-08-08 08:00:00.0', 2, 1, 2, 500);

INSERT INTO activities (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1003, '2018-08-09 10:00:00.0', 1, 2, 1, 1000);

INSERT INTO activities (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1004, '2018-08-09 10:00:00.0', 2, 2, 1, 1000);

INSERT INTO activities (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1005, '2019-08-09 09:00:00.0', 1, 1, 2, 1000);

INSERT INTO activities (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1006, '2019-08-09 09:00:00.0', 2, 1, 2, 1000);

INSERT INTO activities (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1007, '2019-08-09 10:00:00.0', 1, 2, 1, 1000);

INSERT INTO activities (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1008, '2019-08-09 10:00:00.0', 2, 2, 1, 1000);
