package com.rena.Todo.controller;

import com.rena.Todo.dto.request.RegistrationRequest;
import com.rena.Todo.entity.User;
import com.rena.Todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser (@RequestBody RegistrationRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getAuser(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody RegistrationRequest request){
        return ResponseEntity.ok(userService.updateAccount(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteAccount(id),HttpStatus.OK);
    }


}
