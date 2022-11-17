package com.gmail.vishchak.denis.task3;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class SerializeDeserialize {
    public static <T> File serialize(T t, String file) throws IOException, IllegalAccessException {
        File createdFile = new File(file);
        FileWriter fw = new FileWriter(createdFile);
        Class<?> cls = Actor.class;
        Field[] fields = cls.getDeclaredFields();
        for (Field f :
                fields) {
            if (f.isAnnotationPresent(SaveAnnotation.class)) {
                f.setAccessible(true);
                fw.write(f.getName() + "=" + f.get(t).toString() + ";");
            }

        }
        fw.close();
        return createdFile;
    }

    public static <T> T deserialize(File file, Class<T> tClass) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        StringBuilder sb = new StringBuilder();
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            sb.append(myReader.nextLine());
        }
        myReader.close();

        String[] parts = sb.toString().split(";");
        T t = tClass.getDeclaredConstructor().newInstance();
        for (String part : parts) {
            String[] elementsAndValues = part.split("=");
            for (int j = 0; j < elementsAndValues.length; j++) {
                Field[] fields = tClass.getDeclaredFields();
                for (Field f :
                        fields) {
                    if (elementsAndValues[0].equals(f.getName())) {
                        f.setAccessible(true);
                        if (f.getType().equals(String.class)) {
                            f.set(t, (f.getType()).cast(elementsAndValues[1]));
                        } else
                            f.set(t, Integer.parseInt(elementsAndValues[1]));
                    }
                }
            }
        }
        return t;
    }

}
