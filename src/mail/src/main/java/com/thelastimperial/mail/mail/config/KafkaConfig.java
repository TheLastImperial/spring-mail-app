package com.thelastimperial.mail.mail.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import lombok.extern.slf4j.Slf4j;

@EnableKafka
@Configuration
@Profile("kafka")
@Slf4j
public class KafkaConfig {
  private final String kafkaServers;
  private final String groupId;
  private final String autoOffset;

  public KafkaConfig(
      @Value("${spring.kafka.bootstrap-servers}") String kafkaServers,
      @Value("${spring.kafka.consumer.group-id}") String groupId,
      @Value("${spring.kafka.consumer.auto-offset-reset}") String autoOffset
  ){
      this.kafkaServers = kafkaServers;
      this.groupId = groupId;
      this.autoOffset = autoOffset;
  }

  @Bean
  public ProducerFactory<String, Object> kafkaProducer() {
    Map<String, Object> map = new HashMap<>();
    map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
    map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(map);
  }

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    KafkaTemplate<String, Object> template = new KafkaTemplate<String, Object>(kafkaProducer());
    return template;
  }

  @Bean
  public ConsumerFactory<String, Object> kafkaConsumer() {
    Map<String, Object> map = new HashMap<>();
    map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
    map.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffset);
    map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);

    return new DefaultKafkaConsumerFactory<>(map);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
      new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(kafkaConsumer());
    return factory;
  }
}
