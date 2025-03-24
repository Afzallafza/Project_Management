package org.example.ProjectManagement.dao;

import org.example.ProjectManagement.config.DbConfig;
import org.example.ProjectManagement.exception.ProjectNotFoundException;
import org.example.ProjectManagement.model.Project;
import org.example.ProjectManagement.model.User;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {
    private final UserDao userDao = UserDao.getInstance();
    private static final ProjectDao projectDao = new ProjectDao();
    private ProjectDao() {
    }

    public static ProjectDao getInstance() {
        return projectDao;
    }

    public void save(String name,int managerId,int leadId) throws SQLException, ClassNotFoundException, IOException {
        String query = "insert into project(name,projectManager,teamLead,startDate) values(?,?,?,?)";
        try (Connection connection = DbConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, managerId);
            preparedStatement.setInt(3, leadId);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.execute();
        }
    }
    public Project mapResultSetToProject(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException {
        String name = resultSet.getString("name");
        int projectId = resultSet.getInt("id");
        LocalDateTime startDate = resultSet.getTimestamp("startDate").toLocalDateTime();
        int managerId = resultSet.getInt("projectManager");
        User manager= userDao.findById(managerId);
        int teamLeadId = resultSet.getInt("teamLead");
        User lead= userDao.findById(teamLeadId);
        return new Project.Builder()
                .name(name)
                .id(projectId)
                .projectManager(manager)
                .teamLead(lead)
                .startDate(startDate)
                .build();

    }
    public Project findByLeadId(int id) throws SQLException, ClassNotFoundException,IOException {
        System.out.println(id);
        String query = "select * from project where teamLead=?";
        Project project = null;
        try (Connection connection = DbConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.absolute(1)) {
                project = mapResultSetToProject(resultSet);
            }
        }
        return project;
    }

    public List<Project> findAllByManagerId(int id) throws SQLException, ClassNotFoundException, IOException {
        List<Project> projects = new ArrayList<>();
        String query = "select * from project where projectManager=?";
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                projects.add(mapResultSetToProject(resultSet));
            }
        }
        return projects;
    }
    public Project findById(int id) throws SQLException, ClassNotFoundException ,ProjectNotFoundException, IOException{
        String query = "select * from project where id=?";
        Project project = null;
        try (Connection con = DbConfig.connect();
        PreparedStatement preparedStatement = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.absolute(1)) {
                project = mapResultSetToProject(resultSet);
            }else{
                throw new ProjectNotFoundException("Project does not exist!!!");
            }
        }
        return project;
    }
}
