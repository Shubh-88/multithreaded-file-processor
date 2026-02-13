
//This holds result of one file


package com.fileprocessor;

import java.util.Map;

public class FileProcessingResult {
    private int lineCount;
    private int wordCount;
    private  Map<String, Integer> wordFrequency;

    public FileProcessingResult(int lineCount, int wordCount, Map<String, Integer> wordFrequency) {
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.wordFrequency = wordFrequency;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public Map<String, Integer> getWordFrequency() {
        return wordFrequency;
    }
}