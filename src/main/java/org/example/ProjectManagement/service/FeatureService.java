package org.example.ProjectManagement.service;

import org.example.ProjectManagement.dao.FeatureDao;
import org.example.ProjectManagement.dto.FeatureDto;
import org.example.ProjectManagement.dto.ProjectDto;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.exception.ProjectNotFoundException;
import org.example.ProjectManagement.exception.UserNotFoundException;
import org.example.ProjectManagement.mapper.EntityToDtoMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FeatureService {
    private final FeatureDao featureDao = FeatureDao.getInstance();
    private final UserService userService = UserService.getInstance();
    private final ProjectService projectService = ProjectService.getInstance();
    private static final FeatureService featureService = new FeatureService();

    private FeatureService() {
    }

    public static FeatureService getInstance() {
        return featureService;
    }

    public void save(String name, int developerId, String priority, String leadUsername, String description) throws SQLException, IOException, ClassNotFoundException {
        UserDto userDto = userService.findById(developerId);
        try {
            UserDto leadBy = userService.findByUsername(leadUsername);
            ProjectDto projectDto = projectService.findByLeadId(leadBy.getId());
            featureDao.save(name, userDto, projectDto, priority, description);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("Incorrect username provided");
        }
    }

    public List<FeatureDto> findAllByLeadId(String username) throws SQLException, ClassNotFoundException, IOException, ProjectNotFoundException {
        UserDto lead = userService.findByUsername(username);
        ProjectDto project = projectService.findByLeadId(lead.getId());
        return  EntityToDtoMapper.toFeatureDtoList(featureDao.findAllByProjectId(project.getId()));
    }

    public List<FeatureDto> findAllByProjectId(int id) throws SQLException, ClassNotFoundException, IOException, ProjectNotFoundException {
        ProjectDto projectDto = projectService.findById(id);
        return  EntityToDtoMapper.toFeatureDtoList(featureDao.findAllByProjectId(projectDto.getId()));
    }

    public List<FeatureDto> findAllByDeveloperId(String username, String priority, List<String> statuses) throws SQLException, ProjectNotFoundException, IOException, ClassNotFoundException {
        UserDto developer = userService.findByUsername(username);
        return EntityToDtoMapper.toFeatureDtoList(featureDao.findAllByDeveloperId(developer.getId(), priority, statuses));
    }

    public FeatureDto findById(String id) throws SQLException, ClassNotFoundException, IOException,ProjectNotFoundException {
        int featureId = Integer.parseInt(id);
        return EntityToDtoMapper.toFeatureDto(featureDao.findById(featureId));
    }

    public void changeStatus(int featureId, String status) throws SQLException, ClassNotFoundException, IOException {
        featureDao.changeStatus(featureId, status);
    }
}
