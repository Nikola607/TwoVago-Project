package personal.project.two_vago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.two_vago.models.entities.City;
import personal.project.two_vago.models.entities.enums.CityNameEnum;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(CityNameEnum name);
}
