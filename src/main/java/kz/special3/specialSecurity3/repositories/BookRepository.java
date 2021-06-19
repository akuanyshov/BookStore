package kz.special3.specialSecurity3.repositories;

import kz.special3.specialSecurity3.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByPriceIsGreaterThanEqual(int price);
    Book findByIdAndPriceIsGreaterThanEqual(Long id, int price);

}
