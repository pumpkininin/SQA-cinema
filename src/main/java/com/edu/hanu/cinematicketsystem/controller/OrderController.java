package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.model.Order;
import com.edu.hanu.cinematicketsystem.payload.TicketRequest;
import com.edu.hanu.cinematicketsystem.response.OrderResponse;
import com.edu.hanu.cinematicketsystem.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/staff/order-fulfill")
@CrossOrigin
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping
  public ResponseEntity<Order> fulfilOrder(@RequestBody TicketRequest ticketRequest) {
    return ResponseEntity.ok().body(orderService.mapRequestToOrder(ticketRequest));
  }

  @PostMapping("/checkout")
  public ResponseEntity checkout(@RequestBody TicketRequest ticketRequest) {
    return ResponseEntity.ok().body(orderService.checkOut(ticketRequest));
  }

  @GetMapping(params = {"page", "size"})
  public ResponseEntity<List<OrderResponse>> getPaginatedOrder(@RequestParam("page") int page,
      @RequestParam("size") int size, UriComponentsBuilder uriComponentsBuilder){
    List<OrderResponse> resultRes = orderService.findPaginatedOrder(page, size);
    return ResponseEntity.ok(resultRes);
  }
}
