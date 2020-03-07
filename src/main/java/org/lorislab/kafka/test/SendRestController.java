package org.lorislab.kafka.test;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("send")
public class SendRestController {

    @Inject
    @Channel("send")
    Emitter<KafkaMessage<String, Event>> send;

    @GET
    @Path("{topic}/{guid}")
    public Response send(@PathParam("topic") String topic,@PathParam("guid") String guid) {
        Event event  = new Event();
        event.guid = guid;
        event.data = UUID.randomUUID().toString();
        send.send(KafkaMessage.of(topic, event.guid, event));
        return Response.ok().build();
    }

}
