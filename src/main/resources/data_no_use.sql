--delete from Ingredient_Ref;
--delete from Kebab;
--delete from Kebab_Order;
--delete from Ingredient;
insert into Ingredient (id,ingredient_id, name, type)
values (1,'FLTO', 'Flour Tortilla', 0);
insert into Ingredient (id,ingredient_id, name, type)
values (2,'COTO', 'Corn Tortilla', 0);
insert into Ingredient (id,ingredient_id, name, type)
values (3,'GRBF', 'Ground Beef', 1);
insert into Ingredient (id,ingredient_id, name, type)
values (4,'CARN', 'Carnitas', 1);
insert into Ingredient (id,ingredient_id, name, type)
values (5,'TMTO', 'Diced Tomatoes', 2);
insert into Ingredient (id,ingredient_id, name, type)
values (6,'LETC', 'Lettuce', 2);
insert into Ingredient (id,ingredient_id, name, type)
values (7,'CHED', 'Cheddar', 3);
insert into Ingredient (id,ingredient_id, name, type)
values (8,'JACK', 'Monterrey Jack', 3);
insert into Ingredient (id,ingredient_id, name, type)
values (9,'SLSA', 'Salsa', 4);
insert into Ingredient (id,ingredient_id, name, type)
values (10,'SRCR', 'Sour Cream', 4);
--insert into User_kebab_cloud(id,username,password)
--values(100,'max','123');