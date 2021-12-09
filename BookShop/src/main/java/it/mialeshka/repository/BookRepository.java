package it.mialeshka.repository;

import it.mialeshka.entity.Book;
import it.mialeshka.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book as b join b.userShopList c where c.id = :id")
    List<Book>  userBooks(@Param("id") Long id);
}
