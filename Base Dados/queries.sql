-- 1) Membros de cada grupo e respetivos papeis
 
select grp_name, usr_name, mem_role, mem_joined_at
FROM memberships 
join users on usr_id = mem_usr_id
join groupss on grp_id = mem_grp_id
order by grp_name, mem_role desc, usr_name

-- 2. Ver owners de cada grupo
select grp_name , usr_name as owner
from memberships
join users   on usr_id = mem_usr_id
join groupss on grp_id = mem_grp_id
where mem_role = 'owner'
order by grp_name

-- 3) Itens pendentes por grupo 

select grp_name, count(*) itens_pendentes
from groupss 
join shopping_lists on lst_grp_id = grp_id
join list_items on itm_lst_id = lst_id and itm_done = 0
group by grp_id
order by itens_pendentes desc

-- 4) Percentagem de conclusao de cada lista 

select lst_title, round(sum(itm_done = 1)/count(itm_id)*100, 1) percentange
from shopping_lists 
join list_items on itm_lst_id = lst_id
group by lst_id, lst_title
order by percentange desc

-- 5) Supermercados favoritos de um user por distancia guardada, substituir sav_user_id pelo utilizador pretendido (ex: sav_usr_id = 1)

select usr_name, sup_name, sav_label, sav_distance
from saved_places 
join users on usr_id = sav_usr_id
join supermarkets on sup_id = sav_sup_id
where sav_usr_id = 2
order by sav_distance asc

-- 6) Top contribuidores (quem mais mexe em itens) 

select usr_name, count(*) edits
from memberships
join users on usr_id = mem_usr_id
join shopping_lists on lst_grp_id = mem_grp_id
join list_items on itm_lst_id = lst_id
group by usr_id, usr_name
order by edits desc

-- 7. Procurar utilizadores email(@gmail.com)
select usr_name, usr_email, usr_created_at
from users
where usr_email like '%@gmail.com'
order by usr_created_at desc

-- 8. Listas com nº de itens abertos e total
select l.lst_id, l.lst_title, v.open_items, v.total_items, l.lst_created_at
from vw_list_open_items v
join shopping_lists l on l.lst_id = v.lst_id
order by v.open_items desc, l.lst_created_at desc

-- 9. Escalar uma receita para 4 pessoas

select rec_name, rin_name, rin_qty_serving * 4 as qty_for_4_people, rin_unit
from recipes
join recipe_ingredients on recipe_ingredients.rin_rec_id = recipes.rec_id
where rec_id = 3;

-- 10. Receitas que usam um ingrediente 

select rec_id, rec_name, rin_name, rin_qty_serving, rin_unit
from recipe_ingredients
join recipes on recipes.rec_id = recipe_ingredients.rin_rec_id
where rin_name like '%ovo%'
order by rec_name, rin_name;

-- 11. Supermercados por rating e distância

select sup_id, sup_name, sup_rating, sup_distance
from supermarkets
order by sup_rating desc, sup_distance asc, sup_name

-- 12. Locais guardados de um utilizador

select sav_id, sup_name, sav_label, sav_distance, sav_created_at
from saved_places
join supermarkets on supermarkets.sup_id = saved_places.sav_sup_id
where sav_usr_id = 2
order by sav_created_at desc

-- 13. Top 3 utilizadores com mais listas 

select usr_id, usr_name, count(distinct lst_id) as total_lists
from users
join memberships on memberships.mem_usr_id = users.usr_id
join shopping_lists on shopping_lists.lst_grp_id = memberships.mem_grp_id
group by usr_id, usr_name
order by total_lists desc, usr_name
limit 3;

-- 14. Itens mais frequentes em todas as listas
select itm_name as item, count(*) as vezes
from list_items
group by itm_name
order by vezes desc, item asc



