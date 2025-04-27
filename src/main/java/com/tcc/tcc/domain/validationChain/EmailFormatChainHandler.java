package com.tcc.tcc.domain.validationChain;

import java.util.regex.Pattern;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;

public final class EmailFormatChainHandler extends BaseChainHandler{
    public EmailFormatChainHandler(ChainHandler next){
        super(next);
    }

    @Override
    public void handle(UserRequestDTO dto) throws UnsuccessfulRequestException{
        if(!Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
        .matcher(dto.getEmail()).matches()){
            throw new UnsuccessfulRequestException("Invalid e-mail format!");
        }
        if (next != null) {
            next.handle(dto);
        }
    }
}
