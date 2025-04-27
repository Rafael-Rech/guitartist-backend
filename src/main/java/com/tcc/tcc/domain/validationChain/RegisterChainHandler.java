package com.tcc.tcc.domain.validationChain;

import com.tcc.tcc.domain.repository.UserRepository;

public final class RegisterChainHandler extends BaseChainHandler {
    public RegisterChainHandler(UserRepository userRepository) {
        super();
        this.next = new EmptyUsernameChainHandler(
            new UsernameLengthChainHandler(
                new EmptyEmailChainHandler(
                    new EmailFormatChainHandler(
                        new EmailLengthChainHandler(
                            new EmailAlreadyRegisteredChainHandler(
                                new PasswordLengthChainHandler( 
                                    null), 
                                userRepository
                            )
                        )
                    )
                )
            )
        );
    }
}
