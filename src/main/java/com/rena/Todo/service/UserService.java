package com.rena.Todo.service;

import com.rena.Todo.dto.request.RegistrationRequest;
import com.rena.Todo.entity.User;

public interface UserService {
    String createUser(RegistrationRequest request);

    User getAuser(Long id);

    String updateAccount(Long id, RegistrationRequest request);

    String deleteAccount(Long id);
}
