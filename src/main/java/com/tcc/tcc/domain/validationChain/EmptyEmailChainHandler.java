package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;

public final class EmptyEmailChainHandler extends BaseChainHandler {
    public EmptyEmailChainHandler(ChainHandler next){
        super(next);
    }

    @Override
    public void handle(UserRequestDTO dto) throws UnsuccessfulRequestException{
        final String email = dto.getEmail();
        if(email == null || email.isEmpty()){
            throw new UnsuccessfulRequestException("E-mail field can't be empty!");
        }
        if (next != null) {
            next.handle(dto);
        }
    }
}
