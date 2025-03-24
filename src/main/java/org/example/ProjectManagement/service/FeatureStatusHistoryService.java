package org.example.ProjectManagement.service;

import org.example.ProjectManagement.dao.FeatureStatusHistoryDao;
import org.example.ProjectManagement.enums.FeatureStatus;
import org.example.ProjectManagement.model.FeatureStatusHistory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class FeatureStatusHistoryService {
    private final FeatureStatusHistoryDao featureStatusHistoryDao=FeatureStatusHistoryDao.getInstance();
    private final FeatureService featureService=FeatureService.getInstance();
    private static final FeatureStatusHistoryService featureStatusHistoryService=new FeatureStatusHistoryService();
    private FeatureStatusHistoryService(){}
    public static FeatureStatusHistoryService getInstance(){
        return featureStatusHistoryService;
    }
    public void save(String id,String description, FeatureStatus status) throws SQLException, ClassNotFoundException , IOException {
        int featureId=Integer.parseInt(id);
        LocalDateTime now=LocalDateTime.now();
        featureStatusHistoryDao.save(featureId,description,status.toString(),now);
        featureService.changeStatus(featureId,status.toString());
    }

    public List<FeatureStatusHistory> findByFeatureId(int featureId) throws SQLException, ClassNotFoundException,IOException {
        return featureStatusHistoryDao.findByFeatureId(featureId);
    }
}
