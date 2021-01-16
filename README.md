# Hackaton CCR - Estagiando API
Nesse projeto foi utilizado a linguagem de programação Java, framework Spring Boot, RestAPI e banco de dados não relacional MongoDB.

Para que todos os passos sejam concluídos com sucesso é necessário que sua máquina tenha acesso a internet e o maven instalado. Além disso, é necessário ter uma string de conexão do banco de dados não relacional MongoDB.

Primeiro é necessário alterar a string de conexão do banco de dados mongoDB no arquivo src\main\resources\application.properties
```sh
spring.data.mongodb.uri={url conexão mongoDB}
```

Para que o maven baixe todas as dependêcias utilizadas no projeto execute a seguinte linha de comando no prompt de comando:
```sh
$ mvn clean install
```

Por padrão a porta utilizada é a 8080. Para iniciar a aplicação é necessário executar o seguinte comando:
```sh
$  mvn spring-boot:run
```

Para testar a aplicação baixe os arquivos que estão na pasta /postman e os importe no software do postman. Após isso, execute as requisições desejadas.
