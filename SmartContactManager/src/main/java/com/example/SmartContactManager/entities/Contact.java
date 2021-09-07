package com.example.SmartContactManager.entities;

import javax.persistence.*;

@Entity
@Table(name = "CONTACTS")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cID;
    private String name;
    private String secondName;
    private String Work;
    private String email;
    private String phone;
    private String Image;
    @Column (length = 1000)
    private String description;

    @ManyToOne
    private User user;

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {

        this.cID = cID;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSecondName() {

        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        Work = work;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {

        Image = image;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }
}
