package com.mmj.beprepared.service.impl;

import com.mmj.beprepared.model.City;
import com.mmj.beprepared.model.Province;
import com.mmj.beprepared.repository.CitizenRepository;
import com.mmj.beprepared.repository.CityRepository;
import com.mmj.beprepared.repository.ProvinceRepository;
import com.mmj.beprepared.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final ProvinceRepository provinceRepository;
    private final CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCities() {

        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCitiesByProvince(Long provinceId) {
        return cityRepository.findAllByProvinceId(provinceId);

    }

    @Override
    @Transactional(readOnly = true)
    public Province getProvinceById(Long provinceId) {
        return provinceRepository.findById(provinceId).orElseThrow(() ->
                new EntityNotFoundException("A provincia não foi encontrada!"));
    }

    @Override
    @Transactional(readOnly = true)
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(()->
                new EntityNotFoundException("O distrito não foi encontrado"));
    }
}
