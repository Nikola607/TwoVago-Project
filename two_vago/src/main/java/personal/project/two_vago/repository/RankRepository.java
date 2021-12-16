package personal.project.two_vago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.two_vago.models.entities.Rank;
import personal.project.two_vago.models.entities.enums.RankNameEnum;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    Rank findByRankName(RankNameEnum rank_name);
}
