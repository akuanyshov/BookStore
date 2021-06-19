package kz.special3.specialSecurity3.repositories;

import kz.special3.specialSecurity3.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Countries,Long> {
}
