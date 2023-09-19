CREATE DATABASE `waren_db`;

CREATE TABLE `waren` (
	`warennummer` INT(10) UNSIGNED NOT NULL,
	`warenname` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',
	`preis` FLOAT UNSIGNED NOT NULL DEFAULT '0',
	`beschreibung` VARCHAR(150) NOT NULL DEFAULT '0' COLLATE 'utf8mb4_general_ci',
	PRIMARY KEY (`warennummer`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;

INSERT INTO `waren_db`.`waren` (`warennummer`, `warenname`, `preis`, `beschreibung`) VALUES ('1234', 'Tisch', '500', 'Huebscher Tisch');
INSERT INTO `waren_db`.`waren` (`warennummer`, `warenname`, `preis`, `beschreibung`) VALUES ('1235', 'Stuhl', '250', 'Geiler Stuhl');
INSERT INTO `waren_db`.`waren` (`warennummer`, `warenname`, `preis`, `beschreibung`) VALUES ('1236', 'Stift', '2', 'Mega Stift');
INSERT INTO `waren_db`.`waren` (`warennummer`, `warenname`, `preis`, `beschreibung`) VALUES ('1237', 'Lampe', '25', 'Helle Lampe');
INSERT INTO `waren_db`.`waren` (`warennummer`, `warenname`, `preis`, `beschreibung`) VALUES ('1238', 'Kerze', '3', 'Weihnachtskerze');