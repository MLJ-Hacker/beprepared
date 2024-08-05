package com.mmj.beprepared.service;

import com.mmj.beprepared.model.Citizen;
import org.hibernate.dialect.function.array.AbstractArrayTrimFunction;

import java.util.List;

public interface CitizenService {

    String createCitizen(Citizen citizen, Long cityId);

    List<Citizen> getAllCitizens();

    List<Citizen> getAllCitizensCityId(Long cityId);

    List<Citizen> getAllCitizensByProvinceId(Long provinceId);

    Citizen getCitizenById(Long id);

    String verifyAccount(String otp);

    String generateOTPForCitizen(String phone);

}
