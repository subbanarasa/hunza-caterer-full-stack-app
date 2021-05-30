package com.hunza.services.catererservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMsgSendServiceImpl implements KafkaMsgSendService {

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMsgSendServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, String payload) {
        log.info("sending payload='{}' to topic='{}'", payload, topic);
         kafkaTemplate.send(topic, payload);
    }

}
