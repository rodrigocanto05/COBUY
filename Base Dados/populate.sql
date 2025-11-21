#users
  
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Rodrigo Canto', 'rodrigocanto@hotmail.com', 'canto', 'M', str_to_date('2025.10.20','%Y.%m.%d'));         #usr_id = 1
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Rodrigo Daibert', 'rodrigodaibert@hotmail.com', '1234', 'M', str_to_date('2025.10.22','%Y.%m.%d'));      #usr_id =2
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Marco Fonseca', 'mf2006@gmail.com', 'hash1', 'M', str_to_date('2025.10.24','%Y.%m.%d'));                 #usr_id =3
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Luis Quirim', 'luisquirim@gmail.com', 'hash1', 'M', str_to_date('2025.10.28','%Y.%m.%d'));               #usr_id =4
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Sandra Estrela', 'sandra@hotmail.com', 'hash1', 'F', str_to_date('2025.10.30','%Y.%m.%d'));              #usr_id =5
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Daniel Paulo', 'dexpaulo@hotmail.com', 'hash1', 'M', str_to_date('2025.11.01','%Y.%m.%d'));              #usr_id =6
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Jocy Grangeiro', 'jocy12@gmail.com', 'hash1', 'F', str_to_date('2025.11.04','%Y.%m.%d'));                #usr_id =7
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Paulo Alberto', 'pauloencomendas@gmail.com', 'hash1', 'M', str_to_date('2025.11.09','%Y.%m.%d'));        #usr_id =8
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Patricia Daibert', 'patriciadaibert@hotmail.com', 'hash1', 'F', str_to_date('2025.11.13','%Y.%m.%d'));   #usr_id =9
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Martim Fonseca', 'mrmartim@hotmail.com', 'hash1', 'M', str_to_date('2025.12.01','%Y.%m.%d'));            #usr_id =10
insert into users (usr_name, usr_email, usr_password, usr_gender, usr_created_at) values ('Tomas Lebre', 'tomaslebre@gmail.com', 'hash1', 'M', str_to_date('2025.12.02','%Y.%m.%d'));               #usr_id =11
      
      
#groups
      
insert into groupss (grp_name, grp_owner_usr_id, grp_created_at, grp_code) values ('IADE', 1, str_to_date('2025.12.03','%Y.%m.%d'), 'X9TPQ');              -- grp_id = 1
insert into groupss (grp_name, grp_owner_usr_id, grp_created_at, grp_code) values ('Colegas de casa', 2, str_to_date('2025.12.04','%Y.%m.%d'), 'M7K2A');   -- grp_id = 2
insert into groupss (grp_name, grp_owner_usr_id, grp_created_at, grp_code) values ('Churrasco', 3, str_to_date('2025.12.05','%Y.%m.%d'), 'Q4W9E');         -- grp_id = 3
insert into groupss (grp_name, grp_owner_usr_id, grp_created_at, grp_code) values ('Mulheres', 5, str_to_date('2025.12.05','%Y.%m.%d'), 'A8ZLM');          -- grp_id = 4
insert into groupss (grp_name, grp_owner_usr_id, grp_created_at, grp_code)values ('Montijo', 4, str_to_date('2025.12.07','%Y.%m.%d'), 'P6X7R');            -- grp_id = 5


#memberships

insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (1, 1, 'owner', str_to_date('2025.12.03','%Y.%m.%d'));   # Rodrigo é owner do grupo 1 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (2, 1, 'member', str_to_date('2025.12.03','%Y.%m.%d'));  # Daibert é member do grupo 1
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (3, 1, 'member', str_to_date('2025.12.03','%Y.%m.%d'));  # Marco é member do grupo 1
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (4, 1, 'member', str_to_date('2025.12.03','%Y.%m.%d'));  # Luis é member do grupo 1
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (11, 1, 'member', str_to_date('2025.12.04','%Y.%m.%d')); # Tomas é member do grupo 1
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (2, 2, 'owner', str_to_date('2025.12.04','%Y.%m.%d'));   # Daibert é owner do grupo 2
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (4, 2, 'member', str_to_date('2025.12.04','%Y.%m.%d'));  # Luis é member do grupo 2 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (6, 2, 'member', str_to_date('2025.12.04','%Y.%m.%d'));  # Dex é member do grupo 2 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (8, 2, 'member', str_to_date('2025.12.04','%Y.%m.%d'));  # Paulo é member do grupo 2 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (10, 2, 'member', str_to_date('2025.12.04','%Y.%m.%d')); # Martim é member do grupo 2 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (3, 3, 'owner', str_to_date('2025.12.05','%Y.%m.%d'));   # Marco é owner do grupo 3 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (1, 3, 'member', str_to_date('2025.12.05','%Y.%m.%d'));  # Rodrigo é member do grupo 3 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (2, 3, 'member', str_to_date('2025.12.05','%Y.%m.%d'));  # Daibert é member do grupo 3 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (4, 3, 'member', str_to_date('2025.12.05','%Y.%m.%d'));  # Luis é member do grupo 3 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (5, 4, 'owner', str_to_date('2025.12.05','%Y.%m.%d'));   # Sandra é owner do grupo 4 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (7, 4, 'member', str_to_date('2025.12.05','%Y.%m.%d'));  # Jocy é member do grupo 4 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (9, 4, 'member', str_to_date('2025.12.05','%Y.%m.%d'));  # Patricia é member do grupo 4 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (4, 5, 'owner', str_to_date('2025.12.07','%Y.%m.%d'));   # Luis é owner do grupo 5  
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (3, 5, 'member', str_to_date('2025.12.07','%Y.%m.%d'));  # Marco é member do grupo 5 
insert into memberships (mem_usr_id, mem_grp_id, mem_role, mem_joined_at) values (6, 5, 'member', str_to_date('2025.12.07','%Y.%m.%d'));  # Dex é member do grupo 5 


