package it.mialeshka.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class UserShop implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long idUser;
   private String username;
   private String name;
   private String password;
   @ManyToMany(fetch = FetchType.EAGER)
   private List<Role> roles;
   /*   @ManyToMany(fetch = FetchType.LAZY)
   private List<Book> booksList;*/

   public List<Role> getRoles() {
      return roles;
   }

   public void setRoles(List<Role> roles) {
      this.roles = roles;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public void setIdUser(Long idUser) {
      this.idUser = idUser;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setPassword(String password) {
      this.password = password;
   }

/*   public void setBooksList(List<Book> booksList) {
      this.booksList = booksList;
   }*/

   public Long getIdUser() {
      return idUser;
   }

   public String getName() {
      return name;
   }

/*   public List<Book> getBooksList() {
      return booksList;
   }*/
}
