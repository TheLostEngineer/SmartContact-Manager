package com.example.SmartContactManager.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CONTACTS")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cID;

//    @NotBlank(message = "First name cannot be blank")
//    @Size(min = 3, max = 23456, message = "Name should be more than 3 characters")
    private String name;
    private String secondName;
    private String Work;

//    @NotBlank(message = "Email cannot be blank")
    private String email;

//    @NotBlank(message = "Phone number cannot be blank")
//    @Size(min = 10, max = 12, message = "Please Enter a Valid Phone Number")
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

//    @Override
//    public String toString() {
//        return "Contact{" +
//                "cID=" + cID +
//                ", name='" + name + '\'' +
//                ", secondName='" + secondName + '\'' +
//                ", Work='" + Work + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", Image='" + Image + '\'' +
//                ", description='" + description + '\'' +
//                ", user=" + user +
//                '}';
//    }
}
