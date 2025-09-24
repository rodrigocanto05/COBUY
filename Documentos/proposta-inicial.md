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
A aplicação **COBUY** surge como resposta a um problema muito comum em famílias, casais ou grupos de amigos: a falta de coordenação nas compras do dia a dia.  

Numa família típica de quatro pessoas, é frequente cada elemento ir ao supermercado em momentos diferentes. Esta prática leva a dois cenários problemáticos: a compra repetida de produtos (ex.: três embalagens de leite quando já havia suficiente) e o esquecimento de itens essenciais (ex.: detergente ou pão).  

Além disso, a ausência de uma lista partilhada torna difícil controlar o stock de alimentos em casa, resultando em desperdício alimentar e gastos desnecessários.  

A **COBUY** resolve estas falhas através de:  
- **Listas de compras colaborativas em tempo real**: todos os membros do grupo têm acesso à mesma informação, podendo adicionar, editar ou remover produtos.  
- **Localização inteligente**: a app identifica os supermercados mais próximos do utilizador e apresenta a rota até ao local escolhido.  
- **Refeições inteligentes**: ao escrever o nome de uma refeição (ex.: “massa com atum”), a aplicação gera automaticamente os ingredientes necessários, ajustando as quantidades ao número de pessoas.  

Este conjunto de funcionalidades visa **otimizar o tempo, reduzir custos e aumentar a eficiência das compras**, trazendo modernidade e colaboração ao ato de fazer compras.  

---

## 4. Objetivos e motivação
- Melhorar a organização das compras.  
- Evitar esquecimentos de produtos essenciais.  
- Reduzir deslocações desnecessárias.  
- Tornar o processo de compras mais colaborativo.  
- Incentivar uma alimentação planeada através de receitas.  

---

## 5. Público-alvo
O público-alvo da COBUY é bastante abrangente, englobando vários perfis:  

- **Famílias**: que precisam de coordenar as compras de vários membros.  
- **Casais**: que desejam dividir responsabilidades de forma prática.  
- **Estudantes**: que partilham casa e dividem despesas, podendo usar a app para controlar o orçamento conjunto.  
- **Grupos de amigos**: que organizam eventos (como churrascos ou festas) e necessitam de gerir as compras de forma colaborativa.  

**Exemplos de personas:**  
- *João, 25 anos*, estudante universitário que divide casa com três colegas. A COBUY ajuda-o a gerir de forma justa e organizada as compras semanais do grupo.  
- *Marta, 40 anos*, mãe de dois filhos, utiliza a COBUY para garantir que a família não se esquece de nenhum produto essencial e para planear refeições semanais.  

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

## 7. Guiões de teste

### Caso Core — Gerir lista partilhada
- **Ator:** Membro do grupo  
- **Pré-condição:** Grupo criado; utilizador autenticado.  
1. Utilizador abre a app e seleciona o Grupo “Casa”.  
2. Adiciona item à lista.  
3. A lista sincroniza e todos veem o estado atualizado.  
- **Pós-condição:** Lista atualizada e visível para todos.  

### Caso 2 — Supermercados próximos e rota
- **Ator:** Utilizador  
- **Pré-condição:** Permissões de GPS ativas.  
1. Utilizador seleciona a opção “Encontrar Supermercado”.  
2. A app mostra os supermercados mais próximos (ex.: “Continente a 2 km”).  
3. O utilizador seleciona um supermercado e a app apresenta a rota no mapa.  
4. O utilizador segue a rota até ao supermercado.  
- **Pós-condição:** O utilizador encontra o supermercado e acede à rota.  

### Caso 3 — Refeição → ingredientes e quantidades
- **Ator:** Utilizador  
- **Pré-condição:** Grupo criado; utilizador autenticado.  
1. Utilizador escreve “Massa com atum” e indica “4 pessoas”.  
2. A app devolve lista de ingredientes com quantidades ajustadas (ex.: 400g massa, 2 latas atum, azeite, sal).  
3. Utilizador verifica se tem os ingredientes e adiciona os que faltam à lista.  
- **Pós-condição:** Refeição concluída.  

### Caso 4 — Edição em tempo real
- **Ator:** Membro do grupo  
- **Pré-condição:** Grupo criado; lista já existente.  
1. Utilizador 1 adiciona “leite” à lista.  
2. Utilizador 2 remove “leite” ao mesmo tempo.  
3. O sistema resolve conflito e apresenta estado atualizado em ambos os dispositivos.  
- **Pós-condição:** Lista final sincronizada.  

### Caso 5 — Erro de autenticação
- **Ator:** Utilizador  
- **Pré-condição:** Sem ligação à internet.  
1. Utilizador tenta entrar.  
2. A aplicação apresenta mensagem de erro: *“Sem conexão. Tente novamente mais tarde.”*  
- **Pós-condição:** Utilizador informado e tentativa guardada em cache.  

---

## 8. Project Charter

