/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentPackage;

/**
 *
 * @author Amir
 */
public class StudentClass {
   String id , fname , lname , gender , classs, phone , email , address , hobbies ,  parentid , birthdate ,  regdate , regname;

    public StudentClass(String id, String fname, String lname, String gender, String classs, String phone, String email, String address, String hobbies, String parentid, String birthdate, String regdate, String regname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.classs = classs;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.hobbies = hobbies;
        this.parentid = parentid;
        this.birthdate = birthdate;
        this.regdate = regdate;
        this.regname = regname;
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

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getRegname() {
        return regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }
          
}
