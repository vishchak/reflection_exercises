package com.gmail.vishchak.denis.task3;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Write code that serializes and deserializes to/from a file all the values of the class fields that are marked with the @Save annotation.
 */
public class Main {
    public static void main(String[] args) {
        Actor actor = new Actor("Brad", "Pitt", 58, 180);
        try {
            File file = SerializeDeserialize.serialize(actor, "pitt.txt");
            SerializeDeserialize.deserialize(file, Actor.class);
        } catch (IOException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
