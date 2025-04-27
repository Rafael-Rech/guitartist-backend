package com.tcc.tcc.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.tcc.common.DateConverter;
import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.dto.user.UserResponseDTO;
import com.tcc.tcc.domain.exception.ResourceNotFoundException;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;
import com.tcc.tcc.domain.model.User;
import com.tcc.tcc.domain.repository.UserRepository;
import com.tcc.tcc.domain.validationChain.ChainHandler;
import com.tcc.tcc.domain.validationChain.RegisterChainHandler;
import com.tcc.tcc.domain.validationChain.UpdateChainHandler;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ChainHandler registerChainHandler;
    private ChainHandler updateChainHandler = new UpdateChainHandler();

    public List<UserResponseDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> response = new ArrayList<UserResponseDTO>();
        for(User user : users){
            response.add(mapper.map(user, UserResponseDTO.class));
        }
        return response;
    }

    public UserResponseDTO findUserById(String id) throws ResourceNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User with id " + id + " does not exist!");
        }
        UserResponseDTO userResponseDTO = mapper.map(user.get(), UserResponseDTO.class);
        return userResponseDTO;
    }

    public UserResponseDTO createUser(UserRequestDTO dto) throws UnsuccessfulRequestException{
        if (registerChainHandler == null){
            registerChainHandler = new RegisterChainHandler(userRepository);
        }

        registerChainHandler.handle(dto);

        User newUser = mapper.map(dto, User.class);

        String encryptedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encryptedPassword);

        newUser = userRepository.save(newUser);
        return mapper.map(newUser, UserResponseDTO.class);
    }

    public UserResponseDTO updateUser(String id, UserRequestDTO dto) throws UnsuccessfulRequestException, ResourceNotFoundException{
        Optional<User> databaseUser = userRepository.findById(id);
        if(databaseUser.isEmpty()){
            throw new ResourceNotFoundException("User with id " + id + " does not exist!");
        }

        updateChainHandler.handle(dto);


        User newUser = mapper.map(dto, User.class);
        newUser.setId(id);
        if(newUser.getPassword() != null){
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        } else {
            newUser.setPassword(databaseUser.get().getPassword());
        }
        newUser = userRepository.save(newUser);
        return mapper.map(newUser, UserResponseDTO.class);
    }

    public void deleteUser(String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User not found!");
        }
        userRepository.delete(user.get());
    }
}
