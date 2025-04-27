package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;

public final class UsernameLengthChainHandler extends BaseChainHandler{
    public UsernameLengthChainHandler(ChainHandler next){
        super(next);
    }

    @Override
    public void handle(UserRequestDTO dto) throws UnsuccessfulRequestException{
        String name = dto.getName();
        if(name.length() >= 64){
            throw new UnsuccessfulRequestException("Username can't have more than 63 characters!");
        }
        if (next != null) {
            next.handle(dto);
        }
    }
}
