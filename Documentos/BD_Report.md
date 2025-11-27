## 1. Base de Dados (BD report)

Este capítulo apresenta o modelo de dados utilizado no projeto **CoBuy**, incluindo o diagrama conceptual, a definição das entidades e relações, o dicionário de dados e os exemplos que compõem a base de dados de referência para testes.

A base de dados reflete os requisitos funcionais da aplicação, permitindo gerir utilizadores, grupos, listas de compras partilhadas, itens, receitas pré-definidas e locais de compras favoritos.

---

### 1.1 Modelo Conceptual (MER)

O sistema assenta nas seguintes entidades:

- User
- Groups
- Memberships 
- Lists
- List Items
- Items
- Recipes
- Recipe Ingredients
- Ingredients
- Unit
- Supermarkets
- Saved Places 
  
#### Diagrama MER

<img width="700" height="700" alt="Captura de ecrã 2025-11-18, às 10 22 53" src="https://github.com/user-attachments/assets/9eb1af67-40ff-4304-8bf2-5da28465d5c0" />

---

### 1.2 Dicionário de Dados

# Dicionário de Dados

## Tabela: `users`
| Atributo       | Tipo de dado     | Tamanho | Restrição                       | Descrição                         |
| -------------- | ---------------- | ------- | ------------------------------- | --------------------------------- |
| usr_id         | numérico inteiro | —       | Chave primária / Auto Increment | Identificador único do utilizador |
| usr_name       | alfanumérico     | 80      | Obrigatório                     | Nome do utilizador                |
| usr_email      | alfanumérico     | 120     | Único / Obrigatório             | Email do utilizador               |
| usr_password   | alfanumérico     | 200     | Obrigatório                     | Palavra-passe encriptada          |
| usr_gender     | char             | 1       | Obrigatório                     | Género do utilizador              |
| usr_created_at | datetime         | —       | Default CURRENT_TIMESTAMP       | Data de criação do registo        |


## Tabela: `memberships`
| Atributo      | Tipo de dado     | Tamanho | Restrição                       | Descrição                           |
| ------------- | ---------------- | ------- | ------------------------------- | ----------------------------------- |
| mem_id        | numérico inteiro | —       | Chave primária / Auto Increment | Identificador da associação         |
| mem_usr_id    | numérico inteiro | —       | FK → users.usr_id               | ID do utilizador associado          |
| mem_grp_id    | numérico inteiro | —       | FK → groupss.grp_id             | ID do grupo                         |
| mem_role      | alfanumérico     | 10      | Obrigatório                     | Função no grupo (ex: owner, member) |
| mem_joined_at | datetime         | —       | Default CURRENT_TIMESTAMP       | Quando o utilizador entrou no grupo |


## Tabela: `groupss`
| Atributo         | Tipo de dado | Tamanho | Restrição                 | Descrição                         |
| ---------------- | ------------ | ------- | ------------------------- | --------------------------------- |
| grp_id           | inteiro      | —       | Chave primária            | Identificador do grupo            |
| grp_name         | alfanumérico | 80      | Obrigatório               | Nome do grupo                     |
| grp_code         | alfanumérico | 5       | Único                     | Código usado para entrar no grupo |
| grp_owner_usr_id | inteiro      | —       | FK → users.usr_id         | Dono/criador do grupo             |
| grp_created_at   | datetime     | —       | Default CURRENT_TIMESTAMP | Data de criação                   |


## Tabela: `lists`
| Atributo       | Tipo de dado | Tamanho | Restrição                 | Descrição                      |
| -------------- | ------------ | ------- | ------------------------- | ------------------------------ |
| lst_id         | inteiro      | —       | Chave primária            | Identificador da lista         |
| lst_grp_id     | inteiro      | —       | FK → groupss.grp_id       | Grupo ao qual a lista pertence |
| lst_title      | alfanumérico | 80      | Obrigatório               | Nome/título da lista           |
| lst_created_at | datetime     | —       | Default CURRENT_TIMESTAMP | Data de criação                |


