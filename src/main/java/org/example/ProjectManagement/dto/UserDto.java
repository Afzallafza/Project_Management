package org.example.ProjectManagement.dto;

public class UserDto {
    private int id;
    private String username;
    private String name;
    private String role;
    public int getId() {
        return id;
    }

    public UserDto(int id, String name, String username, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
