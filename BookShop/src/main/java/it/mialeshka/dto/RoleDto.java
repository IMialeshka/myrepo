package it.mialeshka.dto;

import it.mialeshka.entity.UserShop;

import java.util.List;

public class RoleDto {
    private Long id;
    private String name;
    private List<UserShop> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserShop> getUsers() {
        return users;
    }

    public void setUsers(List<UserShop> users) {
        this.users = users;
    }
}
