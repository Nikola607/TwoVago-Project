package personal.project.two_vago.models.entities;

import personal.project.two_vago.models.entities.enums.CityNameEnum;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "cities")
public class City extends BaseEntity{

    private CityNameEnum name;
    private Set<Offer> offers;

    public City() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public CityNameEnum getName() {
        return name;
    }

    public void setName(CityNameEnum name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
