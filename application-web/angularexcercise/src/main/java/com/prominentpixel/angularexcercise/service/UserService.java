package com.prominentpixel.angularexcercise.service;

import com.prominentpixel.angularexcercise.domain.User;
import com.prominentpixel.angularexcercise.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO createUser(UserDTO userDTO) throws Exception;

    Map<String, Object> getUsers(int offset, int size, String searchTerm, String sortColumn, String sortDirection);

    void deleteUserById(Long id);

    void updateUser(UserDTO userDTO) throws Exception;

    UserDTO getUserById(Long id);

    List<UserDTO> getSimpleUsers();
}
