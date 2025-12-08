# 1. Identificação
- Universidade: Universidade Europeia  
- Faculdade: IADE  
- Elementos do grupo: Rodrigo Canto, Rodrigo Daibert, Marco Fonseca e Luís Quirin
- Nome do projeto: COBUY  
- Repositório GitHub: https://github.com/rodrigocanto05/COBUY.git

---
# 2. Distribuição das principais tarefas

| **Tarefas Principais** | **Rodrigo Canto**(~34%) | **Rodrigo Daibert**(~32%) | **Marco Fonseca**(~22%) | **Luís Quirin**(~12%) | **Total** |
|----------------------|-------------------|----------------------|-------------------|------------------|-----------|
| **1. Levantamento de requisitos** | 25% | 25% | 25% | 25% | **100%** |
| **2. Pesquisa de mercado** | 25% | 25% | 25% | 25% | **100%** |
| **3. Design e mockups (Figma)** | 30% | 10% | 5% | 55% | **100%** |
| **4. Base de Dados (MER + tabelas + inserts + queries)** | 85% | 0% | 15% | 0% | **100%** |
| **5. Desenvolvimento Android (Frontend)** | 15% | 70% | 15% | 0% | **100%** |
| **6. Desenvolvimento da API (Backend – Spring Boot, Postman)** | 70% | 25% | 0% | 5% | **100%** |
| **7. Integração com Mapas / Rotas / Localização** | 75% | 25% | 0% | 0% | **100%** |
| **8. Implementação de Grupos e Listas** | 0% | 100% | 0% | 0% | **100%** |
| **9. Implementação de Receitas** | 0% | 0% | 100% | 0% | **100%** |
| **10. Testes e validação (funcionais + usabilidade)** | 25% | 45% | 30% | 0% | **100%** |
| **11. Relatório final + poster + vídeo de apresentação** | 25% | 25% | 25% | 25% | **100%** |

---

# 3. Descrição da app e problemas a resolver

A **COBUY** é uma aplicação móvel desenvolvida para resolver problemas comuns na organização das compras do dia a dia, especialmente em famílias, casais, estudantes e grupos de amigos. A ausência de uma lista partilhada e atualizada leva frequentemente à compra duplicada de produtos, ao esquecimento de itens essenciais e à falta de controlo do stock doméstico, resultando em desperdício, gastos desnecessários e deslocações evitáveis ao supermercado.

Para responder a estas falhas, a COBUY integra três funcionalidades principais:

- **Listas de compras colaborativas em tempo real**, permitindo que vários utilizadores adicionem, editem ou concluam itens numa lista partilhada. Esta sincronização evita esquecimentos e compras repetidas.
  
- **Localização inteligente**, que identifica supermercados próximos e apresenta rotas, facilitando a escolha do local mais conveniente e reduzindo o tempo gasto em deslocações.

- **Módulo de receitas**, onde os utilizadores podem consultar receitas completas, visualizar o modo de preparação e os ingredientes necessários. Caso pretendam, podem adicionar automaticamente esses ingredientes às suas listas de compras, tornando o planeamento das refeições mais simples e eficiente.

Com estas funcionalidades, a COBUY transforma o processo de compras numa experiência mais organizada, colaborativa e prática, ajudando os utilizadores a poupar tempo, reduzir desperdício e melhorar a gestão do seu dia a dia.

---

# 4. Objetivos e motivação

## Descrição dos objetivos e da motivação do trabalho realizado

A **COBUY** foi desenvolvida com o objetivo principal de criar uma solução digital que melhore a organização das compras e o planeamento alimentar entre utilizadores que partilham responsabilidades no dia a dia. O trabalho pretendeu responder a problemas reais relacionados com a falta de coordenação, desperdício de recursos e ausência de ferramentas colaborativas eficientes.

## Objetivos do trabalho

Os principais objetivos definidos para o desenvolvimento da COBUY foram:

- **Centralizar a gestão das compras domésticas** através de listas partilhadas e atualizadas em tempo real.
- **Reduzir compras duplicadas e esquecimentos**, garantindo que todos os utilizadores têm acesso à mesma informação.
- **Facilitar a colaboração entre membros de um grupo**, promovendo uma divisão equilibrada das tarefas.
- **Integrar funcionalidades de localização**, permitindo encontrar rapidamente supermercados próximos e aceder às rotas.
- **Apoiar o planeamento alimentar** através de um módulo de receitas que apresenta ingredientes e modo de preparação, permitindo adicionar os ingredientes necessários à lista de compras.
- **Desenvolver uma aplicação intuitiva e acessível**, capaz de melhorar o dia a dia do utilizador através da tecnologia.

