package com.mmj.beprepared.service;

import com.mmj.beprepared.model.City;
import com.mmj.beprepared.model.Province;

import java.util.List;

public interface LocationService {

    List<Province> getAllProvinces();

    List<City> getAllCities();

    List<City> getAllCitiesByProvince(Long provinceId);

    Province getProvinceById(Long provinceId);

    City getCityById(Long cityId);
}
