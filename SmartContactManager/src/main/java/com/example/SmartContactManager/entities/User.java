package com.example.SmartContactManager.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "USER")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name should not be blank.")
    @Size(min = 3, max = 20, message = "Name should be between 3 to 20 characters")
    private String name;
    @Column(unique = true)
    private String email;
    private String Password;
    private String role;
    private boolean enabled;
    private String imageurl;
    @Column(length = 500)
    private String about;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Contact> contacts = new ArrayList<>();

    public User() {
        super();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return Password;
    }

    public void setPassword(String password) {

        Password = password;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public boolean isEnabled() {

        return enabled;
    }

    public void setEnabled(boolean enabled) {

        this.enabled = enabled;
    }

    public String getImageurl() {

        return imageurl;
    }

    public void setImageurl(String imageurl) {

        this.imageurl = imageurl;
    }

    public String getAbout() {

        return about;
    }

    public void setAbout(String about) {

        this.about = about;
    }

    public List<Contact> getContacts() {

        return contacts;
    }

    public void setContacts(List<Contact> contacts) {

        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                ", imageurl='" + imageurl + '\'' +
                ", about='" + about + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
