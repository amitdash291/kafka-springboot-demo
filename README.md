# Kafka SpringBoot Demo
This is a demo web service using Spring Boot Kafka SDK to produce and consume events.
It includes basic Docker compose which spins up Zookeeper + Kafka + Kafdrop along with the Spring Boot service.

## Prerequisites
1. Docker

## Docker Compose
1. `./gradlew clean build`
2. `docker-compose -p kafka-springboot-demo up`

## Setup Command Line Producer / Consumer
1. `kafka-console-producer --broker-list localhost:9092 --topic ping-topic`
2. `kafka-console-consumer --bootstrap-server localhost:9092 --topic ping-topic --from-beginning --consumer-property group.id=consumer-group-1`

## Bootstrap Local Kafka (on Mac)
1. `brew install kafka`
2. `zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties`
3. `kafka-server-start /usr/local/etc/kafka/server.properties`
4. `kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic ping-topic`
