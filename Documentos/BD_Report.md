## 1. Base de Dados (BD report)

Este capítulo apresenta o modelo de dados utilizado no projeto **CoBuy**, incluindo o diagrama MER, a definição das entidades e relações, o dicionário de dados e os exemplos que compõem a base de dados de referência para testes. A base de dados reflete os requisitos funcionais da aplicação, permitindo gerir utilizadores, grupos, listas de compras partilhadas, itens, receitas e locais de compras favoritos.

---

### 1.1 Modelo Conceptual (MER)

O Modelo Entidade-Relação do sistema CoBuy organiza os dados essenciais para a gestão colaborativa de compras. O modelo estrutura-se em torno das entidades Users, Groups e Memberships, que permitem a associação de múltiplos utilizadores a múltiplos grupos. A gestão das compras é suportada pelas entidades Lists, List Items e Items, assegurando a criação e partilha de listas com itens reutilizáveis.

As entidades Recipes, Ingredients, Recipe Ingredients e Unit permitem representar receitas e os respetivos ingredientes de forma estruturada, recorrendo a relações N:N normalizadas. Adicionalmente, Supermarkets e Saved Places armazenam locais de compra preferidos com informação geográfica.

De forma geral, o MER garante coerência, normalização e suporte direto aos requisitos funcionais da aplicação, articulando utilizadores, grupos, listas, receitas e supermercados num modelo de dados consistente e escalável.
  
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
| Atributo | Tipo de dado | Tamanho | Restrição      | Descrição                 |
| -------- | ------------ | ------- | -------------- | ------------------------- |
| sup_id   | inteiro      | —       | Chave primária | Identificador             |
| sup_name | alfanumérico | 120     | Obrigatório    | Nome do supermercado      |
| sup_lat  |  double      | -       | Opcional       | Latitude do supermercado  |
| sup_lng  |  double      | -       | Opcional       | Longitude do supermercado |




## Tabela: `saved_places`
| Atributo   | Tipo de dado | Tamanho | Restrição                | Descrição              |
| ---------- | ------------ | ------- | ------------------------ | ---------------------- |
| sav_id     | inteiro      | —       | Chave primária           | Identificador          |
| sav_usr_id | inteiro      | —       | FK → users.usr_id        | Utilizador que guardou |
| sav_sup_id | inteiro      | —       | FK → supermarkets.sup_id | Supermercado guardado  |

---

### 1.3 Guia de Dados 

## Introdução
O Guia de Dados descreve a estrutura lógica da Base de Dados, explicando o propósito de cada tabela, as relações existentes e ilustrando exemplos reais de registos.

### Tabela: `users`
Armazena todos os utilizadores da aplicação.

**Funções principais:**
- Criar grupos
- Participar em grupos
- Adicionar itens a listas
- Guardar supermercados favoritos

#### Exemplos de registos

| usr_id | usr_name       | usr_email                  | usr_gender | usr_created_at      |
|--------|----------------|----------------------------|------------|---------------------|
| 1      | Rodrigo Canto  | rodrigocanto@hotmail.com   | M          | 2025-10-20 00:00:00 |
| 2      | Rodrigo Daibert| rodrigodaibert@hotmail.com | M          | 2025-10-22 00:00:00 |

---

### Tabela: `memberships`
Associa utilizadores a grupos e define o respetivo papel.

**Funções principais:**
- Associar utilizadores a grupos
- Definir papéis (owner, member)
- Registar data de entrada

#### Exemplos de registos

| mem_id | mem_usr_id | mem_grp_id | mem_role | mem_joined_at       |
|--------|------------|------------|----------|---------------------|
| 1      | 1          | 1          | owner    | 2025-12-03 00:00:00 |
| 2      | 2          | 1          | member   | 2025-12-03 00:00:00 |

---

### Tabela: `groupss`
Representa grupos de utilizadores.

**Funções principais:**
- Organizar utilizadores
- Permitir listas partilhadas
- Definir um utilizador dono

#### Exemplos de registos

| grp_id | grp_name         | grp_code | grp_owner_usr_id | grp_created_at      |
|--------|------------------|----------|------------------|---------------------|
| 1      | IADE             | X9TPQ    | 1                | 2025-12-03 00:00:00 |
| 2      | Colegas de casa  | M7K2A    | 2                | 2025-12-04 00:00:00 |

