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
public class techerTable {
    String TFname , TLname ,subject , TGender, phone , email;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public techerTable(String TFname, String TLname, String subject, String TGender, String phone, String email) {
        this.TFname = TFname;
        this.TLname = TLname;
        this.subject = subject;
        this.TGender = TGender;
        this.phone = phone;
        this.email = email;
    }

    public String getTFname() {
        return TFname;
    }

    public void setTFname(String TFname) {
        this.TFname = TFname;
    }

    public String getTLname() {
        return TLname;
    }

    public void setTLname(String TLname) {
        this.TLname = TLname;
    }

    public String getTGender() {
        return TGender;
    }

    public void setTGender(String TGender) {
        this.TGender = TGender;
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

    
            
            
            
            
            
            
            }
