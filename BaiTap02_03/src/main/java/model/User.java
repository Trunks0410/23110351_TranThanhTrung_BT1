package model;

import java.sql.Date;

public class User {
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String phone;
    private Date createdDate;

    public User(String username, String password, String email, String fullname, String phone, Date createdDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.createdDate = createdDate;
    }
    
    // Getter và Setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Date getCreatedDate() { return createdDate; } // Getter cho createdDate
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}