package it.mialeshka.repository;

import it.mialeshka.entity.Book;
import it.mialeshka.entity.UserShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserShopRepository extends JpaRepository<UserShop, Long> {
    @Query("select b from UserShop b where b.username = :username")
    UserShop findUserByUserName(@Param("username") String username);
}
