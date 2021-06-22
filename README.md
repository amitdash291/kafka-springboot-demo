# Kafka Spring Boot Demo
This is a demo web service using Spring Boot Kafka SDK to produce and consume events.
It includes basic Docker compose which spins up Zookeeper + Kafka + Kafdrop along with the Spring Boot service.

## Prerequisites
1. Docker

## Docker Compose
1. `./gradlew clean build`
2. `docker-compose -p kafka-springboot-demo up`

## Setup Kafka CLI Producer / Consumer
### On Mac
1. `kafka-console-producer --broker-list localhost:9092 --topic ping-topic`
2. `kafka-console-consumer --bootstrap-server localhost:9092 --topic ping-topic --from-beginning --consumer-property group.id=consumer-group-1`

### On Docker Containers
One can log into the the container shell and execute the commands as follows:
1. `docker exec -itu root kafka-container /bin/bash`
2. `kafka-console-producer.sh --broker-list localhost:9092 --topic ping-topic`
3. `kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ping-topic --from-beginning --consumer-property group.id=consumer-group-1`

Some commands are convenient to be directly executed on the Mac terminal as follows:

1.
```
docker exec -t kafka-container \
kafka-topics.sh \
--bootstrap-server localhost:9092 \
--list
```

2.
```
docker exec -t kafka-container \
kafka-console-producer.sh \
--broker-list localhost:9092 \
--topic ping-topic
```

**Note:** The producer command has issues when being directly executed from Mac


## Bootstrap Local Kafka (on Mac)
1. `brew install kafka`
2. `zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties`
3. `kafka-server-start /usr/local/etc/kafka/server.properties`
4. `kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic ping-topic`
