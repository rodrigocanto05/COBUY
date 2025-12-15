# 1. Base de Dados (BD report)

Este capítulo apresenta o modelo de dados utilizado no projeto **CoBuy**, incluindo o diagrama ER, a definição das entidades e relações, o dicionário de dados e os exemplos que compõem a base de dados de referência para testes. A base de dados reflete os requisitos funcionais da aplicação, permitindo gerir utilizadores, grupos, listas de compras partilhadas, itens, receitas e locais de compras favoritos.

---

# 1.1 Modelo (MER)

O Modelo Entidade-Relação do sistema CoBuy organiza os dados essenciais para a gestão colaborativa de compras. O modelo estrutura-se em torno das entidades Users, Groups e Memberships, que permitem a associação de múltiplos utilizadores a múltiplos grupos. A gestão das compras é suportada pelas entidades Lists, List Items e Items, assegurando a criação e partilha de listas com itens reutilizáveis.

As entidades Recipes, Ingredients, Recipe Ingredients e Unit permitem representar receitas e os respetivos ingredientes de forma estruturada, recorrendo a relações N:N normalizadas. Adicionalmente, Supermarkets e Saved Places armazenam locais de compra preferidos com informação geográfica.

De forma geral, o MER garante coerência, normalização e suporte direto aos requisitos funcionais da aplicação, articulando utilizadores, grupos, listas, receitas e supermercados num modelo de dados consistente e escalável.
  
<img width="700" height="700" alt="Captura de ecrã 2025-11-18, às 10 22 53" src="https://github.com/user-attachments/assets/9eb1af67-40ff-4304-8bf2-5da28465d5c0" />

---

# 1.2 Dicionário de Dados

## Tabela: `users`
Armazena todos os utilizadores da aplicação.

**Funções principais:**
- Criar grupos
- Participar em grupos
- Adicionar itens a listas
- Guardar supermercados favoritos

| Atributo       | Tipo de dado     | Tamanho | Restrição                       | Descrição                         |
| -------------- | ---------------- | ------- | ------------------------------- | --------------------------------- |
| usr_id         | numérico inteiro | —       | Chave primária / Auto Increment | Identificador único do utilizador |
| usr_name       | alfanumérico     | 80      | Obrigatório                     | Nome do utilizador                |
| usr_email      | alfanumérico     | 120     | Único / Obrigatório             | Email do utilizador               |
| usr_password   | alfanumérico     | 200     | Obrigatório                     | Palavra-passe encriptada          |
| usr_gender     | char             | 1       | Obrigatório                     | Género do utilizador              |
| usr_created_at | datetime         | —       | Default CURRENT_TIMESTAMP       | Data de criação do registo        |


## Tabela: `memberships`
Associa utilizadores a grupos e define o respetivo papel.

**Funções principais:**
- Associar utilizadores a grupos
- Definir papéis (owner, member)
- Registar data de entrada

| Atributo      | Tipo de dado     | Tamanho | Restrição                       | Descrição                           |
| ------------- | ---------------- | ------- | ------------------------------- | ----------------------------------- |
| mem_id        | numérico inteiro | —       | Chave primária / Auto Increment | Identificador da associação         |
| mem_usr_id    | numérico inteiro | —       | FK → users.usr_id               | ID do utilizador associado          |
| mem_grp_id    | numérico inteiro | —       | FK → groupss.grp_id             | ID do grupo                         |
| mem_role      | alfanumérico     | 10      | Obrigatório                     | Função no grupo (ex: owner, member) |
| mem_joined_at | datetime         | —       | Default CURRENT_TIMESTAMP       | Quando o utilizador entrou no grupo |


## Tabela: `groupss`
Representa grupos de utilizadores.

**Funções principais:**
- Organizar utilizadores
- Permitir listas partilhadas
- Definir um utilizador dono

| Atributo         | Tipo de dado | Tamanho | Restrição                 | Descrição                         |
| ---------------- | ------------ | ------- | ------------------------- | --------------------------------- |
| grp_id           | inteiro      | —       | Chave primária            | Identificador do grupo            |
| grp_name         | alfanumérico | 80      | Obrigatório               | Nome do grupo                     |
| grp_code         | alfanumérico | 5       | Único                     | Código usado para entrar no grupo |
| grp_owner_usr_id | inteiro      | —       | FK → users.usr_id         | Dono/criador do grupo             |
| grp_created_at   | datetime     | —       | Default CURRENT_TIMESTAMP | Data de criação                   |


## Tabela: `lists`
Guarda listas associadas a grupos.

**Funções principais:**
- Agrupar itens
- Organizar compras

