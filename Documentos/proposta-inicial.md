## 1. Identificação
- Universidade: Universidade Europeia  
- Faculdade: IADE
- Elementos do grupo: Rodrigo Canto, Rodrigo Daibert, Marco Fosenca e Luís Quirim   
- Nome do projeto: BuyTogether  
- Repositório GitHub: https://github.com/rodrigocanto05/BuyTogether.git 

---

## 2. Palavras-chave
lista de compras, localização, notificações, refeições práticas, compras colaborativas.

---

## 3. Descriçao da app e problemas a resolver
A aplicação **BuyTogether** pretende resolver a falta de coordenação familiar criando listas de compras partilhadas entre os membros. Os utilizadores podem criar um grupo e adicionar os restantes membros da casa, permitindo que todos tenham acesso à mesma lista de compras, cada membro pode adicionar, editar ou remover produtos, garantindo que a informação está sempre atualizada. Frequentemente, diferentes membros da família vão ao supermercado em momentos distintos, o que leva a esquecimentos. Para evitar isso, a aplicação centraliza a lista de compras e utiliza a localização para enviar notificações quando um membro entra num supermercado, permitindo ainda sugerir refeições com base nos ingredientes disponíveis em casa e indicando os supermecados mais pertos conforme a sua localização atual. Caso exista parceria com supermercados, a aplicação poderá ainda disponibilizar um QR Code que os membros do grupo podem utilizar no ato da compra para obter descontos exclusivos.

---

## 4. Objetivos e motivação
- Melhorar a organização das compras familiares; 
- Evitar esquecimentos de produtos essenciais;
- Reduzir deslocações desnecessárias;
- Tornar o processo de compras mais colaborativo:  
- Incentivar uma alimentação mais planeada com base nos ingredientes disponíveis em casa.

---

## 5. Público-alvo
Famílias, casais e grupos de amigos.

---

## 6. APPs Semelhantes
**Semelhanças**:
- AnyList — listas compartilhadas, planeamento de refeições;
- Bring! - listas compartilhadas, ideias de receitas e cartoes de fidelidade; 
- Listonic - listas compartilhdas.

**Diferenças:** Nenhuma destas apps inclui notificações baseadas em GPS nem sugestões de refeições baseadas nos ingredientes disponíveis como a BuyTogether, porém Listonic tem guias de produtos para aconselhamento mais saudavel e controle de orçamento.

---

## 7. Guiões de teste

### Guião 1 — Caso Core: Adicionar produto à lista
1. Utilizador abre a app;  
2. Seleciona o grupo pretendido; 
3. Escreve "Pão" e carrega em "Adicionar"  
4. Produto aparece na lista para todos os membros

### Guião 2 — Notificação por localização
1. Utilizador aproxima-se de um :contentReference[oaicite:4]{index=4}  
2. A app deteta a localização e envia notificação "Vai às compras? Adicione produtos de última hora"  
3. Outros membros recebem aviso para adicionar produtos

### Guião 3 — Escolher supermercado e rota
1. Utilizador abre o mapa na app  
2. Seleciona :contentReference[oaicite:5]{index=5}  
3. A app mostra o :contentReference[oaicite:6]{index=6} mais próximo e o trajeto com :contentReference[oaicite:7]{index=7}

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
