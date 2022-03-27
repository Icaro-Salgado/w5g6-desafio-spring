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



## Membros do grupo
| Evandro | Icaro | Klinton | Maram |Paulo| Pedro | Thainan |
| :---: | :---: | :---: | :---: |:---: | :---: | :---: |
| [<img src="https://avatars.githubusercontent.com/u/39993682?v=4" width=115><br><sub></sub>](https://github.com/evandrosutil)|[<img src="https://avatars.githubusercontent.com/u/101267189?v=4" width=115><br><sub></sub>](https://github.com/Icaro-Salgado) |[<img src="https://avatars.githubusercontent.com/u/97066287?v=4" width=115><br><sub></sub>](https://github.com/MeliKlin) |[<img src="https://avatars.githubusercontent.com/u/80549051?v=4" width=115><br><sub></sub>](https://github.com/maranbrasil) |[<img src="https://avatars.githubusercontent.com/u/101268601?v=4" width=115><br><sub></sub>](https://github.com/Paulorlima) |[<img src="https://avatars.githubusercontent.com/u/73892750?v=4" width=115><br><sub></sub>](https://github.com/pedroLSoares) |[<img src="https://avatars.githubusercontent.com/u/101267217?v=4" width=115><br><sub></sub>](https://github.com/ThainanEsteves) 