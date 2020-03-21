package org.lorislab.kafka.test;


import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("send")
public class SendRestController {

    private static final Logger log = LoggerFactory.getLogger(SendRestController.class);

    @Inject
    @Channel("out")
    Emitter<String> send;

    @GET
    @Path("{message}")
    public Response send(@PathParam("message") String message) {
        log.info("Send message: {}", message);
        send.send(KafkaRecord.of(null, message));
        return Response.ok().build();
    }

}
