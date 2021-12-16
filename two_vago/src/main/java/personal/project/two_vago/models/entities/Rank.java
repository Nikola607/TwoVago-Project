package personal.project.two_vago.models.entities;

import personal.project.two_vago.models.entities.enums.RankNameEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "ranks")
public class Rank extends BaseEntity{
    private RankNameEnum rankName;

    public Rank() {
    }

    @Enumerated(EnumType.STRING)
    public RankNameEnum getRankName() {
        return rankName;
    }

    public void setRankName(RankNameEnum rankName) {
        this.rankName = rankName;
    }
}
