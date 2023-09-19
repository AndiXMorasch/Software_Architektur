-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into ShipEntity (id, name, available, version) values (1, 'Ever Given', false, 0);
insert into ShipEntity (id, name, available, version) values (2, 'msc titanic', false, 0);
insert into ShipEntity (id, name, available, version) values (3, 'black pearl', false, 0);
insert into ShipEntity (id, name, available, version) values (4, 'queen annes revenge', true, 0);
alter sequence ShipEntity_seq restart with 5;

insert into OrderEntity (orderId, description, orderReceivementDate, shipUrl, version) values (1, 'Lieferung von Vorräten zum Nordpol', '05.05.2023', 'http://localhost:8080/ships/2', 0);
insert into OrderEntity (orderId, description, orderReceivementDate, shipUrl, version) values (2, 'Raub der isla de muerta', '05.05.2023', 'http://localhost:8080/ships/3', 0);
alter sequence OrderEntity_seq restart with 3;