| Atributo       | Tipo de dado | Tamanho | Restrição                 | Descrição                      |
| -------------- | ------------ | ------- | ------------------------- | ------------------------------ |
| lst_id         | inteiro      | —       | Chave primária            | Identificador da lista         |
| lst_grp_id     | inteiro      | —       | FK → groupss.grp_id       | Grupo ao qual a lista pertence |
| lst_title      | alfanumérico | 80      | Obrigatório               | Nome/título da lista           |
| lst_created_at | datetime     | —       | Default CURRENT_TIMESTAMP | Data de criação                |


## Tabela: `list_items`
Itens adicionados às listas, com quantidade e unidade.

**Funções principais:**
- Registar itens concretos dentro da lista
- Identificar quem adicionou
- Definir quantidade/unidade
- Marcar como concluído

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
Itens genéricos utilizados em listas.

**Funções principais:**
- Definir item base
- Associar unidade

| Atributo   | Tipo de dado | Tamanho | Restrição        | Descrição              |
| ---------- | ------------ | ------- | ---------------- | ---------------------- |
| it_id      | inteiro      | —       | Chave primária   | Identificador          |
| it_name    | alfanumérico | 120     | Obrigatório      | Nome do item           |
| it_unit_id | inteiro      | —       | FK → unit.uni_id | Unidade padrão do item |


## Tabela: `recipes`
Armazena receitas.

**Funções principais:**
- Criar receitas com ingredientes associados

| Atributo | Tipo de dado | Tamanho | Restrição      | Descrição                |
| -------- | ------------ | ------- | -------------- | ------------------------ |
| rec_id   | inteiro      | —       | Chave primária | Identificador da receita |
| rec_name | alfanumérico | 120     | Obrigatório    | Nome da receita          |


## Tabela: `recipe_ingredients`
Liga receitas a ingredientes com quantidades específicas.

**Funções principais:**
- Associar quantidade
- Definir unidade utilizada

| Atributo    | Tipo de dado | Tamanho | Restrição               | Descrição             |
| ----------- | ------------ | ------- | ----------------------- | --------------------- |
| rgi_id      | inteiro      | —       | Chave primária          | Identificador         |
| rgi_rec_id  | inteiro      | —       | FK → recipes.rec_id     | Receita associada     |
| rgi_ing_id  | inteiro      | —       | FK → ingredients.ing_id | Ingrediente usado     |
| rgi_qty     | decimal      | 10,2    | Obrigatório             | Quantidade necessária |
| rgi_unit_id | inteiro      | —       | FK → unit.uni_id        | Unidade de medida     |


## Tabela: `ingredients`
Ingredientes utilizados nas receitas.

**Funções principais:**
- Definir ingredientes base
- Associar unidade

| Atributo    | Tipo de dado | Tamanho | Restrição           | Descrição           |
| ----------- | ------------ | ------- | ------------------- | ------------------- |
| ing_id      | inteiro      | —       | Chave primária      | Identificador       |
| ing_name    | alfanumérico | 120     | Único / Obrigatório | Nome do ingrediente |
| ing_unit_id | inteiro      | —       | FK → unit.uni_id    | Unidade de medida   |


## Tabela: `unit`
Tabela de unidades.

| Atributo | Tipo de dado | Tamanho | Restrição      | Descrição                                    |
| -------- | ------------ | ------- | -------------- | -------------------------------------------- |
| uni_id   | inteiro      | —       | Chave primária | Identificador                                |
| uni_name | alfanumérico | 16      | Obrigatório    | Abreviatura da unidade (kg, g, ml, un, etc.) |


## Tabela: `supermarkets`
Supermercados disponíveis com coordenadas GPS.

**Funções principais:**
- Identificar supermercados próximos
- Permitir guardar favoritos

| Atributo | Tipo de dado | Tamanho | Restrição      | Descrição                 |
| -------- | ------------ | ------- | -------------- | ------------------------- |
| sup_id   | inteiro      | —       | Chave primária | Identificador             |
| sup_name | alfanumérico | 120     | Obrigatório    | Nome do supermercado      |
| sup_lat  |  double      | -       | Opcional       | Latitude do supermercado  |
| sup_lng  |  double      | -       | Opcional       | Longitude do supermercado |




## Tabela: `saved_places`
### Tabela: `saved_places`
Associa utilizadores a supermercados guardados.

| Atributo   | Tipo de dado | Tamanho | Restrição                | Descrição              |
| ---------- | ------------ | ------- | ------------------------ | ---------------------- |
| sav_id     | inteiro      | —       | Chave primária           | Identificador          |
| sav_usr_id | inteiro      | —       | FK → users.usr_id        | Utilizador que guardou |
| sav_sup_id | inteiro      | —       | FK → supermarkets.sup_id | Supermercado guardado  |

---

