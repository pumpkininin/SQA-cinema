package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.payload.TicketRequest;
import com.edu.hanu.cinematicketsystem.response.TicketResponse;
import com.edu.hanu.cinematicketsystem.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/ticket-fulfill")
@CrossOrigin
public class TicketController {

    @Autowired
    private ShowService showService;
    @Autowired
    private Roo

    @PostMapping
    public ResponseEntity<TicketResponse> fulfilTicketRequest(@RequestBody TicketRequest request){

    }
}