## Motivação

A motivação para este trabalho surgiu da observação de um problema comum: apesar de existirem várias apps de listas de compras, poucas oferecem uma experiência verdadeiramente colaborativa, integrada com localização e planeamento de refeições. No quotidiano, é frequente existirem falhas de comunicação entre membros de um agregado, resultando em compras repetidas, falta de produtos essenciais e fraca coordenação.

Assim, o grupo identificou a oportunidade de criar uma solução que:

- **simplificasse o processo de compras**,  
- **reduzisse desperdício e custos**,  
- **e tornasse a organização alimentar mais eficiente e prática**.

A COBUY representa, por isso, a união entre necessidades reais e a aplicação prática dos conhecimentos adquiridos ao longo do semestre, motivando o desenvolvimento de uma ferramenta útil, moderna e orientada para a melhoria do dia a dia das pessoas.


---

## 5. Público-alvo
O público-alvo da COBUY é bastante abrangente, englobando vários perfis:  

- **Famílias**: Coordenação de compras e stock.  
- **Casais**: Divisão de responsabilidades de forma prática e organizada.  
- **Estudantes**: Divisão de despesas para controlar o iventário e o orçamento em conjunto.  
- **Grupos de amigos**: organização de eventos (como churrascos ou festas) que necessitam de gerirência das compras de forma colaborativa.  

**Exemplos de pessoas**:  
- *João, 25 anos*, estudante universitário que divide casa com três colegas. A COBUY ajuda-o a gerir de forma justa e organizada as compras semanais do grupo, de forma a que seja dividdida a despesa de cada um.  
- *Marta, 40 anos*, mãe de dois filhos, utiliza a COBUY para garantir uma listagem rápida e eficiente para que a família não se esqueça de nenhum produto essencial planeando refeições semanais, a partir do inventário.  

---

## 6. Pesquisa de mercado

### AnyList
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Partilha de listas entre membros | Não mostra supermercados próximos nem rotas |
| Sincronização em tempo real | Não gera ingredientes e quantidades a partir de refeições |
| Planeamento de refeições | - |

### Bring!
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Listas de compras partilhadas | Não mostra supermercados próximos nem rotas |
| Sugestões de receitas | Não calcula quantidades relativas |
| Organização por categorias | - |

### Listonic
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Listas partilhadas | Não sugere refeições completas |
| Sugestões de produtos | Não apresenta rotas até supermercados |
| Organização clara | - |

### SuperCook
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Sugere refeições com base nos ingredientes | Não tem lista de compras colaborativa |
| Evita desperdício alimentar | Não mostra supermercados próximos |
| Gestão de stock | Não apresenta rotas no mapa |

---

# 7. Descrição da solução implementada

### i. Descrição genérica da solução implementada
A **COBUY** é uma aplicação móvel colaborativa que facilita a gestão de compras e o planeamento alimentar entre utilizadores que partilham responsabilidades no quotidiano. A aplicação permite criar listas de compras partilhadas, consultar receitas completas, adicionar ingredientes automaticamente às listas e localizar supermercados próximos a partir do IADE.

A solução comunica com um backend em Spring Boot, utiliza uma base de dados MySQL e integra funcionalidades nativas de Android. O objetivo foi desenvolver uma ferramenta intuitiva, eficiente e orientada à redução de desperdício e melhoria da organização dos utilizadores.

### ii. Enquadramento nas diversas Unidades Curriculares

- **Programação de Dispositivos Móveis** – Desenvolvimento da aplicação Android, criação das interfaces, navegação, componentes visuais e comunicação com a API através de Kotlin.

- **Programação Orientada a Objetos** – Estruturação da lógica de negócio, criação das classes, entidades e relações, implementação dos controladores e serviços, bem como desenvolvimento do backend em **Spring Boot**, aplicando princípios como encapsulamento, herança e modularidade.

- **Bases de Dados** – Modelação do diagrama MER, definição das entidades e relacionamentos, normalização, criação das tabelas, views e implementação dos dados e queries na base de dados MySQL.

- **Competências Comunicacionais** – Produção do relatório técnico, apresentação oral, vídeo demonstrativo e criação do poster final, garantindo uma comunicação clara e eficaz do trabalho desenvolvido.

