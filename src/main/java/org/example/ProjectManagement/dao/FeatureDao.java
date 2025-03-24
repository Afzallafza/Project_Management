package org.example.ProjectManagement.dao;

import org.example.ProjectManagement.config.DbConfig;
import org.example.ProjectManagement.dto.ProjectDto;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.enums.FeaturePriority;
import org.example.ProjectManagement.enums.FeatureStatus;
import org.example.ProjectManagement.exception.ProjectNotFoundException;
import org.example.ProjectManagement.model.Feature;
import org.example.ProjectManagement.model.User;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeatureDao {
    public static FeatureDao featureDao = new FeatureDao();
    private final ProjectDao projectDao = ProjectDao.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    private FeatureDao() {
    }

    public static FeatureDao getInstance() {
        return featureDao;
    }

    public void save(String name, UserDto developer, ProjectDto project, String priority, String description) throws SQLException, ClassNotFoundException , IOException {
        String query = "insert into feature(name,project,developer,priority,startDate,status,description) values(?,?,?,?,?,?,?)";
        try (Connection connection = DbConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, project.getId());
            preparedStatement.setInt(3, developer.getId());
            preparedStatement.setString(4, priority);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(6, "PENDING");
            preparedStatement.setString(7, description);
            preparedStatement.execute();
        }
    }
    public Feature mapResultsetToFeature(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException, ProjectNotFoundException {
        User developer = userDao.findById(resultSet.getInt("developer"));
        return new Feature.Builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .developer(developer)
                .project(projectDao.findById(resultSet.getInt("project")))
                .priority(FeaturePriority.valueOf(resultSet.getString("priority")))
                .status(FeatureStatus.valueOf(resultSet.getString("status")))
                .description(resultSet.getString("description"))
                .build();
    }

    public List<Feature> findAllByProjectId(int id) throws SQLException, ClassNotFoundException, ProjectNotFoundException,IOException{
        StringBuilder query = new StringBuilder("select * from feature where project=?");
        List<Feature> features = new ArrayList<>();
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                features.add(mapResultsetToFeature(resultSet));
            }
        }
        return features;
    }

    public List<Feature> findAllByDeveloperId(int id,String priority,List<String> statuses) throws SQLException, IOException,ClassNotFoundException,ProjectNotFoundException {
        List<Feature> features = new ArrayList<>();
        StringBuilder query =new StringBuilder( "select * from feature where developer=?");
        if(statuses != null && !statuses.isEmpty()){
            query.append(" and status in (");
            query.append("?,".repeat((statuses.size() - 1)));
            query.append("?)");
        }
        query.append(" order by priority ");
        query.append(priority);
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query.toString());
        ) {
            preparedStatement.setInt(1, id);
            if(statuses!=null ){
                for(int i=0;i<statuses.size();i++){
                    int parameterIndex=2+i;
                    preparedStatement.setString(parameterIndex, statuses.get(i));
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               features.add(mapResultsetToFeature(resultSet));
            }
        }
        return features;
    }

    public Feature findById(int id) throws SQLException, ClassNotFoundException,IOException, ProjectNotFoundException {
        String query = "select * from feature where id=?";
        Feature feature = null;
        try (Connection con = DbConfig.connect();
        PreparedStatement preparedStatement = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.absolute(1)){
                 feature = mapResultsetToFeature(resultSet);
            }
        }
        return feature;
    }

    public void changeStatus(int featureId, String status) throws SQLException, ClassNotFoundException ,IOException{
        String query = "update feature set status=? where id=?";
        try (Connection con = DbConfig.connect();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ){
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, featureId);
            preparedStatement.executeUpdate();
        }
    }
}