---

### Tabela: `lists`
Guarda listas associadas a grupos.

**Funções principais:**
- Agrupar itens
- Organizar compras

#### Exemplos de registos

| lst_id | lst_grp_id | lst_title                    | lst_created_at      |
|--------|------------|------------------------------|---------------------|
| 1      | 1          | Compras IADE - Semana 1      | 2025-12-03 00:00:00 |
| 2      | 1          | Lanche Reunião de Projeto    | 2025-12-04 00:00:00 |

---

### Tabela: `list_items`
Itens adicionados às listas, com quantidade e unidade.

**Funções principais:**
- Registar itens concretos dentro da lista
- Identificar quem adicionou
- Definir quantidade/unidade
- Marcar como concluído

#### Exemplos de registos

| li_id | li_lst_id | li_item_id | li_usr_id | li_qty | li_unit_id | li_done |
|-------|-----------|------------|-----------|--------|------------|---------|
| 1     | 1         | 2          | 1         | 1.00   | 1          | 0       |
| 2     | 1         | 1          | 2         | 2.00   | 1          | 0       |

---

### Tabela: `items`
Itens genéricos utilizados em listas.

**Funções principais:**
- Definir item base
- Associar unidade

#### Exemplos de registos

| it_id | it_name | it_unit_id |
|-------|---------|------------|
| 1     | Arroz   | 1          |
| 2     | Massa   | 1          |

---

### Tabela: `recipes`
Armazena receitas.

**Funções principais:**
- Criar receitas com ingredientes associados

#### Exemplos de registos

| rec_id | rec_name                            |
|--------|--------------------------------------|
| 1      | Massa Carbonara                      |
| 2      | Frango Grelhado com Arroz            |

---

### Tabela: `ingredients`
Ingredientes utilizados nas receitas.

**Funções principais:**
- Definir ingredientes base
- Associar unidade

#### Exemplos de registos

| ing_id | ing_name          | ing_unit_id |
|--------|-------------------|-------------|
| 1      | Esparguete        | 1           |
| 2      | Placas de lasanha | 5           |

---

### Tabela: `recipe_ingredients`
Liga receitas a ingredientes com quantidades específicas.

**Funções principais:**
- Associar quantidade
- Definir unidade utilizada

#### Exemplos de registos

| rgi_id | rgi_rec_id | rgi_ing_id | rgi_qty | rgi_unit_id |
|--------|------------|------------|---------|-------------|
| 1      | 1          | 1          | 0.40    | 1           |
| 2      | 1          | 8          | 0.15    | 1           |

---

### Tabela: `unit`
Tabela de unidades de medida.

#### Exemplos de registos

| uni_id | uni_name |
|--------|----------|
| 1      | kg       |
| 2      | g        |

---

### Tabela: `supermarkets`
Supermercados disponíveis com coordenadas GPS.

**Funções principais:**
- Identificar supermercados próximos
- Permitir guardar favoritos

#### Exemplos de registos

| sup_id | sup_name                  | sup_lat     | sup_lng      |
|--------|---------------------------|-------------|--------------|
| 1      | Pingo Doce               | 38.7481278  | -9.1404258   |
| 2      | Lidl Loures Moscavide    | 38.7804700  | -9.1040500   |

---

### Tabela: `saved_places`
Associa utilizadores a supermercados guardados.

#### Exemplos de registos

| sav_id | sav_usr_id | sav_sup_id |
|--------|------------|------------|
| 1      | 1          | 1          |
| 2      | 1          | 2          |


## 2.4 Modelo Lógico Resumido

**Entidades principais:**  
users, groupss, lists, items, recipes, supermarkets  

**Entidades de ligação:**  
memberships, list_items, recipe_ingredients, saved_places  

---


---

### 1.4 Scripts SQL

> Scripts completos incluídos na entrega da tarefa:
- `creates.sql`
- `populate.sql`
- `queries.sql`
  
---

### 1.5 Conclusão

A base de dados do projeto CoBuy foi desenhada de forma relacional, garantindo integridade e escalabilidade.  
Esta primeira versão cumpre os requisitos para a entrega intermédia e será expandida ao longo do desenvolvimento do projeto.