- **Matemática Discreta** – Aplicação de conceitos estruturais, relações entre conjuntos (ex.: grupos, listas e elementos), e apoio à definição lógica do módulo de receitas e proporções.

### iii. Requisitos Técnicos Finais

#### Requisitos Funcionais
- Registo e autenticação de utilizadores.
- Gestão de grupos colaborativos.
- Criação e edição de listas de compras partilhadas.
- Gestão de itens (adicionar, remover, concluir).
- Consulta de receitas completas com modo de preparação.
- Adicionar automaticamente ingredientes da receita à lista.
- Localização de supermercados próximos a partir do IADE.
- Visualização de rotas até um supermercado.
- Consultar/Editar perfil de utilizador.

#### Requisitos Não Funcionais (previsão do início do projeto)
- Consultar receitas com Inteligencia Artificial.
- Notifições quando o utilizador entra num supermercado.
- Supermercados proximos com base na localização atual.


### iv. Arquitetura da Solução
A arquitetura segue um modelo **cliente-servidor**:

**Aplicação Android (Frontend)**  
- Kotlin + Jetpack Compose  
- UI, navegação, comunicação com API  

**API REST — Spring Boot(Backend)**  
- Endpoints para utilizadores, grupos, listas, receitas e mapas  
- Autenticação JWT  
- Serviços para lógica de negócio  

**Base de Dados — MySQL**  
- Modelos normalizados  
- Armazena utilizadores, listas, itens, receitas...  

**Integrações Externas**
- Google Maps API (Places + Directions)

### v. Tecnologias utilizadas
- **Frontend:** Android Studio, Kotlin, Jetpack Compose  
- **Backend:** Spring Boot, Spring Security, JPA/Hibernate  
- **BD:** MySQLWorkbench, MAMP  
- **APIs:** Google Maps API  
- **Outras:** GitHub, Figma, Postman  

## 7.vi. Versão atualizada dos Casos de Utilização

### **Lista de Casos de Utilização**
1. Criar Conta  
2. Autenticar Utilizador  
3. Consultar Página Inicial  
4. Criar Grupo  
5. Entrar num Grupo  
6. Ver Grupos Existentes  
7. Gerir Membros do Grupo  
8. Consultar Listas do Grupo  
9. Criar Lista de Compras  
10. Consultar Lista de Compras (Simulado)  
11. Adicionar Item à Lista (Simulado)  
12. Marcar Item como Concluído (Simulado)  
13. Consultar Receitas  
14. Ver Detalhes da Receita  
15. Adicionar Ingredientes da Receita à Lista  
16. Ver Supermercados Próximos  
17. Obter Informações de um Supermercado  
18. Guardar Supermercado nos Favoritos  
19. Ver Locais Guardados  
20. Ver Rota até ao Supermercado  
21. Consultar Perfil  
22. Editar Perfil  
23. Alterar Email ou Password  

---

### **1. Criar Conta**
**Ator:** Utilizador  
**Objetivo:** Registar uma nova conta na aplicação.  
**Fluxo principal:**  
- O utilizador introduz nome, email, password, confirmação de password e género.  
- O sistema valida os dados.  
- A conta é criada e o utilizador entra automaticamente na aplicação.

**Exceções:**  
- Email já registado.  
- Campos inválidos ou incompletos.

---

### **2. Autenticar Utilizador (Login)**
**Objetivo:** Permitir ao utilizador aceder à conta.  
**Fluxo principal:**  
- O utilizador introduz email/telefone e password.  
- O sistema valida credenciais e abre o dashboard.

**Exceções:**  
- Credenciais incorretas.  

---

### **3. Consultar Página Inicial**
**Objetivo:** Aceder ao painel com informação geral do utilizador.  
**Fluxo:**  
- O utilizador vê os seus grupos, locais guardados e opções principais (entrar grupo, criar grupo).

---

### **4. Criar Grupo**
**Objetivo:** Criar um novo grupo para organização colaborativa.  
**Fluxo:**  
- O utilizador introduz o nome do grupo.  
- O sistema gera um código único.  
- O grupo é criado e o utilizador torna-se Owner.

---

### **5. Entrar num Grupo**
**Objetivo:** Ingressar num grupo existente através de código.  
**Fluxo:**  
- O utilizador introduz o código do grupo.  
- O sistema valida e adiciona o utilizador ao grupo.

**Exceções:**  
- Código inexistente ou inválido.

