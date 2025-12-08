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

- Registo e autenticação de utilizadores.
- Gestão de grupos colaborativos.
- Criação e edição de listas de compras partilhadas.
- Gestão de itens (adicionar, remover, concluir).
- Consulta de receitas completas com modo de preparação.
- Adicionar automaticamente ingredientes da receita à lista.
- Localização de supermercados próximos a partir do IADE.
- Visualização de rotas até um supermercado.
- Consultar/Editar perfil de utilizador.


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

### vii. Diagrama de classes

Aqui se encontra a versao final do diagrama de calsses da **COBUY**:

<img width="1010" height="500" alt="Captura de ecrã 2025-12-08, às 11 48 54" src="https://github.com/user-attachments/assets/132216bf-410c-437f-84e4-b19050ba9069" />

---

### viii. BD_Report 



---

### ix. Documentação REST



---


# 8. 

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
