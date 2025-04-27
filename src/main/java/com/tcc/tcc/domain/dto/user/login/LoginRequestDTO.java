package com.tcc.tcc.domain.dto.user.login;

public class LoginRequestDTO {
    private String email;
    private String password;


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

    @Override
    public String toString(){
        return this.email + " - " + this.password;
    }
}