---

### **6. Ver Grupos Existentes**
**Objetivo:** Consultar a lista dos grupos a que o utilizador pertence.  
**Fluxo:**  
- O sistema apresenta todos os grupos, cada um com botão “Entrar”.

---

### **7. Gerir Membros do Grupo**
**Objetivo:** Consultar e administrar membros de um grupo.  
**Fluxo:**  
- O utilizador acede à página “Membros”.  
- O Owner pode expulsar membros.  
- Qualquer utilizador pode sair do grupo.

**Exceções:**  
- Falta de permissões para expulsar.

---

### **8. Consultar Listas do Grupo**
**Objetivo:** Ver todas as listas associadas ao grupo.  
**Fluxo:**  
- O sistema apresenta cartões com nome da lista e data de criação.  
- O utilizador pode abrir qualquer lista.

---

### **9. Criar Lista de Compras**
**Objetivo:** Adicionar uma nova lista ao grupo selecionado.  
**Fluxo:**  
- O utilizador seleciona “Criar lista”.  
- Introduz nome e descrição opcional.  
- O sistema cria a lista e retorna à página das listas.

---

### **10. Consultar Lista de Compras (Simulado)**
**Objetivo:** Ver os itens de uma lista.  
**Fluxo sugerido:**  
- O utilizador abre a lista.  
- A lista mostra todos os itens, quantidades e estado (feito/não feito).

---

### **11. Adicionar Item à Lista (Simulado)**
**Fluxo sugerido:**  
- O utilizizador seleciona “Adicionar item”.  
- Introduz nome, quantidade e unidade.  
- O sistema adiciona o item à lista.

---

### **12. Marcar Item como Concluído (Simulado)**
**Fluxo sugerido:**  
- O utilizador toca no checkbox do item.  
- O sistema atualiza o estado e sincroniza com o grupo.

---

### **13. Consultar Receitas**
**Objetivo:** Ver todas as receitas disponíveis.  
**Fluxo:**  
- O utilizador abre o separador “Receitas”.  
- O sistema apresenta uma grelha de receitas com imagem e nome.

---

### **14. Ver Detalhes da Receita**
**Objetivo:** Aceder aos ingredientes e modo de preparação.  
**Fluxo:**  
- O utilizador seleciona uma receita.  
- O sistema apresenta ingredientes, quantidades e passos.

---

### **15. Adicionar Ingredientes da Receita à Lista**
**Objetivo:** Enviar automaticamente ingredientes para uma lista de compras.  
**Fluxo:**  
- O utilizador escolhe uma lista onde adicionar os ingredientes.  
- O sistema adiciona os itens automaticamente.

---

### **16. Ver Supermercados Próximos**
**Objetivo:** Ver no mapa os supermercados nas proximidades.  
**Fluxo:**  
- O utilizador abre o separador de localização.  
- O sistema pede permissão de localização.  
- São apresentados vários supermercados no mapa.

**Exceções:**  
- Localização desativada.  
- Nenhum local encontrado.

---

### **17. Obter Informações de um Supermercado**
**Objetivo:** Abrir opções do supermercado selecionado.  
**Fluxo:**  
- O utilizador toca num pin no mapa.  
- O sistema apresenta card com opções:  
  - Guardar nos Favoritos  
  - Ver rotas  
  - Cancelar

---

### **18. Guardar Supermercado nos Favoritos**
**Fluxo:**  
- O utilizador seleciona “Guardar nos Favoritos”.  
- O supermercado é adicionado à lista de locais guardados.

---

### **19. Ver Locais Guardados**
**Objetivo:** Consultar todos os locais favoritos.  
**Fluxo:**  
- O utilizador abre “Locais Salvos”.  
- Vê lista de locais com opção de remover.

---

### **20. Ver Rota até ao Supermercado**
**Fluxo:**  
- O utilizador seleciona “Ver rotas”.  
- O sistema abre o percurso no Google Maps.

---

### **21. Consultar Perfil**
**Objetivo:** Ver dados pessoais do utilizador.  
**Fluxo:**  
- O utilizizador abre o separador do perfil.  
- O sistema apresenta nome, email e género.

---

### **22. Editar Perfil**
**Fluxo:**  
- O utilizador altera nome ou género.  
- O sistema atualiza a informação.

---

### **23. Alterar Email ou Password**
**Fluxo:**  
- O utilizizador introduz novo email/password e a password atual.  
- O sistema valida e atualiza.

