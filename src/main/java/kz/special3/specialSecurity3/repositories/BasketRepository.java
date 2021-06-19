package kz.special3.specialSecurity3.repositories;

import kz.special3.specialSecurity3.entities.Art;
import kz.special3.specialSecurity3.entities.Baskets;
import kz.special3.specialSecurity3.entities.SchoolSuplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BasketRepository extends JpaRepository<Baskets, Long> {
}
