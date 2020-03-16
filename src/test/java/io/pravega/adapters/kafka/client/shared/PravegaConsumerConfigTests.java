package io.pravega.adapters.kafka.client.shared;

import io.pravega.adapters.kafka.client.utils.PersonSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PravegaConsumerConfigTests {

    @Test(expected = IllegalArgumentException.class)
    public void emptyServerConfigCausesInstantiationFailure() {
        PravegaConsumerConfig config = new PravegaConsumerConfig(new Properties());
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptySerializerConfigCausesInstantiationFailure() {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy");
        PravegaConsumerConfig config = new PravegaConsumerConfig(props);
    }

    @Test
    public void instantiatesSerializerIfSpecified() {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy");
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "io.pravega.adapters.kafka.client.utils.PersonSerializer");
        PravegaConsumerConfig config = new PravegaConsumerConfig(props);
        assertEquals(PersonSerializer.class, config.getSerializer().getClass());
    }
}
