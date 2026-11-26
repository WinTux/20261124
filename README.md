# Kafka
## Para crear un topic en el contenedor
```bash
docker exec -it principal-kafka /opt/kafka/bin/kafka-topics.sh --create --topic principal-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```
## Para verificar qué topics existen en el contenedor
```bash
docker exec -it 09f824ab0ffc /opt/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```
# Realizando pruebas
## Enviando un paquete
```bash
curl -i -X POST http://localhost:8090/api/v1/principales/receive -H "Content-Type: application/json" -d '{"principalId": "PACK-1300","sender":"Pepe","recipient":"Sofía","origen":"La Paz","destino":"Santa Cruz"}'
```
## Verificando que se publicó en el broker de Kafka
```bash
docker exec -it principal-kafka /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic principal-events --from-beginning --formatter-property print.key=true --formatter-property key.separator=" | "
```
