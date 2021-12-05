package personal.project.two_vago.service;

import org.springframework.stereotype.Service;
import personal.project.two_vago.models.entities.City;
import personal.project.two_vago.models.entities.enums.CityNameEnum;

import java.util.List;

@Service
public interface CityService {
    void initializeRoles();

    City findByCityName(CityNameEnum city);
}
