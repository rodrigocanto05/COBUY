## 1. Identificação
- Universidade: Universidade Europeia  
- Faculdade: IADE  
- Elementos do grupo: Rodrigo Canto, Rodrigo Daibert, Marco Fonseca e Luís Quirim   
- Nome do projeto: COBUY  
- Repositório GitHub: https://github.com/rodrigocanto05/COBUY.git 

---

## 2. Palavras-chave
- listas de compras;  
- localização;  
- rotas;  
- refeições práticas;  
- compras colaborativas.

---

## 3. Descrição da app e problemas a resolver
A aplicação **COBUY** pretende resolver a falta de coordenação familiar criando listas de compras partilhadas entre os membros. Os utilizadores podem criar um grupo e adicionar os restantes membros, permitindo que todos tenham acesso à mesma lista de compras. Cada membro pode adicionar, editar ou remover produtos, garantindo que a informação está sempre atualizada.  

Adicionalmente, a app utiliza a localização do utilizador para mostrar os supermercados mais próximos e apresentar a rota até ao supermercado selecionado.  

Por fim, o utilizador pode escrever o nome de uma **refeição** que pretende cozinhar e a aplicação gera automaticamente a **lista de ingredientes necessários com quantidades ajustadas** ao número de pessoas indicado.

---

## 4. Objetivos e motivação
- Melhorar a organização das compras;
- Evitar esquecimentos de produtos essenciai;s  
- Reduzir deslocações desnecessárias; 
- Tornar o processo de compras mais colaborativo;
- Incentivar uma alimentação mais planeada através de receitas.

---

## 5. Público-alvo
- Famílias que partilham as tarefas domésticas;  
- Casais que fazem compras em conjunto;  
- Estudantes ou colegas de casa que dividem despesas;  
- Grupos de amigos que organizam eventos e precisam de listas partilhadas.  

---

## 6. Pesquisa de mercado

### AnyList
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Partilha de listas entre membros | Não mostra supermercados próximos nem rotas |
| Sincronização em tempo real | Não gera ingredientes e quantidades a partir de refeições |
| Planeamento de refeições | 

### Bring!
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Listas de compras partilhadas | Não mostra supermercados próximos nem rotas |
| Sugestões de receitas | Não calcula quantidades relativas |
| Organização por categorias | 

### Listonic
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Listas partilhadas | Não sugere refeições completas |
| Sugestões de produtos | Não apresenta rotas até supermercados |
| Organização clara | 

### SuperCook
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Sugere refeições com base nos ingredientes | Não tem lista de compras colaborativa |
| Evita desperdício alimentar | Não mostra supermercados próximos |
| Gestão de stock | Não apresenta rotas no mapa |

---

## 7. Guiões de teste

### Caso Core — Gerir lista partilhada
- **Ator:** Membro do grupo  
- **Pré-condição:** Grupo criado; utilizador autenticado.  
  1. Utilizador abre a app e seleciona o Grupo “Casa”;  
  2. Adiciona item à lista;  
  3. A lista sincroniza e todos veem o estado atualizado.  
- **Pós-condição:** Lista atualizada e visível para todos.  

### Caso 2 — Supermercados próximos e rota
- **Ator:** Utilizador  
- **Pré-condição:** Permissões de GPS ativas.  
  1. Utilizador seleciona a opção “Encontrar Supermercado”;  
  2. A app mostra os supermercados mais próximos (ex.: “Continente a 2 km”);  
  3. O utilizador seleciona um supermercado e a app apresenta a rota no mapa;  
  4. O utilizador segue a rota até ao supermercado.  
- **Pós-condição:** O utilizador encontra o supermercado e acede à rota.  

### Caso 3 — Refeição → ingredientes e quantidades
- **Ator:** Utilizador  
- **Pré-condição:** Grupo criado,utlilizador autenticado.  
  1. Utilizador escreve “Massa com atum” e indica “4 pessoas”;  
  2. A app devolve lista de ingredientes com quantidades ajustadas (ex.: 400g massa, 2 latas atum, azeite, sal);  
  3. Utilizador verifica se tem os ingredientes, caso não tenha adiciona á lista.  
- **Pós-condição:** Refeição concluída.  

---

## 8. Project Charter

### 8.1 Descrição genérica
A aplicação **COBUY** é uma solução mobile colaborativa que pretende facilitar a organização das compras em grupo. Os utilizadores partilham uma lista de compras em tempo real, podem visualizar os supermercados mais próximos através de localização e visualizar ingredientes e quantidades a partir de refeições introduzidas.  

### 8.2 Enquadramento nas diversas Unidades Curriculares
O projeto **COBUY** resulta da integração dos conhecimentos adquiridos em várias Unidades Curriculares do 3.º semestre:

- **Programação de Dipositivos Moveis**: desenvolvimento da aplicação;
- **Programação Orientada a Objetos**: implementação da lógica de negócio;
- **Bases de Dados**: modelação em MYSQLWorkbench (para sincronização servidor);  
- **Competências Comunicacionais**: relatório, poster e vídeo; 
- **Matemática Discreta**: quantidades em receitas (proporções)  

### 8.3 Requisitos técnicos (provisórios)
- Autenticação de utilizadores  
- Criação e gestão de grupos  
- Listas de compras colaborativas  
- Localização e mapas com rotas  
- Receitas + cálculo de quantidades  

### 8.4 Arquitetura da solução (provisória)
- Arquitetura **MVC**  
- **View**: Android (Kotlin, Jetpack Compose)  
- **Controller**: Serviços locais + API REST (Spring Boot)  
- **Model**: MYSQLWorkbench  
- **Localização**: Google Maps API (Places + Directions)  
- **Receitas**: Iteligencia Artificial (provisório)

### 8.5 Tecnologias a utilizar 
- **Frontend:** Android Studio
- **Backend:** Spring Boot  
- **Base de Dados:** MySQLWorkBench  
- **Integrações externas:** Google Maps API (localização/rotas)  
- **Ferramentas:** GitHub, Figma

---

## 9. Planeamento e Calendarização

### 9.1 Distribuição de Tarefas

---

### 9.2 Plano de Trabalhos

