package com.emcee.howzzapp.user.service;

import com.emcee.howzzapp.user.dto.PublishToKafkaDTO;

/**
 * 
 * KafkaPublisher
 */
public interface KafkaPublisher {

    public void publishToKafka(PublishToKafkaDTO publishToKafkaDTO);

}
