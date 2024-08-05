package com.mmj.beprepared.repository;

import com.mmj.beprepared.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>{


    List<Alert> findAllByActive(boolean isActive);

    List<Alert> findAllByActiveAndCityId(boolean isActive, Long cityId); /*Encontra todos alertas activos pelo id da cidade*/

    List<Alert> findAllByActiveAndProvinceId(boolean isActive, Long provinceId);

    long countByActive(boolean isActive);
}
