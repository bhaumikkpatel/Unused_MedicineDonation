package com.example.medonation;

public class UserHelperClass {
    String name,cnic,email,phno,password;

    public UserHelperClass() {
        //empty constructor to avoid firebase errors
    }

    public UserHelperClass(String name, String cnic, String email, String phno, String password) {
        //constructor to save value in firebase
        this.name = name;
        this.cnic = cnic;
        this.email = email;
        this.phno = phno;
        this.password = password;
    }

    public String getName() {
        //getter setters because we use all the in project
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
