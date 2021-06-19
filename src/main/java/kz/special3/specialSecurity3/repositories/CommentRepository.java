package kz.special3.specialSecurity3.repositories;

import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import kz.special3.specialSecurity3.entities.Art;
import kz.special3.specialSecurity3.entities.Comments;
import kz.special3.specialSecurity3.entities.Book;
import kz.special3.specialSecurity3.entities.SchoolSuplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comments,Long> {
    List<Comments> findAllByBookOrderByAddedDateDesc(Book books);
    List<Comments> findAllByArtOrderByAddedDateDesc(Art arts);
    List<Comments> findAllBySchoolSuppliesOrderByAddedDateDesc(SchoolSuplies schoolsupplies);
    void deleteAllBySchoolSupplies(SchoolSuplies scoolSupplies);
    void deleteAllByBook(Book book);
    void deleteAllByArt(Art art);
}

