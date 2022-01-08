INSERT INTO account (id)
VALUES (1);
INSERT INTO account (id)
VALUES (2);

INSERT INTO activity (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (1, '2018-08-08 08:00:00.0', 1, 1, 2, 500);

INSERT INTO activity (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (2, '2018-08-08 08:00:00.0', 2, 1, 2, 500);

INSERT INTO activity (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (3, '2018-08-09 10:00:00.0', 1, 2, 1, 1000);

INSERT INTO activity (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (4, '2018-08-09 10:00:00.0', 2, 2, 1, 1000);

INSERT INTO activity (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (5, '2019-08-09 09:00:00.0', 1, 1, 2, 1000);

INSERT INTO activity (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (6, '2019-08-09 09:00:00.0', 2, 1, 2, 1000);

INSERT INTO activity (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (7, '2019-08-09 10:00:00.0', 1, 2, 1, 1000);

INSERT INTO activity (id, timestamp, owner_account_id, source_account_id, target_account_id, amount)
VALUES (8, '2019-08-09 10:00:00.0', 2, 2, 1, 1000);
