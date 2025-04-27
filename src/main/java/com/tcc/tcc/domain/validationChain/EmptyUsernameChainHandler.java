package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;

public final class EmptyUsernameChainHandler extends BaseChainHandler{
    public EmptyUsernameChainHandler(ChainHandler next){
        super(next);
    }

    // @Override
    // public boolean handle(UserRequestDTO dto){
    //     String name = dto.getName();
    //     if(name == null || name.isEmpty()){
    //         return false;
    //     }
    //     return ((next == null)? true : next.handle(dto));
    // }

    @Override
    public void handle(UserRequestDTO dto) throws UnsuccessfulRequestException{
        String name = dto.getName();
        if(name == null || name.isEmpty()){
            throw new UnsuccessfulRequestException("Username field can't be empty!");
        }
        if (next != null) {
            next.handle(dto);
        }
    }
}
