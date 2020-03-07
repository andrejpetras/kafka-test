package org.lorislab.kafka.test;



import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

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
    Emitter<KafkaRecord<String, Event>> send;

    @GET
    @Path("{topic}/{guid}")
    public Response send(@PathParam("topic") String topic,@PathParam("guid") String guid) {
        Event event  = new Event();
        event.guid = guid;
        event.data = UUID.randomUUID().toString();
        send.send(KafkaRecord.of(topic, event.guid, event));
        return Response.ok().build();
    }

}
