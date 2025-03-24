package org.example.ProjectManagement.service;

import org.example.ProjectManagement.dao.UserDao;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.exception.UserNotFoundException;
import org.example.ProjectManagement.mapper.EntityToDtoMapper;
import org.example.ProjectManagement.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao = UserDao.getInstance();
    private static final UserService userService = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return userService;
    }

    public void save(User user) throws SQLException, ClassNotFoundException, IOException {
        userDao.save(user);
    }

    public UserDto findByUsername(String username) throws SQLException, ClassNotFoundException, IOException {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isPresent()) {
            return new UserDto(user.get().getId(), user.get().getName(), user.get().getUsername(), user.get().getRole());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public List<UserDto> findAll() throws SQLException, ClassNotFoundException, IOException {

        return EntityToDtoMapper.toUserDtoList(userDao.findAll());
    }

    public boolean validateCredentials(UserDto userDto, String password) throws SQLException, IOException, ClassNotFoundException {
        System.out.println(userDto);
        User user = userDao.findById(userDto.getId());
        return user.getPassword().equals(password);
    }

    public UserDto findById(int id) throws SQLException, ClassNotFoundException, IOException {
        return EntityToDtoMapper.toUserDto(userDao.findById(id));
    }

    public void updateRole(Integer userId, String name) throws SQLException, ClassNotFoundException, IOException {
        User user = userDao.findById(userId);
        userDao.updateRole(user, name);
    }

    public List<UserDto> findAllDevelopers() throws SQLException, ClassNotFoundException, IOException {
        return EntityToDtoMapper.toUserDtoList(userDao.findAllDevelopers());
    }

    public List<UserDto> findAllLeads() throws SQLException, ClassNotFoundException, IOException {
        return EntityToDtoMapper.toUserDtoList(userDao.findAllLeads());
    }

    public List<UserDto> findAllManagers() throws SQLException, ClassNotFoundException, IOException {
        return EntityToDtoMapper.toUserDtoList(userDao.findAllManagers());

    }

}
