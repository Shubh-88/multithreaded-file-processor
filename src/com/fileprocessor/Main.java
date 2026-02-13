package com.fileprocessor;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {

        File folder = new File("input-files");
        File[] files = folder.listFiles();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<Future<FileProcessingResult>> futures = new ArrayList<>();
        ResultAggregator aggregator = new ResultAggregator();

        for (File file : files) {
            futures.add(executor.submit(new FileProcessorTask(file)));
        }

        for (Future<FileProcessingResult> future : futures) {
            FileProcessingResult result = future.get();
            aggregator.aggregate(result);
        }

        executor.shutdown();
        aggregator.printSummary();
    }
}
