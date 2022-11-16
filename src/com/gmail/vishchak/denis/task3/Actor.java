package com.gmail.vishchak.denis.task3;

public class Actor {
    @SaveAnnotation
    private String name;
    @SaveAnnotation
    private String surname;
    @SaveAnnotation
    private Integer age;
    private Integer height;

    public Actor() {
    }
    public Actor(String name, String surname, Integer age, Integer height) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
