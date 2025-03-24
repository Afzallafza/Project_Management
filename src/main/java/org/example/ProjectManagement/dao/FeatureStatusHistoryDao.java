package org.example.ProjectManagement.dao;

import org.example.ProjectManagement.config.DbConfig;
import org.example.ProjectManagement.enums.FeatureStatus;
import org.example.ProjectManagement.model.FeatureStatusHistory;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeatureStatusHistoryDao {
    private static final FeatureStatusHistoryDao featureStatusHistoryDao = new FeatureStatusHistoryDao();

    private FeatureStatusHistoryDao() {
    }

    public static FeatureStatusHistoryDao getInstance() {
        return featureStatusHistoryDao;
    }

    public void save(int featureId, String description, String status, LocalDateTime timeOfChange) throws SQLException, ClassNotFoundException, IOException {
        String query = "insert into feature_status_history(description,status,feature,time_of_change) values(?,?,?,?)";
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, featureId);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(timeOfChange));
            preparedStatement.executeUpdate();
        }
    }

    public List<FeatureStatusHistory> findByFeatureId(int featureId) throws SQLException, ClassNotFoundException, IOException {
        String query = "select * from feature_status_history where feature=?";
        List<FeatureStatusHistory> featureStatusHistories = new ArrayList<FeatureStatusHistory>();
        try (Connection con = DbConfig.connect();
             PreparedStatement preparedStatement = con.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, featureId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                FeatureStatusHistory featureStatusHistory = new FeatureStatusHistory.Builder()
                        .id(resultSet.getInt("id"))
                        .description(resultSet.getString("description"))
                        .status(FeatureStatus.valueOf(resultSet.getString("status")))
                        .timeOfChange(resultSet.getTimestamp("time_of_change").toLocalDateTime())
                        .build();
                featureStatusHistories.add(featureStatusHistory);
            }
        }
        return featureStatusHistories;
    }
}
