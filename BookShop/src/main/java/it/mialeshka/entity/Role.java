package it.mialeshka.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
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

    @Override
    public String getAuthority() {
        return getName();
    }


}
