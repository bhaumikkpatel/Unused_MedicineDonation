package com.example.medonation;

public class register_medicineHelperClass {
    String medname,medtype,medqty,medcondition,medexpiry,med_donor,md_phno;
    public register_medicineHelperClass() { }
    public register_medicineHelperClass(String medname, String medtype,String medqty,String medcondition,String medexpiry,String med_donor,String md_phno) {
        this.medname = medname;
        this.medtype = medtype;
        this.medqty= medqty;
        this.medcondition=medcondition;
        this.medexpiry=medexpiry;
        this.med_donor=med_donor;
        this.md_phno=md_phno;
    }

    public String getMedname() {
        return medname;
    }
    public String getMedtype() {
        return medtype;
    }
    public String getMedqty() {
        return medqty;
    }
    public String getMedcondition() {
        return medcondition;
    }
    public String getMedexpiry() {
        return medexpiry;
    }
    public String getMed_donor() {
        return med_donor;
    }
    public String getMd_phno() {
        return md_phno;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }
    public void setMedtype(String medtype) {
        this.medtype = medtype;
    }
    public void setMedqty(String medqty) {
        this.medqty = medqty;
    }
    public void setMedcondition(String medcondition) {
        this.medcondition = medcondition;
    }
    public void setMedexpiry(String medexpiry) {
        this.medexpiry = medexpiry;
    }
    public void setMed_donor(String med_donor) {
        this.med_donor = med_donor;
    }
    public void setMd_phno(String md_phno) {
        this.md_phno = md_phno;
    }
}
