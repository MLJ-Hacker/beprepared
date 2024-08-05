package com.mmj.beprepared.service.impl;

import com.mmj.beprepared.exception.BadRequestException;
import com.mmj.beprepared.exception.EntityNotFoundException;
import com.mmj.beprepared.model.Citizen;
import com.mmj.beprepared.model.City;
import com.mmj.beprepared.model.enums.Role;
import com.mmj.beprepared.repository.CitizenRepository;
import com.mmj.beprepared.service.CitizenService;
import com.mmj.beprepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sqm.EntityTypeException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private  final PasswordEncoder passwordEncoder;
    private final LocationService locationService;
    private final CitizenRepository CitizenRepository;
    private final CitizenRepository citizenRepository;


    @Override
    @Transactional
    public String createCitizen(Citizen citizen, Long cityId) {
        if (citizenRepository.existsByPhone(citizen.getPhone())){
            throw new BadRequestException("Jã  ha um cidadao com esse nú numero!");
        }
        City city = locationService.getCityById(cityId);
        citizen.setCity(city);
        citizen.setVerified(false);
        citizen.setRole(Role.USER);
        citizen.setOtp(generateOtp(6));
        var savedCitizen = CitizenRepository.save(citizen);
        return "Cidadão creiadp com sucesso! O seu código de verificação é: "+savedCitizen.getOtp();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensCityId(Long cityId) {
        return citizenRepository.findAllByCityId(cityId);
    }

    @Override
    @Transactional (readOnly = true)
    public List<Citizen> getAllCitizensByProvinceId(Long provinceId) {
        return citizenRepository.findAllByCityProvinceId (provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Citizen getCitizenById(Long id) {
        return citizenRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Cidadão não encontrado!"));
    }

    @Override
    @Transactional
    public String verifyAccount(String otp) {
        Citizen citizen = citizenRepository.findByOtp(otp).orElseThrow(()->
            new EntityNotFoundException("Cidadão não encontrado!"));
        citizen.setVerified(true);
        citizen.setOtp(null);
        citizenRepository.save(citizen);
        return "A sua conta for verificada com sucesso!";
    }

    @Override
    @Transactional
    public String generateOTPForCitizen(String phone) {
        Citizen citizen = citizenRepository.findByPhone(phone).orElseThrow(() ->
                new EntityNotFoundException("Cidadão não encontrado, não foi possivel gerar o seu OTP!"));
        citizen.setOtp(null);
        String otp = generateOtp(6);
        citizen.setOtp(passwordEncoder.encode(otp));
        return "O seu código de acesso é: "+otp;
    }

    private  static String generateOtp(int lengh){ /*Metodo para gerar um numero aleatorio*/
        String otp = "";
        int x;
        char[] chars = new char[lengh];

        for (int i = 0; i < lengh; i++){
            Random random = new Random();
            x = random.nextInt(6);
            chars[i] = Integer.toString(x).toCharArray()[0];
        }

        otp = new String(chars);
        return otp.trim();
    }


}
