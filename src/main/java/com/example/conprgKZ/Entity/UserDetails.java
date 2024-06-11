package com.example.conprgKZ.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserDetails {


    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int Id;
    private String username;
    private String email;
    private String password;
    private String mobileNo;

    public UserDetails() {

    }

    public UserDetails(int id, String username, String email, String password, String mobileNo) {
        super();
        this.Id=id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "UserDetails [Id=" + Id + ", username=" + username + ", email=" + email + ", password=" + password
                + ", mobileNo=" + mobileNo + "]";
    }
}
