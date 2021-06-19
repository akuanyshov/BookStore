package kz.special3.specialSecurity3.repositories;

import kz.special3.specialSecurity3.entities.Art;
import kz.special3.specialSecurity3.entities.SchoolSuplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface ArtRepository extends JpaRepository<Art,Long> {
    List<Art> findAllByPriceIsGreaterThanEqual(int price);
    Art findByIdAndPriceIsGreaterThanEqual(Long id, int price);

}
