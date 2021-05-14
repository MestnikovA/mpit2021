package com.example.learnandearnv01.models;

public class User {
    private String name, mail, pass, surname, type, orders, requests, courses;

    public User() {
        this.orders = orders;
        this.requests = requests;
        this.courses = courses;
        this.mail = mail;
        this.surname = surname;
        this.name = name;
        this.pass = pass;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name   =  name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname   =  surname;
    }
    public String getMail(){
        return mail;
    }
    public void setMail(String mail){
        this.mail   =  mail;
    }

    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass   =  pass;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type   =  type;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

}
