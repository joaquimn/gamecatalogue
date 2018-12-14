package service;
import domain.Province;
import repository.ProvinceRepository;

import java.util.ArrayList;
import java.util.List;

public class ProvinceService {
    ProvinceRepository provinceRepository = new ProvinceRepository();

    public List<Province> listProvinces(){
        List<Province> provinces = new ArrayList<>();

        provinces = provinceRepository.listProvinces();

        return provinces;
    }
}
