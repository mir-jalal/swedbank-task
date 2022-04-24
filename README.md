# Demo Project for Swedbank



```shell
docker run --name elastic --net elastic -p 127.0.0.1:9200:9200 -p 127.0.0.1:9300:9300 -d -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.15.2
docker run --name kibana -d --net elastic -p 127.0.0.1:5601:5601 -e "ELASTICSEARCH_HOSTS=http://elastic:9200" docker.elastic.co/kibana/kibana:7.15.2
sudo sysctl -w vm.max_map_count=262144
```
