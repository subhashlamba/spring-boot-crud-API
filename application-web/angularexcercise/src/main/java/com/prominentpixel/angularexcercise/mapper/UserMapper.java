package com.prominentpixel.angularexcercise.mapper;

import com.prominentpixel.angularexcercise.domain.User;
import com.prominentpixel.angularexcercise.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public List<UserDTO> getUserPaginations(Iterable<User> userList) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User userObj : userList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userObj.getId());
            userDTO.setPassword(userObj.getPassword());
            userDTO.setEmail(userObj.getEmail());
            userDTO.setMobile(userObj.getMobile());
            userDTO.setFirstName(userObj.getFirstName());
            userDTO.setStatus(userObj.getStatus());
            userDTO.setLastName(userObj.getLastName());
            userDTO.setNewCompany(userObj.getNewCompany());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    public UserDTO convertUser(User fetchedUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(fetchedUser.getId());
        userDTO.setFirstName(fetchedUser.getFirstName());
        userDTO.setLastName(fetchedUser.getLastName());
        userDTO.setEmail(fetchedUser.getEmail());
        userDTO.setStatus(fetchedUser.getStatus());
        userDTO.setMobile(fetchedUser.getMobile());
        userDTO.setPassword(fetchedUser.getPassword());
        userDTO.setNewCompany(fetchedUser.getNewCompany());
        return userDTO;
    }
}
