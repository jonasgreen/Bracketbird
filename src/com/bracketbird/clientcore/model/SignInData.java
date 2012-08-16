package com.bracketbird.clientcore.model;

import java.io.Serializable;

/**
 *
 */
public class SignInData implements Serializable{
    private static final long serialVersionUID = 4197908513520125744L;

    private String email;
    private String password;

    public SignInData() {
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
}