**Exceções:**  
- Password atual incorreta.  
- Email inválido.



---

## 10 Documentação REST 

A### 📌 Documentação da API REST – CoBuy

A API segue o estilo REST e permite a interação com utilizadores, grupos, listas de compras, receitas e supermercados.

| Recurso | Método | Endpoint | Descrição | Corpo de Exemplo / Resposta |
|--------|--------|----------|-----------|------------------------------|
| **Auth** | `POST` | `/api/auth/register` | Registar novo utilizador | `{ "name":"Ana", "email":"ana@gmail.com", "password":"****" }` |
| | `POST` | `/api/auth/login` | Autenticar utilizador | `{ "email":"ana@gmail.com", "password":"****" }` → `{ "token":"xxxxx" }` |

### 👥 Users

| Método | Endpoint | Descrição | Exemplo de Resposta |
|--------|---------|-----------|----------------------|
| `GET` | `/users` | Listar utilizadores | `[ { "id":1, "name":"Ana", "email":"ana@gmail.com" } ]` |

---

### 👪 Groups

| Método | Endpoint | Descrição | Corpo/Resposta |
|--------|---------|-----------|---------------|
| `GET` | `/groups` | Listar grupos | `[{"id":1,"name":"Casa"}]` |
| `POST` | `/groups` | Criar grupo | `{ "name":"Casa Nova" }` |

---

### 🧑‍🤝‍🧑 Memberships (utilizadores em grupos)

| Método | Endpoint | Descrição | Corpo/Resposta |
|--------|---------|-----------|----------------|
| `GET` | `/groups/{groupId}/members` | Listar membros do grupo | `[{"id":2,"name":"João","role":"member"}]` |
| `POST` | `/memberships/{groupId}/add/{userId}?role=member` | Adicionar membro ao grupo | `201 Created` |
| `DELETE` | `/memberships/{groupId}/remove/{userId}` | Remover membro do grupo | `204 No Content` |

---

### 🛒 Shopping Lists

| Método | Endpoint | Descrição | Corpo/Resposta |
|--------|---------|-----------|----------------|
| `GET` | `/lists` | Listar listas | `[{"id":1,"title":"Supermercado"}]` |
| `POST` | `/lists` | Criar lista | `{ "groupId":1, "title":"Domingo compras" }` |

---

### ✅ List Items 

| Método | Endpoint | Descrição | Corpo/Resposta |
|--------|---------|-----------|----------------|
| `GET` | `/lists/{listId}/items` | Listar itens | `[ { "name":"Leite", "qty":2 } ]` |
| `POST` | `/lists/{listId}/items` | Adicionar item | `{ "name":"Ovos", "qty":12, "unit":"un" }` |
| `PATCH` | `/items/{itemId}` | Marcar/desmarcar item comprado | `{ "done":true }` |
| `DELETE` | `/items/{itemId}` | Remover item | `204 No Content` |

---

### 🍽️ Recipes

| Método | Endpoint | Descrição | Corpo/Resposta |
|--------|---------|-----------|----------------|
| `GET` | `/recipes` | Listar receitas | `[ { "id":1, "name":"Lasanha" } ]` |

---

### 🧂 Recipe Ingredients

| Método | Endpoint | Descrição | Corpo/Resposta |
|--------|---------|-----------|----------------|
| `GET` | `/recipes/{id}/ingredients` | Ingredientes da receita | `[{"name":"Carne","qty":300,"unit":"g"}]` |
| `POST` | `/recipes/{id}/ingredients` | Adicionar ingrediente | `{ "name":"Tomate", "qty":100, "unit":"g" }` |
| `DELETE` | `/recipes/{id}/ingredients/{ingredientId}` | Remover ingrediente | `204 No Content` |

---

### 🛍 Supermarkets

| Método | Endpoint | Descrição | Corpo/Resposta |
|--------|---------|-----------|----------------|
| `GET` | `/supermarkets` | Listar supermercados | `[{"id":1,"name":"Continente","rating":4.5}]` |

---

### 📍 Saved Places (Favoritos do utilizador)

| Método | Endpoint | Descrição | Corpo/Resposta |
|--------|---------|-----------|----------------|
| `GET` | `/saved-places` | Listar favoritos | `[{"label":"Perto de casa","supermarket":"Lidl"}]` |
| `POST` | `/saved-places` | Guardar supermercado como favorito | `{ "supermarketId":2, "label":"Lidl Centro" }` |
| `DELETE` | `/saved-places/{id}` | Apagar favorito | `204 No Content` |

