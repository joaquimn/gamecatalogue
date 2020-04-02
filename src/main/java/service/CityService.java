package service;

import domain.*;
import repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

public class CityService {

    CityRepository cityRepository = new CityRepository();

    public List<City> listCity() {
        List<City> cities = new ArrayList<>();

        cities = cityRepository.listCity();

        return cities;
    }
}