# 1.3 Guia de Dados 

## Introdução
O Guia de Dados descreve a estrutura lógica da Base de Dados, explicando o propósito de cada tabela, as relações existentes e ilustrando exemplos reais de registos.

## Tabela: users

A tabela **users** é responsável por armazenar todos os utilizadores registados na aplicação **COBUY**.  
Cada utilizador representa uma pessoa que pode criar ou entrar em grupos, adicionar itens às listas de compras, guardar supermercados e utilizar o módulo de receitas.

Atualmente, esta tabela contém **11 utilizadores**.

| usr_id | usr_name         | usr_email                                                         | usr_password | usr_gender | usr_created_at |
| -----: | ---------------- | ----------------------------------------------------------------- | ------------ | ---------- | -------------- |
|      1 | Rodrigo Canto    | [rodrigocanto@hotmail.com](mailto:rodrigocanto@hotmail.com)       | canto        | M          | 2025-10-20     |
|      2 | Rodrigo Daibert  | [rodrigodaibert@hotmail.com](mailto:rodrigodaibert@hotmail.com)   | 1234         | M          | 2025-10-22     |
|      3 | Marco Fonseca    | [mf2006@gmail.com](mailto:mf2006@gmail.com)                       | hash1        | M          | 2025-10-24     |
|      4 | Luis Quirim      | [luisquirim@gmail.com](mailto:luisquirim@gmail.com)               | hash1        | M          | 2025-10-28     |
|      5 | Sandra Estrela   | [sandra@hotmail.com](mailto:sandra@hotmail.com)                   | hash1        | F          | 2025-10-30     |
|      6 | Daniel Paulo     | [dexpaulo@hotmail.com](mailto:dexpaulo@hotmail.com)               | hash1        | M          | 2025-11-01     |
|      7 | Jocy Grangeiro   | [jocy12@gmail.com](mailto:jocy12@gmail.com)                       | hash1        | F          | 2025-11-04     |
|      8 | Paulo Alberto    | [pauloencomendas@gmail.com](mailto:pauloencomendas@gmail.com)     | hash1        | M          | 2025-11-09     |
|      9 | Patricia Daibert | [patriciadaibert@hotmail.com](mailto:patriciadaibert@hotmail.com) | hash1        | F          | 2025-11-13     |
|     10 | Martim Fonseca   | [mrmartim@hotmail.com](mailto:mrmartim@hotmail.com)               | hash1        | M          | 2025-12-01     |
|     11 | Tomas Lebre      | [tomaslebre@gmail.com](mailto:tomaslebre@gmail.com)               | hash1        | M          | 2025-12-02     |

---

## Tabela: memberships

A tabela **memberships** é responsável por representar a ligação entre **utilizadores** e **grupos** na aplicação **COBUY**.  
É nesta tabela que se define **quem pertence a que grupo**, **qual o seu papel** dentro do grupo e **quando entrou**.

Um registo nesta tabela significa que:
- um utilizador faz parte de um grupo;
- pode ter o papel de **owner** (dono) ou **member** (membro).

---

### Como funcionam os IDs (explicação simples)

Nesta tabela não são usados nomes diretamente, mas sim **IDs** (números).

- **mem_usr_id** → identifica o utilizador  
  (corresponde ao `usr_id` da tabela `users`)
- **mem_grp_id** → identifica o grupo  
  (corresponde ao `grp_id` da tabela `groupss`)

Os nomes reais dos utilizadores e dos grupos são obtidos através destas ligações.

---

### Exemplo de funcionamento

Cada linha da tabela `memberships` pode ser lida como:

> “O utilizador **X** pertence ao grupo **Y**, com o papel **Z**, desde a data **D**.”

#### Exemplos práticos

- `(1, 1, owner, 2025-12-03)`  
  → O utilizador com `usr_id = 1` (**Rodrigo Canto**) é o **dono** do grupo com `grp_id = 1` (**IADE**) desde 03/12/2025.

- `(11, 1, member, 2025-12-04)`  
  → O utilizador com `usr_id = 11` (**Tomas Lebre**) é **membro** do grupo **IADE** desde 04/12/2025.

---

### Conteúdo da tabela memberships

