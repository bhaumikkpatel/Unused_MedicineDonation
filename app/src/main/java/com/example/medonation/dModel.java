package com.example.medonation;

public class dModel {
    String dsmedicineName,dsmedicineQuantity,dsArea,dspostDate,dstype;

    public dModel() {
    }

    public dModel(String dsmedicineName, String dsmedicineQuantity, String dsArea, String dspostDate,String dstype) {
        this.dsmedicineName = dsmedicineName;
        this.dsmedicineQuantity = dsmedicineQuantity;
        this.dsArea = dsArea;
        this.dspostDate = dspostDate;
        this.dstype=dstype;
    }

    public String getDsmedicineName() {
        return dsmedicineName;
    }

    public void setDsmedicineName(String dsmedicineName) {
        this.dsmedicineName = dsmedicineName;
    }

    public String getDsmedicineQuantity() {
        return dsmedicineQuantity;
    }

    public void setDsmedicineQuantity(String dsmedicineQuantity) {
        this.dsmedicineQuantity = dsmedicineQuantity;
    }

    public String getDsArea() {
        return dsArea;
    }

    public void setDsArea(String dsArea) {
        this.dsArea = dsArea;
    }

    public String getDspostDate() {
        return dspostDate;
    }

    public void setDspostDate(String dspostDate) {
        this.dspostDate = dspostDate;
    }

    public String getDstype() {
        return dstype;
    }

    public void setDstype(String dstype) {
        this.dstype = dstype;
    }
}
