# Sample Spring Boot application packaged / built using Docker


## Build

- build with Gradle

      gradle clean docker dockerTag

- or, build with Docker

      docker image build \
        --build-arg BUILD_VERSION=0.0.1-SNAPSHOT \
        -f Dockerfile.build \
        -t jcg/spring-boot-webapp:latest \
        -t jcg/spring-boot-webapp:0.0.1-SNAPSHOT .

## Run

- run MySQL container:

      docker run --rm -d \
        --name mysql \
        -e MYSQL_ROOT_PASSWORD='p$ssw0rd' \
        -e MYSQL_DATABASE=my_app_db \
        -e MYSQL_ROOT_HOST=% \
        mysql:8.0.2

- run Spring Boot application container

      docker run -d --rm \
        --name spring-boot-webapp \
        -p 19900:19900 \
        -e DB_HOST=`docker inspect --format '{{ .NetworkSettings.IPAddress }}' mysql` \
        jcg/spring-boot-webapp:0.0.1-SNAPSHOT

- check the Spring Boot server is up and running

      curl http://localhost:19900/application/health


- Run with docker-compose (Check the port with docker ps, it's randomly mapped)

      docker-compose -f src/main/docker-compose/docker-compose.yml up


- invoke REST(ful) APIs using `curl` commands
  - create new task

        curl -X POST http://localhost:19900/tasks \
           -d '[{"title": "Task #1", "description": "Sample Task"}]' \
           -H "Content-Type: application/json"

  - list all tasks

        curl http://localhost:19900/tasks

  - get task by its identifier

        curl http://localhost:19900/tasks/1

  - delete task by its identifier

        curl -X DELETE http://localhost:19900/tasks/1
