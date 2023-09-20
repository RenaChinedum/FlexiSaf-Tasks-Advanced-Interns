package com.rena.Todo.service.serviceImpl;

import com.rena.Todo.dto.request.RegistrationRequest;
import com.rena.Todo.entity.User;
import com.rena.Todo.repository.UserRepository;
import com.rena.Todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String createUser(RegistrationRequest request){
        User user = new User(request);
        userRepository.save(user);
        return user.getFirstName()+ " " + user.getLastName() + " registered Successfully";
    }

    @Override
    public User getAuser(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User Not Found"));
    }
    @Override
    public String updateAccount(Long id, RegistrationRequest request){
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User Not Found"));
        if(request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        userRepository.save(user);
        return "Account Update Successfully";
    }
    @Override
    public String deleteAccount(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User Not Found"));
        userRepository.delete(user);
        return "Account Deleted Successfully";
    }

}
