package com.example.medonation;

public class AdminHelperClass {
    String adname,adcnic,ademail,adphno,adpassword;

    public AdminHelperClass() {
        //empty constructor
    }

    public AdminHelperClass(String adname, String adcnic, String ademail, String adphno, String adpassword) {
        this.adname = adname;
        this.adcnic = adcnic;
        this.ademail = ademail;
        this.adphno = adphno;
        this.adpassword = adpassword;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getAdcnic() {
        return adcnic;
    }

    public void setAdcnic(String adcnic) {
        this.adcnic = adcnic;
    }

    public String getAdemail() {
        return ademail;
    }

    public void setAdemail(String ademail) {
        this.ademail = ademail;
    }

    public String getAdphno() {
        return adphno;
    }

    public void setAdphno(String adphno) {
        this.adphno = adphno;
    }

    public String getAdpassword() {
        return adpassword;
    }

    public void setAdpassword(String adpassword) {
        this.adpassword = adpassword;
    }
}
