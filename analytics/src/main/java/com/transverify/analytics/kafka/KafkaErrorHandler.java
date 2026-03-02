package com.transverify.analytics.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.DeserializationException;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaErrorHandler implements CommonErrorHandler{

    @Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        handle(exception, consumer, record);
        return true;
    }

    private void handle(Exception ex, Consumer<?, ?> consumer, ConsumerRecord<?, ?> record) {

        var unwrappedEx = ex.getCause();
        log.error(unwrappedEx.getMessage(), unwrappedEx);
        if (unwrappedEx instanceof DeserializationException) {
            log.error(
                    "Skipping record due to deserialization failure: topic={} partition={} offset={}",
                    record.topic(),
                    record.partition(),
                    record.offset(),
                    unwrappedEx
            );
        }
        else {
            log.error("Consumer failed due to error", ex);
        }

        TopicPartition tp = new TopicPartition(record.topic(), record.partition());
        consumer.seek(tp, record.offset() + 1);
    }

}
