package com.example.demo2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import java.util.Map;


@Named
@Singleton
public class RatingService {

    private final AtomicInteger cnt = new AtomicInteger(0);

    public String getId() {
        return "Hello visitor #" + cnt.incrementAndGet();
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

    public void createCSVFile() throws IOException {
        FileWriter out = new FileWriter("data.csv");
        //File myFile = new File ("data.csv");
        String[] HEADER = {"ID", "Likes", "Dislikes"};

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADER))) {
            //System.out.println(myFile.getAbsolutePath());

            allDocuments.forEach((ID, Document) -> {
                try {
                    printer.printRecord(ID, Document.getLikes(), Document.getDislikes());
                } catch (IOException e) {
                    System.out.println("Error in CsvFileWriter !!!");
                    e.printStackTrace();
                }
            });
        }
    }
}
