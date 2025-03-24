package org.example.ProjectManagement.mapper;

import org.example.ProjectManagement.dto.FeatureDto;
import org.example.ProjectManagement.dto.ProjectDto;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.model.Feature;
import org.example.ProjectManagement.model.Project;
import org.example.ProjectManagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class EntityToDtoMapper {
    public static FeatureDto toFeatureDto(Feature feature) {
        return new FeatureDto(feature.getId(), feature.getName(), feature.getProject(), feature.getDeveloper(), feature.getPriority(), feature.getStatus(), feature.getDescription());
    }

    public static List<FeatureDto> toFeatureDtoList(List<Feature> features) {
        List<FeatureDto> featureDtos = new ArrayList<FeatureDto>();
        for (Feature feature : features) {
            featureDtos.add(toFeatureDto(feature));
        }
        return featureDtos;
    }

    public static ProjectDto toProjectDto(Project project) {
        return new ProjectDto(project.getId(), project.getName(), project.getProjectManager(), project.getTeamLead());
    }

    public static List<ProjectDto> toProjectDtoList(List<Project> projects) {
        List<ProjectDto> projectDto = new ArrayList<>();
        for (Project project : projects) {
            projectDto.add(toProjectDto(project));
        }
        return projectDto;
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(),user.getName(),user.getUsername(),user.getRole());
    }

    public static List<UserDto> toUserDtoList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(toUserDto(user));
        }
        return userDtos;
    }
}
