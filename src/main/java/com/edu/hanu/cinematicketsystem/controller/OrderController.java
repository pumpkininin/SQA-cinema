package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.model.Order;
import com.edu.hanu.cinematicketsystem.payload.TicketRequest;
import com.edu.hanu.cinematicketsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/order-fulfill")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> fulfilOrder(@RequestBody TicketRequest ticketRequest){
        return ResponseEntity.ok().body(orderService.mapRequestToOrder(ticketRequest));
    }
}