| mem_usr_id | mem_grp_id | mem_role | mem_joined_at |
|----------:|-----------:|----------|---------------|
| 1 | 1 | owner  | 2025-12-03 |
| 2 | 1 | member | 2025-12-03 |
| 3 | 1 | member | 2025-12-03 |
| 4 | 1 | member | 2025-12-03 |
| 11 | 1 | member | 2025-12-04 |
| 2 | 2 | owner  | 2025-12-04 |
| 4 | 2 | member | 2025-12-04 |
| 6 | 2 | member | 2025-12-04 |
| 8 | 2 | member | 2025-12-04 |
| 10 | 2 | member | 2025-12-04 |
| 3 | 3 | owner  | 2025-12-05 |
| 1 | 3 | member | 2025-12-05 |
| 2 | 3 | member | 2025-12-05 |
| 4 | 3 | member | 2025-12-05 |
| 5 | 4 | owner  | 2025-12-05 |
| 7 | 4 | member | 2025-12-05 |
| 9 | 4 | member | 2025-12-05 |
| 4 | 5 | owner  | 2025-12-07 |
| 3 | 5 | member | 2025-12-07 |
| 6 | 5 | member | 2025-12-07 |

---

## Tabela: groupss

A tabela **groupss** armazena todos os grupos criados na aplicação **COBUY**.  
Um grupo representa um conjunto de utilizadores que partilham listas de compras e responsabilidades comuns.

Cada grupo:
- tem um **dono (owner)**, que é um utilizador;
- possui um **código único**, usado para outros utilizadores entrarem no grupo;
- pode ter vários **membros**, definidos na tabela `memberships`.

Atualmente, existem **5 grupos** registados.

| grp_id | grp_name          | grp_owner_usr_id | grp_code | grp_created_at |
| -----: | ----------------- | ------------------------- | -------- | -------------- |
| 1 | IADE              | Rodrigo Canto        | X9TPQ    | 2025-12-03 |
| 2 | Colegas de casa   | Rodrigo Daibert      | M7K2A    | 2025-12-04 |
| 3 | Churrasco         | Marco Fonseca        | Q4W9E    | 2025-12-05 |
| 4 | Mulheres          | Sandra Estrela       | A8ZLM    | 2025-12-05 |
| 5 | Montijo           | Luís Quirim          | P6X7R    | 2025-12-07 |

---

## Grupos e respetivos membros

### Grupo 1 — IADE

- **Owner:**  
  - Rodrigo Canto (`usr_id = 1`)

- **Members:**  
  - Rodrigo Daibert (`usr_id = 2`)  
  - Marco Fonseca (`usr_id = 3`)  
  - Luís Quirim (`usr_id = 4`)  
  - Tomas Lebre (`usr_id = 11`)

---

### Grupo 2 — Colegas de casa

- **Owner:**  
  - Rodrigo Daibert (`usr_id = 2`)

- **Members:**  
  - Luís Quirim (`usr_id = 4`)  
  - Daniel Paulo (`usr_id = 6`)  
  - Paulo Alberto (`usr_id = 8`)  
  - Martim Fonseca (`usr_id = 10`)

---

### Grupo 3 — Churrasco

- **Owner:**  
  - Marco Fonseca (`usr_id = 3`)

- **Members:**  
  - Rodrigo Canto (`usr_id = 1`)  
  - Rodrigo Daibert (`usr_id = 2`)  
  - Luís Quirim (`usr_id = 4`)

---

### Grupo 4 — Mulheres

- **Owner:**  
  - Sandra Estrela (`usr_id = 5`)

- **Members:**  
  - Jocy Grangeiro (`usr_id = 7`)  
  - Patricia Daibert (`usr_id = 9`)

---

### Grupo 5 — Montijo

- **Owner:**  
  - Luís Quirim (`usr_id = 4`)

- **Members:**  
  - Marco Fonseca (`usr_id = 3`)  
  - Daniel Paulo (`usr_id = 6`)

---

## Tabela: unit

A tabela **unit** define as **unidades de medida** utilizadas na aplicação **COBUY**.  
Estas unidades são usadas tanto nos **itens das listas de compras** como nos **ingredientes das receitas**.

Em vez de escrever a unidade em texto várias vezes, a aplicação usa um **ID de unidade**, garantindo consistência.

---

### Como funcionam os IDs

- **uni_id** → identifica a unidade
- O nome da unidade está em **uni_name**
- Outras tabelas usam o `uni_id` para saber a unidade correta

---

### Conteúdo da tabela unit

| uni_id | uni_name |
|-----:|----------|
| 1 | kg |
| 2 | g |
| 3 | L |
| 4 | ml |
| 5 | un |

---

### Exemplo prático

- Se um item tiver `uni_id = 1` → a quantidade está em **quilogramas (kg)**
- Se um item tiver `uni_id = 5` → a quantidade está em **unidades (un)**

---
## Tabela: lists

A tabela **lists** representa as **listas de compras** criadas na aplicação **COBUY**.  
Cada lista corresponde a um conjunto de itens a comprar e está **sempre associada a um grupo**.

As listas permitem que os membros de um grupo organizem as suas compras de forma colaborativa.

---

### Como funcionam os IDs

