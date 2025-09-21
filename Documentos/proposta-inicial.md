## 1. Identificação
- Universidade: Universidade Europeia  
- Faculdade: IADE
- Elementos do grupo: Rodrigo Canto, Rodrigo Daibert, Marco Fosenca e Luís Quirim   
- Nome do projeto: BuyTogether  
- Repositório GitHub: https://github.com/rodrigocanto05/BuyTogether.git 

---

## 2. Palavras-chave
- listas de compras;
- localização;
- notificações;
- refeições práticas;
- compras colaborativas;
- QR code.

---

## 3. Descriçao da app e problemas a resolver
 A aplicação **BuyTogether** pretende resolver a falta de coordenação familiar criando listas de compras partilhadas entre os membros. Os utilizadores podem criar um grupo e adicionar os restantes membros, permitindo que todos tenham acesso à mesma lista de compras, cada membro pode adicionar, editar ou remover produtos, garantindo que a informação está sempre atualizada. Frequentemente, diferentes membros da família vão ao supermercado em momentos distintos, o que leva a esquecimentos. Para evitar isso, a aplicação centraliza a lista de compras e utiliza a localização para enviar notificações quando um membro entra num supermercado, permitindo ainda sugerir refeições com base nos ingredientes disponíveis em casa e indicando os supermecados mais pertos conforme a sua localização atual. Caso exista parceria com supermercados, a aplicação poderá futuramente disponibilizar um QR Code onde os membros do grupo podem utilizar no ato da compra para obter descontos exclusivos.

---

## 4. Objetivos e motivação
- Melhorar a organização das compras familiares  
- Evitar esquecimentos de produtos essenciais  
- Reduzir deslocações desnecessárias  
- Tornar o processo de compras mais colaborativo  
- Incentivar uma alimentação mais planeada com base nos ingredientes disponíveis em casa  
- Criar uma experiência mais moderna de compra com integrações (QR codes, descontos)  

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
| Partilha de listas entre membros | Não envia notificações ao entrar num supermercado |
| Sincronização em tempo real | Não sugere refeições com base no stock real em casa |
| Planeamento de refeições | Não indica supermercados mais próximos |
| Gestão de receitas | Não tem descontos ou QR codes |

### Bring!
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Listas de compras partilhadas | Não envia notificações ao entrar num supermercado |
| Lista de compras automática | Não sugere refeições com base nos ingredientes em casa |
| Sugestões de receitas | Não mostra supermercados próximos nem rotas |
| Organização por categorias | Não tem QR codes ou parcerias de descontos |

### Listonic
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Listas partilhadas | Não envia notificações ao entrar num supermercado |
| Sugestões inteligentes de produtos | Não faz planeamento de refeições  |
| Organização clara de itens e financeiro | Não tem descontos ou QR codes |

### SuperCook
| 🟢 Semelhanças | 🔴 Diferenças |
|---|---|
| Sugere refeições com base nos ingredientes disponíveis | Não tem lista de compras colaborativa |
| Evita desperdício alimentar | Não envia notificações ao entrar num supermercado |
| Ajuda a gerir stock doméstico | Não tem QR codes ou rotas para supermercados |

---

## 7. Guiões de teste

### Caso Core — Gerir lista partilhada
- **Ator:** Membro do grupo  
- **Pré-condição:** Grupo criado; utilizador autenticado.  
  1. Utilizador abre a app e seleciona o Grupo “Casa”; 
  2. Adiciona item à lista;
  3. A lista sincroniza e todos veem o estado atualizado.  
- **Pós-condição:** Lista atualizada e visível para todos.

### Caso 2 — Notificação por localização
- **Ator:** Membro do grupo (utilizador1) 
- **Pré-condição:** Grupo criado; utilizadores autenticados; Permissões de GPS ativas.
  1. utilizador1 entra no supermercado;
  2. A app envia notificação para todos aos restantes membros do grupo: “utilizador1 entrou no supermercado, pretende adicionar algum item á lista?”;
  3. Outros membros recebem a notificação e podem adicionar produtos;
  4. Item adicionado utilizador1 recebe notificação: "utilizador2 atualizou a lista, reveja!".
- **Pós-condição:** Lista atualizada antes da compra.

