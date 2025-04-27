package com.tcc.tcc.domain.dto.user.login;

import com.tcc.tcc.domain.dto.user.UserResponseDTO;

public class LoginResponseDTO {
        private String token;
    private UserResponseDTO userResponseDTO;


    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserResponseDTO getUserResponseDTO() {
        return userResponseDTO;
    }
    public void setUserResponseDTO(UserResponseDTO userResponseDTO) {
        this.userResponseDTO = userResponseDTO;
    }
}