- **lst_id** → identifica a lista de compras
- **lst_grp_id** → identifica o grupo a que a lista pertence  
  (corresponde ao `grp_id` da tabela `groupss`)

Desta forma, é possível saber a que grupo pertence cada lista.

---

### Conteúdo da tabela lists

| lst_id | lst_grp_id | lst_title                         | lst_created_at |
|------:|-----------:|-----------------------------------|----------------|
| 1 | 1 | Compras IADE - Semana 1 | 2025-12-03 |
| 2 | 1 | Lanche da Reunião de Projeto | 2025-12-04 |
| 3 | 1 | Material para Apresentação | 2025-12-05 |
| 4 | 2 | Compras da Casa - Mensal | 2025-12-04 |
| 5 | 2 | Compras do Fim de Semana | 2025-12-06 |
| 6 | 2 | Produtos de Limpeza | 2025-12-07 |
| 7 | 3 | Churrasco Rapazes | 2025-12-05 |
| 8 | 4 | Jantar das Mulheres | 2025-12-08 |
| 9 | 4 | Brunch de Domingo | 2025-12-09 |
|10 | 4 | Noite de Cinema | 2025-12-10 |
|11 | 5 | Compras Montijo - Família | 2025-12-08 |
|12 | 5 | Fim de Semana em Casa | 2025-12-10 |
|13 | 5 | Ceia de Natal Montijo | 2025-12-11 |

---

### Exemplo prático

- A lista com `lst_id = 1` pertence ao grupo com `grp_id = 1` (**IADE**)
- A lista **"Compras da Casa - Mensal"** pertence ao grupo **Colegas de casa**

---

### Relação entre listas e grupos

- Um grupo pode ter **várias listas**
- Uma lista pertence **apenas a um grupo**

Esta relação permite manter as listas organizadas por grupo.

---

## Tabela: list_items

A tabela **list_items** representa os **itens que pertencem a cada lista de compras** na aplicação **COBUY**.  
Cada registo corresponde a **um item específico adicionado a uma determinada lista**, indicando **quem o adicionou**, **a quantidade** e **a unidade de medida**.

Esta tabela é o ponto central das listas de compras, pois liga:
- listas
- itens
- utilizadores
- unidades de medida

---

### Como funcionam os IDs

- **li_lst_id** → identifica a lista onde o item foi adicionado  
  (corresponde ao `lst_id` da tabela `lists`)
- **li_item_id** → identifica o item  
  (corresponde ao `it_id` da tabela `items`)
- **li_usr_id** → identifica o utilizador que adicionou o item  
  (corresponde ao `usr_id` da tabela `users`)
- **li_unit_id** → identifica a unidade de medida  
  (corresponde ao `uni_id` da tabela `unit`)

---

### Conteúdo da tabela list_items

| li_lst_id | li_item_id | li_usr_id | li_qty | li_unit_id |
|---------:|-----------:|----------:|-------:|-----------:|
| 1 | 2 | 1 | 1.00 | 1 |
| 1 | 1 | 2 | 2.00 | 1 |
| 1 | 5 | 3 | 3.00 | 3 |
| 1 | 25 | 4 | 6.00 | 5 |
| 1 | 30 | 11 | 1.00 | 5 |
| 2 | 25 | 1 | 10.00 | 5 |
| 2 | 26 | 2 | 0.20 | 2 |
| 2 | 27 | 3 | 0.20 | 2 |
| 2 | 46 | 4 | 2.00 | 3 |
| 2 | 45 | 11 | 10.00 | 5 |
| ... | ... | ... | ... | ... |

> A tabela contém muitos registos, nesta apresentação são mostrados apenas alguns exemplos representativos.

---

### Exemplo prático

Um registo como:

- `li_lst_id = 1`
- `li_item_id = 2`
- `li_usr_id = 1`
- `li_qty = 1.00`
- `li_unit_id = 1`

significa:

> “Na lista **Compras IADE - Semana 1**, o utilizador **Rodrigo Canto** adicionou **1 kg de Massa**.”

---

### Relação entre listas e itens

- Uma lista pode conter **vários itens**
- Um item pode aparecer em **várias listas**
- Cada item é associado a:
  - quem o adicionou
  - quantidade
  - unidade

Esta estrutura permite total flexibilidade e colaboração dentro das listas de compras.

---

## Tabela: items

A tabela **items** armazena todos os **produtos disponíveis** na aplicação **COBUY**.  
Estes itens podem ser adicionados às **listas de compras** e associados às **receitas**.

Cada item representa um produto genérico (ex.: arroz, leite, pão), não estando ligado a uma lista específica — essa ligação é feita através da tabela `list_items`.

---

### Como funcionam os IDs

