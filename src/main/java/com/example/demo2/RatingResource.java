package com.example.demo2;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Resource
@Path("/")
@Singleton
public class RatingResource {

    private final HelloService helloService;

    @Inject
    public RatingResource(final HelloService helloService) {
        this.helloService = helloService;
    }

    @GET
    @Path("/rate/{documentId}")
    @Produces("text/plain")
    public Response getHello(@PathParam("documentId") String documentId ) {
        return Response.ok(documentId).build();
    }


    //added
    @GET
    @Path("/rate/{like}")
    @Produces("text/plain")
    public Response getLike(@PathParam("like") String like ) {
        return Response.ok(like).build();
    }

    @GET
    @Path("/rate/{dislike}")
    @Produces("text/plain")
    public Response getDislike(@PathParam("dislike") String dislike ) {
        return Response.ok(dislike).build();
    }


}