## Tabela: `list_items`
| Atributo   | Tipo de dado | Tamanho | Restrição         | Descrição                        |
| ---------- | ------------ | ------- | ----------------- | -------------------------------- |
| li_id      | inteiro      | —       | Chave primária    | Identificador do item            |
| li_lst_id  | inteiro      | —       | FK → lists.lst_id | Lista onde o item está inserido  |
| li_item_id | inteiro      | —       | FK → items.it_id  | Referência ao item               |
| li_usr_id  | inteiro      | —       | FK → users.usr_id | Autor que adicionou o item       |
| li_qty     | decimal      | 10,2    | —                 | Quantidade desejada              |
| li_unit_id | inteiro      | —       | FK → unit.uni_id  | Unidade de medida                |
| li_done    | boolean      | —       | Default FALSE     | Indica se o item já foi comprado |


## Tabela: `items`
| Atributo   | Tipo de dado | Tamanho | Restrição        | Descrição              |
| ---------- | ------------ | ------- | ---------------- | ---------------------- |
| it_id      | inteiro      | —       | Chave primária   | Identificador          |
| it_name    | alfanumérico | 120     | Obrigatório      | Nome do item           |
| it_unit_id | inteiro      | —       | FK → unit.uni_id | Unidade padrão do item |


## Tabela: `recipes`
| Atributo | Tipo de dado | Tamanho | Restrição      | Descrição                |
| -------- | ------------ | ------- | -------------- | ------------------------ |
| rec_id   | inteiro      | —       | Chave primária | Identificador da receita |
| rec_name | alfanumérico | 120     | Obrigatório    | Nome da receita          |


## Tabela: `recipe_ingredients`
| Atributo    | Tipo de dado | Tamanho | Restrição               | Descrição             |
| ----------- | ------------ | ------- | ----------------------- | --------------------- |
| rgi_id      | inteiro      | —       | Chave primária          | Identificador         |
| rgi_rec_id  | inteiro      | —       | FK → recipes.rec_id     | Receita associada     |
| rgi_ing_id  | inteiro      | —       | FK → ingredients.ing_id | Ingrediente usado     |
| rgi_qty     | decimal      | 10,2    | Obrigatório             | Quantidade necessária |
| rgi_unit_id | inteiro      | —       | FK → unit.uni_id        | Unidade de medida     |


## Tabela: `ingredients`
| Atributo    | Tipo de dado | Tamanho | Restrição           | Descrição           |
| ----------- | ------------ | ------- | ------------------- | ------------------- |
| ing_id      | inteiro      | —       | Chave primária      | Identificador       |
| ing_name    | alfanumérico | 120     | Único / Obrigatório | Nome do ingrediente |
| ing_unit_id | inteiro      | —       | FK → unit.uni_id    | Unidade de medida   |


## Tabela: `unit`
| Atributo | Tipo de dado | Tamanho | Restrição      | Descrição                                    |
| -------- | ------------ | ------- | -------------- | -------------------------------------------- |
| uni_id   | inteiro      | —       | Chave primária | Identificador                                |
| uni_name | alfanumérico | 16      | Obrigatório    | Abreviatura da unidade (kg, g, ml, un, etc.) |


## Tabela: `supermarkets`
| Atributo | Tipo de dado | Tamanho | Restrição      | Descrição            |
| -------- | ------------ | ------- | -------------- | -------------------- |
| sup_id   | inteiro      | —       | Chave primária | Identificador        |
| sup_name | alfanumérico | 120     | Obrigatório    | Nome do supermercado |


## Tabela: `saved_places`
| Atributo   | Tipo de dado | Tamanho | Restrição                | Descrição              |
| ---------- | ------------ | ------- | ------------------------ | ---------------------- |
| sav_id     | inteiro      | —       | Chave primária           | Identificador          |
| sav_usr_id | inteiro      | —       | FK → users.usr_id        | Utilizador que guardou |
| sav_sup_id | inteiro      | —       | FK → supermarkets.sup_id | Supermercado guardado  |

---

### 1.3 Guia de Dados (exemplo)


---

### 1.4 Scripts SQL

> Scripts completos incluídos na entrega da tarefa:
- `creates.sql`
- `populate.sql`

---

### 1.5 Conclusão

A base de dados do projeto CoBuy foi desenhada de forma relacional, garantindo integridade e escalabilidade.  
Esta primeira versão cumpre os requisitos para a entrega intermédia e será expandida ao longo do desenvolvimento do projeto.
