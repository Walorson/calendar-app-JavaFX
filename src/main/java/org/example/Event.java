package org.example;

import org.example.Date;

import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class Event {
    static void log(String name, Date date) throws IOException
    {
        try(Writer writer = new FileWriter("log.txt", true)) {
            writer.write(name + ", " + date + "\n");
        }
    }
}