#lists

-- Grupo 1: IADE
insert into lists (lst_grp_id, lst_title, lst_created_at) values (1, 'Compras IADE - Semana 1', str_to_date('2025.12.03','%Y.%m.%d'));        #lst_id = 1
insert into lists (lst_grp_id, lst_title, lst_created_at) values (1, 'Lanche da Reunião de Projeto', str_to_date('2025.12.04','%Y.%m.%d'));   #lst_id = 2
insert into lists (lst_grp_id, lst_title, lst_created_at) values (1, 'Material para Apresentação', str_to_date('2025.12.05','%Y.%m.%d'));     #lst_id = 3
-- Grupo 2: Colegas de casa
insert into lists (lst_grp_id, lst_title, lst_created_at) values (2, 'Compras da Casa - Mensal', str_to_date('2025.12.04','%Y.%m.%d'));       #lst_id = 4
insert into lists (lst_grp_id, lst_title, lst_created_at) values (2, 'Compras do Fim de Semana', str_to_date('2025.12.06','%Y.%m.%d'));       #lst_id = 5
insert into lists (lst_grp_id, lst_title, lst_created_at) values (2, 'Produtos de Limpeza', str_to_date('2025.12.07','%Y.%m.%d'));            #lst_id = 6
-- Grupo 3: Churrasco 
insert into lists (lst_grp_id, lst_title, lst_created_at) values (3, 'Churrasco Rapazes', str_to_date('2025.12.05','%Y.%m.%d'));              #lst_id = 7
-- Grupo 4: Mulheres 
insert into lists (lst_grp_id, lst_title, lst_created_at) values (4, 'Jantar das Mulheres', str_to_date('2025.12.08','%Y.%m.%d'));            #lst_id = 8
insert into lists (lst_grp_id, lst_title, lst_created_at) values (4, 'Brunch de Domingo', str_to_date('2025.12.09','%Y.%m.%d'));              #lst_id = 9
insert into lists (lst_grp_id, lst_title, lst_created_at) values (4, 'Noite de Cinema', str_to_date('2025.12.10','%Y.%m.%d'));                #lst_id = 10
-- Grupo 5: Montijo
insert into lists (lst_grp_id, lst_title, lst_created_at) values (5, 'Compras Montijo - Família', str_to_date('2025.12.08','%Y.%m.%d'));      #lst_id = 11
insert into lists (lst_grp_id, lst_title, lst_created_at) values (5, 'Fim de Semana em Casa', str_to_date('2025.12.10','%Y.%m.%d'));          #lst_id = 12
insert into lists (lst_grp_id, lst_title, lst_created_at) values (5, 'Ceia de Natal Montijo', str_to_date('2025.12.11','%Y.%m.%d'));          #lst_id = 13


#unit

insert into unit (uni_name) values ('kg');   #1
insert into unit (uni_name) values ('g');    #2
insert into unit (uni_name) values ('L');    #3
insert into unit (uni_name) values ('ml');   #4
insert into unit (uni_name) values ('un');   #5


#items