### 8.1 Descrição genérica
A aplicação **COBUY** é uma solução mobile colaborativa que pretende facilitar a organização das compras em grupo. Os utilizadores partilham uma lista de compras em tempo real, podem visualizar os supermercados mais próximos através de localização e visualizar ingredientes e quantidades a partir de refeições introduzidas.  

### 8.2 Enquadramento nas diversas Unidades Curriculares
O projeto **COBUY** resulta da integração dos conhecimentos adquiridos em várias Unidades Curriculares do 3.º semestre:  

- **Programação de Dispositivos Móveis**: desenvolvimento da aplicação.  
- **Programação Orientada a Objetos**: implementação da lógica de negócio.  
- **Bases de Dados**: modelação em MySQLWorkbench (para sincronização servidor).  
- **Competências Comunicacionais**: relatório, poster e vídeo.  
- **Matemática Discreta**: quantidades em receitas (proporções).  

### 8.3 Requisitos técnicos (provisórios)

**Funcionais:**  

**Não funcionais:**  


### 8.4 Arquitetura da solução (provisória)
- Arquitetura **MVC**  
- **View**: Android (Kotlin, Jetpack Compose)  
- **Controller**: Serviços locais + API REST (Spring Boot)  
- **Model**: MySQLWorkbench  
- **Localização**: Google Maps API (Places + Directions)  
- **Receitas**: Inteligência Artificial (provisório)  

### 8.5 Tecnologias a utilizar
- **Frontend:** Android Studio  
- **Backend:** Spring Boot  
- **Base de Dados:** MySQLWorkbench  
- **Integrações externas:** Google Maps API (localização/rotas)  
- **Ferramentas:** GitHub, Figma  

---

## 9. Planeamento e Calendarização

### 9.1 Distribuição de Tarefas
A distribuição de tarefas pelos membros do grupo está representada no gráfico de Gantt seguinte:  

![Gráfico de Gantt — Distribuição de Tarefas](https://github.com/user-attachments/assets/3c26bd66-a243-4799-87a2-9ded1f1976a8)  

---

### 9.2 Plano de Trabalhos
O planeamento temporal do projeto encontra-se representado no gráfico de Gantt:  

![Gráfico de Gantt do Projeto COBUY](https://github.com/user-attachments/assets/325e8b1b-d552-4638-b5c6-dc74c1d6d615)  

---

### 9.3 Modelo do Domínio
O modelo do domínio da aplicação pode ser representado pelas seguintes entidades:  

- **Utilizador** (id, nome, email, password)  
- **Grupo** (id, nome, lista associada)  
- **Lista** (id, produtos, estado)  
- **Produto** (id, nome, quantidade, estado comprado/não comprado)  
- **Refeição** (id, nome, ingredientes, quantidades)  

**Relações:**  
- Um **utilizador** pode pertencer a vários grupos.  
- Cada **grupo** tem pelo menos uma **lista**.  
- A **lista** contém vários **produtos**.  
- As **refeições** geram automaticamente produtos para a lista.  

---

#### 9.3.1 Mockups e Interfaces
- **Ecrã de Login / Registo**  
- **Ecrã Lista de Compras**  
- **Rota de Supermercados**  
- **Refeições**  

---

## 10. Conclusão
A **COBUY** não é apenas uma lista de compras digital, mas sim uma **plataforma colaborativa e inteligente**. O seu impacto pode ser sentido em três níveis:  

- **Social:** fortalece a colaboração entre membros de famílias, casais e grupos de amigos, criando um hábito mais organizado e participativo.  
- **Económico:** reduz desperdícios e gastos desnecessários, já que os utilizadores compram apenas o que precisam.  
- **Tecnológico:** combina funcionalidades modernas como sincronização em tempo real, geolocalização e algoritmos inteligentes de recomendação.  

Futuramente, a COBUY poderá integrar-se com supermercados locais para permitir compras online, gerar listas a partir de comandos de voz e até sugerir menus semanais completos com base em restrições alimentares.  

Assim, este projeto não só cumpre os objetivos inicialmente propostos, como também abre caminho para evolução futura em direção a uma solução de **smart shopping**.  

---

## 11. Bibliografia
AnyList. (2025). *AnyList app*. Recuperado de https://www.anylist.com/  

Bring! Labs AG. (2025). *Bring! Shopping list & recipes*. Recuperado de https://www.getbring.com/  

Listonic. (2025). *Smart grocery shopping list*. Recuperado de https://listonic.com/  

SuperCook. (2025). *Recipe generator*. Recuperado de https://www.supercook.com/  

Google Developers. (2025). *Google Maps Platform documentation*. Recuperado de https://developers.google.com/maps  

Android Developers. (2025). *Android Studio documentation*. Recuperado de https://developer.android.com/studio  

Spring. (2025). *Spring Boot reference documentation*. Recuperado de https://spring.io/projects/spring-boot  

Oracle. (2025). *MySQL reference manual*. Recuperado de https://dev.mysql.com/doc/  
