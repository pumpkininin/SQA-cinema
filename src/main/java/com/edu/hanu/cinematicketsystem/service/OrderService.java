package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.*;
import com.edu.hanu.cinematicketsystem.model.composite.OrderComboId;
import com.edu.hanu.cinematicketsystem.payload.TicketRequest;
import com.edu.hanu.cinematicketsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.edu.hanu.cinematicketsystem.model.SeatType.STANDARD;
import static com.edu.hanu.cinematicketsystem.model.SeatType.VIP;

@Service
public class OrderService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private RoomSeatRepository roomSeatRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderComboRepository orderComboRepository;
    @Autowired
    private ComboRepository comboRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    public Order mapRequestToOrder(TicketRequest ticketRequest) {
        Order newOrder = new Order();
        Show show = showRepository.getById(ticketRequest.getShowId());
        Set<RoomSeat> roomSeatSet = ticketRequest.getSeatIds()
                .stream().map(id -> roomSeatRepository.getById(id))
                .collect(Collectors.toSet());
        Set<Room> roomSet = new HashSet<>();
        double ticketPrice = show.getShowSeats()
                .stream().filter(showSeat -> ticketRequest.getSeatIds().contains(showSeat.getId()))
                .mapToDouble(ShowSeat::getPrice)
                .sum();
        double comboPrice = ticketRequest.getCombo().stream()
                .mapToDouble(comboResponse -> comboResponse.getPrice() * comboResponse.getQuantity())
                .sum();
        double totalPrice = ticketPrice + comboPrice;
        roomSeatSet.forEach(seat -> {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShows(show);
            showSeat.setRoomSeat(seat);
            if(seat.getSeatType().equals(STANDARD)){
                showSeat.setPrice(100000);
            }else if(seat.getSeatType().equals(VIP)){
                showSeat.setPrice(130000);
            }else {
                showSeat.setPrice(240000);
            }
            showSeat.setStatus(SeatStatus.BOOKED);
            showSeatRepository.save(showSeat);
        });
        roomSet.add(show.getRoom());
        newOrder.setMovie(show.getMovie());
        newOrder.setRoomSeatsSet(roomSeatSet);
        newOrder.setRoomSet(roomSet);
        newOrder.setShow(show);
        newOrder.setTicketQuantity(ticketRequest.getSeatIds().size());
        newOrder.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(newOrder);
        Set<OrderCombo> orderCombos = ticketRequest.getCombo().stream().map(comboResponse -> {
            OrderCombo orderCombo = new OrderCombo();
            orderCombo.setId(new OrderComboId(savedOrder.getId(), comboResponse.getComboId()));
            orderCombo.setOrder(savedOrder);
            orderCombo.setCombo(comboRepository.getById(comboResponse.getComboId()));
            orderCombo.setQuantity(comboResponse.getQuantity());
            return orderComboRepository.save(orderCombo);
        }).collect(Collectors.toSet());
        newOrder.setOrderCombos(orderCombos);
        return newOrder;
    }
}
