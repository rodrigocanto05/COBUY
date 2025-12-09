# 1. Identificação
- Universidade: Universidade Europeia  
- Faculdade: IADE  
- Elementos do grupo: Rodrigo Canto, Rodrigo Daibert, Marco Fonseca e Luís Quirin
- Nome do projeto: COBUY  
- Repositório GitHub: https://github.com/rodrigocanto05/COBUY.git

---
# 2. Distribuição das principais tarefas

| **Tarefas Principais** | **Rodrigo Canto**(~33%) | **Rodrigo Daibert**(~30%) | **Marco Fonseca**(~22%) | **Luís Quirin**(~15%) | **Total** |
|----------------------|-------------------|----------------------|-------------------|------------------|-----------|
| **1. Levantamento de requisitos** | 25% | 25% | 25% | 25% | **100%** |
| **2. Pesquisa de mercado** | 25% | 25% | 25% | 25% | **100%** |
| **3. Design e mockups (Figma)** | 30% | 10% | 5% | 55% | **100%** |
| **4. Base de Dados** | 85% | 0% | 15% | 0% | **100%** |
| **5. Desenvolvimento Android (Frontend)** | 15% | 70% | 15% | 0% | **100%** |
| **6. Desenvolvimento da API (Backend – Spring Boot, Postman)** | 70% | 25% | 0% | 5% | **100%** |
| **7. Integração com Mapas / Rotas / Locais Salvos** | 75% | 25% | 0% | 0% | **100%** |
| **8. Implementação de Grupos e Listas** | 0% | 75% | 0% | 25% | **100%** |
| **9. Implementação de Receitas** | 0% | 0% | 100% | 0% | **100%** |
| **10. Testes e validação (funcionais + usabilidade)** | 25% | 45% | 30% | 0% | **100%** |
| **11. Relatório final + poster + vídeo de apresentação** | 25% | 25% | 25% | 25% | **100%** |

---

# 3. Descrição da app e problemas a resolver

A **COBUY** é uma aplicação móvel desenvolvida para resolver problemas comuns na organização das compras do dia a dia, especialmente em famílias, casais, estudantes e grupos de amigos. A ausência de uma lista partilhada e atualizada leva frequentemente à compra duplicada de produtos, ao esquecimento de itens essenciais e à falta de controlo do stock doméstico, resultando em desperdício, gastos desnecessários e deslocações evitáveis ao supermercado.

Para responder a estas falhas, a COBUY integra três funcionalidades principais:

- **Listas de compras colaborativas em tempo real**, permitindo que vários utilizadores adicionem, editem ou concluam itens numa lista partilhada. Esta sincronização evita esquecimentos e compras repetidas.
  
- **Localização inteligente**, que identifica supermercados próximos do IADE e apresenta rotas, facilitando a escolha do local mais conveniente e reduzindo o tempo gasto em deslocações.

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
- **Integrar funcionalidades de localização**, permitindo encontrar rapidamente supermercados próximos do IADE e aceder às rotas.
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
- Guardar/Remover supermercados proximos nos Locais Salvos.
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

## vi. Versão atualizada dos Casos de Utilização

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

https://github.com/rodrigocanto05/COBUY/blob/main/Documentos/BD_Report.md

---

### ix. Documentação REST

https://github.com/rodrigocanto05/COBUY/blob/main/Documentos/REST.md

---

### ix. Manual de utilização

https://github.com/rodrigocanto05/COBUY/blob/main/Documentos/Manual.md

---

# 8. Planeamento e calendarização final

O planeamento do projeto **CoBuy** foi estruturado com base numa distribuição temporal coerente com as necessidades do desenvolvimento, garantindo uma progressão lógica desde a fase inicial de análise até à implementação e validação final. O cronograma foi organizado em semanas e contempla todas as etapas essenciais do projeto, permitindo acompanhar a evolução das tarefas e identificar a carga de trabalho ao longo do semestre.

