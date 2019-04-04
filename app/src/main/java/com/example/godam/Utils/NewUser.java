package com.example.godam.Utils;

public class NewUser {
    private String fname,lname,type,uname,password,mobile_no;

    public NewUser() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public NewUser(String fname, String lname, String type, String uname, String password, String mobile_no) {
        this.fname = fname;
        this.lname = lname;
        this.type = type;
        this.uname = uname;
        this.password = password;
        this.mobile_no = mobile_no;
    }
}