### Caso 3 — Sugestão de refeições
- **Ator:** Membro do grupo  
- **Pré-condição:** Grupo criado; utilizadores autenticados; Igredientes do stock registados.   
  1. Utilizador abre “Sugestões de refeições”  
  2. App propõe “Massa com atum”  
  3. Utilizador aproveita a ideia. 
- **Pós-condição:** Refeição escolhida.

### Caso 4 — Encontrar supermercados próximos
- **Ator:** Utilizador  
- **Pré-condição:** Permissões de GPS ativas.  
  1. Utilizador seleciona a opção “Encontrar supermercados”;  
  2. A app mostra os supermercados mais próximos (ex.: “Continente a 2 km”);
  3. O utilizador seleciona um supermercado e a app apresenta a rota no mapa;  
  4. O utilizador segue a rota até ao supermercado.
- **Pós-condição:** O utilizador encontra o supermercado mais próximo e tem acesso à rota até lá.

---

## 8. Descrição da solução a implementar

### 8.1 Descrição genérica
 A aplicação **BuyTogether** é uma solução mobile colaborativa que pretende facilitar a organização das compras em grupo. Os utilizadores podem criar ou entrar num grupo e partilhar uma lista de compras em tempo real, garantindo que todos têm acesso à mesma informação atualizada. Além disso, a app integra funcionalidades inteligentes: envia notificações quando um membro entra num supermercado, sugere refeições com base nos ingredientes disponíveis em casa e mostra os supermercados mais próximos através de geolocalização. Futuramente, poderá também incluir parcerias com supermercados, permitindo que os utilizadores utilizem um QR Code para obter descontos nas suas compras.

### 8.2 Enquadramento nas diversas Unidades Curriculares
O projeto **BuyTogether** resulta da integração dos conhecimentos adquiridos em várias Unidades Curriculares do 3.º semestre da Licenciatura em Engenharia Informática:

- **Programação Mobile**: desenvolvimento da aplicação em Android, utilizando Kotlin e Jetpack Compose para criar a interface e as funcionalidades principais da app.  
- **Programação Orientada a Objetos**: implementação das regras de negócio e da lógica do sistema no backend, através de Spring Boot e uma API REST.  
- **Bases de Dados**: modelação e implementação de uma base de dados relacional em MySQLWorkbench, responsável por armazenar utilizadores, grupos, listas de compras e refeições.  
- **Competências Comunicacionais**: elaboração dos documentos escritos (relatório), materiais gráficos (poster) e produção do vídeo promocional.  
- **Matemática Discreta**: apoio na definição de algoritmos para recomendações de refeições, bem como na estruturação lógica dos dados.

### 8.3 Requisitos técnicos (provisórios)
- Autenticação de utilizadores; 
- Criação e gestão de grupos;  
- Listas de compras colaborativas em tempo real;  
- Notificações push baseadas em GPS;  
- Sugestão de refeições a partir dos ingredientes baseados no stock;
- Integração com mapas para mostrar supermercados mais próximos;  
- Possibilidade de QR Code para descontos.  

### 8.4 Arquitetura da solução (provisória)
- Arquitetura **MVC**  
- **View**: Android Studio (Kotlin, Jetpack Compose) para interface e interação com o utilizador;  
- **Controller**: Backend em Spring Boot com REST API para lógica de negócio e integração;  
- **Model**: Base de dados relacional em MySQL (MySQL Workbench) para armazenamento de utilizadores, grupos, listas e produtos;  
- **Comunicação**: API REST com troca de dados em formato JSON/HTTP; 
- **Localização**: Integração com serviços de mapas (Google Maps API ou similar) para deteção de supermercados próximos e rotas;  
- **Sugestão de refeições**: Algoritmos baseados nos ingredientes disponíveis; possibilidade futura de integrar Inteligência Artificial (sistemas de recomendação) para sugerir refeições personalizadas;

### 8.5 Tecnologias a utilizar (provisórias)
- **Frontend (Aplicação Mobile)**  
  - Android Studio  
  - Kotlin  
  - Jetpack Compose (UI)

- **Backend (Servidor / API)**  
  - Spring Boot (Java)  
  - API REST para comunicação com a app  
  - JSON/HTTP para troca de dados  

- **Base de Dados**  
  - MySQL / PostgreSQL (gestão com MySQL Workbench ou pgAdmin)

