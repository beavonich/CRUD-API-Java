package ru.andrey.springcourse.models;

import org.hibernate.validator.constraints.pl.REGON;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 symbols")
    private String name;

    @Min(value = 0, message = "Age should be more than zero")
    private int age;

    @NotEmpty(message = "Field email should not be empty")
    @Pattern(regexp = "[a-zA-Z0-9\\.]+@(mail|gmail)\\.(ru|com)", message = "Field \"email\" has incorrect format")
    private String email;

    public Person(){

    }

    public Person(int id, String name, int age, String email) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
