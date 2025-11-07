START TRANSACTION;

-- USERS
INSERT INTO users (usr_id, usr_name, usr_email, usr_password, usr_created_at) VALUES
(1,'Rodrigo Canto','rodrigo.canto@hotmail.com','canto','2025-09-20 10:00:00'),
(2,'Rodrigo Daibert','rodrigo.daibert@gmail.com','daibert','2025-09-20 17:05:00'),
(3,'Marco Fonseca','marco.fonseca@hotmail.com','1234','2025-09-20 09:10:00'),
(4,'Luís Quirim','luis.quirim@gmail.com','kiwi','2025-10-21 10:15:00'),
(5,'Joana Silva','joana.silva@hotmail.com','joana','2025-09-21 11:30:00'),
(6,'Miguel Costa','miguel.costa@gmail.com','costa','2025-10-10 10:35:00'),
(7,'Bento D’Orey','bento.dorey@gmail.com','ola','2025-10-11 19:35:00'),
(8,'Paulinho Encomedas','paulinho.encomendas@hotmail.com','encomenda','2025-10-12 18:35:00');

-- GROUPS
INSERT INTO groupss (grp_id, grp_name, grp_created_at) VALUES
(1,'Casa A','2025-09-21 11:00:00'),
(2,'Festa','2025-09-21 00:10:00'),
(3,'Porto','2025-09-21 09:20:00'),
(4,'Churrasco Rapazes','2025-10-21 12:20:00');

-- MEMBERSHIPS
INSERT INTO memberships (mem_id, mem_usr_id, mem_grp_id, mem_role, mem_joined_at) VALUES
(1,1,1,'owner','2025-09-21 11:01:00'),
(2,2,1,'member','2025-09-21 11:02:00'),
(3,3,1,'member','2025-09-21 11:03:00'),
(4,2,2,'owner','2025-09-21 11:11:00'),
(5,4,2,'member','2025-09-21 11:12:00'),
(6,5,3,'owner','2025-09-21 11:21:00'),
(7,6,3,'member','2025-09-21 11:22:00'),
(8,7,4,'owner','2025-10-21 12:21:00'),
(9,8,4,'member','2025-10-21 12:22:00');

-- SHOPPING LISTS
INSERT INTO shopping_lists (lst_id, lst_grp_id, lst_title, lst_created_at) VALUES
(1,1,'Compras Semanais','2025-09-22 18:00:00'),
(2,1,'Churrasco sábado','2025-09-23 10:00:00'),
(3,2,'Casa B – Reposição','2025-09-22 18:30:00'),
(4,3,'Erasmus – Partilhada','2025-09-23 09:00:00'),
(5,4,'Churrasco Rapazes - Preparação','2025-10-21 13:00:00');

-- LIST ITEMS
INSERT INTO list_items (itm_id, itm_lst_id, itm_name, itm_qty, itm_unit, itm_done, itm_updated_at) VALUES
(1,1,'Leite',6,'un',0,'2025-09-22 18:05:00'),
(2,1,'Pão',10,'un',1,'2025-09-22 19:10:00'),
(3,1,'Arroz',2.00,'kg',0,'2025-09-22 18:06:00'),
(4,2,'Carvão',1.00,'saco',0,'2025-09-23 10:05:00'),
(5,2,'Entrecosto',2.50,'kg',0,'2025-09-23 10:06:00'),
(6,3,'Água',12,'un',0,'2025-09-22 18:35:00'),
(7,3,'Detergente',1,'un',0,'2025-09-22 18:36:00'),
(8,4,'Massa',1.00,'kg',0,'2025-09-23 09:05:00'),
(9,4,'Atum',4,'lata',0,'2025-09-23 09:06:00'),
(10,5,'Cerveja',24,'un',0,'2025-10-21 13:05:00'),
(11,5,'Picanha',3.00,'kg',0,'2025-10-21 13:06:00'),
(12,5,'Carvão',2,'saco',0,'2025-10-21 13:07:00');

-- RECIPES
INSERT INTO recipes (rec_id, rec_usr_id, rec_name, rec_serves) VALUES
(1,1,'Massa com Atum',4),
(2,2,'Frango no Forno',3),
(3,5,'Panquecas',2),
(4,7,'Hambúrgueres Caseiros',4);

-- RECIPE INGREDIENTS
INSERT INTO recipe_ingredients (rin_id, rin_rec_id, rin_name, rin_qty_serving, rin_unit) VALUES
(1,1,'Massa',100,'g'),
(2,1,'Atum',0.5,'lata'),
(3,1,'Azeite',1,'colher'),
(4,1,'Sal',1,'pitada'),
(5,2,'Frango',250,'g'),
(6,2,'Batata',150,'g'),
(7,2,'Alho',1,'dente'),
(8,3,'Farinha',60,'g'),
(9,3,'Ovo',0.5,'un'),
(10,3,'Leite',80,'ml'),
(11,4,'Carne Picada',150,'g'),
(12,4,'Pão Ralado',30,'g'),
(13,4,'Ovo',0.25,'un'),
(14,4,'Sal',1,'pitada');

-- SUPERMARKETS
INSERT INTO supermarkets (sup_id, sup_name, sup_rating, sup_distance) VALUES
(1,'Continente',4.3,1.20),
(2,'Lidl',4.1,0.85),
(3,'Mercadona',4.4,2.10),
(4,'Pingo Doce',4.0,0.60),
(5,'Aldi',3.9,1.75),
(6,'Auchan',4.2,2.00);

-- SAVED PLACES
INSERT INTO saved_places (sav_id, sav_usr_id, sav_sup_id, sav_label, sav_distance, sav_created_at) VALUES
(1,1,1,'Perto de casa',1.20,'2025-09-24 12:00:00'),
(2,1,4,'No caminho do trabalho',0.60,'2025-09-24 12:01:00'),
(3,2,2,'Mais barato',0.85,'2025-09-24 12:02:00'),
(4,3,3,'Domingos',2.10,'2025-09-24 12:03:00'),
(5,5,5,'Ao pé do ginásio',1.75,'2025-09-24 12:04:00'),
(6,7,6,'Perto do treino',2.00,'2025-10-21 13:10:00');

COMMIT;