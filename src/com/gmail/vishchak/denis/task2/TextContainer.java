package com.gmail.vishchak.denis.task2;

import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "C:/Users/denis/Documents/Java/reflection_exercises")
public class TextContainer {
    private static final String TEXT = "Hello world";

    @Saver
    public void saver(String text, String path) throws IOException {
        FileWriter fw = new FileWriter(path + "/text.txt");
        fw.write(text);
        fw.close();
    }
}

