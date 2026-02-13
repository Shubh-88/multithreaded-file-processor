//Thread-safe aggregation.  Thread-safe aggregation of FileProcessingResult instances 
// produced by parallel FileProcessorTasks.

//We use:
//AtomicInteger
//ConcurrentHashMap


package com.fileprocessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultAggregator {

    private AtomicInteger totalLines = new AtomicInteger();
    private AtomicInteger totalWords = new AtomicInteger();
    private ConcurrentHashMap<String, Integer> globalFrequency = new ConcurrentHashMap<>();

    public void aggregate(FileProcessingResult result) {

        totalLines.addAndGet(result.getLineCount());
        totalWords.addAndGet(result.getWordCount());

        for (Map.Entry<String, Integer> entry : result.getWordFrequency().entrySet()) {
            globalFrequency.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    public void printSummary() {
        System.out.println("Total Lines: " + totalLines.get());
        System.out.println("Total Words: " + totalWords.get());
        System.out.println("Unique Words: " + globalFrequency.size());
    }
}
