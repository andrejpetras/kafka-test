# kafka-test project

```shell script
docker-compose rm zookeper kafka
docker-compose up
mvn compile quarkus:dev
```

```shell script
curl http://localhost:8080/send/message1
curl http://localhost:8080/send/stop1   
curl http://localhost:8080/send/message2
```
