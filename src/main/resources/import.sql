insert into products(name, price) values('Product 1', 100);
insert into products(name, price) values('Product 2', 200);
insert into products(name, price) values('Product 3', 300);
insert into products(name, price) values('Product 4', 400);
insert into products(name, price) values('Product 5', 500);
insert into products(name, price) values('Product 6', 600);
insert into products(name, price) values('Product 7', 700);
insert into products(name, price) values('Product 8', 800);
insert into products(name, price) values('Product 9', 900);
insert into products(name, price) values('Product 10', 1000);

insert into orders (reg_date, total) values (now(), 1500);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (1,1,100,1,100);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (1,2,200,1,200);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (1,3,300,1,300);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (1,4,400,1,400);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (1,5,500, 1, 500);
insert into orders (reg_date, total) values (now(), 4000);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (2,6,600,1,600);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (2,7,700,1,700);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (2,8,800, 1,800);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (2,9,900, 1,900);
insert into order_lines (fk_order, fk_product, price, quantity, total) values (2,10,1000, 1, 1000);

insert into users(username, password) values ('oscar', '1234');
