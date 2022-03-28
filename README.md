# Desafio - Spring
> Wave 5 - Grupo 6 (Java Lee)

## Objetivo
O objetivo deste sprint é aplicar os conteúdos fornecidos até o momento durante o
BOOTCAMP MeLi (Git, Java e Spring), para poder implementar uma API REST a partir de uma
declaração proposta, uma especificação de requisitos e documentação anexada.

**A collection utilizada no POSTMAN se encontra na pasta *doc/documentacao_do_desafio***

**O LOG dos requisitos estão no arquivo *doc/documentacao_do_desafio/LOG_REQUISITOS.md"***

## Arquitetura da aplicação (multicamadas)
- **DTO** - Camada responsável por receber as informações brutas e transforma-las em um objeto que a aplicação compreenda, além disso, ela pode servir também para transformar os dados a serem retornados para o cliente.
- **Controller** - Esta camada está responsável por interceptar as requisições que chegam na API, e transformar os dados através da camada de DTO para um formato que a aplicação compreenda. Em seguida estes dados são enviados à camada de Services e o retorno pode ou não passar pelo DTO antes da resposta ao cliente.
- **Services** - Esta camada está responsável por encapsular toda a regra de negócio da aplicação. Inclusive, o controller desconhece sua implementação e basicamente, ele apenas passa os parâmetros necessários para o Service e recebe um retorno.
- **Repositories** - Responsável por separar a camada de domínio da camada de persistência de dados, os Repositories auxiliam na manutenção, extensão e até na criação dos testes. Além disso, esta isolação permite camada de Services se dedique apenas pela lógica de negócio.
- **Models** - É a camada responsável por manter todas as entidades existentes na aplicação, como Produto, Endereço, Cliente, etc.
- **Database** - É a camada que cuida de toda configuração necessária para se conectar com o banco de dados ou arquivo que irá persistir os dados.
- **Exceptions** - A aplicação está preparada para tratar as principais exceções, esta camada serve para centralizar as exceções.
- **Strategies** - Inspirado no Design Pattern do Gang of Four, esta camada mantém as principais estratégias para lidar com o filtro de produtos, em ordem crescente ou decrescente por nome ou preço.
- **Utils** - Camada responsável por manter algumas classes com métodos utilitários que são usados ao longo do fluxo da aplicação.
- **Validators** - Camada que centraliza as principais validações necessárias na aplicação.

## Como rodar o projeto

### Clone o repositório
> git clone git@github.com:Icaro-Salgado/w5g6-desafio-spring.git

### Caso queira abrir o projeto no IntelliJ

Abra a pasta clonada no passo 1, aguarde a instalação das dependencias e rode o projeto.

### Caso queira rodar pela linha de comando

#### Entre na pasta do desafio
``` sh
cd w5g6-desafio-spring
```
#### Instale as dependências do maven
``` sh
mvn install
```
ou
``` sh
maven install
```

#### Realize o build do projeto
``` sh
mvn package
```
ou 
``` sh
maven package
```

#### Rode o projeto localmente
**Importante que essa etapa seja realizada na raiz do projeto, na pasta "w5g6-desafio-spring"**
``` sh
java -jar ./target/desafio-spring-0.0.1-SNAPSHOT.jar
```

#### Realize uma chamada de teste em algum endpoint
``` sh
curl -X GET http://localhost:8080/api/v1/articles/
```

Os endpoints estão discriminados na pasta **doc** do projeto.
Caso utilize o POSTMAN você pode importar a collection contida na pasta **doc** também.


## Membros do grupo
| Evandro | Icaro | Klinton | Maram |Paulo| Pedro | Thainan |
| :---: | :---: | :---: | :---: |:---: | :---: | :---: |
| [<img src="https://avatars.githubusercontent.com/u/39993682?v=4" width=115><br><sub></sub>](https://github.com/evandrosutil)|[<img src="https://avatars.githubusercontent.com/u/101267189?v=4" width=115><br><sub></sub>](https://github.com/Icaro-Salgado) |[<img src="https://avatars.githubusercontent.com/u/97066287?v=4" width=115><br><sub></sub>](https://github.com/MeliKlin) |[<img src="https://avatars.githubusercontent.com/u/80549051?v=4" width=115><br><sub></sub>](https://github.com/maranbrasil) |[<img src="https://avatars.githubusercontent.com/u/101268601?v=4" width=115><br><sub></sub>](https://github.com/Paulorlima) |[<img src="https://avatars.githubusercontent.com/u/73892750?v=4" width=115><br><sub></sub>](https://github.com/pedroLSoares) |[<img src="https://avatars.githubusercontent.com/u/101267217?v=4" width=115><br><sub></sub>](https://github.com/ThainanEsteves) 
