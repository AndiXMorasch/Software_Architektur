-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into PizzaEntity (id, version, name, price) values (1, 0, 'Margherita', 6.99);
insert into PizzaEntity (id, version, name, price) values (2, 0, 'Salami', 7.99);
insert into PizzaEntity (id, version, name, price) values (3, 0, 'Funghi', 8.99);
insert into PizzaEntity (id, version, name, price) values (4, 0, 'Prosciutto', 8.99);
insert into PizzaEntity (id, version, name, price) values (5, 0, 'Bianca', 9.99);
insert into PizzaEntity (id, version, name, price) values (6, 0, 'Quattro Formaggi', 7.99);
alter sequence PizzaEntity_seq restart with 7;

insert into IngredientEntity (id, version, name) values (1, 0, 'Mozarella');
insert into IngredientEntity (id, version, name) values (2, 0, 'Tomaten');
insert into IngredientEntity (id, version, name) values (3, 0, 'Salami');
insert into IngredientEntity (id, version, name) values (4, 0, 'Pilze');
insert into IngredientEntity (id, version, name) values (5, 0, 'Schinken');
insert into IngredientEntity (id, version, name) values (6, 0, 'Thunfisch');
insert into IngredientEntity (id, version, name) values (7, 0, 'Parmesan');
insert into IngredientEntity (id, version, name) values (8, 0, 'Gorgonzola');
insert into IngredientEntity (id, version, name) values (9, 0, 'Gouda');
alter sequence IngredientEntity_seq restart with 10;

insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (1,1);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (1,2);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (2,1);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (2,3);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (3,1);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (3,4);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (4,1);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (4,5);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (5,6);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (6,1);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (6,7);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (6,8);
insert into PizzaEntity_ingredientEntity (PizzaEntity_id, ingredients_id) values (6,9);