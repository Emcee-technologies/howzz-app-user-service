package com.emcee.howzzapp.user.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.emcee.howzzapp.user.dto.PublishToKafkaDTO;
import com.emcee.howzzapp.user.service.KafkaPublisher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * KafkaPublisherImpl
 */
@Slf4j
@Service
@AllArgsConstructor
public class KafkaPublisherImpl implements KafkaPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * (non-Javadoc)
     * 
     * @see com.emcee.howzzapp.user.service.KafkaPublisher#publishToKafka(com.emcee.howzzapp.user.dto.PublishToKafkaDTO)
     */
    @Async
    @Override
    public void publishToKafka(PublishToKafkaDTO publishToKafkaDTO) {

        String key = publishToKafkaDTO.getKey();

        if (key == null) {
            kafkaTemplate.send(
                    publishToKafkaDTO.getTopicName(),
                    publishToKafkaDTO.getMessage());
            return;
        }

        kafkaTemplate.send(
                publishToKafkaDTO.getTopicName(),
                publishToKafkaDTO.getKey(),
                publishToKafkaDTO.getMessage());

    }

}
