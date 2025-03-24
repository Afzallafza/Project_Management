package org.example.ProjectManagement.service;

import org.example.ProjectManagement.dao.ProjectDao;
import org.example.ProjectManagement.dto.ProjectDto;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.exception.ProjectNotFoundException;
import org.example.ProjectManagement.mapper.EntityToDtoMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    private final ProjectDao projectDao = ProjectDao.getInstance();
    private final UserService userService = UserService.getInstance();
    private static final ProjectService projectService = new ProjectService();

    private ProjectService() {
    }

    public static ProjectService getInstance() {
        return projectService;
    }

    public void save(String name, int managerId, int leadId) throws SQLException, ClassNotFoundException, IOException {
        projectDao.save(name, managerId, leadId);
    }

    public ProjectDto findByLeadId(int id) throws SQLException, ClassNotFoundException, IOException {
        return EntityToDtoMapper.toProjectDto(projectDao.findByLeadId(id));
    }

    public List<ProjectDto> findAllByManagerUsername(String username) throws SQLException, ClassNotFoundException, IOException {
        UserDto manager = userService.findByUsername(username);
        return EntityToDtoMapper.toProjectDtoList(projectDao.findAllByManagerId(manager.getId()));

    }

    public ProjectDto findById(int id) throws SQLException, ClassNotFoundException, ProjectNotFoundException, IOException {
        return EntityToDtoMapper.toProjectDto(projectDao.findById(id));
    }
}
