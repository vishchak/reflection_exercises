package com.gmail.vishchak.denis.task2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Write a TextContainer class that contains a string.
 * With the mechanism annotations, specify 1) in which file the text should be saved 2) a method that will execute preservation.
 * Write a Saver class that will save a field of the TextContainer class to the specified file.
 */
public class Main {
    public static void main(String[] args) {
        Class<?> cls = TextContainer.class;
        if (cls.isAnnotationPresent(SaveTo.class)) {
            SaveTo st = cls.getAnnotation(SaveTo.class);
            Field[] fields = cls.getDeclaredFields();
            Method[] methods = cls.getDeclaredMethods();
            for (Field f :
                    fields) {
                if (f.getName().equals("TEXT")) {
                    f.setAccessible(true);
                    for (Method m :
                            methods) {
                        if (m.isAnnotationPresent(Saver.class)) {
                            try {
                                m.invoke(cls.getDeclaredConstructor().newInstance(), f.get(cls), st.path());
                            } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                                     NoSuchMethodException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }
}
