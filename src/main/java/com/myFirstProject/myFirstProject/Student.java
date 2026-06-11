package com.myFirstProject.myFirstProject;


public class Student {
    int age;
    String name;
    String course;

    public  Student(int age, String name, String course){
        this.age=age;
        this.name=name;
        this.course=course;
    }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

}
