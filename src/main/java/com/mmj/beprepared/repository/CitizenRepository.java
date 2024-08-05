package com.mmj.beprepared.repository;

import com.mmj.beprepared.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long>{
   boolean existsByPhone(String phone); /*Metodo para verificar se existe um cidadio com o numero*/

   Optional<Citizen> findByPhone(String phone);

   Optional<Citizen> findByOtp(String opt); /*Lanca uma excessao, caso nao encontre. caso encontre vai lancar o citizen*/

   List<Citizen> findAllByCityId(Long cityId);

   List<Citizen> findAllByCityProvinceId(Long provinceId);


}
