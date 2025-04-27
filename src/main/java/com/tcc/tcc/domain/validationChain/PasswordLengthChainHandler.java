package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;

public final class PasswordLengthChainHandler extends BaseChainHandler{
    public PasswordLengthChainHandler(ChainHandler next){
        super(next);
    }

    // @Override
    // public boolean handle(UserRequestDTO dto){
    //     final String password = dto.getPassword();
    //     if(password == null || password.length() < 8){
    //         return false;
    //     }
    //     return ((next == null)? true : next.handle(dto));
    // }

    @Override
    public void handle(UserRequestDTO dto) throws UnsuccessfulRequestException{
        final String password = dto.getPassword();
        if(password == null || password.length() < 8){
            throw new UnsuccessfulRequestException("Password must have at least 8 characters!");
        }
        if(password.length() >= 64){
            throw new UnsuccessfulRequestException("Password can't have more than 63 characters!");
        }
        if (next != null) {
            next.handle(dto);
        }
    }
}
