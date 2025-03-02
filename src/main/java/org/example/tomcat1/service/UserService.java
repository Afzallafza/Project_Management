package org.example.tomcat1.service;

import org.example.tomcat1.dao.UserDao;
import org.example.tomcat1.exception.UserNotFoundException;
import org.example.tomcat1.model.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private UserDao userDao=UserDao.getInstance();
    private static UserService userService = new UserService();
    private UserService() {}
    public static UserService getInstance() {
        return userService;
    }
    public void save(User user) throws SQLException,ClassNotFoundException {
        userDao.save(user);
    }
    public User findByUsername(String username) throws SQLException,ClassNotFoundException {
        Optional<User> user = userDao.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new UserNotFoundException("Incorect username provided");
        }
    }
}
