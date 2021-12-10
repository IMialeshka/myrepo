package it.mialeshka.repository;

import it.mialeshka.entity.Role;
import it.mialeshka.entity.UserShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserShopRepository extends JpaRepository<UserShop, Long> {
    @Query("select u from UserShop u where u.username = :username")
    UserShop findUserByUserName(@Param("username") String username);

    @Query("select c from UserShop as u join u.roles c where u.id = :id")
    List<Role> findAllUserRole(@Param("id") Long id);
}
