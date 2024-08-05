package com.mmj.beprepared.service;

import com.mmj.beprepared.model.Alert;

import java.util.List;

public interface AlertService {

    String createAlert(Alert alert, Long cityId, Long provinceId); /*Lanca uma alerta para um certo distrito e para um certa provincia*/

    List<Alert>getAllAlerts(); /*Lanca uma lista de alertas*/

    List<Alert>getAllActiveAlerts(); /*pega todas alertas que estao activas*/

    List<Alert>getAllAlertsByCityId(Long cityId); /*Pega todas alertas de uma certa provincia*/

    List<Alert>getAllAlertsByProvinceId(Long provinceId);/*pega uma alerta pelo Id da provincao*/

    Alert getAlertById(Long alertId); /*Pega uma alerta pelo Id*/

    String activeAlert(Long alertId); /*Metodo para tornar uma alertaactiva*/

}