- **it_id** → identifica o item
- **it_unit_id** → identifica a unidade de medida associada ao item  
  (corresponde ao `uni_id` da tabela `unit`)

A unidade define **como o item é contabilizado** (kg, litros, unidades, etc.).

---

### Conteúdo da tabela items

| it_id | it_name                    | it_unit_id |
|-----:|-----------------------------|-----------:|
| 1 | Arroz | 1 |
| 2 | Massa | 1 |
| 3 | Farinha | 1 |
| 4 | Frango | 1 |
| 5 | Leite | 3 |
| 6 | Água | 3 |
| 7 | Óleo | 3 |
| 8 | Vinho | 3 |
| 9 | Ovos | 5 |
|10 | Manteiga | 2 |
|11 | Açúcar | 2 |
|12 | Sal | 5 |
|13 | Pimenta | 5 |
|14 | Entrecosto | 1 |
|15 | Bananas | 5 |
|...| ... | ... |

> Apenas alguns itens são apresentados para simplificação.

---

### Exemplo prático

- Um item com `it_id = 1` (**Arroz**) tem `it_unit_id = 1`  
  → a unidade é **kg**
- Um item com `it_id = 5` (**Leite**) tem `it_unit_id = 3`  
  → a unidade é **litros (L)**

---

### Relação com outras tabelas

- A tabela **items** é usada por:
  - `list_items` → para adicionar produtos às listas
  - `recipe_ingredients` → para associar ingredientes às receitas
- Um item pode aparecer:
  - em várias listas
  - em várias receitas

Esta separação garante organização, reutilização e consistência dos dados.

---

## Tabela: recipes

A tabela **recipes** armazena todas as **receitas disponíveis** na aplicação **COBUY**.  
Cada receita representa um prato completo que pode ser consultado pelos utilizadores e cujos **ingredientes podem ser adicionados automaticamente às listas de compras**.

Esta tabela guarda apenas a **identificação da receita**.  
A lista de ingredientes e respetivas quantidades é definida noutra tabela (`recipe_ingredients`).

---

### Como funcionam os IDs

- **rec_id** → identifica a receita
O `rec_id` é utilizado para associar a receita aos seus ingredientes.

---

### Conteúdo da tabela recipes

| rec_id | rec_name |
|------:|----------|
| 1 | Massa Carbonara |
| 2 | Frango Grelhado com Arroz e Legumes |
| 3 | Lasanha de Carne |
| 4 | Arroz de Marisco |
| 5 | Bacalhau à Brás |
| 6 | Salmão no Forno com Batatas |
| 7 | Salame de Chocolate |
| 8 | Chili com Carne |
| 9 | Panquecas |
|10 | Omelete de Queijo e Fiambre |
|11 | Sopa de Legumes |
|12 | Tosta Mista |
|13 | Wrap de Frango com Alface |
|14 | Hambúrguer Caseiro |
|15 | Pizza Caseira |
|16 | Esparguete à Bolonhesa |
|17 | Arroz Doce |
|18 | Gelatina com Iogurte |
|19 | Salada Mediterrânica |
|20 | Bolo de Chocolate |

---

### Exemplo prático

- A receita **"Massa Carbonara"** tem `rec_id = 1`
- Quando um utilizador seleciona esta receita, o sistema usa o `rec_id` para:
  - ir à tabela `recipe_ingredients`
  - obter os ingredientes necessários
  - adicionar esses ingredientes a uma lista de compras

---

### Relação com outras tabelas

- Uma receita pode ter **vários ingredientes**
- A ligação entre receitas e ingredientes é feita através da tabela:
  - **`recipe_ingredients`**

Esta separação permite reutilizar ingredientes, definir quantidades corretas e manter as receitas organizadas.

---

## Tabela: ingredients

A tabela **ingredients** armazena todos os **ingredientes utilizados nas receitas** da aplicação **COBUY**.  
Cada ingrediente representa um elemento básico de uma receita (ex.: arroz, carne, legumes, especiarias).

Esta tabela não indica **quantidades nem receitas específicas** — essa informação é definida na tabela `recipe_ingredients`.

---

### Como funcionam os IDs

- **ing_id** → identifica o ingrediente
- **ing_unit_id** → identifica a unidade de medida do ingrediente  
  (corresponde ao `uni_id` da tabela `unit`)

A unidade indica como o ingrediente é medido nas receitas.

---

### Conteúdo da tabela ingredients