- **Integrações externas**  
  - Google Maps API (localização de supermercados e rotas)  
  - Firebase Cloud Messaging (para notificações push)  
  - Possibilidade futura: modelos de Inteligência Artificial para recomendação de refeições  

- **Ferramentas de apoio**  
  - GitHub (controlo de versão e documentação)  
  - Figma (mockups e prototipagem)  
  - ProjectLibre / Excel (gestão de planeamento e Gantt)
 
---
## 9. Planeamento e Calendarização

### 9.1 Distribuição de Tarefas
A tabela seguinte apresenta a distribuição preliminar de tarefas entre os membros do grupo:

| Membro        | Tarefas principais |
|---------------|-------------------|
| Rodrigo Canto | Documentação (relatório, poster), pesquisa de mercado |
| Rodrigo Daibert | Backend (Spring Boot, API REST) |
| Marco Fonseca | Base de dados (modelação, queries) |
| Luís Quirim   | Frontend mobile (Kotlin, mockups Figma) |

---

### 9.2 Plano de Trabalhos

#### 9.2.1 Project Charter

- **Nome do Projeto**: BuyTogether  
- **Equipa**: Rodrigo Canto, Rodrigo Daibert, Marco Fonseca, Luís Quirim  
- **Data de Início**: Setembro 2025  
- **Prazo de Conclusão**: Janeiro 2026  

**Descrição do Projeto**  
Aplicação mobile colaborativa para gestão de listas de compras partilhadas. Permite adicionar e remover produtos em tempo real, receber notificações quando um membro entra num supermercado e sugerir refeições com base nos ingredientes disponíveis em casa. Futuramente poderá integrar QR Codes para descontos em supermercados parceiros.  

**Scope (Âmbito)**  
- **Incluído**: listas de compras colaborativas, notificações por geolocalização, sugestões de refeições, integração com mapas.  
- **Excluído**: pagamentos dentro da aplicação, parcerias comerciais formais (fase futura).  

**Business Case**  
- Melhorar a organização das compras familiares.  
- Evitar esquecimentos e duplicação de produtos.  
- Incentivar refeições mais planeadas.  
- Potencial parceria futura com supermercados para descontos.  

**Restrições**  
- **Tempo**: 3º semestre letivo (14 semanas).  
- **Equipa**: 4 elementos com funções distintas (frontend, backend, BD, documentação).  
- **Tecnologia**: Android Studio, Spring Boot, MySQLWorkbench.  

**Entregáveis**  
- Protótipo funcional da aplicação.  
- Relatório técnico e académico.  
- Poster de apresentação.  
- Vídeo promocional.  

**Stakeholders**  
- **Primários**: famílias, casais, estudantes que vivem juntos.  
- **Secundários**: supermercados.  

**Riscos (iniciais)**  
- Dificuldade em integrar notificações por localização em tempo real.  
- Problemas de sincronização entre utilizadores.  
- Possível subestimação do esforço de desenvolvimento.  

---

#### 9.2.2 Work Breakdown Structure (WBS)

---

#### 9.2.3 Requisitos Funcionais e Não Funcionais
- **Funcionais**:  


- **Não Funcionais**:  


---

#### 9.2.4 Modelo do Domínio
![Modelo do Domínio](Imagens/modelo-dominio.png)

---

#### 9.2.5 Mockups e Interfaces
- **Ecrã de Login / Registo**  
![Mockup Login](Imagens/mockup-login.png)

- **Ecrã Lista de Compras**  
![Mockup Lista](Imagens/mockup-lista.png)

- **Notificação por Localização**  
![Mockup Notificação](Imagens/mockup-notificacao.png)

- **Sugestão de Refeições**  
![Mockup Refeições](Imagens/mockup-refeicoes.png)

---

#### 9.2.6 Planificação 

| Fase                  | S1 | S2 | S3 | S4 | S5 | S6–10 | S11–14 |
|-----------------------|----|----|----|----|----|-------|--------|
| Análise & Planeamento | X  | X  |    |    |    |       |        |
| Design (mockups, BD)  |    | X  | X  | X  |    |       |        |
| Implementação inicial |    |    | X  | X  | X  |   X   |        |
| Testes iniciais       |    |    |    |    | X  |   X   |        |
| Protótipo funcional   |    |    |    |    |    |   X   |        |
| Relatório / Poster    | X  |    |    | X  |    |       |   X    |
| Apresentação final    |    |    |    |    |    |       |   X    |

