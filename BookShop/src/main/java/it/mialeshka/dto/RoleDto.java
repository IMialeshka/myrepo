package it.mialeshka.dto;

import java.util.List;

public class RoleDto {
    private Long id;
    private String name;
    private List<UserShopDto> users;

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

    public List<UserShopDto> getUsers() {
        return null;
    }

    public void setUsers(List<UserShopDto> users) {
        this.users = users;
    }
}
