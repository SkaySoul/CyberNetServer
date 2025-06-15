package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.dto.UserDTO;
import com.cybernet.cybernetserver.dtoconverter.UserDTOConverter;
import com.cybernet.cybernetserver.entities.User;
import com.cybernet.cybernetserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDTOConverter userDTOConverter;
    private final UserService userService;


    @GetMapping()
    public ResponseEntity<List<User>> readAll(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> readAll(@PathVariable String username){
        return ResponseEntity.ok(
                userDTOConverter.mapUserToUserDTO(
                        userService.findByUsername(username)
                                .orElseThrow(() -> new RuntimeException("User not found"))
                )
        );
    }


//    public ResponseEntity<UserDTO> mappingResponseGetUserByUsername(){
//
//    }

//    @PutMapping
//    public ResponseEntity<User> update(@RequestBody User user){
//        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
//    }


    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        userService.deleteUser(id);
        return HttpStatus.OK;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        System.out.println("Authenticated user: " + authentication.getName());
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(userDTOConverter.mapUserToUserDTO(user));
    }
}
