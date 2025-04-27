package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;

public final class EmailLengthChainHandler extends BaseChainHandler{
    public EmailLengthChainHandler(ChainHandler next){
        super(next);
    }

    @Override
    public void handle(UserRequestDTO dto) throws UnsuccessfulRequestException{
        String email = dto.getEmail();
        if(email.length() >= 64){
            throw new UnsuccessfulRequestException("E-mail can't have more than 63 characters!");
        }
        if (next != null) {
            next.handle(dto);
        }
    }
}
