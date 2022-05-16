package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.Combo;
import com.edu.hanu.cinematicketsystem.repository.ComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboService {
    @Autowired
    private ComboRepository comboRepository;

    public List<Combo> getAllCombo(){
        return comboRepository.findAll();
    }
}
