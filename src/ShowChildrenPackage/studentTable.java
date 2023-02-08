/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShowChildrenPackage;

/**
 *
 * @author Amir
 */
public class studentTable {
 String id , fname , lname , gender , classs;   

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

    public studentTable(String id, String fname, String lname, String gender, String classs) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.classs = classs;
    }
}
