package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.UserDTO;
import com.cybernet.cybernetserver.entities.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDTOConverter {
    private ModelMapper modelMapper;

    public User mapUserDTOtoUser(User userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO mapUserToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}