insert into items (it_name, it_unit_id) values ('Arroz', 1);                    # kg  | it_id = 1
insert into items (it_name, it_unit_id) values ('Massa', 1);                    # kg  | it_id = 2
insert into items (it_name, it_unit_id) values ('Farinha', 1);                  # kg  | it_id = 3
insert into items (it_name, it_unit_id) values ('Frango', 1);                   # kg  | it_id = 4
insert into items (it_name, it_unit_id) values ('Leite', 3);                    # L   | it_id = 5
insert into items (it_name, it_unit_id) values ('Água', 3);                     # L   | it_id = 6
insert into items (it_name, it_unit_id) values ('Óleo', 3);                     # L   | it_id = 7
insert into items (it_name, it_unit_id) values ('Vinho', 3);                    # L   | it_id = 8
insert into items (it_name, it_unit_id) values ('Ovos', 5);                     # un  | it_id = 9
insert into items (it_name, it_unit_id) values ('Manteiga', 2);                 # g   | it_id = 10
insert into items (it_name, it_unit_id) values ('Açúcar', 2);                   # g   | it_id = 11
insert into items (it_name, it_unit_id) values ('Sal', 5);                      # un  | it_id = 12
insert into items (it_name, it_unit_id) values ('Pimenta', 5);                  # un  | it_id = 13
insert into items (it_name, it_unit_id) values ('Entrecosto', 1);               # kg  | it_id = 14
insert into items (it_name, it_unit_id) values ('Bananas', 5);                  # un  | it_id = 15
insert into items (it_name, it_unit_id) values ('Maçãs', 5);                    # un  | it_id = 16
insert into items (it_name, it_unit_id) values ('Cebolas', 5);                  # un  | it_id = 17
insert into items (it_name, it_unit_id) values ('Alho', 5);                     # un  | it_id = 18
insert into items (it_name, it_unit_id) values ('Cerveja', 3);                  # L   | it_id = 19
insert into items (it_name, it_unit_id) values ('Refrigerante', 3);             # L   | it_id = 20
insert into items (it_name, it_unit_id) values ('Carvão', 1);                   # kg  | it_id = 21
insert into items (it_name, it_unit_id) values ('Guardanapos', 5);              # un  | it_id = 22
insert into items (it_name, it_unit_id) values ('Pratos de plástico', 5);       # un  | it_id = 23
insert into items (it_name, it_unit_id) values ('Sacos do lixo', 5);            # un  | it_id = 24
insert into items (it_name, it_unit_id) values ('Pão', 5);                      # un  | it_id = 25
insert into items (it_name, it_unit_id) values ('Queijo', 2);                   # g   | it_id = 26
insert into items (it_name, it_unit_id) values ('Fiambre', 2);                  # g   | it_id = 27
insert into items (it_name, it_unit_id) values ('Tomates', 5);                  # un  | it_id = 28
insert into items (it_name, it_unit_id) values ('Alface', 5);                   # un  | it_id = 29
insert into items (it_name, it_unit_id) values ('Café', 5);                     # un  | it_id = 30
insert into items (it_name, it_unit_id) values ('Salsichas', 5);                # un  | it_id = 31
insert into items (it_name, it_unit_id) values ('Detergente da loiça', 3);      # L   | it_id = 32
insert into items (it_name, it_unit_id) values ('Detergente da roupa', 4);      # ml  | it_id = 33
insert into items (it_name, it_unit_id) values ('Amaciador', 4);                # ml  | it_id = 34
insert into items (it_name, it_unit_id) values ('Esponjas', 5);                 # un  | it_id = 35
insert into items (it_name, it_unit_id) values ('Papel higiénico', 5);          # un  | it_id = 36
insert into items (it_name, it_unit_id) values ('Pikels ', 2);                  # g   | it_id = 37
insert into items (it_name, it_unit_id) values ('Bolo', 1);                     # kg  | it_id = 38
insert into items (it_name, it_unit_id) values ('Spray multiusos', 5);          # un  | it_id = 39
insert into items (it_name, it_unit_id) values ('Limpa-vidros', 5);             # un  | it_id = 40
insert into items (it_name, it_unit_id) values ('Pastilhas para máquina', 5);   # un  | it_id = 41
insert into items (it_name, it_unit_id) values ('Toalhitas ', 5);               # un  | it_id = 42
insert into items (it_name, it_unit_id) values ('Sacos do aspirador', 5);       # un  | it_id = 43
insert into items (it_name, it_unit_id) values ('Chocolate', 2);                # g   | it_id = 44
insert into items (it_name, it_unit_id) values ('Bolachas', 5);                 # un  | it_id = 45
insert into items (it_name, it_unit_id) values ('Sumo', 3);                     # L   | it_id = 46
insert into items (it_name, it_unit_id) values ('Iogurtes', 5);                 # un  | it_id = 47
insert into items (it_name, it_unit_id) values ('Gelatina', 5);                 # un  | it_id = 48
insert into items (it_name, it_unit_id) values ('Marmelada', 2);                # g   | it_id = 49
insert into items (it_name, it_unit_id) values ('Cenouras', 5);                 # un  | it_id = 50


#list_items

