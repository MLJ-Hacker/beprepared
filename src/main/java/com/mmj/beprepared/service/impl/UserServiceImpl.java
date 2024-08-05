package com.mmj.beprepared.service.impl;

import com.mmj.beprepared.dto.response.StatsResponse;
import com.mmj.beprepared.exception.BadRequestException;
import com.mmj.beprepared.exception.EntityNotFoundException;
import com.mmj.beprepared.model.City;
import com.mmj.beprepared.model.User;
import com.mmj.beprepared.model.enums.Role;
import com.mmj.beprepared.repository.AlertRepository;
import com.mmj.beprepared.repository.CitizenRepository;
import com.mmj.beprepared.repository.UserRepository;
import com.mmj.beprepared.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncode;
   private final UserRepository userRepository;
   private final AlertRepository alertRepository;
   private final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public String createUser(User user) {
      if (userRepository.existsByEmail(user.getEmail())){
          throw new BadRequestException("Já existe um usuario com esse email!");
      }
      user.setRole(Role.ADMIN);
      user.setPassword(passwordEncode.encode(user.getPassword()));
      userRepository.save(user);
      return "Usuario criado com sucesso!";
    }

    @Override
    @Transactional(readOnly = true)
    public User getUSerById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Usuario não encontrado!"));
    }

    @Override
    @Transactional(readOnly = true)
    public StatsResponse getAllStats() {
        return  StatsResponse.builder()
                .citizens(citizenRepository.count())
                .totalAlerts(alertRepository.count())
                .activeAlerts(alertRepository.countByActive(true))
                .build();
    }
}
