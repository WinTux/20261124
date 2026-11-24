# Kafka
## Para crear un topic en el contenedor
```bash
docker exec -it principal-kafka /opt/kafka/bin/kafka-topics.sh --create --topic principal-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```