| ing_id | ing_name                     | ing_unit_id |
|------:|------------------------------|------------:|
| 1 | Esparguete | 1 |
| 2 | Placas de lasanha | 5 |
| 3 | Arroz carolino | 1 |
| 4 | Pão de forma | 5 |
| 5 | Pão de hambúrguer | 5 |
| 6 | Tortilhas de trigo | 5 |
| 7 | Bolacha maria | 5 |
| 8 | Bacon em tiras | 1 |
| 9 | Peito de frango | 1 |
|10 | Carne picada de vaca | 1 |
|11 | Bacalhau desfiado | 1 |
|12 | Salmão | 1 |
|13 | Miolo de camarão | 1 |
|14 | Miolo de mexilhão | 1 |
|15 | Amêijoas | 1 |
|...| ... | ... |
> Apenas alguns exemplos são apresentados para simplificação.

---

### Exemplo prático

- O ingrediente **"Esparguete"** tem `ing_unit_id = 1`  
  → a quantidade é medida em **kg**
- O ingrediente **"Ovos"** tem `ing_unit_id = 5`  
  → a quantidade é medida em **unidades**

---

### Relação com outras tabelas

- A tabela **ingredients** é usada por:
  - `recipe_ingredients` → para associar ingredientes às receitas
- Um ingrediente pode ser usado:
  - em várias receitas
  - com quantidades diferentes

Esta estrutura permite reutilização de ingredientes e definição clara das receitas.

## Tabela: recipe_ingredients

A tabela **recipe_ingredients** define a **ligação entre receitas e ingredientes** na aplicação **COBUY**.  
É nesta tabela que se especifica **quais os ingredientes necessários para cada receita**, bem como as **quantidades** e **unidades de medida**.

Cada registo indica exatamente:
- a que receita o ingrediente pertence;
- qual o ingrediente;
- a quantidade necessária;
- a unidade de medida utilizada.

---

### Como funcionam os IDs

- **rgi_rec_id** → identifica a receita  
  (corresponde ao `rec_id` da tabela `recipes`)
- **rgi_ing_id** → identifica o ingrediente  
  (corresponde ao `ing_id` da tabela `ingredients`)
- **rgi_unit_id** → identifica a unidade de medida  
  (corresponde ao `uni_id` da tabela `unit`)

---

### Conteúdo da tabela recipe_ingredients

| rgi_rec_id | rgi_ing_id | rgi_qty | rgi_unit_id |
|----------:|-----------:|--------:|------------:|
| 1 | 1 | 0.40 | 1 |
| 1 | 8 | 0.15 | 1 |
| 1 | 16 | 4.00 | 5 |
| 1 | 18 | 200.00 | 2 |
| 1 | 20 | 60.00 | 2 |
| 1 | 28 | 2.00 | 5 |
| 1 | 50 | 0.03 | 3 |
| 1 | 58 | 10.00 | 5 |
| 1 | 59 | 5.00 | 2 |
| 2 | 9 | 0.40 | 1 |
| ... | ... | ... | ... |
> Apenas alguns exemplos representativos são apresentados.

---

### Exemplo prático

Um registo como:

- `rgi_rec_id = 1`
- `rgi_ing_id = 1`
- `rgi_qty = 0.40`
- `rgi_unit_id = 1`

significa:

> “A receita **Massa Carbonara** necessita de **0,40 kg de Esparguete**.”

---

### Relação entre receitas e ingredientes

- Uma receita pode ter **vários ingredientes**
- Um ingrediente pode ser usado em **várias receitas**
- As quantidades e unidades podem variar conforme a receita

Esta tabela permite que a aplicação:
- apresente receitas completas ao utilizador;
- calcule corretamente os ingredientes;
- adicione automaticamente os ingredientes às listas de compras.


## Tabela: supermarkets

A tabela **supermarkets** armazena todos os **supermercados disponíveis na aplicação COBUY**.  
Cada registo representa um local físico real, que pode ser apresentado no mapa, utilizado para cálculo de rotas e guardado pelos utilizadores como favorito.

Os supermercados são identificados pelo nome e pela sua **localização geográfica** (latitude e longitude).

---

### Como funcionam os IDs

- **sup_id** → identifica o supermercado
As coordenadas permitem mostrar o supermercado no mapa e calcular rotas através da Google Maps API.

---

### Conteúdo da tabela supermarkets

| sup_id | sup_name | sup_lat | sup_lng |
|-----:|----------|---------|---------|
| 1 | Pingo Doce | 38.7481278 | -9.1404258 |
| 2 | Lidl Loures Moscavide | 38.78047 | -9.10405 |
| 3 | Pingo Doce Moscavide - Jardins de Cristo Rei | 38.775529 | -9.1098712 |
| 4 | Intermarché Sacavém | 38.7905701 | -9.102083 |
| 5 | Continente Bom Dia Sacavém | 38.790213 | -9.1069358 |
| 6 | Lidl Loures Portela | 38.78506 | -9.11669 |
| 7 | Continente | 38.767721 | -9.0968531 |
| 8 | Minipreço | 38.7903225 | -9.1211724 |
| 9 | Lidl Loures Sacavém | 38.789602 | -9.11747 |
|10 | Pingo Doce Parque das Nações Sul | 38.7586015 | -9.0976235 |
|11 | Lidl Lisboa Av. Infante D. Henrique | 38.75747 | -9.10347 |
|12 | Minipreço Market | 38.7594417 | -9.1248517 |
|13 | Pingo Doce Camarate | 38.7932239 | -9.1378146 |
|14 | Pingo Doce Bela Vista - Hiper | 38.7497235 | -9.1177561 |