#grupo 1
-- lst_id = 1
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (1, 2, 1, 1.00, 1);    # Massa 1 kg (Rodrigo)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (1, 1, 2, 2.00, 1);    # Arroz 2 kg (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (1, 5, 3, 3.00, 3);    # Leite 3 L (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (1, 25, 4, 6.00, 5);   # Pão 6 un (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (1, 30, 11, 1.00, 5);  # Café 1 un (Tomas)
-- lst_id = 2
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (2, 25, 1, 10.00, 5);  # Pão 10 un (Rodrigo)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (2, 26, 2, 0.20, 2);   # Queijo 200 g (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (2, 27, 3, 0.20, 2);   # Fiambre 200 g (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (2, 46, 4, 2.00, 3);   # Sumo 2 L (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (2, 45, 11, 10.00, 5); # Bolachas 10 un (Tomas)
-- lst_id = 3
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (3, 6, 1, 6.00, 3);    # Água 6 L (Rodrigo)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (3, 30, 2, 1.00, 5);   # Café 1 un (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (3, 22, 3, 30.00, 5);  # Guardanapos 30 un (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (3, 23, 4, 20.00, 5);  # Pratos de plástico 20 un (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (3, 38, 11, 2.00, 1);  # Bolo 2 kg (Tomas)
#grupo 2
-- lst_id = 4
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (4, 1, 2, 5.00, 1);    # Arroz 5 kg (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (4, 2, 4, 3.00, 1);    # Massa 3 kg (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (4, 3, 6, 2.00, 1);    # Farinha 2 kg (Dex)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (4, 5, 8, 6.00, 3);    # Leite 6 L (Paulo)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (4, 6, 10, 10.00, 3);  # Água 10 L (Martim)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (4, 9, 2, 30.00, 5);   # Ovos 30 un (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (4, 36, 4, 12.00, 5);  # Papel higiénico 12 un (Luis)
-- lst_id = 5
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (5, 4, 2, 2.00, 1);    # Frango 2 kg (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (5, 14, 4, 1.50, 1);   # Entrecosto 1.5 kg (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (5, 31, 6, 10.00, 5);  # Salsichas 10 un (Dex)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (5, 19, 8, 6.00, 3);   # Cerveja 6 L (Paulo)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (5, 20, 10, 4.00, 3);  # Refrigerante 4 L (Martim)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (5, 25, 4, 8.00, 5);   # Pão 8 un (Luis)
-- lst_id = 6
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (6, 32, 2, 1.00, 3);   # Detergente da loiça 1 L (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (6, 33, 4, 100.00, 4); # Detergente da roupa 100 frasco (ml) (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (6, 34, 6, 100.00, 4); # Amaciador 100 ml (Dex)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (6, 35, 8, 4.00, 5);   # Esponjas 4 un (Paulo)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (6, 39, 10, 2.00, 5);  # Spray multiusos 2 un (Martim)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (6, 40, 4, 1.00, 5);   # Limpa-vidros 1 un (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (6, 24, 2, 2.00, 5);   # Sacos do lixo 2 rolos (Daibert)
#grupo 3
-- lst_id = 7
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 14, 3, 3.00, 1);   # Entrecosto 3 kg (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 4, 1, 2.00, 1);    # Frango 2 kg (Rodrigo)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 31, 2, 15.00, 5);  # Salsichas 15 un (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 21, 4, 5.00, 1);   # Carvão 5 kg (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 19, 3, 8.00, 3);   # Cerveja 8 L (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 25, 1, 10.00, 5);  # Pão 10 un (Rodrigo)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 22, 2, 40.00, 5);  # Guardanapos 40 un (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 50, 2, 9.00, 5);   # Cenoura 9 un (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 28, 2, 5.00, 5);   # Tomate 5 un (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 29, 2, 2.00, 5);   # Alface 2 un (Daibert)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (7, 5, 4, 1.00, 5);    # Sal 1 un (Luis)
#grupo 4
-- lst_id = 8
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (8, 4, 5, 1.50, 1);    # Frango 1.5 kg (Sandra)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (8, 28, 7, 4.00, 5);   # Tomates 4 un (Jocy)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (8, 29, 9, 2.00, 5);   # Alface 2 un (Patricia)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (8, 8, 5, 2.00, 3);    # Vinho 2 L (Sandra)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (8, 44, 7, 0.30, 2);   # Chocolate 300 g (Jocy)
-- lst_id = 9
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (9, 25, 5, 6.00, 5);   # Pão 6 un (Sandra)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (9, 47, 7, 8.00, 5);   # Iogurtes 8 un (Jocy)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (9, 5, 9, 3.00, 3);    # Leite 3 L (Patricia)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (9, 46, 5, 2.00, 3);   # Sumo 2 L (Sandra)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (9, 45, 7, 2.00, 5);   # Bolachas 2 un (Jocy)
-- lst_id = 10
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (10, 44, 5, 0.20, 2);  # Chocolate 200 g (Sandra)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (10, 45, 7, 3.00, 5);  # Bolachas 3 un (Jocy)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (10, 46, 9, 2.00, 3);  # Sumo 2 L (Patricia)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (10, 47, 7, 4.00, 5);  # Iogurtes 4 un (Jocy)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (10, 48, 5, 2.00, 5);  # Gelatina 2 un (Sandra)
#grupo 5
-- lst_id = 11
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (11, 1, 4, 3.00, 1);   # Arroz 3 kg (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (11, 2, 3, 2.00, 1);   # Massa 2 kg (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (11, 5, 6, 4.00, 3);   # Leite 4 L (Dex)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (11, 16, 3, 6.00, 5);  # Maçãs 6 un (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (11, 17, 4, 4.00, 5);  # Cebolas 4 un (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (11, 36, 6, 12.00, 5); # Papel higiénico 12 un (Dex)
-- lst_id = 12
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (12, 4, 3, 2.00, 1);   # Frango 2 kg (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (12, 8, 4, 2.00, 3);   # Vinho 2 L (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (12, 19, 6, 4.00, 3);  # Cerveja 4 L (Dex)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (12, 25, 3, 6.00, 5);  # Pão 6 un (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (12, 44, 4, 0.25, 2);  # Chocolate 250 g (Luis)
-- lst_id = 13
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (13, 4, 3, 3.00, 1);   # Frango 3 kg (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (13, 1, 4, 2.00, 1);   # Arroz 2 kg (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (13, 9, 6, 18.00, 5);  # Ovos 18 un (Dex)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (13, 8, 4, 3.00, 3);   # Vinho 3 L (Luis)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (13, 49, 3, 0.50, 2);  # Marmelada 500 g (Marco)
insert into list_items (li_lst_id, li_item_id, li_usr_id, li_qty, li_unit_id) values (13, 50, 6, 6.00, 5);  # Cenouras 6 un (Dex)


#recipes

insert into recipes (rec_name) values ('Massa Carbonara');                        # rec_id = 1
insert into recipes (rec_name) values ('Frango Grelhado com Arroz e Legumes');    # rec_id = 2
insert into recipes (rec_name) values ('Lasanha de Carne');                       # rec_id = 3
insert into recipes (rec_name) values ('Arroz de Marisco');                       # rec_id = 4
insert into recipes (rec_name) values ('Bacalhau à Brás');                        # rec_id = 5
insert into recipes (rec_name) values ('Salmão no Forno com Batatas');            # rec_id = 6
insert into recipes (rec_name) values ('Salame de Chocolate');                    # rec_id = 7
insert into recipes (rec_name) values ('Chili com Carne');                        # rec_id = 8
insert into recipes (rec_name) values ('Panquecas');                              # rec_id = 9
insert into recipes (rec_name) values ('Omelete de Queijo e Fiambre');            # rec_id = 10
insert into recipes (rec_name) values ('Sopa de Legumes');                        # rec_id = 11
insert into recipes (rec_name) values ('Tosta Mista');                            # rec_id = 12
insert into recipes (rec_name) values ('Wrap de Frango com Alface');              # rec_id = 13
insert into recipes (rec_name) values ('Hambúrguer Caseiro');                     # rec_id = 14
insert into recipes (rec_name) values ('Pizza Caseira');                          # rec_id = 15
insert into recipes (rec_name) values ('Esparguete à Bolonhesa');                 # rec_id = 16
insert into recipes (rec_name) values ('Arroz Doce');                             # rec_id = 17
insert into recipes (rec_name) values ('Gelatina com Iogurte');                   # rec_id = 18
insert into recipes (rec_name) values ('Salada Mediterrânica');                   # rec_id = 19
insert into recipes (rec_name) values ('Bolo de Chocolate');                      # rec_id = 20


