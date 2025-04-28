# 🧠 FlashCardsReview

Aplicativo de Flash Cards para revisão espaçada!  
Com o FlashCardsReview, você pode cadastrar perguntas e respostas para serem revisadas em intervalos estratégicos: agora, 1 dia, 1 semana, 2 semanas e 1 mês.  
Ideal para fixar conteúdos na memória de longo prazo. 🚀

## ✨ Funcionalidades

- Cadastro de novas questões
- Consulta de todas as questões cadastradas
- Consulta de questões específicas por ID
- Atualização de questões
- Remoção de questões
- Listagem de questões que precisam ser revisadas
- Responder questões e atualizar seu ciclo de revisão

## 🔥 Principais Endpoints

| Método | Endpoint                | Descrição                              |
|:------:|:-------------------------|:--------------------------------------|
| POST   | `/add`                   | Cadastrar nova questão                |
| GET    | `/all`                   | Listar todas as questões              |
| GET    | `/{id}`                  | Buscar questão por ID                 |
| PATCH  | `/update/{id}`            | Atualizar questão                     |
| DELETE | `/delete/{id}`            | Deletar questão                       |
| GET    | `/all-review`             | Listar questões para revisão          |
| POST   | `/answer/{id}`            | Responder uma questão                 |

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
<!-- Swagger OpenAPI-->
- Lombok
<!-- 
## 📄 Documentação da API

Após rodar a aplicação, acesse a documentação no Swagger:

> [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) ↗️
-->
## 📋 Requisitos

- Java 17
- Maven 3.x
<!--- PostgreSQL-->

## 📚 Licença

Este projeto está sob a licença MIT.  
Veja mais detalhes no arquivo [LICENSE](LICENSE).

---
