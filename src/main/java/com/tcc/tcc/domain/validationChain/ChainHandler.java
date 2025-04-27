package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;

public interface ChainHandler {
    public void handle(UserRequestDTO dto);
}
