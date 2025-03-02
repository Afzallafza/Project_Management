package org.example.tomcat1.dao;

import org.example.tomcat1.config.DbConfig;
import org.example.tomcat1.exception.UserNotFoundException;
import org.example.tomcat1.model.User;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private static UserDao userDao = new UserDao();
    private UserDao() {}
    public static UserDao getInstance() {
        return userDao;
    }
    public void save(User user) throws SQLException,ClassNotFoundException {
        String query="insert into user (name,email,username,password) values(?,?,?,?)";
        try(Connection con=DbConfig.connect();
        PreparedStatement preparedStatement=con.prepareStatement(query);
        ){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.execute();
        }
    }
    public Optional<User> findByUsername(String username) throws SQLException,ClassNotFoundException {
        String query="select * from user where username=?";
        try(Connection con=DbConfig.connect();
        PreparedStatement preparedStatement=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ){
            preparedStatement.setString(1,username);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.absolute(1)){
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                String password=resultSet.getString("password");
                String uname=resultSet.getString("username");
                User user=new User(name,username,email,password);
                return Optional.of(user);
            }else{
                return Optional.empty();
            }
        }
    }
}
