package com.example.springboot_service_demo.streams

import com.example.springboot_service_demo.configurations.KafkaConfig
import com.example.springboot_service_demo.configurations.KafkaIntegrationConfig
import com.example.springboot_service_demo.models.Constants
import com.example.springboot_service_demo.models.PingState
import com.example.springboot_service_demo.streams.serdes.PingStateDeserializer
import com.example.springboot_service_demo.streams.serdes.PingStateSerializer
import com.example.springboot_service_demo.streams.serdes.PingUpdatedDeserializer
import com.example.springboot_service_demo.streams.serdes.PingUpdatedSerializer
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Produced
import org.springframework.stereotype.Component
import java.util.*

@Component
//@EnableKafkaStreams
class CriticalPingsStream(
    private val kafkaConfig: KafkaConfig,
    private val kafkaIntegrationConfig: KafkaIntegrationConfig
) {
    fun startUpdateStream() {
        if (!kafkaIntegrationConfig.enabled) return

        val builder = StreamsBuilder()
        val properties = Properties()
        properties[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaConfig.bootstrapServers;
        properties[StreamsConfig.APPLICATION_ID_CONFIG] = "ping-app-id";

        val pingUpdates = builder.stream(
            Constants.PING_TOPIC,
            Consumed.with(
                Serdes.String(),
                Serdes.serdeFrom(PingUpdatedSerializer(), PingUpdatedDeserializer())
            )
        )
        pingUpdates
            .filter { k, _ -> k == Constants.CRITICAL_PING_KEY }
            .mapValues { pingUpdated ->
                pingUpdated?.let {
                    PingState(
                        pingId = it.pingId,
                        status = it.status,
                        updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                    )
                } ?: PingState()
            }
            .to(
                Constants.CRITICAL_PING_TOPIC,
                Produced.with(
                    Serdes.String(),
                    Serdes.serdeFrom(PingStateSerializer(), PingStateDeserializer())
                )
            )
        //Produced.with(Serdes.String(), Serdes.serdeFrom(PingState::class.java))
        val streams = KafkaStreams(builder.build(), properties)
        streams.start()
    }
}
