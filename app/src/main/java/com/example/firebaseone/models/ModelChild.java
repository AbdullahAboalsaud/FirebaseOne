package com.example.firebaseone.models;

public class ModelChild {
    private String name;
    private String age;

    public ModelChild() {
    }

    public ModelChild(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