### 🔐 Autorização

- Endpoints com criação, edição e delete exigem **JWT Bearer Token**
- Header exemplo: uid:1|email:rodrigo.canto@hotmail.com

 ### 📎 Notas Técnicas

- Respostas seguem formato JSON
- Passwords são **hashes Bcrypt**
- API stateless com **Spring Security + JWT**

---

# 11. Diagrama de Classes

O diagrama de classes representa a estrutura principal da aplicação CoBuy, descrevendo as entidades do sistema, os seus atributos e as relações entre elas.
O modelo inclui utilizadores, grupos, listas de compras, itens, receitas, ingredientes e supermercados, bem como tabelas associativas para gerir relações como Memberships e Saved Places.

<img width="800" height="1200" alt="Untitled diagram-2025-11-07-123824" src="https://github.com/user-attachments/assets/de67fe9a-cf63-4504-8e0c-ed381e1b3955" />

As ligações mostram relações 1-para-N (ex.: um grupo possui várias listas) e N-para-N (ex.: utilizadores pertencem a vários grupos).
Este diagrama assegura uma organização clara dos dados e serve como base para a implementação da base de dados e da API.

---

## 12. Dicionário de Dados

O Dicionário de Dados descreve as tabelas principais da base de dados, os respetivos campos e o seu significado no contexto da aplicação CoBuy.

### 12.1. Tabela `users`

| Campo           | Tipo           | Descrição                                      |
|-----------------|----------------|-----------------------------------------------|
| usr_id (PK)     | int            | Identificador único do utilizador             |
| usr_name        | varchar(80)    | Nome do utilizador                            |
| usr_email       | varchar(120)   | Email único do utilizador                     |
| usr_password    | varchar(200)   | Password do utilizador (armazenada em hash)  |
| usr_gender      | char(1)        | Género do utilizador                          |
| usr_created_at  | datetime       | Data e hora de criação do registo             |

**Descrição:** Armazena a informação base de cada utilizador da aplicação.

---

### 12.2. Tabela `groupss`

| Campo             | Tipo         | Descrição                                              |
|-------------------|--------------|--------------------------------------------------------|
| grp_id (PK)       | int          | Identificador único do grupo                           |
| grp_name          | varchar(80)  | Nome do grupo                                          |
| grp_code          | varchar(5)   | Código de convite único do grupo                       |
| grp_owner_usr_id  | int (FK)     | ID do utilizador que é proprietário do grupo           |
| grp_created_at    | datetime     | Data e hora de criação do grupo                        |

**Descrição:** Representa os grupos colaborativos criados pelos utilizadores.

---

### 12.3. Tabela `memberships`

| Campo           | Tipo         | Descrição                                            |
|-----------------|--------------|------------------------------------------------------|
| mem_id (PK)     | int          | Identificador único da associação utilizador–grupo   |
| mem_usr_id      | int (FK)     | ID do utilizador associado                           |
| mem_grp_id      | int (FK)     | ID do grupo associado                                |
| mem_role        | varchar(10)  | Função do utilizador no grupo (ex.: owner, member)   |
| mem_joined_at   | datetime     | Data e hora de entrada do utilizador no grupo        |

**Descrição:** Define a participação dos utilizadores nos diferentes grupos.

---

### 12.4. Tabela `lists`

| Campo           | Tipo         | Descrição                                      |
|-----------------|--------------|-----------------------------------------------|
| lst_id (PK)     | int          | Identificador único da lista                  |
| lst_grp_id      | int (FK)     | ID do grupo ao qual a lista pertence          |
| lst_title       | varchar(80)  | Título da lista de compras                    |
| lst_created_at  | datetime     | Data e hora de criação da lista               |

**Descrição:** Armazena as listas de compras criadas dentro de cada grupo.

---

### 12.5. Tabela `items`

| Campo           | Tipo           | Descrição                                  |
|-----------------|----------------|-------------------------------------------|
| it_id (PK)      | int            | Identificador único do item               |
| it_name         | varchar(120)   | Nome do item (ex.: Arroz, Leite)          |
| it_unit_id      | int (FK)       | Unidade de medida associada ao item       |

**Descrição:** Catálogo de itens que podem ser usados nas listas de compras.

---

### 12.6. Tabela `list_items`

