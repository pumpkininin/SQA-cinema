package com.edu.hanu.cinematicketsystem.response;

import com.edu.hanu.cinematicketsystem.model.Order;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderResponse {
    private long orderId;
    private String movieTitle;
    private String orderDate;
    private List<String> seatLocations;
    private List<ComboResponse> combo;
    private double totalPrice;
}
