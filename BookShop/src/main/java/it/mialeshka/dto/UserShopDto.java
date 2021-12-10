package it.mialeshka.dto;

import java.util.List;


public class UserShopDto {
    private Long id;
    private String username;
    private String name;
    private String password;
    private List<RoleDto> roles;
    private List<BookDto> booksList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public List<BookDto> getBooksList() {
        return null;
    }

    public void setBooksList(List<BookDto> booksList) {
        this.booksList = booksList;
    }
}