| Campo          | Tipo          | Descrição                                            |
|----------------|---------------|------------------------------------------------------|
| li_id (PK)     | int           | Identificador único da linha de lista               |
| li_lst_id      | int (FK)      | ID da lista a que o item pertence                    |
| li_item_id     | int (FK)      | ID do item                                          |
| li_usr_id      | int (FK)      | ID do utilizador que adicionou o item               |
| li_qty         | decimal(10,2) | Quantidade do item                                  |
| li_unit_id     | int (FK)      | ID da unidade de medida                             |
| li_done        | boolean       | Indica se o item já foi comprado (true/false)       |

**Descrição:** Representa os itens concretos inseridos em cada lista de compras.

---

### 12.7. Tabela `unit`

| Campo          | Tipo           | Descrição                                  |
|----------------|----------------|-------------------------------------------|
| uni_id (PK)    | int            | Identificador único da unidade            |
| uni_name       | varchar(16)    | Nome/abreviatura da unidade (kg, g, L...) |

**Descrição:** Lista de unidades de medida utilizadas para itens e ingredientes.

---

### 12.8. Tabela `recipes`

| Campo         | Tipo           | Descrição                         |
|---------------|----------------|------------------------------------|
| rec_id (PK)   | int            | Identificador único da receita    |
| rec_name      | varchar(120)   | Nome da receita                   |

**Descrição:** Armazena o catálogo de receitas disponíveis na aplicação.

---

### 12.9. Tabela `ingredients`

| Campo           | Tipo           | Descrição                                 |
|-----------------|----------------|-------------------------------------------|
| ing_id (PK)     | int            | Identificador único do ingrediente        |
| ing_name        | varchar(120)   | Nome do ingrediente                       |
| ing_unit_id     | int (FK)       | Unidade de medida associada ao ingrediente|

**Descrição:** Catálogo de ingredientes que podem ser utilizados nas receitas.

---

### 12.10. Tabela `recipe_ingredients`

| Campo           | Tipo           | Descrição                                          |
|-----------------|----------------|----------------------------------------------------|
| rgi_id (PK)     | int            | Identificador único da linha receita–ingrediente   |
| rgi_rec_id      | int (FK)       | ID da receita                                     |
| rgi_ing_id      | int (FK)       | ID do ingrediente                                 |
| rgi_qty         | decimal(10,2)  | Quantidade do ingrediente                         |
| rgi_unit_id     | int (FK)       | Unidade de medida usada                           |

**Descrição:** Define os ingredientes e respetivas quantidades utilizadas em cada receita.

---

### 12.11. Tabela `supermarkets`

| Campo         | Tipo           | Descrição                         |
|---------------|----------------|------------------------------------|
| sup_id (PK)   | int            | Identificador único do supermercado |
| sup_name      | varchar(120)   | Nome do supermercado               |

**Descrição:** Lista de supermercados conhecidos pela aplicação, usados no módulo de mapas.

---

### 12.12. Tabela `saved_places`

| Campo          | Tipo        | Descrição                                          |
|----------------|-------------|----------------------------------------------------|
| sav_id (PK)    | int         | Identificador único do registo                    |
| sav_usr_id     | int (FK)    | ID do utilizador que guardou o local              |
| sav_sup_id     | int (FK)    | ID do supermercado guardado como favorito         |

**Descrição:** Regista os supermercados marcados como favoritos pelos utilizadores.

---

## 13.3 Guia de Dados (exemplo)

O Guia de Dados apresenta exemplos concretos dos registos existentes na base de dados de exemplo, ilustrando a forma como as tabelas se relacionam e suportam os casos de utilização da aplicação.

### Utilizadores e Grupos

- Existem vários utilizadores registados, entre os quais os elementos do grupo de projeto:  
  - Rodrigo Canto  
  - Rodrigo Daibert  
  - Marco Fonseca  
  - Luís Quirin  

- O grupo **“IADE”** é um dos grupos criados na tabela `groupss`, tendo como proprietário um dos utilizadores (por exemplo, o utilizador com ID 1).

No contexto desse grupo, a tabela `memberships` regista a participação dos membros, indicando quem é owner e quem é member.

---

### Listas de Compras

No grupo **“IADE”** existem várias listas, por exemplo:

- “Compras IADE - Semana 1”  
- “Lanche da Reunião de Projeto”  

Cada uma destas listas está associada a um registo na tabela `lists`.  
Os itens concretos inseridos em cada lista encontram-se na tabela `list_items`, indicando:

