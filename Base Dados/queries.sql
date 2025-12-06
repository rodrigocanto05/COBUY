-- selecionar utilizadores do género masculino com email hotmail
select usr_name, usr_email, usr_gender
from users
where usr_gender = 'M'
and usr_email like '%hotmail%'

-- selecionar todos os grupos em que um utilizador participa, ordenados dos mais recentes para os mais antigos
select grp_name, grp_code, grp_created_at
from memberships
join groupss on mem_grp_id = grp_id
where mem_usr_id = 1
order by grp_created_at desc

-- selecionar todos os membros de um grupo, ordenados alfabeticamente
select usr_name, usr_email, usr_gender
from memberships
join users on mem_usr_id = usr_id
where mem_grp_id = 3
order by usr_name asc

-- selecionar as listas de um grupo ordenadas pela quantidade de itens (da maior para a menor)
select lst_title, count(li_lst_id) total_itens
from lists
join list_items on lst_id = li_lst_id
where lst_grp_id = 2
group by lst_title
order by total_itens desc

-- selecionar os items mais usados nas listas de compras, ordenados do mais usado para o menos usado
select it_name, count(li_item_id) total_utilizacoes
from items
join list_items on it_id = li_item_id
group by it_name
order by total_utilizacoes desc

-- selecionar os items das listas que utilizam a unidade 'L', ordenados pelos mais utilizados
select it_name, count(li_item_id) total_utilizacoes
from items
join list_items on it_id = li_item_id
join unit on it_unit_id = uni_id
where uni_name = 'L'
group by it_name
order by total_utilizacoes desc

-- selecionar as listas que mais utilizam os itens 'arroz' ou 'pão'
select lst_title, count(li_item_id) total_utilizacoes
from lists
join list_items on lst_id = li_lst_id
join items on it_id = li_item_id
where it_name in ('Arroz', 'Pão')
group by lst_title
order by total_utilizacoes desc;

-- selecionar as receitas que utilizam menos ingredientes, ordenadas da que usa menos para a que usa mais
select rec_name, count(rgi_ing_id) total_ingredientes
from recipes
join recipe_ingredients on rec_id = rgi_rec_id
group by rec_name
order by total_ingredientes asc

-- selecionar os supermercados mais guardados por todos os utilizadores, ordenados do mais guardado para o menos guardado
select sup_name, count(sav_sup_id) as total_guardados
from supermarkets
join saved_places on sup_id = sav_sup_id
group by sup_name
order by total_guardados desc;

-- selecionar utilizadores que ainda não pertencem a nenhum grupo
select usr_id, usr_name, usr_email
from users
where usr_id not in (select mem_usr_id from memberships)

-- selecionar utilizadores que sao donos de grupos
select usr_name, usr_email, count(grp_id) total_grupos_criados
from users
join groupss on usr_id = grp_owner_usr_id
group by usr_id
order by total_grupos_criados desc

-- selecionar listas com percentagem de itens concluidos
select lst_title, (sum(case when li_done = true then 1 else 0 end) / count(li_id)) * 100 percentagem_concluida
from lists
join list_items on lst_id = li_lst_id
group by lst_id
order by percentagem_concluida desc

-- selecionar supermercados que nunca foram guardados
select sup_name
from supermarkets
left join saved_places on sup_id = sav_sup_id
where sav_sup_id is null

-- selecionar utilizadores que mais adicionaram itens 
select usr_name, count(li_id) total_itens
from list_items
join users on usr_id = li_usr_id
join lists on lst_id = li_lst_id
join memberships on mem_usr_id = li_usr_id and mem_grp_id = lst_grp_id
group by usr_id
order by total_itens desc;