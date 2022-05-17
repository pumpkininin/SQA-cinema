package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.Combo;
import com.edu.hanu.cinematicketsystem.repository.ComboRepository;
import com.edu.hanu.cinematicketsystem.response.ComboResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboService {

  @Autowired
  private ComboRepository comboRepository;

  public List<Combo> getAllCombo() {
    return comboRepository.findAll();
  }

  public List<Combo> getListByIds(List<Long> ids) {
    return ids.stream()
        .map(id -> comboRepository.getById(id))
        .collect(Collectors.toList());
  }

  public List<ComboResponse> getByMaps(Map<Long, Integer> comboMaps) {
    List<ComboResponse> responses = new ArrayList<>();
    for (Long id : comboMaps.keySet()) {
      ComboResponse comboResponse = new ComboResponse();
      Combo chosenCombo = comboRepository.getById(id);
      comboResponse.setComboId(id);
      comboResponse.setComboName(chosenCombo.getComboName());
      comboResponse.setPrice(chosenCombo.getPrice());
      comboResponse.setQuantity(comboMaps.get(id));
      responses.add(comboResponse);
    }
    return responses;
  }

  public double calculateComboPrice(Map<Long, Integer> comboIds) {
    return comboIds.entrySet().stream().mapToDouble(entry ->
        comboRepository.getById(entry.getKey()).getPrice() * entry.getValue()
    ).sum();
  }
}
