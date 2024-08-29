# Aplicação para Gerenciamento de Produtos

## Descrição

Esta aplicação foi desenvolvida para o gerenciamento de produtos utilizando **Angular** no frontend e **Spring Boot** no backend. A aplicação oferece uma interface amigável para a administração de produtos, com funcionalidades para adicionar, visualizar, editar e remover produtos, e utiliza **MySQL** para a persistência de dados. A segurança é garantida através da implementação de **JSON Web Tokens (JWT)** e **OAuth2**.

## Badges
</br>
<p align="center">
  <img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=FINALIZADO&color=GREEN&style=for-the-badge"/>
</p>

<h3>Front-end: </h3>
<p align="center">
  <img loading="lazy" src="https://img.shields.io/badge/Angular-9.1.1-green"/>
  <img loading="lazy" src="https://img.shields.io/badge/Angular_JWT-4.0.0-green"/>
  <img loading="lazy" src="https://img.shields.io/badge/Angular_CLI-13.3.11-green"/>
  <img loading="lazy" src="https://img.shields.io/badge/Jquery-3.7.1-green"/>
  <img loading="lazy" src="https://img.shields.io/badge/Bootstrap-4.3.1-green"/>
  <img loading="lazy" src="https://img.shields.io/badge/Node-12.11.1-green"/>
  <img loading="lazy" src="https://img.shields.io/badge/Font_Awesome-5.11.2-green"/>
  <img loading="lazy" src="https://img.shields.io/badge/CSS-3-green"/>
  <img loading="lazy" src="https://img.shields.io/badge/HTML-5-green"/>
</p>

<h3>Back-end: </h3>
<p align="center">
  <img loading="lazy" src="https://img.shields.io/badge/Java-11-blue"/>
  <img loading="lazy" src="https://img.shields.io/badge/Maven-4.0.0-blue"/>
  <img loading="lazy" src="https://img.shields.io/badge/Spring_Boot-2.3.4_RELEASE-blue"/>
  <img loading="lazy" src="https://img.shields.io/badge/Lombok-Not_Especified-909194"/>
  <img loading="lazy" src="https://img.shields.io/badge/JPA-Not_Especified-909194"/>
  <img loading="lazy" src="https://img.shields.io/badge/Oauth2-Not_Especified-909194"/>
  <img loading="lazy" src="https://img.shields.io/badge/Crypto-Not_Especified-909194"/>
  <img loading="lazy" src="https://img.shields.io/badge/Validation_api-Not_Especified-909194"/>
</p>

<h3>Database: </h3>
<p align="center">
  <img loading="lazy" src="https://img.shields.io/badge/MySQL-Not_Especified-909194"/>  
  <img loading="lazy" src="https://img.shields.io/badge/H2-Not_Especified-909194"/>
</p>

## Índice

1. [Funcionalidades](#funcionalidades)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Fluxo de Trabalho](#fluxo-de-trabalho)
4. [Conclusão](#conclusão)

## Funcionalidades

- **Autenticação e Autorização**: Acesso ao sistema é feito através de login, utilizando autenticação baseada em **JWT** e **OAuth2**.
- **Cadastro de Produtos**: Adicione novos produtos com nome, preço e quantidade.
- **Visualização de Produtos**: Exiba uma lista de produtos com detalhes sobre nome, preço e quantidade.
- **Edição de Produtos**: Atualize informações de produtos existentes.
- **Exclusão de Produtos**: Remova produtos que não são mais necessários.

## Tecnologias Utilizadas

- **Frontend**: [Angular](https://angular.io/) - Framework para construção da interface de usuário com design moderno e responsivo.
- **Backend**: [Spring Boot](https://spring.io/projects/spring-boot) - Framework para o gerenciamento da lógica de negócios e persistência de dados.
- **Autenticação**: [JSON Web Tokens (JWT)](https://jwt.io/) - Para criação e verificação de tokens de autenticação.
- **Autorização**: [OAuth2](https://oauth.net/2/) - Para garantir uma autorização robusta e segura.
- **Banco de Dados**: [MySQL](https://www.mysql.com/) - Sistema de gerenciamento de banco de dados relacional para armazenar informações dos produtos.

## Fluxo de Trabalho

1. **Login**: Acesse a página de login e insira suas credenciais, caso não tenha uma credencial, cadastre-se. Após a autenticação bem-sucedida, um token JWT é gerado e armazenado para gerenciar a sessão do usuário.
   
2. **Gerenciamento de Produtos**:
   - **Cadastro de Produtos**: Adicione novos produtos fornecendo nome, preço e quantidade.
   - **Visualização de Produtos**: Consulte a lista de produtos com informações detalhadas.
   - **Edição de Produtos**: Atualize as informações de produtos existentes.
   - **Exclusão de Produtos**: Remova produtos que não são mais necessários.

3. **Operações de Produto**: Utilize formulários e botões interativos para realizar operações sobre produtos. O Angular lida com a interação do usuário e faz chamadas para a API fornecida pelo Spring Boot, que utiliza o JWT para validar as requisições.

4. **Persistência de Dados**: As informações dos produtos são armazenadas em um banco de dados **MySQL**, com o Spring Boot gerenciando as transações e operações CRUD (Create, Read, Update, Delete) necessárias.

## Conclusão

A aplicação combina o poder do Angular para criar uma interface de usuário dinâmica e responsiva com a robustez do Spring Boot para gerenciar dados e lógica de backend. A integração de JWT e OAuth2 proporciona segurança, e o uso do MySQL garante uma persistência de dados eficiente. Esta solução oferece uma maneira eficaz e segura para empresas gerenciarem seu inventário.

