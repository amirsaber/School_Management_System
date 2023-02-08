/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeacherPackage;

/**
 *
 * @author Amir
 */
public class TeacherClass {
    String id,fname,lname,gender,
            classs,phonenumber,email,
            jobtitle,subject,salary,age,registerdate
            ,registerby;

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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public String getRegisterby() {
        return registerby;
    }

    public void setRegisterby(String registerby) {
        this.registerby = registerby;
    }

    public TeacherClass(String id, String fname, String lname, String gender, String classs, String phonenumber, String email, String jobtitle, String subject, String salary, String age, String registerdate, String registerby) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.classs = classs;
        this.phonenumber = phonenumber;
        this.email = email;
        this.jobtitle = jobtitle;
        this.subject = subject;
        this.salary = salary;
        this.age = age;
        this.registerdate = registerdate;
        this.registerby = registerby;
    }

  
}
