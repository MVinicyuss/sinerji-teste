## Sobre o projeto

Este projeto foi desenvolvido como um teste para uma vaga de emprego. Ele demonstra minhas habilidades e competências técnicas necessárias para a posição. Através deste projeto, busco mostrar minha capacidade de resolver problemas e escrever código limpo e eficiente.

## Estrutura das Pastas

O projeto está organizado da seguinte maneira:
Dessa forma, consegui ter uma boa separação de onde cada escopo agia, e pude encontrar rapidamente os principais problemas ao debugar o código.

- `src/`: Esta pasta contém todo o código-fonte do projeto.
  - `main/`: Aqui estão todas as pastas principais do projeto.
    - `java/`: Aqui estão os pacotes das classes Java utilizadas.
      - `model/`: Aqui estão as classes utilizadas para mapear o banco.
      - `controller/`: Aqui estão as classes Bean utilizadas para controlar as funcionalidades e a comunicação com o front-end da aplicação.
      - `service/`: Aqui estão as classes chamadas antes de interagir com o banco de dados, para abrir uma transação.
      - `util/`: Aqui estão as classes utilizadas para configurar o uso do banco e o uso das transações.
    - `resources/`: Aqui está as configurações de conexão ao banco de dados e o arquivo para configuração do *Beans*.
    - `webapp/`: Aqui estão as telas da aplicação e as configurações de front-end.

## Tecnologias utilizadas

- **Tomcat**: Versão utilizada a 8.5.100.
- **Docker**: Utilizado para subir o container com o banco de dados.
- **Mysql**: Tecnologia utilizada para o banco de dados.
- **Hibernate**: Utilizado para gerenciar as transações com o banco de dados.
- **JSF (JavaServer Faces)**: Framework de interface de usuário para aplicações Java Web. Utilizado para facilitar a construção de interfaces de usuário em páginas .xhtml.

## Rodando localmente

Crie um container MYSQL:

```bash
docker run -d --name mysql_database -p 3307:3306 -e MYSQL_ROOT_PASSWORD=1234 mysql:latest
```

> :warning: **Aviso**: Se necessário, configure o arquivo `persistence.xml`

Instale as dependencias do pom.xml:

```bash
mvn install
```

  ## Funcionalidades
  - **Tela Inicial**:
    - `Cadastro`: Esta tela contém opções de cadastro de pessoas e *n* endereços atrelados a ela, podendo os endereços serem removidos antes de serem atrelados a pessoa.
    - **Criar Pessoa**: Permite a criação da Pessoa no banco.
    - **Adicionar Endereço**: Atrela os Endereços a essa pessoa onde posteriormente os salva no banco.
    - **Remover Endereço**: Remove o atrelamento desse Endereço com a Pessoa.
  - **Lista De Cadastrados**:
    - `Lista`: Uma lista de todas as pessoas cadastradas no banco, podendo as mesmas ter endereço ou não. É possível adicionar apenas um endereço quando a pessoa já está cadastrada. Esta tela permite editar ou remover os endereços e a própria pessoa.
    - **Editar Pessoa**: Permite a edição da Pessoa no banco.
    - **Remover Pessoa**: Permite a remoção da Pessoa no banco junto com seus Endereços.
    - **Editar Endereço**: Permite a edição do Endereço a em questão, e depois salva no banco.
    - **Remover Endereço**: Permite a remoção do Endereço do banco.