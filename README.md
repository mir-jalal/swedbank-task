# Demo Project for Swedbank

Stack:
* Java 17 (Spring Boot)
* RabbitMQ
* ElasticSearch + Kibana

> You can test [this link](http://34.88.126.223:5601) to open `Kibana Dashboard` in your browser.
> If you cannot open the link please manually copy and paste `http://34.88.126.223:5601` in your browser
> 
> You will need username and password to access Kibana
> 
> *Note:* This link might not be working after some time, but I can guarantee it will be running by **15 May 2022**. 

## Configuration

Generator and Reader application needs to have RabbitMQ for message queue.
> In case you have your own generator, you can directly send request to that queue.

### RabbitMQ

I used following `docker-compose.yml` to run RabbitMQ in `docker`:
```yaml
version: "3"

services:
  rabbitmq:
    image: rabbitmq:management
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      - RABBITMQ_DEFAULT_USER=rabbitmq
      - RABBITMQ_DEFAULT_PASS=rabbitmq
```

### Elastic Search + Kibana

I used these [ElasticSearch](https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html) and [Kibana](https://www.elastic.co/guide/en/kibana/current/docker.html) tutorials, to run ElasticSearch and Kibana.

However, I'll share my `docker run` commands:
> Also need to have `network`
> You might also need to run following command to increase `max_map_count`. ElasticSearch sometimes creates a problem because of that.
> ```shell
> sudo sysctl -w vm.max_map_count=262144
> ```

```shell
docker network create elastic
docker run -d --name elasticsearch --net elastic -p 9200:9200 -p 9300:9300 -it docker.elastic.co/elasticsearch/elasticsearch:8.1.3
docker run -d --name kibana --net elastic -p 5601:5601 docker.elastic.co/kibana/kibana:8.1.3
```
> After running those commands open `localhost:5601` in the browser to configure ElasticSearch and Kibana

To reset `elastic` user password:
```shell
docker exec -ti elasticsearch /usr/share/elasticsearch/bin/elasticsearch-reset-password -u elastic
```

And to get enrollment key from `ElasticSearch` for `Kibana`:
```shell
docker exec -ti elasticsearch /usr/share/elasticsearch/bin/elasticsearch-create-enrollment-token -s kibana
```

## Spring Boot Application

In order to run `data-generator` you can use following command:
```shell
cd data-generator
./gradlew bootRun
```

However, you need to specify `ELASTIC_PASS` to run `data-reader`:
```shell
cd data-reader
ELASTIC_PASS=<your-password> ./gradlew bootRun
```
