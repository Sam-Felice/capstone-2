BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account, transfers;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id;

-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);

CREATE TABLE transfers
(
	transfer_id		SERIAL NOT NULL PRIMARY KEY
	, from_account	int NOT NULL
	, to_account	int NOT NULL
	, transfer_amount decimal(13, 2) NOT NULL

	, CONSTRAINT fk_transfers_from_account FOREIGN KEY (from_account) REFERENCES account (account_id)
	, CONSTRAINT fk_transfers_to_account FOREIGN KEY (to_account) REFERENCES account (account_id)
);
INSERT into tenmo_user (username, password_hash)
VALUES ('Mitch','$2a$10$hZp2.P7/bdPuoTOFPGGn1OBZzPhBLrNSrEsV08KTXtuugFdZb0qgm')
,('Jeff', '$2a$10$0FajsoNCalTR/EA2n7bb/e.hDH.sYRgKAMHYMqrqwS1d6oItG89Hu')
,('Sam','$2a$10$iSEs8KgvkWr25WoJ1iyV7ub.qkx4SG4jV1FY/Hdnwjihk5gj7cXhy');

INSERT into account (user_id, balance)
VALUES ((SELECT user_id from tenmo_user where username = 'Mitch'), 1000)
,((SELECT user_id from tenmo_user where username = 'Jeff'), 1000)
,((SELECT user_id from tenmo_user where username = 'Sam'), 1000);

INSERT into transfers (from_account, to_account, transfer_amount)
VALUES (2001, 2002, 25.0);

-- ADD APPROVED AND PENDING BOOLEAN ROWS TO TRANSFERS TABLE

COMMIT;

BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account, transfers;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id;

-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);

CREATE TABLE transfers
(
	transfer_id		SERIAL NOT NULL PRIMARY KEY
	, from_account	int NOT NULL
	, to_account	int NOT NULL
	, transfer_amount decimal(13, 2) NOT NULL

	, CONSTRAINT fk_transfers_from_account FOREIGN KEY (from_account) REFERENCES account (account_id)
	, CONSTRAINT fk_transfers_to_account FOREIGN KEY (to_account) REFERENCES account (account_id)
);
INSERT into tenmo_user (username, password_hash)
VALUES ('Mitch','$2a$10$hZp2.P7/bdPuoTOFPGGn1OBZzPhBLrNSrEsV08KTXtuugFdZb0qgm')
,('Jeff', '$2a$10$0FajsoNCalTR/EA2n7bb/e.hDH.sYRgKAMHYMqrqwS1d6oItG89Hu')
,('Sam','$2a$10$iSEs8KgvkWr25WoJ1iyV7ub.qkx4SG4jV1FY/Hdnwjihk5gj7cXhy');

INSERT into account (user_id, balance)
VALUES ((SELECT user_id from tenmo_user where username = 'Mitch'), 1000)
,((SELECT user_id from tenmo_user where username = 'Jeff'), 1000)
,((SELECT user_id from tenmo_user where username = 'Sam'), 1000);

INSERT into transfers (from_account, to_account, transfer_amount)
VALUES (2001, 2002, 25.0);

-- ADD APPROVED AND PENDING BOOLEAN ROWS TO TRANSFERS TABLE

COMMIT;



