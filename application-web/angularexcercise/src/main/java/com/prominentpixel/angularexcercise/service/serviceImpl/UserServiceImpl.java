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
import java.util.Optional;

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
        userDAO.save(userObj);
        return userDTO;
    }

    @Override
    public Map<String, Object> getUsers(int pageNumber, int pageSize, String searchTerm, String sortColumn, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("desc")) {
            sort = Sort.by(Sort.Direction.DESC, sortColumn);
        } else {
            sort = Sort.by(Sort.Direction.ASC, sortColumn);
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

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
        System.out.println("Delete Operation" + id);
        userDAO.deActivateUser(id);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws Exception {
        Optional<User> updatedUser = userDAO.findById(userDTO.getId());
        if (updatedUser == null) {
            throw new Exception(CommonConstants.USER_NOT_FOUND + updatedUser.get().getId());
        } else {
            updatedUser.get().setFirstName(userDTO.getFirstName());
            updatedUser.get().setLastName(userDTO.getLastName());
            updatedUser.get().setMobile(userDTO.getMobile());
            updatedUser.get().setPassword(userDTO.getPassword());
            updatedUser.get().setEmail(userDTO.getEmail());
            updatedUser.get().setStatus(userDTO.getStatus());
            updatedUser.get().setId(userDTO.getId());
            updatedUser.get().setNewCompany(userDTO.getNewCompany());
            userDAO.save(updatedUser.get());
        }
    }

    @Override
    public UserDTO getUserById(Long id) {
        User fetchedUser = userDAO.findById(id).get();
        return userMapper.convertUser(fetchedUser);
    }

}
