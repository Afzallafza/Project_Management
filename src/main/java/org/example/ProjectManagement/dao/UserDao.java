package org.example.ProjectManagement.dao;

import org.example.ProjectManagement.config.DbConfig;
import org.example.ProjectManagement.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private static final UserDao userDao = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return userDao;
    }

    public void save(User user) throws SQLException, ClassNotFoundException, IOException {
        String query = "insert into user (name,email,username,password,role) values(?,?,?,?,?)";
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, "Developer");
            preparedStatement.execute();
        }
    }

    public User mapResultSetToUser(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException {
        String name = resultSet.getString("name");
        String uname = resultSet.getString("username");
        String role = resultSet.getString("role");
        int id = resultSet.getInt("id");
        return new User.Builder()
                .id(id)
                .name(name)
                .username(uname)
                .role(role)
                .build();
    }

    public Optional<User> findByUsername(String username) throws SQLException, ClassNotFoundException, IOException {
        String query = "select * from user where username=?";
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.absolute(1)) {
                return Optional.of(mapResultSetToUser(resultSet));
            } else {
                return Optional.empty();
            }
        }
    }

    public List<User> findAll() throws SQLException, ClassNotFoundException, IOException {
        String query = "select * from user where username!=?";
        List<User> users = new ArrayList<>();
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            preparedStatement.setString(1, "admin");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }
        }
        return users;
    }

    public User findById(int id) throws SQLException, ClassNotFoundException, IOException {
        String query = "select * from user where id=?";
        User user = null;
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.absolute(1)) {
                user = mapResultSetToUser(resultSet);
                user.setPassword(resultSet.getString("password"));
            }
        }
        return user;
    }

    public void updateRole(User user, String role) throws SQLException, ClassNotFoundException, IOException {
        String query = "update user set role=? where id=?";
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();

        }
    }

    public List<User> findAllDevelopers() throws SQLException, ClassNotFoundException, IOException {
        String query = "select * from user where role='Developer'";
        List<User> devs = new ArrayList<>();
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                devs.add(mapResultSetToUser(resultSet));
            }
        }
        return devs;
    }

    public List<User> findAllLeads() throws SQLException, IOException, ClassNotFoundException {
        String query = "select * from user where role='Team Lead'";
        List<User> leads = new ArrayList<>();
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                leads.add(mapResultSetToUser(resultSet));
            }
        }
        return leads;
    }

    public List<User> findAllManagers() throws SQLException, IOException, ClassNotFoundException {
        String query = "select * from user where role='Project Manager'";
        List<User> managers = new ArrayList<>();
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                managers.add(mapResultSetToUser(resultSet));
            }
        }
        return managers;
    }
}
