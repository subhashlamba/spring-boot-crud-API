package com.prominentpixel.angularexcercise.controller;

import com.prominentpixel.angularexcercise.dto.UserDTO;
import com.prominentpixel.angularexcercise.service.UserService;
import com.prominentpixel.angularexcercise.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(Constants.API_BASE + Constants.USER)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createUser")
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception {
        return userService.createUser(userDTO);
    }

    @PutMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody UserDTO userDTO) throws Exception {
        userService.updateUser(userDTO);
    }

    @DeleteMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getAllUsers(@RequestParam int pageNumber, @RequestParam int pageSize,
                                           @RequestParam(defaultValue = "", name = "searchTerm") String searchTerm,
                                           @RequestParam(defaultValue = "column", name = "sortColumn") String sortColumn,
                                           @RequestParam(defaultValue = "asc", name = "sortOrder") String sortOrder) {
        return userService.getUsers(pageNumber, pageSize, searchTerm, sortColumn, sortOrder);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }
}
