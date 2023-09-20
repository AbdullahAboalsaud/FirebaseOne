package com.example.firebaseone.models;

public class ModelParent {

    private String name;
    private String email;
    private String phone;


    private String id;

    public ModelParent() {
    }

    public ModelParent(String name, String email, String phone, String id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
