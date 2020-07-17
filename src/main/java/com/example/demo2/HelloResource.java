package com.example.demo2;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Resource
@Path("/")
@Singleton
public class HelloResource {

    private final HelloService helloService;

    @Inject
    public HelloResource(final HelloService helloService) {
        this.helloService = helloService;
    }

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public Response getId() {
        return Response.ok(helloService.getId()).build();
    }

//added
    @GET
    @Path("/like")
    @Produces("text/plain")
    public Response like() {return Response.ok(helloService.like()).build();}

    @GET
    @Path("/dislike")
    @Produces("text/plain")
    public Response dislike() {return Response.ok(helloService.dislike()).build();}
}
