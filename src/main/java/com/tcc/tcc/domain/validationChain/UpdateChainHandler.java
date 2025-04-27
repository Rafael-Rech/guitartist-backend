package com.tcc.tcc.domain.validationChain;

public final class UpdateChainHandler extends BaseChainHandler {
    public UpdateChainHandler() {
        super();
        this.next = new EmptyUsernameChainHandler(
            new UsernameLengthChainHandler(
                new EmptyEmailChainHandler(
                    new EmailLengthChainHandler(
                        new EmailFormatChainHandler(
                            new PasswordLengthChainHandler(null)
                        )
                    ) 
                )
            )
        );
    }
}