A calendarização encontra-se representada no gráfico de Gantt abaixo, onde é possível visualizar a duração de cada tarefa, bem como os respetivos períodos de execução ao longo do projeto.

<img width="800" height="500" alt="gantt" src="https://github.com/user-attachments/assets/788723b5-cebd-42df-acf2-d07909543e1c" />

---

### 8.1 Justificação do planeamento

O planeamento foi elaborado de forma a:

- **Garantir uma evolução progressiva** desde a análise de requisitos até à implementação completa;
- **Distribuir as fases críticas ao longo do tempo**, evitando picos de trabalho excessivo;
- **Permitir ciclos de iteração**, sobretudo nas fases de desenvolvimento Android, backend e testes;
- **Reservar períodos específicos para documentação e entrega**, refletidos nas duas fases do relatório (semanas 1–4 e 12–14).

A sobreposição controlada de tarefas reflete uma abordagem de desenvolvimento iterativo, em que o backend, o frontend e a integração foram evoluindo em paralelo, permitindo testar e ajustar funcionalidades ao longo do processo.

---

### 8.3 Análise da execução

- As fases de **levantamento de requisitos**, **pesquisa de mercado** e **design de interfaces** foram concentradas no início do projeto, criando uma base sólida para o desenvolvimento técnico.
- A implementação da **base de dados**, **API REST** e **aplicação Android** decorreu em paralelo, permitindo integrar progressivamente as funcionalidades principais.
- A **integração com Mapas, Rotas e Locais Salvos** foi iniciada apenas após a existência da API do Google e de uma estrutura mínima de dados.
- As funcionalidades de **grupos, listas colaborativas e receitas** foram desenvolvidas numa fase intermédia, já com a arquitetura estabilizada.
- Os **testes funcionais e de usabilidade** acompanharam as últimas semanas de desenvolvimento, permitindo corrigir erros e refinar a experiência do utilizador.
- A **documentação final** (relatório, poster e vídeo) foi trabalhada em dois momentos: uma fase inicial dedicada à proposta e estrutura do relatório, e uma fase final para consolidação dos resultados e preparação da apresentação.

Este planeamento permitiu manter uma visão clara da evolução do projeto, assegurando que as funcionalidades críticas ficassem concluídas antes da fase de testes e da preparação da entrega final.

---

## 9. Autoavaliação da implementação do projeto

A implementação do projeto CoBuy revelou-se um desafio significativo para o grupo, sobretudo pela necessidade de integrar múltiplas componentes — base de dados, backend, frontend e APIs externas — num único sistema coerente e funcional. Ao longo do desenvolvimento, surgiram vários obstáculos técnicos e organizacionais que influenciaram o resultado final, mas que também contribuíram para um processo de aprendizagem aprofundado.

---

### 9.1 Dificuldades encontradas

O projeto exigiu a articulação constante entre diferentes áreas tecnológicas, sendo o frontend a parte mais trabalhosa devido ao volume de ecrãs, navegação e interações necessárias. A integração com o backend e com funcionalidades adicionais, como mapas, rotas e receitas, aumentou a complexidade geral do sistema.

A nível organizacional, verificou-se que um dos membros do grupo não conseguiu contribuir como esperado na fase mais intensiva do desenvolvimento, o que gerou frustração e desequilíbrios na distribuição de tarefas. Embora o grupo reconheça que poderia ter existido uma melhor organização interna, a falta de participação desse elemento afetou a carga de trabalho dos restantes membros.

---

### 9.2 Funcionalidades que não foram implementadas e razões

Apesar do esforço geral, algumas funcionalidades planeadas inicialmente não foram implementadas, por motivos de tempo, complexidade técnica ou aconselhamento direto dos professores:

- **Notificações quando alguém entra no supermercado**: ideia presente na proposta inicial, mas considerada demasiado complexa devido à necessidade de monitorização contínua de localização e comunicação em tempo real. Os professores recomendaram não avançar com esta funcionalidade.

