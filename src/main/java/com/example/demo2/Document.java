package com.example.demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Document {

    private final String documentId;
    private final AtomicInteger likes = new AtomicInteger(0);
    private final AtomicInteger dislikes = new AtomicInteger(0);

    private final Map<String, AtomicInteger> tags = new ConcurrentHashMap<>();

    public Document(String docId) {
        this.documentId = docId;
    }

    public double getDocumentRating() {
        // returns 0 when both rating(likes & dislikes) are zero
        if (this.likes.get() == 0 && this.dislikes.get() == 0) {
            return 0.0;
        }
        //returns 100 when at least one of the rating(likes & dislikes) is zero
        if (this.dislikes.get() == 0) {
            return 100.0;
        }

        double numLikes = this.likes.get();
        double numDislikes = this.dislikes.get();
        double total = numLikes + numDislikes;

        double percentage = (numLikes / total);
        double result = percentage * 100;
        result = Math.round(result);
        return result;
    }

    public void like() {
        int new_like = likes.incrementAndGet();
        //just for checking
        System.out.println(new_like + " " + documentId + " like");
    }

    public void dislike() {
        int new_dislike = dislikes.incrementAndGet();
        //just for checking
        System.out.println(new_dislike + " " + documentId + " dislike");
    }

    public void addTag(final String tag) {
        tags.computeIfAbsent(tag.strip().toLowerCase(), t -> new AtomicInteger(0)).incrementAndGet();
    }

    public Map<String, Integer> getTags() {
        final Map<String, Integer> result = new HashMap<>();
        this.tags.entrySet().forEach(e -> {
            result.put(e.getKey(), e.getValue().get());
        });
        return result;
    }

    public String getDocumentId() {
        return documentId;
    }
}