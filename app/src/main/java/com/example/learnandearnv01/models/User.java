package com.example.learnandearnv01.models;

public class User {
    private String name;
    private String mail;
    private String pass;
    private String surname;
    private String type;
    private String orders;
    private String requests;
    private String courses;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

    public User() {
        this.orders = orders;
        this.requests = requests;
        this.courses = courses;
        this.mail = mail;
        this.surname = surname;
        this.name = name;
        this.pass = pass;
        this.type = type;
        this.phone = phone;
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
