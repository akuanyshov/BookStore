package kz.special3.specialSecurity3.repositories;

import kz.special3.specialSecurity3.entities.Book;
import kz.special3.specialSecurity3.entities.SchoolSuplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface SchoolSupliesRepository extends JpaRepository<SchoolSuplies,Long> {
    List<SchoolSuplies> findAllByPriceIsGreaterThanEqual(int price);
    SchoolSuplies findByIdAndPriceIsGreaterThanEqual(Long id, int price);

}
