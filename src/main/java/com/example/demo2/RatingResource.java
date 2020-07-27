package com.example.demo2;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Map;

@Resource
@Path("/")
@Singleton
public class RatingResource {

    private final RatingService ratingService;

    @Inject
    public RatingResource(final RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GET
    @Path("/rate/{documentId}")
    @Produces("text/plain")
    public Response getHello(@PathParam("documentId") String documentId) {
        return Response.ok(documentId).build();
    }

    //added
    @GET
    @Path("/rate/{documentId}/like")
    @Produces("text/plain")
    public Response like(@PathParam("documentId") String documentId) {
        ratingService.like(documentId);
        return Response.ok(documentId + " -> 1 like added").build();
    }

    @GET
    @Path("/rate/{documentId}/dislike")
    @Produces("text/plain")
    public Response dislike(@PathParam("documentId") String documentId) {
        ratingService.dislike(documentId);
        return Response.ok(documentId + " -> 1 dislike added").build();
    }

    @GET
    @Path("/rate/{documentId}/percentage")
    @Produces("text/plain")
    public Response getDocumentRating(@PathParam("documentId") String documentId) {

        // Map<String, Document> docs = ratingService.getAllDocuments();
        // for (String key : docs.keySet()) {

        final double rating = ratingService.getAllDocuments().get(documentId).getDocumentRating();


        return Response.ok("Rating for " + documentId + " is " + rating + " %").build();
    }


}