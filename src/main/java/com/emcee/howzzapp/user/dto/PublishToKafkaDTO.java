package com.emcee.howzzapp.user.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 * PublishToKafkaDTO
 */
@Builder
@Getter
public class PublishToKafkaDTO {

    private String topicName;
    private String key;
    private Object message;
}
