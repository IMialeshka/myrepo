package it.mialeshka.dto;
import it.mialeshka.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class UserShopDto {
    private Long idUser;
    private String username;
    private String name;
    private String password;
    private List<Role> roles;
/*    private List<BookDto> booksList;*/

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

/*    public List<BookDto> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<BookDto> booksList) {
        this.booksList = booksList;
    }*/
}
