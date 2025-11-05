## 1. Base de Dados (BD report)

Este capítulo apresenta o modelo de dados utilizado no projeto **CoBuy**, incluindo o diagrama conceptual, a definição das entidades e relações, o dicionário de dados e os exemplos que compõem a base de dados de referência para testes.

A base de dados reflete os requisitos funcionais da aplicação, permitindo gerir utilizadores, grupos, listas de compras partilhadas, itens, receitas pré-definidas e locais de compras favoritos.

---

### 1.1 Modelo Conceptual (MER)

O sistema assenta nas seguintes entidades principais:

- User
- Groupss
- Memberships (ligação N:N entre Users e Groups)
- Shopping Lists
- List Items
- Recipes
- Recipe Ingredients
- Supermarkets
- Saved Places (ligação N:N entre Users e Supermarkets)
  
#### Diagrama MER

<img width="600" height="1200" alt="MER drawio-1" src="https://github.com/user-attachments/assets/3aa71048-3044-4ffe-bea6-938de18dc1c7" />

Relações:

- Um utilizador pode pertencer a vários grupos e cada grupo tem varios utilizadores(`memberships`);
- Um grupo pode ter várias listas de compras;
- Cada lista contém vários itens;
- O utilizador pode consultar receitas pré-definidas, consultado os igredientes necessarios;
- O utizador pode consultar os supermecados mais porximos de si e guardar supermercados como favoritos para referência rápida.

---

### 1.2 Dicionário de Dados

#### Tabela: users

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| usr_id | INT | PK | Identificador do utilizador |
| usr_name | VARCHAR(80) |  | Nome do utilizador |
| usr_email | VARCHAR(120) | UQ | Email único |
| usr_password | VARCHAR(200) |  | Password cifrada |
| usr_created_at | DATETIME |  | Data de criação |

#### Tabela: groupss

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| grp_id | INT | PK | Identificador do grupo |
| grp_name | VARCHAR(80) | UQ | Nome do grupo |
| grp_created_at | DATETIME |  | Data de criação |

#### Tabela: memberships

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| mem_id | INT | PK | ID associação user-group |
| mem_usr_id | INT | FK → users | Utilizador associado |
| mem_grp_id | INT | FK → groupss | Grupo associado |
| mem_role | VARCHAR(10) |  | owner / member |
| mem_joined_at | DATETIME |  | Data de adesão |

#### Tabela: shopping_lists

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| lst_id | INT | PK | ID lista |
| lst_grp_id | INT | FK → groupss | Grupo dono |
| lst_title | VARCHAR(80) |  | Nome |
| lst_created_at | DATETIME |  | Data de criação |

#### Tabela: list_items

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| itm_id | INT | PK | ID item |
| itm_lst_id | INT | FK → shopping_lists | Lista associada |
| itm_name | VARCHAR(120) |  | Nome |
| itm_qty | DECIMAL(10,2) |  | Quantidade |
| itm_unit | VARCHAR(16) |  | Unidade |
| itm_done | BOOLEAN |  | Feito / não feito |
| itm_updated_at | TIMESTAMP |  | Última atualização |

#### Tabela: recipes

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| rec_id | INT | PK | ID receita |
| rec_usr_id | INT | FK → users | Autor da receita |
| rec_name | VARCHAR(120) |  | Nome |
| rec_serves | INT |  | Nº porções |

#### Tabela: recipe_ingredients

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| rin_id | INT | PK | ID ingrediente |
| rin_rec_id | INT | FK → recipes | Receita associada |
| rin_name | VARCHAR(120) |  | Nome do ingrediente |
| rin_qty_serving | DECIMAL(10,2) |  | Quantidade por dose |
| rin_unit | VARCHAR(16) |  | Unidade |

#### Tabela: supermarkets

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| sup_id | INT | PK | ID supermercado |
| sup_name | VARCHAR(120) | UQ | Nome |
| sup_rating | DECIMAL(2,1) |  | Avaliação |
| sup_distance | DECIMAL(6,2) |  | Distância ao utilizador (km) |

#### Tabela: saved_places

| Campo | Tipo | Chave | Descrição |
|------|------|-------|-----------|
| sav_id | INT | PK | ID favorito |
| sav_usr_id | INT | FK → users | Utilizador |
| sav_sup_id | INT | FK → supermarkets | Supermercado |
| sav_label | VARCHAR(80) |  | Rótulo |
| sav_distance | DECIMAL(6,2) |  | Distância |
| sav_created_at | DATETIME |  | Data de registo |

---

### 1.3 Guia de Dados (exemplo)

| Tabela | Nº Registos | Exemplos |
|---|---:|---|
| users | 8 | Rodrigo, Daibert, Marco… |
| groupss | 4 | Casa A, Festa… |
| memberships | 9 | owner/member |
| shopping_lists | 5 | Compras Semanais… |
| list_items | 12 | Leite, Pão, Arroz… |
| recipes | 4 | Massa com atum… |
| recipe_ingredients | 14 | Massa 100g… |
| supermarkets | 6 | Continente, Lidl… |
| saved_places | 6 | Perto de casa… |

---

### 1.4 Scripts SQL

> Scripts completos incluídos na entrega da tarefa:
- `creates.sql`
- `populate.sql`

---

### 1.5 Conclusão

A base de dados do projeto CoBuy foi desenhada de forma relacional, garantindo integridade e escalabilidade.  
Esta primeira versão cumpre os requisitos para a entrega intermédia e será expandida ao longo do desenvolvimento do projeto.
