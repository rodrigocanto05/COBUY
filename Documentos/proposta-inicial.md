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
A aplicação **BuyTogether** pretende resolver a falta de coordenação familiar criando listas de compras partilhadas entre os membros. Os utilizadores podem criar um grupo e adicionar os restantes membros da casa, permitindo que todos tenham acesso à mesma lista de compras, cada membro pode adicionar, editar ou remover produtos, garantindo que a informação está sempre atualizada. Frequentemente, diferentes membros da família vão ao supermercado em momentos distintos, o que leva a esquecimentos. Para evitar isso, a aplicação centraliza a lista de compras e utiliza a localização para enviar notificações quando um membro entra num supermercado, permitindo ainda sugerir refeições com base nos ingredientes disponíveis em casa e indicando os supermecados mais pertos conforme a sua localização atual. Caso exista parceria com supermercados, a aplicação poderá ainda disponibilizar um QR Code que os membros do grupo podem utilizar no ato da compra para obter descontos exclusivos.

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

---

## 8. Casos de utilização
- **Core:** Gerir lista de compras partilhada (CRUD de produtos na lista)
- **Complementar 1:** Notificações automáticas quando alguém está perto de um supermercado
- **Complementar 2:** Sugestão de refeições com base nos ingredientes registados como existentes em casa

---

## 9. Descrição da solução

### 9.1 Descrição genérica
App mobile colaborativa com lista de compras partilhada, alertas por geolocalização e sugestões de refeições.

### 9.2 Enquadramento nas UCs
- Programação Mobile — App Android com :contentReference[oaicite:8]{index=8} (:contentReference[oaicite:9]{index=9} e :contentReference[oaicite:10]{index=10})
- Programação Orientada a Objetos — Back-end :contentReference[oaicite:11]{index=11} com API :contentReference[oaicite:12]{index=12}
- Bases de Dados — :contentReference[oaicite:13]{index=13} para armazenamento de utilizadores, produtos, grupos e refeições
- Competências Comunicacionais — Poster, vídeo e pitch
- Matemática Discreta — Algoritmo para sugestão de refeições com base nas combinações possíveis de ingredientes

### 9.3 Requisitos técnicos provisórios
- Autenticação de utilizadores  
- Sistema de grupos  
- Notificações push  
- Permissões de localização (GPS)  
- Integração com mapas  
- Base de dados relacional

### 9.4 Arquitetura provisória
MVC + API REST + Base de Dados relacional

### 9.5 Tecnologias a utilizar
:contentReference[oaicite:14]{index=14}, :contentReference[oaicite:15]{index=15}, :contentReference[oaicite:16]{index=16}, :contentReference[oaicite:17]{index=17}, :contentReference[oaicite:18]{index=18}, :contentReference[oaicite:19]{index=19}

---

## 10. Planeamento e calendarização
- Semana 1-2: Proposta inicial, mockups, modelação da BD  
- Semana 3-5: Protótipo e servidor  
- Semana 6-10: Desenvolvimento das funcionalidades principais  
- Semana 11-14: Testes, integração e relatório final  

*(Será apresentado em gráfico de Gantt no :contentReference[oaicite:20]{index=20} ou :contentReference[oaicite:21]{index=21})*

---

## 11. Conclusão
O projeto **ShopSync** permitirá que as famílias façam compras de forma coordenada e inteligente, com base na geolocalização e nas necessidades reais da casa, reduzindo desperdício e aumentando a eficiência.

---

## 12. Bibliografia
- Documentação oficial :contentReference[oaicite:22]{index=22}  
- Documentação oficial :contentReference[oaicite:23]{index=23}  
- Documentação oficial :contentReference[oaicite:24]{index=24}  
