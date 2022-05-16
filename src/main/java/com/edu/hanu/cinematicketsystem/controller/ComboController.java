package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.model.Combo;
import com.edu.hanu.cinematicketsystem.model.Movie;
import com.edu.hanu.cinematicketsystem.service.ComboService;
import com.edu.hanu.cinematicketsystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/staff/combo")
@CrossOrigin
public class ComboController {
    @Autowired
    ComboService comboService;

    @GetMapping
    public ResponseEntity<List<Combo>> getAllMovie(){
        return ResponseEntity.ok().body(comboService.getAllCombo());
    }


}

