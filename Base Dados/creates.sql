SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 1;

-- =========================
-- TABELAS
-- =========================

-- USERS
CREATE TABLE IF NOT EXISTS users (
  usr_id         INT NOT NULL AUTO_INCREMENT,
  usr_name       VARCHAR(80)  NOT NULL,
  usr_email      VARCHAR(120) NOT NULL,
  usr_password   VARCHAR(200) NOT NULL,              -- hash (não guardar password em claro)
  usr_created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (usr_id),
  UNIQUE KEY uq_users_email (usr_email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- GROUPS (usa "groupss" para evitar palavra reservada)
CREATE TABLE IF NOT EXISTS groupss (
  grp_id         INT NOT NULL AUTO_INCREMENT,
  grp_name       VARCHAR(80) NOT NULL,
  grp_created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (grp_id),
  UNIQUE KEY uq_groups_name (grp_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- MEMBERSHIPS (N:N users <-> groupss)
CREATE TABLE IF NOT EXISTS memberships (
  mem_id        INT NOT NULL AUTO_INCREMENT,
  mem_usr_id    INT NOT NULL,
  mem_grp_id    INT NOT NULL,
  mem_role      VARCHAR(10) NOT NULL,               -- 'owner' | 'member'
  mem_joined_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (mem_id),
  UNIQUE KEY uq_memberships_user_group (mem_usr_id, mem_grp_id),
  KEY idx_mem_user (mem_usr_id),
  KEY idx_mem_group (mem_grp_id),
  CONSTRAINT memberships_fk_user
    FOREIGN KEY (mem_usr_id) REFERENCES users(usr_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT memberships_fk_group
    FOREIGN KEY (mem_grp_id) REFERENCES groupss(grp_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT chk_mem_role CHECK (mem_role IN ('owner','member'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- SHOPPING LISTS (por grupo)
CREATE TABLE IF NOT EXISTS shopping_lists (
  lst_id         INT NOT NULL AUTO_INCREMENT,
  lst_grp_id     INT NOT NULL,
  lst_title      VARCHAR(80) NOT NULL,
  lst_created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (lst_id),
  KEY idx_lst_group (lst_grp_id),
  CONSTRAINT shopping_lists_fk_group
    FOREIGN KEY (lst_grp_id) REFERENCES groupss(grp_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- LIST ITEMS
CREATE TABLE IF NOT EXISTS list_items (
  itm_id         INT NOT NULL AUTO_INCREMENT,
  itm_lst_id     INT NOT NULL,
  itm_name       VARCHAR(120) NOT NULL,
  itm_qty        DECIMAL(10,2) DEFAULT NULL,
  itm_unit       VARCHAR(16)   DEFAULT NULL,
  itm_done       BOOLEAN NOT NULL DEFAULT FALSE,
  itm_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                              ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (itm_id),
  KEY idx_itm_list (itm_lst_id),
  CONSTRAINT list_items_fk_list
    FOREIGN KEY (itm_lst_id) REFERENCES shopping_lists(lst_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT chk_itm_qty_nonneg CHECK (itm_qty IS NULL OR itm_qty >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- RECIPES (do utilizador)
CREATE TABLE IF NOT EXISTS recipes (
  rec_id     INT NOT NULL AUTO_INCREMENT,
  rec_usr_id INT NULL,                              -- se apagar user, deixamos NULL
  rec_name   VARCHAR(120) NOT NULL,
  rec_serves INT NOT NULL DEFAULT 2,
  PRIMARY KEY (rec_id),
  KEY idx_rec_user (rec_usr_id),
  CONSTRAINT recipes_fk_user
    FOREIGN KEY (rec_usr_id) REFERENCES users(usr_id)
    ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT chk_rec_serves_pos CHECK (rec_serves > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- RECIPE INGREDIENTS (qty por porção)
CREATE TABLE IF NOT EXISTS recipe_ingredients (
  rin_id          INT NOT NULL AUTO_INCREMENT,
  rin_rec_id      INT NOT NULL,
  rin_name        VARCHAR(120) NOT NULL,
  rin_qty_serving DECIMAL(10,2) NOT NULL,
  rin_unit        VARCHAR(16),
  PRIMARY KEY (rin_id),
  KEY idx_rin_recipe (rin_rec_id),
  CONSTRAINT recipe_ingredients_fk_recipe
    FOREIGN KEY (rin_rec_id) REFERENCES recipes(rec_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT chk_rin_qty_nonneg CHECK (rin_qty_serving >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- SUPERMARKETS
CREATE TABLE IF NOT EXISTS supermarkets (
  sup_id       INT NOT NULL AUTO_INCREMENT,
  sup_name     VARCHAR(120) NOT NULL,
  sup_rating   DECIMAL(2,1),
  sup_distance DECIMAL(6,2),                        -- km estimados
  PRIMARY KEY (sup_id),
  UNIQUE KEY uq_supermarkets_name (sup_name),
  CONSTRAINT chk_sup_rating_range CHECK (sup_rating IS NULL OR (sup_rating >= 0.0 AND sup_rating <= 5.0)),
  CONSTRAINT chk_sup_distance_nonneg CHECK (sup_distance IS NULL OR sup_distance >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- SAVED PLACES (favoritos user <-> supermarket)
CREATE TABLE IF NOT EXISTS saved_places (
  sav_id         INT NOT NULL AUTO_INCREMENT,
  sav_usr_id     INT NOT NULL,
  sav_sup_id     INT NOT NULL,
  sav_label      VARCHAR(80),
  sav_distance   DECIMAL(6,2),
  sav_created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (sav_id),
  UNIQUE KEY uq_saved_user_market (sav_usr_id, sav_sup_id),
  KEY idx_sav_user (sav_usr_id),
  KEY idx_sav_market (sav_sup_id),
  CONSTRAINT saved_places_fk_user
    FOREIGN KEY (sav_usr_id) REFERENCES users(usr_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT saved_places_fk_supermarket
    FOREIGN KEY (sav_sup_id) REFERENCES supermarkets(sup_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT chk_sav_distance_nonneg CHECK (sav_distance IS NULL OR sav_distance >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- VIEW(S)
-- =========================
DROP VIEW IF EXISTS vw_list_open_items;
CREATE VIEW vw_list_open_items AS
SELECT l.lst_id, l.lst_title,
       COUNT(i.itm_id) AS total_items,
       SUM(CASE WHEN i.itm_done = 0 THEN 1 ELSE 0 END) AS open_items
FROM shopping_lists l
LEFT JOIN list_items i ON i.itm_lst_id = l.lst_id
GROUP BY l.lst_id, l.lst_title;

-- =========================
-- STORED PROCEDURE(S)
-- =========================
DROP PROCEDURE IF EXISTS sp_recipe_scaled;
DELIMITER $$
CREATE PROCEDURE sp_recipe_scaled(IN p_recipe_id INT, IN p_people INT)
BEGIN
  SELECT r.rec_name,
         p_people AS serves,
         rin.rin_name,
         (rin.rin_qty_serving * p_people) AS qty_total,
         rin.rin_unit
  FROM recipes r
  JOIN recipe_ingredients rin ON rin.rin_rec_id = r.rec_id
  WHERE r.rec_id = p_recipe_id;
END$$
DELIMITER ;