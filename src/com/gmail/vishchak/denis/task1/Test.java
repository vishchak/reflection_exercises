package com.gmail.vishchak.denis.task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create an annotation that accepts parameters for the method.
 * Write a code that will call the method marked with this annotation and pass the annotation's parameters to the called method
 */
public class Test {
    public static void main(String[] args) {
        Class<?> cls = Test.class;
        Method[] methods = cls.getMethods();
        for (Method m :
                methods) {
            if (m.isAnnotationPresent(TestAnnotation.class)) {
                TestAnnotation ta = m.getAnnotation(TestAnnotation.class);
                try {
                    m.invoke(cls.getDeclaredConstructor().newInstance(), ta.a(), ta.b());
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @TestAnnotation(a = 5, b = 9)
    public void test(int a, int b) {
        System.out.println(a + b);
    }
}

