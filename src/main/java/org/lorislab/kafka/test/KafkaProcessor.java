package org.lorislab.kafka.test;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class KafkaProcessor {

    private static final Logger log = LoggerFactory.getLogger(KafkaProcessor.class);

    @Incoming("in")
    @Acknowledgment(Acknowledgment.Strategy.MANUAL)
    public CompletionStage<Void> process(KafkaRecord<String, String> message) {
        log.info("Message: {}", message.getPayload());
        return null;
    }

//    @Incoming("in")
//    @Acknowledgment(Acknowledgment.Strategy.MANUAL)
//    public Subscriber<KafkaRecord<String, String>> process2() {
//        return ReactiveStreams.<KafkaRecord<String, String>>builder()
//                .map(m -> {
//                    log.info("Message: {}", m.getPayload());
//                    return m;
//                }).findFirst()
//                .build();
//    }

}
