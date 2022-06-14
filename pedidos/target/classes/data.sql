insert into cliente (id,nome) values (1,'Pedro Campos');
insert into cliente (id,nome) values (2,'Haroldo Lamounier');
insert into cliente (id,nome) values (3,'Octávio Bittencourt');
insert into cliente (id,nome) values (4,'José Sanchez');
insert into cliente (id,nome) values (5,'Marco Aurélio Melo');
insert into cliente (id,nome) values (6,'Ricardo Sasaki');
insert into cliente (id,nome) values (7,'Alessandro Pereira');
insert into cliente (id,nome) values (8,'Carlos Alberto Presinoti');
insert into cliente (id,nome) values (9,'Romildo Lopes');
insert into cliente (id,nome) values (10,'Cristina Soares');
insert into cliente (id,nome) values (11,'Christian Souza');
insert into cliente (id,nome) values (12,'Jean Smith');

insert into item  (id,nome) values (1,'Item 1');
insert into item  (id,nome) values (2,'Item 2');
insert into item  (id,nome) values (3,'Item 3');
insert into item  (id,nome) values (4,'Item 4');
insert into item  (id,nome) values (5,'Item 5');
insert into item  (id,nome) values (6,'Item 6');
insert into item  (id,nome) values (7,'Item 7');
insert into item  (id,nome) values (8,'Item 8');
insert into item  (id,nome) values (9,'Item 9');
insert into item  (id,nome) values (10,'Item 10');

INSERT INTO arquivo (id, nome) VALUES (1,"Arquivo001");

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (1,STR_TO_DATE('2022-06-10','%Y-%m-%d'),'Pedido 1', 200,1,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (1,1,100,1,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (2,2,50,2,1); 

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (2,STR_TO_DATE('2022-06-11','%Y-%m-%d'),'Pedido 2', 150,2,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (3,3,10,3,2);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (4,4,30,4,2);

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (3,STR_TO_DATE('2022-06-11','%Y-%m-%d'),'Pedido 3', 221,3,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (5,5,10,5,3); 
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (6,6,30,6,3); 

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (4,STR_TO_DATE('2022-06-12','%Y-%m-%d'),'Pedido 4', 294.50,4,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (7,7,10,5,4); 
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (8,8,30,6,4); 

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (5,STR_TO_DATE('2022-06-10','%Y-%m-%d'),'Pedido 5', 355.50,5,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (9,9,10,5,5); 
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (10,10,30,6,5); 

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (6,STR_TO_DATE('2022-06-10','%Y-%m-%d'),'Pedido 6',381,6,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (11,6,10,5,6); 
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (12,12,30,6,6); 

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (7,STR_TO_DATE('2022-06-10','%Y-%m-%d'),'Pedido 7', 423,7,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (13,11,10,5,7); 
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (14,12,30,6,7); 

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (8,STR_TO_DATE('2022-06-10','%Y-%m-%d'),'Pedido 8',80,8,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (15,2,10,5,8);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (16,3,20,6,8);

insert into pedido (cod_pedido,data_cadastro, nome,total,cliente_id,codigo_arquivo) values (9,STR_TO_DATE('2022-06-10','%Y-%m-%d'),'Pedido 9',190 ,9,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (17,4,10,5,9);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (18,5,30,6,9);

insert into pedido (cod_pedido,data_cadastro,  nome,total,cliente_id,codigo_arquivo) values (10,STR_TO_DATE('2022-06-10','%Y-%m-%d'),'Pedido 10', 256.50,10,1);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (19,6,10,5,10);
insert into item_pedido (id, quantidade,valor,codigo_item,codigo_pedido) values (20,7,30,6,10);