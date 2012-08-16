package com.bracketbird.client.service;

import com.bracketbird.clientcore.model.*;

import java.io.Serializable;


/**
 *
 */
public class CreateUser extends Creater implements Serializable {
    private static final long serialVersionUID = -6383134529154955454L;

    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private String phone;
    private String password;
    private Long id;

    public CreateUser() {
    }

    public static CreateUser newInstance(String firstName, String lastName, String nickName, String pass, String phone, String email) {
        CreateUser c = new CreateUser();
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setNickName(nickName);
        c.setPassword(pass);
        c.setPhone(phone);
        c.setEmail(email);
        return c;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