#ingredients

insert into ingredients (ing_name, ing_unit_id) values ('Esparguete', 1);                  # kg | ing_id = 1
insert into ingredients (ing_name, ing_unit_id) values ('Placas de lasanha', 5);           # un | ing_id = 2
insert into ingredients (ing_name, ing_unit_id) values ('Arroz carolino', 1);              # kg | ing_id = 3
insert into ingredients (ing_name, ing_unit_id) values ('Pão de forma', 5);                # un | ing_id = 4
insert into ingredients (ing_name, ing_unit_id) values ('Pão de hambúrguer', 5);           # un | ing_id = 5
insert into ingredients (ing_name, ing_unit_id) values ('Tortilhas de trigo', 5);          # un | ing_id = 6
insert into ingredients (ing_name, ing_unit_id) values ('Bolacha maria', 5);               # un | ing_id = 7
insert into ingredients (ing_name, ing_unit_id) values ('Bacon em tiras', 1);              # kg | ing_id = 8
insert into ingredients (ing_name, ing_unit_id) values ('Peito de frango', 1);             # kg | ing_id = 9
insert into ingredients (ing_name, ing_unit_id) values ('Carne picada de vaca', 1);        # kg | ing_id = 10
insert into ingredients (ing_name, ing_unit_id) values ('Bacalhau desfiado', 1);           # kg | ing_id = 11
insert into ingredients (ing_name, ing_unit_id) values ('Salmão', 1);                      # kg | ing_id = 12
insert into ingredients (ing_name, ing_unit_id) values ('Miolo de camarão', 1);            # kg | ing_id = 13
insert into ingredients (ing_name, ing_unit_id) values ('Miolo de mexilhão', 1);           # kg | ing_id = 14
insert into ingredients (ing_name, ing_unit_id) values ('Amêijoas', 1);                    # kg | ing_id = 15
insert into ingredients (ing_name, ing_unit_id) values ('Ovos', 5);                        # un | ing_id = 16
insert into ingredients (ing_name, ing_unit_id) values ('Leite', 3);                       # L  | ing_id = 17
insert into ingredients (ing_name, ing_unit_id) values ('Natas', 2);                       # g  | ing_id = 18
insert into ingredients (ing_name, ing_unit_id) values ('Manteiga', 2);                    # g  | ing_id = 19
insert into ingredients (ing_name, ing_unit_id) values ('Queijo parmesão ralado', 2);      # g  | ing_id = 20
insert into ingredients (ing_name, ing_unit_id) values ('Queijo ralado', 2);               # g  | ing_id = 21
insert into ingredients (ing_name, ing_unit_id) values ('Queijo fatiado', 2);              # g  | ing_id = 22
insert into ingredients (ing_name, ing_unit_id) values ('Queijo mozzarella ralado', 2);    # g  | ing_id = 23
insert into ingredients (ing_name, ing_unit_id) values ('Queijo feta', 2);                 # g  | ing_id = 24
insert into ingredients (ing_name, ing_unit_id) values ('Iogurte natural', 2);             # g  | ing_id = 25
insert into ingredients (ing_name, ing_unit_id) values ('Cebola', 5);                      # un | ing_id = 26
insert into ingredients (ing_name, ing_unit_id) values ('Cebola roxa', 5);                 # un | ing_id = 27
insert into ingredients (ing_name, ing_unit_id) values ('Alho', 5);                        # un | ing_id = 28
insert into ingredients (ing_name, ing_unit_id) values ('Cenoura', 5);                     # un | ing_id = 29
insert into ingredients (ing_name, ing_unit_id) values ('Batata', 1);                      # kg | ing_id = 30
insert into ingredients (ing_name, ing_unit_id) values ('Courgette', 5);                   # un | ing_id = 31
insert into ingredients (ing_name, ing_unit_id) values ('Pimento vermelho', 5);            # un | ing_id = 32
insert into ingredients (ing_name, ing_unit_id) values ('Alface', 5);                      # un | ing_id = 33
insert into ingredients (ing_name, ing_unit_id) values ('Tomate', 5);                      # un | ing_id = 34
insert into ingredients (ing_name, ing_unit_id) values ('Pepino', 5);                      # un | ing_id = 35
insert into ingredients (ing_name, ing_unit_id) values ('Cogumelos', 5);                   # un | ing_id = 36
insert into ingredients (ing_name, ing_unit_id) values ('Coentros frescos', 5);            # un | ing_id = 37
insert into ingredients (ing_name, ing_unit_id) values ('Salsa fresca', 5);                # un | ing_id = 38
insert into ingredients (ing_name, ing_unit_id) values ('Alecrim', 5);                     # un | ing_id = 39
insert into ingredients (ing_name, ing_unit_id) values ('Azeitonas pretas', 2);            # g  | ing_id = 40
insert into ingredients (ing_name, ing_unit_id) values ('Feijão vermelho', 1);             # kg | ing_id = 41
insert into ingredients (ing_name, ing_unit_id) values ('Ervilhas', 1);                    # kg | ing_id = 42
insert into ingredients (ing_name, ing_unit_id) values ('Farinha de trigo', 2);            # g  | ing_id = 43
insert into ingredients (ing_name, ing_unit_id) values ('Açúcar', 2);                      # g  | ing_id = 44
insert into ingredients (ing_name, ing_unit_id) values ('Pão ralado', 2);                  # g  | ing_id = 45
insert into ingredients (ing_name, ing_unit_id) values ('Fermento em pó', 2);              # g  | ing_id = 46
insert into ingredients (ing_name, ing_unit_id) values ('Fermento de padeiro seco', 2);    # g  | ing_id = 47
insert into ingredients (ing_name, ing_unit_id) values ('Chocolate em pó', 2);             # g  | ing_id = 48
insert into ingredients (ing_name, ing_unit_id) values ('Gelatina em pó', 2);              # g  | ing_id = 49
insert into ingredients (ing_name, ing_unit_id) values ('Azeite', 3);                      # L  | ing_id = 50
insert into ingredients (ing_name, ing_unit_id) values ('Molho de tomate', 3);             # L  | ing_id = 51
insert into ingredients (ing_name, ing_unit_id) values ('Polpa de tomate', 3);             # L  | ing_id = 52
insert into ingredients (ing_name, ing_unit_id) values ('Ketchup', 3);                     # L  | ing_id = 53
insert into ingredients (ing_name, ing_unit_id) values ('Maionese', 3);                    # L  | ing_id = 54
insert into ingredients (ing_name, ing_unit_id) values ('Vinagre', 3);                     # L  | ing_id = 55
insert into ingredients (ing_name, ing_unit_id) values ('Água', 3);                        # L  | ing_id = 56
insert into ingredients (ing_name, ing_unit_id) values ('Caldo de peixe', 3);              # L  | ing_id = 57
insert into ingredients (ing_name, ing_unit_id) values ('Sal', 5);                         # un | ing_id = 58
insert into ingredients (ing_name, ing_unit_id) values ('Pimenta preta', 2);               # g  | ing_id = 59
insert into ingredients (ing_name, ing_unit_id) values ('Orégãos secos', 2);               # g  | ing_id = 60
insert into ingredients (ing_name, ing_unit_id) values ('Noz-moscada', 2);                 # g  | ing_id = 61
insert into ingredients (ing_name, ing_unit_id) values ('Cominhos em pó', 2);              # g  | ing_id = 62
insert into ingredients (ing_name, ing_unit_id) values ('Paprika', 2);                     # g  | ing_id = 63
insert into ingredients (ing_name, ing_unit_id) values ('Malagueta', 2);                   # g  | ing_id = 64
insert into ingredients (ing_name, ing_unit_id) values ('Pau de canela', 5);               # un | ing_id = 65
insert into ingredients (ing_name, ing_unit_id) values ('Limão', 5);                       # un | ing_id = 66
insert into ingredients (ing_name, ing_unit_id) values ('Morangos', 2);                    # g  | ing_id = 67
insert into ingredients (ing_name, ing_unit_id) values ('Banana', 2);                      # g  | ing_id = 68
insert into ingredients (ing_name, ing_unit_id) values ('Batata palha', 1);                # kg | ing_id = 69
insert into ingredients (ing_name, ing_unit_id) values ('Fiambre', 2);                     # g  | ing_id = 70


