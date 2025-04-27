package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;
import com.tcc.tcc.domain.repository.UserRepository;

public final class EmailAlreadyRegisteredChainHandler extends BaseChainHandler{
    private UserRepository userRepository;

    public EmailAlreadyRegisteredChainHandler(ChainHandler next, UserRepository userRepository){
        super(next);
        this.userRepository = userRepository;
    }

    @Override
    public void handle(UserRequestDTO dto) throws UnsuccessfulRequestException{
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UnsuccessfulRequestException("The e-mail provided is already registered!");
        }
        if (next != null) {
            next.handle(dto);
        }
    }
}
