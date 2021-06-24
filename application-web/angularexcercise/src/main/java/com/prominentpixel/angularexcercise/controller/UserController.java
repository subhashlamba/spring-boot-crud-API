package com.prominentpixel.angularexcercise.controller;

import com.prominentpixel.angularexcercise.dto.UserDTO;
import com.prominentpixel.angularexcercise.service.UserService;
import com.prominentpixel.angularexcercise.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(Constants.API_BASE + Constants.USER)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create-user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception {
        return userService.createUser(userDTO);
    }

    @PutMapping(value = "/update-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody UserDTO userDTO) throws Exception {
        userService.updateUser(userDTO);
    }

    @DeleteMapping(value = "/delete-user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping(value = "/get-all-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getAllUsers(@RequestParam int offSet, @RequestParam int size,
                                           @RequestParam(defaultValue = "", required = false, name = "searchTerm") String searchTerm,
                                           @RequestParam(defaultValue = "column", name = "sortColumn") String sortColumn,
                                           @RequestParam(defaultValue = "asc", name = "sortOrder") String sortOrder) {
        return userService.getUsers(offSet, size, searchTerm, sortColumn, sortOrder);
    }

    @GetMapping(value = "/get-simple-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getSimpleUsers() {
        List<UserDTO> usersList = userService.getSimpleUsers();
        return usersList;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }
}