#recipe_ingredients
-- Massa Carbonara
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1,  1, 0.40, 1);     # 0.40 kg Esparguete
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1,  8, 0.15, 1);     # 0.15 kg Bacon em tiras
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1, 16, 4.00, 5);     # 4 un Ovos
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1, 18, 200.00, 2);   # 200 g Natas
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1, 20, 60.00, 2);    # 60 g Queijo parmesão ralado
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1, 28, 2.00, 5);     # 2 un Dentes de alho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1, 50, 0.03, 3);     # 0.03 L Azeite (30 ml)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1, 58, 10.00, 5);    # 10 un Sal
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (1, 59, 5.00, 2);     # 5 g Pimenta preta
-- Frango grelhado com arroz e legumes
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 4, 0.40, 1);      # 0.40 kg Peito de frango
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 12, 1.00, 5);     # 1 un Cebola
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 13, 2.00, 5);     # 2 un Dentes de alho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 50, 2.00, 5);     # 2 un Cenouras
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 28, 1.00, 5);     # 1 un Pimento
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 1, 0.30, 1);      # 0.30 kg Arroz
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 58, 0.03, 3);     # 0.03 L Azeite (30 ml)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 59, 10.00, 5);    # 10 un Sal
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (2, 60, 5.00, 2);     # 5 g Pimenta preta
-- Lasanha de Carne 
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3,  2, 12.00, 5);   # 12 un Placas de lasanha
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 10,  0.50, 1);   # 0.50 kg Carne picada de vaca
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 26,  1.00, 5);   # 1 un Cebola
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 28,  2.00, 5);   # 2 un Dentes de alho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 51,  0.20, 3);   # 0.20 L Molho de tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 21, 150.00, 2);  # 150 g Queijo ralado
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 19,  30.00, 2);  # 30 g Manteiga
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 43,  20.00, 2);  # 20 g Farinha de trigo
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 17,   0.50, 3);  # 0.50 L Leite
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 50,   0.02, 3);  # 0.02 L Azeite (20 ml)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 58,   8.00, 2);  # 8 g Sal
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (3, 59,   3.00, 2);  # 3 g Pimenta preta
-- Arroz de Marisco 
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4,  3, 0.35, 1);   # 0.35 kg Arroz carolino
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 13, 0.30, 1);   # 0.30 kg Miolo de camarão
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 14, 0.25, 1);   # 0.25 kg Miolo de mexilhão
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 15, 0.25, 1);   # 0.25 kg Amêijoas
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 26, 1.00, 5);   # 1 un Cebola
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 28, 3.00, 5);   # 3 un Dentes de alho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 32, 1.00, 5);   # 1 un Pimento vermelho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 51, 0.20, 3);   # 0.20 L Molho de tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 57, 0.80, 3);   # 0.80 L Caldo de peixe
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 50, 0.03, 3);   # 0.03 L Azeite (30 ml)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 58, 8.00, 2);   # 8 g Sal
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 59, 3.00, 2);   # 3 g Pimenta preta
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (4, 37, 2.00, 5);   # 2 un Raminhos de coentros frescos
-- Bacalhau à Brás
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 11, 0.50, 1);    # 0.50 kg Bacalhau desfiado
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 69, 0.20, 1);    # 0.20 kg Batata palha
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 26, 1.00, 5);    # 1 un Cebola
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 28, 2.00, 5);    # 2 un Dentes de alho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 50, 0.03, 3);    # 0.03 L Azeite (30 ml)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 16, 4.00, 5);    # 4 un Ovos
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 38, 5.00, 2);    # 5 g Salsa fresca picada
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 58, 8.00, 2);    # 8 g Sal
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 59, 3.00, 2);    # 3 g Pimenta preta
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (5, 40, 10.00, 2);   # 10 g Azeitonas pretas fatiadas
-- Salmão no Forno com Batatas
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (6, 12, 0.60, 1);     # 0.60 kg Salmão
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (6, 30, 2.00, 1);     # 2 kg Batatas
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (6, 26, 1.00, 5);     # 1 un Cebola
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (6, 28, 2.00, 5);     # 2 un Dentes de alho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (6, 50, 0.04, 3);     # 0.04 L Azeite (40 ml)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (6, 39, 3.00, 5);     # 3 un Alecrim
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (6, 59, 3.00, 2);     # 3 g Pimenta preta 
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (6, 58, 8.00, 2);     # 8 g Sal
-- Salame de Chocolate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (7, 7, 200.00, 2);    # 200 g Bolacha Maria
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (7, 19, 125.00, 2);   # 125 g Manteiga
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (7, 48, 100.00, 2);   # 100 g Chocolate em pó
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (7, 44, 150.00, 2);   # 150 g Açúcar
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (7, 16, 1.00, 5);     # 1 un Ovo
-- Chili com Carne
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 10, 0.50, 1);     # 0.50 kg Carne picada
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 26, 1.00, 5);     # 1 un Cebola
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 28, 2.00, 5);     # 2 un Dentes de alho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 32, 1.00, 5);     # 1 un Pimento vermelho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 41, 0.40, 1);     # 0.40 kg Feijão vermelho cozido
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 51, 0.40, 3);     # 0.40 L Molho de tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 62, 5.00, 2);     # 5 g Cominhos
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 63, 5.00, 2);     # 5 g Paprika
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 64, 1.00, 2);     # 1 g Malagueta
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 58, 5.00, 5);     # 5 un Sal (q.b.)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (8, 59, 3.00, 2);     # 3 g Pimenta preta
-- Panquecas
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (9, 43, 150.00, 2);   # 150 g Farinha de trigo
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (9, 44, 30.00, 2);    # 30 g Açúcar
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (9, 46, 10.00, 2);    # 10 g Fermento em pó
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (9, 16, 2.00, 5);     # 2 un Ovos
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (9, 17, 200.00, 4);   # 200 ml Leite
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (9, 19, 20.00, 2);    # 20 g Manteiga derretida
-- Omelete de Queijo e Fiambre
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (10, 16, 3.00, 5);    # 3 un Ovos
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (10, 70, 60.00, 2);   # 60 g Fiambre
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (10, 22, 40.00, 2);   # 40 g Queijo fatiado
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (10, 19, 10.00, 2);   # 10 g Manteiga
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (10, 58, 1.00, 5);    # 1 un Sal (pitada)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (10, 59, 1.00, 2);    # 1 g Pimenta preta
-- Sopa de Legumes
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (11, 29, 2.00, 5);     # 2 un Cenoura
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (11, 30, 0.30, 1);     # 0.3 kg Batata 
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (11, 26, 1.00, 5);     # 1 un Cebola
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (11, 31, 1.00, 5);     # 1 un Courgette
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (11, 56, 1.00, 3);     # 1 L Água
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (11, 50, 0.03, 3);     # 0.03 L Azeite (30 ml)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (11, 58, 5.00, 5);     # 5 un Sal (q.b.)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (11, 59, 2.00, 2);     # 2 g Pimenta preta
-- Tosta Mista
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (12, 4, 2.00, 5);      # 2 un Pão de forma
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (12, 70, 50.00, 2);    # 50 g Fiambre
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (12, 22, 40.00, 2);    # 40 g Queijo fatiado
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (12, 19, 5.00, 2);     # 5 g Manteiga
-- Wrap de Frango com Alface
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (13, 6, 2.00, 5);      # 2 un Tortilhas de trigo
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (13, 9, 0.20, 1);      # 0.20 kg Peito de frango
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (13, 33, 4.00, 5);     # 4 un Alface (folhas)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (13, 34, 1.00, 5);     # 1 un Tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (13, 54, 15.00, 3);    # 15 ml Maionese
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (13, 59, 1.00, 2);     # 1 g Pimenta preta
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (13, 58, 1.00, 5);     # 1 un Sal (pitada)
-- Hambúrguer Caseiro
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 10, 0.40, 1);     # 0.40 kg Carne picada de vaca
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 5, 2.00, 5);      # 2 un Pão de hambúrguer
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 22, 40.00, 2);    # 40 g Queijo fatiado
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 33, 2.00, 5);     # 2 un Alface (folhas)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 34, 1.00, 5);     # 1 un Tomate (fatiado)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 59, 1.00, 2);     # 1 g Pimenta preta
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 58, 1.00, 5);     # 1 un Sal (pitada)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 53, 15.00, 3);    # 15 ml Ketchup
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (14, 54, 15.00, 3);    # 15 ml Maionese
-- Pizza Caseira
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 43, 200.00, 2);   # 200 g Farinha de trigo
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 47, 5.00, 2);     # 5 g Fermento de padeiro seco
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 56, 120.00, 4);   # 120 ml Água
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 50, 10.00, 3);    # 10 ml Azeite
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 51, 80.00, 3);    # 80 ml Molho de tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 23, 120.00, 2);   # 120 g Mozzarella ralada
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 34, 1.00, 5);     # 1 un Tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 60, 3.00, 2);     # 3 g Orégãos
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (15, 58, 1.00, 5);     # 1 un Sal (pitada)
-- Esparguete à Bolonhesa
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (16, 1, 0.30, 1);     # 0.30 kg Esparguete
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (16, 10, 0.40, 1);    # 0.40 kg Carne picada de vaca
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (16, 26, 1.00, 5);    # 1 un Cebola
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (16, 28, 2.00, 5);    # 2 un Dentes de alho
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (16, 51, 100.00, 3);  # 100 ml Molho de tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (16, 52, 50.00, 3);   # 50 ml Polpa de tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (16, 59, 1.00, 2);    # 1 g Pimenta preta
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (16, 58, 1.00, 5);    # 1 un Sal (pitada)
-- Arroz Doce
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (17, 3, 0.20, 1);    # 0.20 kg Arroz carolino
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (17, 17, 1.00, 3);   # 1 L Leite
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (17, 44, 120.00, 2); # 120 g Açúcar
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (17, 65, 1.00, 5);   # 1 un Pau de canela
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (17, 16, 2.00, 5);   # 2 un Ovos (gemas)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (17, 58, 1.00, 5);   # 1 un Sal (pitada)
-- Gelatina com Iogurte
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (18, 49, 20.00, 2);   # 20 g Gelatina em pó
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (18, 56, 200.00, 4);  # 200 ml Água
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (18, 25, 200.00, 2);  # 200 g Iogurte natural
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (18, 67, 50.00, 2);   # 50 g Morangos (opcional)
-- Salada Mediterrânica
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (19, 33, 4.00, 5);    # 4 un Alface (folhas grandes)
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (19, 34, 2.00, 5);    # 2 un Tomate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (19, 35, 1.00, 5);    # 1 un Pepino
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (19, 40, 50.00, 2);   # 50 g Azeitonas pretas
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (19, 24, 80.00, 2);   # 80 g Queijo feta
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (19, 55, 10.00, 3);   # 10 ml Vinagre
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (19, 50, 20.00, 3);   # 20 ml Azeite
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (19, 60, 2.00, 2);    # 2 g Orégãos secos
-- Bolo de Chocolate
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (20, 43, 200.00, 2);   # 200 g Farinha de trigo
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (20, 48, 80.00, 2);    # 80 g Chocolate em pó
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (20, 44, 150.00, 2);   # 150 g Açúcar
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (20, 16, 3.00, 5);     # 3 un Ovos
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (20, 17, 200.00, 4);   # 200 ml Leite
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (20, 19, 50.00, 2);    # 50 g Manteiga
insert into recipe_ingredients (rgi_rec_id, rgi_ing_id, rgi_qty, rgi_unit_id) values (20, 46, 10.00, 2);    # 10 g Fermento em pó


