# Avaliação Java


## Descrição

  O teste consiste em construir a camada de serviço de um pseudo ecommerce de games mobile utilizando Java

## Como executar os testes
  
  O projeto usa o maven wrapper (mvnw).
  Para executar os testes de exemplo basta o comando abaixo:
  ```sh
  ./mvnw clean test
  ```

## Requisitos

  - Existe um exemplo de carga de banco de dados em memória em [ProductDaoExampleTest.java](./src/test/java/br/com/supera/game/store/ProductDaoExampleTest.java)
  - Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente
  - O usuário poderá adicionar e remover produtos do carrinho
  - O usuário poderá ordenar os produtos por preço, popularidade (score) e ordem alfabética.
  - A cada produto adicionado, deve-se somar R$ 10,00 ao frete.
  - Quando o valor dos produtos adicionados ao carrinho for igual ou superior a R$ 250,00, o frete é grátis.

## O que iremos avaliar

Levaremos em conta os seguintes critérios:

  - Cumprimento dos requisitos
  - Qualidade do projeto de API e fluidez da DX
  - Organização do código e boas práticas
  - Domínio das linguagens, bibliotecas e ferramentas utilizadas
  - Organização dos commits
  - Escrita e cobertura de testes

## Sobre a entrega

  - A API pode ser HTTP, Restful, WSDL/SOAP, HATEOAS ou gRCP mas deverá ser documentado no [README.md](./README.md) como executar/compilar/empacotar o projeto e quais os endpoints solicitados nos requisitos acima. 
  - A versão do Java pode ser atualizada para 11 ou 14.
  - Não existe restrição de framework (EE, Spring, Quarkus etc) mas será melhor avaliado se justificado no [README.md](./README.md) os motivos da decisão.

## Entrega do Projeto

### Objetivos do Projeto
- Usuário: Cadastrar, Editar, Excluir, Listar e Buscar por ID [X];
- Produto: Cadastrar, Editar, Excluir, Listar e Buscar por ID [X];
- Ordenação de produtos por: Preço, Pontuação e Ordem Alfabética [X];
- Regra de Negócio: À cada produto incluso no carrinho, soma-se 10,00 de frete [X];
- Regra de Negócio: Quando a compra for igual ou maior que R$250,00 o frete é gratis [X]
- Regra de Negócio: Adição e remoção de produtos do carrinho [X];
- Regra de Negócio: Valores de frete, subtotal e total calculados dinamicamente [X];
- Teste Unitário [ ];

### Tecnologias utilizadas no Projeto:
- Java 11;
- Spring Boot 2.x;
- Maven 3+;
- Banco de dados em memória H2 DataBase;
- IDE Intelij;
- Open API;

### Pré Requisitos:
- JDK 11;
- Intelij ou IDE de preferência;
- Maven;

### Rodando a Aplicação: 
- Você tem duas formas de rodar o programa: a primeira é usando o maven embutido no projeto da seguinte forma, lembrando que o terminal deve estar no diretório e com a pasta aberta PS-Java, 
normalmente usando o comando cd PS-Java.


- Windows:
  ```sh
  mvnw clean install
  ```
- Linux:
  ```sh
  ./mvnw install
  ```
  
### Acessando a API
Como documentação esta API expõe o swagger, você poderá ver quais endpoints estão disponíveis e testar a api sem depender do frontend.
  ```sh
http://localhost:8080/swagger-ui.html
  ```

### Acessando o H2 DataBase:
Você pode verificar todo Schema do banco de dados através da url: 
  ```sh
http://localhost:8080/h2-console.
  ```
  
JDBC URL: jdbc:h2:mem:db_gamestore

USER NAME: sa

Password: password

### Instruções para Ordenação dos produtos:
- Ordenação por ordem alfabética utilize o método GET na URI:
  ```sh
  http://localhost:8080/api/v1/produtos?sort=nome.asc
  ```
- Ordenação por preço mais caro utilize o método GET na URI:
  ```sh
  http://localhost:8080/api/v1/produtos?sort=preco.desc
  ```
- Ordenação por preço mais barato utilize o método GET na URI:
  ```sh
  http://localhost:8080/api/v1/produtos?sort=preco.asc
  ```
- Ordenação por maior pontuação utilize o método GET na URI:
  ```sh
  http://localhost:8080/api/v1/produtos?sort=pontuacao.desc
  ```
- Ordenação por menor pontuação caro utilize o método GET na URI:
  ```sh
  http://localhost:8080/api/v1/produtos?sort=preco.asc
  ```
  

### Rodando os Testes:

É necessário rodar o seguinte comando para rodar os testes.

  ```sh
  ./mvnw clean test
  ```

### Sobre o Autor/Organizador: 
- Deivisson da Silva Santos;
- Email: deivissonsantos@hotmail.com;
- Dúvidas, criticas ou sugestões? Vamos discutir. 
- Linkedin: https://www.linkedin.com/in/deivisson-santos-96296b131/

