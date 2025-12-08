# CoBuy – Documentação da API REST (Atualizada)

A API REST do projeto **CoBuy** é responsável por gerir utilizadores, grupos, listas de compras colaborativas, itens, receitas, ingredientes, supermercados e locais guardados.

Todas as respostas seguem o formato **JSON**.

---

# 1. Autenticação e Autorização

A API utiliza **JWT (JSON Web Token)**.

- Endpoints públicos:
  - `POST /api/auth/register`
  - `POST /api/auth/login`
- Endpoints protegidos:
  - Todos os restantes (`/api/users`, `/api/groups`, `/api/lists`, `/me`, `/saved-places`, etc.)

O token deve ser enviado no header:

```http
Authorization: Bearer <TOKEN_JWT>
```

---

## 1.1 Registo

**POST /api/auth/register**

Regista um novo utilizador.

### Request Body
```json
{
  "name": "Ana Silva",
  "email": "ana@example.com",
  "password": "123456",
  "gender": "F"
}
```

### Response (200)
```json
{
  "token": "JWT_TOKEN_AQUI"
}
```

---

## 1.2 Login

**POST /api/auth/login**

Autentica um utilizador existente.

### Request Body
```json
{
  "email": "ana@example.com",
  "password": "123456"
}
```

### Response (200)
```json
{
  "token": "JWT_TOKEN_AQUI"
}
```

**Erros**
- `401 Unauthorized` – credenciais inválidas.

---

# 2. Utilizadores

Base: `/api/users`

### GET /api/users
Lista todos os utilizadores.

### GET /api/users/{id}
Obtém detalhes de um utilizador.

### POST /api/users
Cria um utilizador.

```json
{
  "name": "João Costa",
  "email": "joao@example.com",
  "password": "123456",
  "gender": "M"
}
```

### PUT /api/users/{id}
Atualiza dados de um utilizador.

### DELETE /api/users/{id}
Remove um utilizador.

---

# 3. Perfil do Utilizador Autenticado

Base: `/me`

### GET /me
Dados do utilizador autenticado.

### PUT /me
Atualiza nome e género.

```json
{
  "name": "Novo Nome",
  "gender": "Outro"
}
```

### PUT /me/email
```json
{
  "email": "novo.email@example.com"
}
```

### PUT /me/password
```json
{
  "oldPassword": "123456",
  "newPassword": "654321"
}
```

---

# 4. Grupos e Memberships

## 4.1 Grupos
Base: `/api/groups`

### GET /api/groups
Lista todos os grupos.

### GET /api/groups/{id}
Detalhes de um grupo.

### GET /api/groups/code/{code}
Busca grupo por código.

### GET /api/groups/user/{userId}
Grupos de um utilizador.

### POST /api/groups?userId={userId}
Cria novo grupo.

```json
{
  "name": "Casa da Marta"
}
```

### POST /api/groups/join/{code}?userId={userId}
Utilizador entra no grupo via código.

### DELETE /api/groups/{id}?userId={userId}
Owner apaga o grupo.

---

## 4.2 Memberships
Base: `/api/memberships`

### GET /api/users/{userId}/memberships
Grupos onde o utilizador participa.

### GET /api/groups/{groupId}/members
Lista membros do grupo.

### POST /api/memberships/{groupId}/add/{userId}?role=member
Adiciona membro ao grupo.

### POST /api/memberships/join
```json
{
  "userId": 5,
  "code": "ABCDE"
}
```

### DELETE /api/memberships/leave
```json
{
  "userId": 5,
  "groupId": 3
}
```

### DELETE /api/memberships/{groupId}/remove/{userId}?requesterId={ownerId}
Owner remove um membro.

---

# 5. Listas de Compras e Itens

## 5.1 Listas de Compras
Base: `/api/lists`

### GET /api/lists/group/{groupId}?userId={userId}
Listas do grupo.

### POST /api/lists?userId={userId}
```json
{
  "title": "Compras do fim de semana",
  "description": "Churrasco sábado",
  "group_id": 3
}
```