#supermarkets

insert into supermarkets (sup_name) values ('Lidl Montijo Retail Park');            # sup_id = 1
insert into supermarkets (sup_name) values ('Continente Montijo Alegro');           # sup_id = 2
insert into supermarkets (sup_name) values ('Pingo Doce Montijo Centro');           # sup_id = 3
insert into supermarkets (sup_name) values ('Mercadona Montijo');                   # sup_id = 4
insert into supermarkets (sup_name) values ('Aldi Montijo');                        # sup_id = 5
insert into supermarkets (sup_name) values ('Minipreço Bairro Afonso');             # sup_id = 6
insert into supermarkets (sup_name) values ('Intermarché Montijo');                 # sup_id = 7
insert into supermarkets (sup_name) values ('Lidl Barreiro');                       # sup_id = 8
insert into supermarkets (sup_name) values ('Continente Modelo Baixa da Banheira'); # sup_id = 9
insert into supermarkets (sup_name) values ('Pingo Doce Baixa da Banheira');        # sup_id = 10


#saved_places

insert into saved_places (sav_usr_id, sav_sup_id) values (1, 1);  # Rodrigo
insert into saved_places (sav_usr_id, sav_sup_id) values (1, 2);  # Rodrigo
insert into saved_places (sav_usr_id, sav_sup_id) values (1, 4);  # Rodrigo
insert into saved_places (sav_usr_id, sav_sup_id) values (2, 2);  # Daibert
insert into saved_places (sav_usr_id, sav_sup_id) values (2, 3);  # Daibert
insert into saved_places (sav_usr_id, sav_sup_id) values (2, 7);  # Daibert
insert into saved_places (sav_usr_id, sav_sup_id) values (3, 1);  # Marco
insert into saved_places (sav_usr_id, sav_sup_id) values (3, 5);  # Marco
insert into saved_places (sav_usr_id, sav_sup_id) values (4, 8);  # Luis
insert into saved_places (sav_usr_id, sav_sup_id) values (4, 9);  # Luis
insert into saved_places (sav_usr_id, sav_sup_id) values (5, 10); # Sandra
insert into saved_places (sav_usr_id, sav_sup_id) values (5, 3);  # Sandra


commit;
          