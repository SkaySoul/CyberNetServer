package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.dto.UserDTO;
import com.cybernet.cybernetserver.entities.User;
import com.cybernet.cybernetserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody UserDTO dto){
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> readAll(){
        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<User>> readAll(@PathVariable String username){
        return new ResponseEntity<>(userService.getUsersByUsername(username), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        userService.deleteUser(id);
        return HttpStatus.OK;
    }
}
