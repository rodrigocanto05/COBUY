create table users (
    usr_id         int not null auto_increment,
    usr_name       varchar(80) not null,              #user name
    usr_email      varchar(120) not null,             #user email
    usr_password   varchar(200) not null,
    usr_gender char(1) not null,
    usr_created_at datetime not null default current_timestamp,
    primary key (usr_id),
    unique key uq_users_email (usr_email)
);

create table memberships (
    mem_id        int not null auto_increment,
    mem_usr_id    int not null,                  #FK to users
    mem_grp_id    int not null,                  #FK to groupss
    mem_role      varchar(10) not null,
    mem_joined_at datetime not null default current_timestamp,
    primary key (mem_id)
); 

create table groupss (
    grp_id           int not null auto_increment,
    grp_name         varchar(80) not null,
    grp_code         varchar(5) not null unique,
    grp_owner_usr_id int not null,              #FK to users
    grp_created_at   datetime not null default current_timestamp,
    primary key (grp_id)

);

create table lists (
    lst_id         int not null auto_increment,
    lst_grp_id     int not null,                      #FK to groupss
    lst_title     varchar(80) not null,             
    lst_created_at datetime not null default current_timestamp,
    primary key (lst_id)

);	    

create table list_items (
    li_id       int not null auto_increment,
    li_lst_id   int not null,   -- FK to lists
    li_item_id  int not null,   -- FK to items
    li_usr_id   int not null,   -- FK to users
    li_qty      decimal(10,2),
    li_unit_id  int not null,   -- FK to unit
    li_done     boolean not null default false,
    primary key (li_id)
);

create table items (
    it_id      int not null auto_increment,
    it_name    varchar(120) not null,      
    it_unit_id int not null,                #FK to unit
    primary key (it_id)
);
		            		                 		     

create table recipes (
    rec_id     int not null auto_increment,
    rec_name   varchar(120) not null,
    primary key (rec_id)
);                    

create table recipe_ingredients (
    rgi_id          int not null auto_increment,
    rgi_rec_id      int not null,               #FK to recipes
    rgi_ing_id      int not null,               #FK ingredients
    rgi_qty         decimal(10,2) not null,
    rgi_unit_id     int not null,               #FK to units
    primary key (rgi_id)
);

create table ingredients ( 
    ing_id      int not null auto_increment,
    ing_name    varchar(120) not null,   
    ing_unit_id int not null,            #FK to unit
    primary key (ing_id),
    unique key uq_ing_name (ing_name)
);


create table unit (
    uni_id   int not null auto_increment,
    uni_name varchar(16) not null,   #abreviatura: kg, g, L, ml, un.
    primary key (uni_id)
);

create table supermarkets (
    sup_id       int not null auto_increment,
    sup_name     varchar(120) not null,               
    primary key (sup_id)
);

create table saved_places (
    sav_id         int not null auto_increment,
    sav_usr_id     int not null,                      # FK -> users
    sav_sup_id     int not null,                      # FK -> supermarkets
    primary key (sav_id),
    unique key uq_saved_user_sup (sav_usr_id, sav_sup_id)
);
    
-- Foreign Keys

alter table memberships
add constraint memberships_fk_user
foreign key (mem_usr_id) references users(usr_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table memberships
add constraint memberships_fk_group
foreign key (mem_grp_id) references groupss(grp_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table groupss
add constraint groupss_fk_owner
foreign key (grp_owner_usr_id) references users(usr_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table lists
add constraint lists_fk_group
foreign key (lst_grp_id) references groupss(grp_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table list_items
add constraint list_items_fk_list
foreign key (li_lst_id) references lists(lst_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table list_items
add constraint list_items_fk_item
foreign key (li_item_id) references items(it_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table list_items
add constraint list_items_fk_user
foreign key (li_usr_id) references users(usr_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table list_items
add constraint list_items_fk_unit
foreign key (li_unit_id) references unit(uni_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table items
add constraint items_fk_unit
foreign key (it_unit_id) references unit(uni_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table recipe_ingredients
add constraint recipe_ingredients_fk_recipe
foreign key (rgi_rec_id) references recipes(rec_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table recipe_ingredients
add constraint recipe_ingredients_fk_ingredient
foreign key (rgi_ing_id) references ingredients(ing_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table recipe_ingredients
add constraint recipe_ingredients_fk_unit
foreign key (rgi_unit_id) references unit(uni_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table ingredients
add constraint ingredients_fk_unit
foreign key (ing_unit_id) references unit(uni_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table saved_places
add constraint saved_places_fk_user
foreign key (sav_usr_id) references users(usr_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table saved_places
add constraint saved_places_fk_supermarket
foreign key (sav_sup_id) references supermarkets(sup_id)
ON DELETE NO ACTION ON UPDATE NO ACTION;