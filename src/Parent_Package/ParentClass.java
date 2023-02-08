/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParentPackage;

/**
 *
 * @author amir
 */
public class ParentClass {
    String id, fname , lname , gender , phonenum1 , phonenum2 , email , address , birthdate , reg ;

    public ParentClass(String id, String fname, String lname, String gender, String phonenum1, String phonenum2, String email, String address, String birthdate, String reg) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.phonenum1 = phonenum1;
        this.phonenum2 = phonenum2;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
        this.reg = reg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenum1() {
        return phonenum1;
    }

    public void setPhonenum1(String phonenum1) {
        this.phonenum1 = phonenum1;
    }

    public String getPhonenum2() {
        return phonenum2;
    }

    public void setPhonenum2(String phonenum2) {
        this.phonenum2 = phonenum2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }
    
 
}
