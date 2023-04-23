package com.example.medonation;

public class sModel {
    String medicineName,medicineQuantity,shortageArea,postDate;

    public sModel() {
    }

    public sModel(String medicineName, String medicineQuantity, String shortageArea, String postDate) {
        this.medicineName = medicineName;
        this.medicineQuantity = medicineQuantity;
        this.shortageArea = shortageArea;
        this.postDate = postDate;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(String medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public String getShortageArea() {
        return shortageArea;
    }

    public void setShortageArea(String shortageArea) {
        this.shortageArea = shortageArea;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
