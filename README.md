# Teste Jaime Camara (Backend)

Este README contém instruções de como rodar o aplicativo localmente. O projeto utiliza banco de dados H2, tornando desnecessário qualquer dependência com algum banco de dados externo.

# Execução do projeto localmente

Para rodar o projeto localmente via docker, basta clonar o projeto e rodar o comando:

```
docker compose up
```

Alternativamente, o projeto já dispõe de uma imagem upada no dockerhub:

```
docker run --rm -p 8080:8080 -d luisccomp/teste-jaime-camara-image
```

Caso tenha clonado o projeto localmente, é possível rodá-lo via maven:

```
mvn clean install&&mvn spring-boot:run
```