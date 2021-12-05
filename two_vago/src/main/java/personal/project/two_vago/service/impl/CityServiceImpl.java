package personal.project.two_vago.service.impl;

import org.springframework.stereotype.Component;
import personal.project.two_vago.models.entities.City;
import personal.project.two_vago.models.entities.enums.CityNameEnum;
import personal.project.two_vago.repository.CityRepository;
import personal.project.two_vago.service.CityService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void initializeRoles() {
        if (cityRepository.count() != 0) {
            return;
        }

        Arrays.stream(CityNameEnum.values())
                .forEach(cityNameEnum -> {
                            City city = new City();
                            city.setName(cityNameEnum);

                            cityRepository.save(city);
                        }
                );
    }

    @Override
    public City findByCityName(CityNameEnum city) {
        return cityRepository.findByName(city)
                .orElse(null);
    }
}