### DELETE /api/lists/{listId}?userId={userId}
Remove lista.

---

## 5.2 Itens da Lista
Base: `/api/lists/{listId}/items`

### GET /api/lists/{listId}/items?userId={userId}
Lista itens.

### POST /api/lists/{listId}/items
```json
{
  "name": "Leite",
  "qty": 3,
  "unitId": 1,
  "userId": 5
}
```

### PATCH /api/lists/{listId}/items/{itemId}/done?userId={userId}
Marca/desmarca item.

### DELETE /api/lists/{listId}/items/{itemId}?userId={userId}
Remove item.

---

# 6. Receitas e Ingredientes

## 6.1 Receitas

Base: `/recipes`

### GET /recipes?userId={userId}
Lista receitas disponíveis.

---

## 6.2 Ingredientes da Receita

Base: `/recipes`

### GET /recipes/{id}/ingredients
Lista os ingredientes associados a uma receita.

### POST /recipes/{recipeId}/add-to-list/{listId}
Adiciona ingredientes da receita à lista de compras.

```json
{
  "userId": 5,
  "ingredients": [1, 2, 3]
}
```

---

## 6.3 Ingredientes (catálogo geral)

Base: `/ingredients`

### GET /ingredients  
Lista todos os ingredientes.

### GET /ingredients/{id}  
Detalhes do ingrediente.

### POST /ingredients
```json
{
  "name": "Arroz",
  "unitId": 2
}
```

### PUT /ingredients/{id}  
Atualiza o ingrediente.

### DELETE /ingredients/{id}  
Apaga o ingrediente.

---

## 6.4 Unidades (Unit)

Base: `/units`

### GET /units  
Lista todas as unidades.

### GET /units/{id}  
Obtém detalhes de uma unidade.

---

## 6.5 Itens (Catálogo Global)

Base: `/items`

### GET /items  
Lista itens gerais.

### GET /items/{id}  
Detalhes de um item.

### POST /items
```json
{
  "name": "Carne picada",
  "unitId": 3
}
```

### DELETE /items/{id}  
Remove item.

---

# 7. Supermercados e Locais Guardados

## 7.1 Supermercados

Base: `/supermarkets`

### GET /supermarkets  
Lista todos os supermercados conhecidos.

### GET /supermarkets/{id}  
Detalhes do supermercado.

### POST /supermarkets/resolve  
Resolve supermercado recebido do Google Places.

```json
{
  "externalId": "google_place_id",
  "name": "Pingo Doce",
  "latitude": 38.123,
  "longitude": -8.987
}
```

---

## 7.2 Locais Guardados (Favoritos)

Base: `/saved-places`

### GET /saved-places  
Lista supermercados favoritos do utilizador.

### POST /saved-places
```json
{
  "supermarketId": 10,
}
```

### DELETE /saved-places/{id}  
Apaga favorito.

---

# 8. Códigos de Erro

### 200 OK  
Pedido bem-sucedido.

### 201 Created  
Recurso criado com sucesso.

### 204 No Content  
Recurso apagado.

### 400 Bad Request  
Pedido inválido.

### 401 Unauthorized  
Token em falta ou inválido.

### 403 Forbidden  
Sem permissões suficientes.

### 404 Not Found  
Recurso não encontrado.

### 500 Internal Server Error  
Erro inesperado no servidor.

---

# 9. Notas Técnicas

- Backend em **Spring Boot**  
- Segurança via **Spring Security + JWT**  
- Repositórios em **Spring Data JPA**  
- Base de dados **MySQL**  
- Estrutura:
  - **controller/** – Endpoints REST  
  - **service/** – Lógica de negócio  
  - **repository/** – Acesso a dados  
  - **model/** – Entidades  
  - **dto/** – Estruturas de transferência de dados  

---

# 10. Versão Atualizada

Esta documentação reflete o estado atual do backend **CoBuy**, incluindo:

- Endpoints completos  
- Controlo de permissões  
- Estrutura de listas, grupos e receitas  
- Integração com Google Maps  
- Gestão de favoritos  

---