---

### Exemplo prático

- O supermercado com `sup_id = 1` corresponde ao **Pingo Doce**
- As coordenadas permitem:
  - mostrar o supermercado no mapa
  - calcular a rota desde o IADE ou da localização do utilizador

---

### Relação com outras tabelas

- A tabela **supermarkets** é usada por:
  - `saved_places` → supermercados guardados pelos utilizadores
- Um supermercado pode ser:
  - guardado por vários utilizadores
  - usado como destino para rotas

Esta estrutura permite integrar localização, favoritos e navegação na aplicação.

## Tabela: saved_places

A tabela **saved_places** representa os **supermercados guardados (favoritos)** por cada utilizador na aplicação **COBUY**.  
Cada registo indica que um determinado utilizador guardou um supermercado para acesso rápido futuro.

---

### Como funcionam os IDs

- **sav_id** → identifica de forma única cada registo de favorito (chave primária)
- **sav_usr_id** → identifica o utilizador  
  (corresponde ao `usr_id` da tabela `users`)
- **sav_sup_id** → identifica o supermercado  
  (corresponde ao `sup_id` da tabela `supermarkets`)

Cada linha representa a relação **utilizador ↔ supermercado**, e o `sav_id` serve apenas para identificar esse registo internamente.

---

### Conteúdo da tabela saved_places

| sav_id | sav_usr_id | sav_sup_id |
|------:|-----------:|-----------:|
| 1 | 1 | 1 |
| 2 | 1 | 2 |
| 3 | 1 | 4 |
| 4 | 2 | 2 |
| 5 | 2 | 3 |
| 6 | 2 | 7 |
| 7 | 3 | 1 |
| 8 | 3 | 5 |
| 9 | 4 | 8 |
|10 | 4 | 9 |
|11 | 5 | 10 |
|12 | 5 | 3 |

---

### Exemplo prático

Um registo como:

- `sav_id = 4`
- `sav_usr_id = 2`
- `sav_sup_id = 2`

significa:

> “O utilizador com `usr_id = 2` (**Rodrigo Daibert**) guardou o supermercado com `sup_id = 2` (**Lidl Loures Moscavide**).”

---

### Relação entre utilizadores e supermercados

- Um utilizador pode guardar **vários supermercados**
- Um supermercado pode ser guardado por **vários utilizadores**

Esta tabela permite à aplicação mostrar os favoritos do utilizador e facilitar o acesso a rotas frequentes.


# 1.4 Scripts SQL

> Scripts completos incluídos na entrega da tarefa:
- `creates.sql`
- `populate.sql`
- `queries.sql`
  
---

## 1.5 Views da Base de Dados

Foram criadas *views* para facilitar o acesso a informação agregada e simplificar consultas recorrentes.  
As principais views implementadas são:

- **membros_grupos** – apresenta os utilizadores e respetivos papéis em cada grupo.
- **listas_com_itens** – reúne listas com os itens associados, incluindo quantidades e unidades.
- **receitas_com_ingredientes** – mostra cada receita juntamente com os ingredientes utilizados.
- **supermercados_favoritos** – identifica os supermercados guardados por cada utilizador.
- **itens_mais_usados** – devolve um ranking dos itens mais utilizados nas listas.

Estas *views* contribuem para uma estrutura de dados mais organizada, permitindo consultas SQL mais simples, rápidas e eficientes, melhorando o acesso e análise da informação na base de dados.


# 1.6 Conclusão

A base de dados desenvolvida para o projeto CoBuy evidencia uma estrutura conceptual e lógica adequada aos requisitos definidos, assegurando a integridade, a normalização e a coerência dos dados ao longo de todo o sistema. O modelo ER e o respetivo dicionário de dados demonstram uma articulação clara entre as entidades e os seus relacionamentos, garantindo suporte às funcionalidades centrais, como a gestão colaborativa de listas, a organização de grupos, o controlo de itens e a integração de receitas e locais de compra.

Em síntese, a modelação apresentada constitui uma base robusta e escalável, proporcionando as condições necessárias para o correto funcionamento da aplicação e para a sua futura evolução.
