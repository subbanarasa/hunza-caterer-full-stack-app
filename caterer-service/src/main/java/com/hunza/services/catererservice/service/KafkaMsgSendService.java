package com.hunza.services.catererservice.service;

public interface KafkaMsgSendService {

     void sendMessage(String topic, String payload);
}