- o item (ligação à tabela `items`),  
- a quantidade e unidade (ligação à tabela `unit`),  
- e o utilizador que adicionou o item (ligação a `users`).

Exemplo prático numa lista:

- Arroz – 2 kg – adicionado por um utilizador específico  
- Leite – 3 L – adicionado por outro membro do grupo  

---

### Receitas e Ingredientes

A base de dados inclui um conjunto de receitas na tabela `recipes` (ex.: “Massa Carbonara”, “Frango Grelhado com Arroz e Legumes”).  

Para cada receita, a tabela `recipe_ingredients` define:

- quais os ingredientes associados (ligação a `ingredients`),  
- as respetivas quantidades,  
- e as unidades de medida (ligação a `unit`).

Exemplo simplificado:

- Receita: “Massa Carbonara”  
  - Esparguete – 0,40 kg  
  - Bacon – 0,15 kg  
  - Ovos – 4 unidades  

Este modelo permite gerar automaticamente listas de compras baseadas em receitas.

---

### Supermercados e Locais Guardados

A tabela `supermarkets` contém uma lista de supermercados (por exemplo, Lidl, Continente, Pingo Doce em determinadas localizações).  

A tabela `saved_places` associa utilizadores a supermercados específicos, representando os locais que cada utilizador guarda como favoritos para futuras compras.

Exemplo:

- O utilizador Rodrigo pode ter guardado:
  - “Lidl Montijo”  
  - “Continente Montijo Alegro”  

Estes dados são utilizados pelo módulo de mapas da aplicação para facilitar o acesso rápido aos locais de compra mais frequentes.

---

## 14. Autoavaliação do Projeto

Ao longo do desenvolvimento do projeto **CoBuy**, o grupo conseguiu implementar a maior parte das funcionalidades previstas na proposta inicial. Destacam-se:

- Sistema de autenticação e gestão de utilizadores;
- Criação e gestão de grupos com diferentes funções (owner e member);
- Listas de compras colaborativas, partilhadas entre os membros do grupo;
- Integração com mapas para visualização de supermercados próximos;
- Base de dados relacional completa, incluindo receitas, ingredientes e unidades;
- Backend em Spring Boot com API REST estruturada;
- Aplicação mobile desenvolvida em Kotlin com Jetpack Compose.

### Funcionalidades não concluídas ou parcialmente implementadas

(Por Fazer)

Apesar disso, o núcleo funcional da aplicação encontra-se implementado e utilizável, cumprindo o objetivo de permitir a gestão colaborativa de listas de compras.

### Alterações face à proposta inicial

(Por fazer)

### Dificuldades e aprendizagens

(Por fazer)

## 14. Conclusão
A **COBUY** não é apenas uma lista de compras digital, mas sim uma **plataforma colaborativa e inteligente**. O seu impacto pode ser sentido em três níveis:  

- **Social:** fortalece a colaboração entre membros de famílias, casais e grupos de amigos, criando um hábito mais organizado e participativo.  
- **Económico:** reduz desperdícios e gastos desnecessários, já que os utilizadores compram apenas o que precisam.  
- **Tecnológico:** combina funcionalidades modernas como sincronização em tempo real, geolocalização e algoritmos inteligentes de recomendação.  

Futuramente, a COBUY poderá integrar-se com supermercados locais para permitir compras online, gerar listas a partir de comandos de voz e até sugerir menus semanais completos com base em restrições alimentares.  

Assim, este projeto não só cumpre os objetivos inicialmente propostos, como também abre caminho para evolução futura em direção a uma solução de **smart shopping**.  

## 15. Bibliografia
AnyList. (2025). *AnyList app*. Recuperado de https://www.anylist.com/  

Bring! Labs AG. (2025). *Bring! Shopping list & recipes*. Recuperado de https://www.getbring.com/  

Listonic. (2025). *Smart grocery shopping list*. Recuperado de https://listonic.com/  

SuperCook. (2025). *Recipe generator*. Recuperado de https://www.supercook.com/  

Google Developers. (2025). *Google Maps Platform documentation*. Recuperado de https://developers.google.com/maps  

Android Developers. (2025). *Android Studio documentation*. Recuperado de https://developer.android.com/studio  

Spring. (2025). *Spring Boot reference documentation*. Recuperado de https://spring.io/projects/spring-boot  

Oracle. (2025). *MySQL reference manual*. Recuperado de https://dev.mysql.com/doc/  
