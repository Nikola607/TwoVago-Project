package personal.project.two_vago.models.binding;

import personal.project.two_vago.models.entities.enums.CategoryNameEnum;
import personal.project.two_vago.models.entities.enums.CityNameEnum;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OfferUpdateBindingModel {
    private Long id;

    private String offerName;
    private BigDecimal price;
    private String picture;
    private String description;
    @NotNull(message = "you must select a category")
    private CategoryNameEnum category;
    @NotNull(message = "you must select a city")
    private CityNameEnum city;

    public OfferUpdateBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }

    public CityNameEnum getCity() {
        return city;
    }

    public void setCity(CityNameEnum city) {
        this.city = city;
    }
}
