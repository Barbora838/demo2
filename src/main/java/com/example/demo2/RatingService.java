package com.example.demo2;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import java.util.Map;


@Named
@Singleton
public class RatingService {

    private final AtomicInteger cnt = new AtomicInteger(0);
    public String getId() { return "Hello visitor #" + cnt.incrementAndGet();
    }

    private final Map<String, Document> allDocuments = new HashMap<>();

    public void like(final String docId) {
        allDocuments.computeIfAbsent(docId, Document::new).like();
    }

    public void dislike(final String docId) {
        allDocuments.computeIfAbsent(docId, Document::new).dislike();
    }

    public Map<String, Document> getAllDocuments() {
        return allDocuments;
    }
}
