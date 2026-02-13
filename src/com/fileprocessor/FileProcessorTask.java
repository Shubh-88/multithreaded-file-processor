//Each file = one thread task    
// FileProcessorTask is a unit of work that processes one File and returns a 
// FileProcessingResult. It implements Callable<FileProcessingResult> 
// so it can run on a thread pool 
// (submit to an ExecutorService) and produce a Future<FileProcessingResult>.

package com.fileprocessor;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

public class FileProcessorTask implements Callable<FileProcessingResult> {

    private File file;

    public FileProcessorTask(File file) {
        this.file = file;
    }

    @Override
    public FileProcessingResult call() throws Exception {

        int lines = 0;
        int words = 0;
        Map<String, Integer> frequency = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            lines++;
            String[] split = line.split("\\s+");
            words += split.length;

            for (String word : split) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        reader.close();

        return new FileProcessingResult(lines, words, frequency);
    }
}