- **Receitas inteligentes geradas por IA**: o objetivo inicial era o utilizador escrever o nome de uma refeição e a aplicação, através de IA, gerar os ingredientes. A complexidade da integração e o tempo necessário para treinar ou consumir modelos externos levaram à substituição por uma solução mais simples e estável.

- **Mapa com supermercados próximos baseados na localização real do utilizador**: embora exista integração com mapas, a funcionalidade ficou limitada a uma zona específica (IADE), devido a constrangimentos técnicos e de tempo. A deteção dinâmica da localização ainda não foi concluída.

O grupo reconhece que algumas destas funcionalidades tinham potencial, mas eram demasiado ambiciosas para o calendário disponível.

---

### 9.3 Alterações e prioridades novas durante o projeto

Embora algumas funcionalidades não tenham avançado, outras acabaram por ser expandidas ou adicionadas:

- A área das **receitas** foi melhorada com visualização de ingredientes e adição rápida à lista de compras.
- A navegação e o design foram ajustados várias vezes para melhorar a experiência do utilizador.
- A estrutura interna do backend e da app foi reorganizada para garantir maior estabilidade.

Estas decisões permitiram focar no essencial e entregar um produto funcional e coerente dentro do prazo.

---

### 9.4 Avaliação final do grupo

A segunda entrega do semestre ficou aquém do esperado devido a atrasos e falhas de organização, mas na terceira fase três membros do grupo trabalharam intensamente para recuperar o ritmo e concluir o projeto. Houve um esforço coletivo significativo para garantir a qualidade e estabilidade da aplicação final.

Apesar das dificuldades, o grupo considera que:

- O resultado final supera as etapas anteriores;  
- A aplicação está funcional, intuitiva e próxima daquilo que foi idealizado;  
- O processo, embora desgastante, foi uma oportunidade real de aprendizagem prática;  
- Este projeto foi mais exigente do que os dos semestres anteriores, mas também o mais enriquecedor.

Existe até a possibilidade futura de continuar o desenvolvimento do CoBuy, caso o grupo deseje transformar este conceito numa aplicação completa.

---

## 10. Conclusão

O projeto CoBuy permitiu integrar conhecimentos de várias áreas do desenvolvimento de software, resultando numa aplicação funcional e alinhada com o objetivo inicial: simplificar e organizar as compras colaborativas do dia-a-dia. Foram implementadas funcionalidades essenciais como criação de grupos, listas partilhadas, integração com mapas e sistema de receitas, garantindo uma experiência consistente e intuitiva para o utilizador.

Apesar de algumas funcionalidades não terem sido concluídas devido a limitações de tempo e complexidade técnica, o projeto evoluiu de forma sólida e manteve-se fiel à visão principal. O trabalho realizado permitiu reforçar competências técnicas, promover trabalho colaborativo e enfrentar desafios reais de implementação.

No geral, o CoBuy representa um projeto completo, útil e com potencial para ser expandido no futuro, demonstrando a capacidade do grupo em conceber, desenvolver e entregar uma solução tecnológica coerente e funcional.

---

## 11. Bibliografia 

AnyList. (2025). *AnyList app*. https://www.anylist.com/

Android Developers. (2025). *Android Studio documentation*. https://developer.android.com/studio

Android Developers. (2025). *Jetpack Compose documentation*. https://developer.android.com/jetpack/compose

Bring! Labs AG. (2025). *Bring! Shopping list & recipes*. https://www.getbring.com/

Google Developers. (2025). *Google Maps Platform documentation*. https://developers.google.com/maps

Listonic. (2025). *Smart grocery shopping list*. https://listonic.com/

Oracle. (2025). *MySQL reference manual*. https://dev.mysql.com/doc/

Postman. (2025). *Postman API testing platform*. https://www.postman.com/

Spring. (2025). *Spring Boot reference documentation*. https://spring.io/projects/spring-boot

SuperCook. (2025). *Recipe generator*. https://www.supercook.com/
