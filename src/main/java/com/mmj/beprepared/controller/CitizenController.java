package com.mmj.beprepared.controller;

import com.mmj.beprepared.dto.request.CitizenRequestDto;
import com.mmj.beprepared.dto.response.CitizenResponseDto;
import com.mmj.beprepared.mapper.Mapper;
import com.mmj.beprepared.model.Citizen;
import com.mmj.beprepared.service.CitizenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/citizens")
@Tag(name = "3. Citizen Controller")
public class CitizenController {

    private  final Mapper mapper;
    private  final CitizenService citizenService;

    @PostMapping("/")
    public ResponseEntity<String> createCitizen(@Valid @RequestBody CitizenRequestDto citizenRequestDto) {
        return new ResponseEntity<>(citizenService.createCitizen(
                mapper.mapCitizenRequestToModel(citizenRequestDto),
                citizenRequestDto.getCityId()),
                HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CitizenResponseDto>> getAllCitizens() {/*Fazemos a busca de todos os cidadaos*/
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizens()
        ));
    }
    @GetMapping("/province")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CitizenResponseDto>> getAllCitizensByProvinceId(@RequestParam Long id){ /*Fazemos a busca de todos os cidadaos, numa determinda privincia*/
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizensByProvinceId(id)
        ));
    }

    @GetMapping("/city") /*Fazemos a busca de todos os cidadaos, numa determinada cidade*/
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CitizenResponseDto>> getAllCitizensByCity(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDtoList(
                citizenService.getAllCitizensCityId(id)
        ));
    }

    @GetMapping("/id") /*Fazemos a busca de todos os cidadaos, pelo seu id*/
    public  ResponseEntity<CitizenResponseDto> getCitizenById(@AuthenticationPrincipal Citizen citizen){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDto(
                citizenService.getCitizenById(citizen.getId())
        ));
    }
    @GetMapping("/{id}") /*Fazemos a busca de todos os cidadaos, pelo seu id*/
    public  ResponseEntity<CitizenResponseDto> getCitizenById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDto(
                citizenService.getCitizenById(id)
        ));
    }

    @PutMapping("/verify-account")  /*Fazemos a verificao de contas*/
    public ResponseEntity<String> verifyAccount(@RequestParam String otp){
        return ResponseEntity.ok(citizenService.verifyAccount(otp));
    }

    @PutMapping("/new-otp")
    public ResponseEntity<String> generateOTP(@RequestParam String phone){
        return  ResponseEntity.ok(citizenService.generateOTPForCitizen(phone));
    }




}

