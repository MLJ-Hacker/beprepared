package com.mmj.beprepared.service;

import com.mmj.beprepared.dto.response.StatsResponse;
import com.mmj.beprepared.model.City;
import com.mmj.beprepared.model.User;

public interface UserService {

    String createUser(User user);

    User getUSerById(Long id);

    StatsResponse getAllStats();




}