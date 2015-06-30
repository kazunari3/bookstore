
/* Drop Tables */

DROP TABLE IF EXISTS t_order_detail;
DROP TABLE IF EXISTS t_book;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS t_customer;



/* Drop Sequences */

DROP SEQUENCE IF EXISTS hibernate_sequence;




/* Create Sequences */

CREATE SEQUENCE hibernate_sequence;



/* Create Tables */

CREATE TABLE t_book
(
	id int DEFAULT nextval('hibernate_sequence') NOT NULL,
	isbn text NOT NULL UNIQUE,
	title text NOT NULL,
	author text NOT NULL,
	publisher text NOT NULL,
	price int NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE t_customer
(
	id int DEFAULT nextval('hibernate_sequence')  NOT NULL,
	uid text NOT NULL UNIQUE,
	passwordmd5 text NOT NULL,
	name text NOT NULL,
	email text NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE t_order
(
	id int DEFAULT nextval('hibernate_sequence') NOT NULL,
	orderday timestamp NOT NULL,
	customer_id_fk int NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE t_order_detail
(
	id int DEFAULT nextval('hibernate_sequence') NOT NULL,
	order_id_fk int NOT NULL,
	book_id_fk int NOT NULL
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE t_order_detail
	ADD FOREIGN KEY (book_id_fk)
	REFERENCES t_book (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_order
	ADD FOREIGN KEY (customer_id_fk)
	REFERENCES t_customer (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_order_detail
	ADD FOREIGN KEY (order_id_fk)
	REFERENCES t_order (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



