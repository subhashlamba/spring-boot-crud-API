package com.prominentpixel.angularexcercise.service.serviceImpl;

import com.prominentpixel.angularexcercise.dao.UserDAO;
import com.prominentpixel.angularexcercise.domain.User;
import com.prominentpixel.angularexcercise.dto.UserDTO;
import com.prominentpixel.angularexcercise.exception.PPException;
import com.prominentpixel.angularexcercise.mapper.UserMapper;
import com.prominentpixel.angularexcercise.service.UserService;
import com.prominentpixel.angularexcercise.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDAO.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new PPException(CommonConstants.USER_ALREADY_EXIST);
        }

        User userObj = new User();
        userObj.setFirstName(userDTO.getFirstName());
        userObj.setLastName(userDTO.getLastName());
        userObj.setEmail(userDTO.getEmail());
        userObj.setMobile(userDTO.getMobile());
        userObj.setStatus(userDTO.getStatus());
        userObj.setPassword(userDTO.getPassword());
        userObj.setNewCompany(userDTO.getNewCompany());
        User saveUser = userDAO.saveAndFlush(userObj);
        userDTO.setId(saveUser.getId());
        return userDTO;
    }

    @Override
    public Map<String, Object> getUsers(int offset, int size, String searchTerm, String sortColumn, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("desc")) {
            sort = Sort.by(Sort.Direction.DESC, sortColumn);
        } else {
            sort = Sort.by(Sort.Direction.ASC, sortColumn);
        }

        Pageable pageable = PageRequest.of(offset, size, sort);

        Page<User> userListPage = userDAO.findAllByUser(pageable, searchTerm);
        Long totalCount = userListPage.getTotalElements();
        List<UserDTO> userList = userMapper.getUserPaginations(userListPage);

        Long searchCount = userDAO.countBySearchTerm(searchTerm);

        Map<String, Object> retMap = new HashMap<>(3);
        retMap.put("totalCount", totalCount);
        retMap.put("searchCount", searchCount);
        retMap.put("userList", userList);
        return retMap;
    }

    @Override
    public void deleteUserById(Long id) {
        userDAO.deActivateUser(id);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws Exception {
        User updatedUser = userDAO.findById(userDTO.getId()).get();
        if (updatedUser == null) {
            throw new Exception(CommonConstants.USER_NOT_FOUND + updatedUser.getId());
        } else {
            updatedUser.setFirstName(userDTO.getFirstName());
            updatedUser.setLastName(userDTO.getLastName());
            updatedUser.setMobile(userDTO.getMobile());
            updatedUser.setPassword(userDTO.getPassword());
            updatedUser.setEmail(userDTO.getEmail());
            updatedUser.setStatus(userDTO.getStatus());
            updatedUser.setId(userDTO.getId());
            updatedUser.setNewCompany(userDTO.getNewCompany());
            userDAO.save(updatedUser);
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        User fetchedUser = userDAO.findById(id).get();
        return userMapper.convertUser(fetchedUser);
    }

    @Override
    public List<UserDTO> getSimpleUsers() {
        List<User> usersList = userDAO.getSimpleUsers();
        return userMapper.getUserPaginations(usersList);
    }
}
