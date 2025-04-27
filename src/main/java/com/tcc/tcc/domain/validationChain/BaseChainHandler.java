package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;

public abstract class BaseChainHandler implements ChainHandler {
    protected ChainHandler next;

    protected BaseChainHandler() {
    }

    protected BaseChainHandler(ChainHandler next) {
        this.next = next;
    }

    public void handle(UserRequestDTO dto) {
        if (next != null) {
            next.handle(dto);
        }
    }
}
