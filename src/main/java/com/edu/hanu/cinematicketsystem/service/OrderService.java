package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.*;
import com.edu.hanu.cinematicketsystem.model.composite.OrderComboId;
import com.edu.hanu.cinematicketsystem.payload.TicketRequest;
import com.edu.hanu.cinematicketsystem.repository.*;
import com.edu.hanu.cinematicketsystem.response.ComboResponse;
import com.edu.hanu.cinematicketsystem.response.OrderResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
        newOrder.setRoomSeatsSet(ticketRequest.getSeatIds().stream().map(seatId -> roomSeatRepository.getById(seatId)).collect(
            Collectors.toSet()));
        return newOrder;

    }
    public Order checkOut(TicketRequest request){
        Order newOrder = mapRequestToOrder(request);
        Order savedOrder = orderRepository.save(newOrder);
        Set<OrderCombo> orderCombos = request.getCombo().stream().map(comboResponse -> {
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

    public List<OrderResponse> findPaginatedOrder(int page, int size) {
        PageRequest pageReq
            = PageRequest.of(page-1  , size, Direction.ASC, "id");
        Page<Order> orders = orderRepository.findAll(pageReq);
        return orders.stream().map(order -> {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(order.getId());
            orderResponse.setOrderDate(order.getShow().getDate().toString().substring(0, 14));
            List<ComboResponse> comboResponseList = new ArrayList<>();
            order.getOrderCombos()
                .stream()
                .filter(orderCombo -> Objects.nonNull(orderCombo.getCombo()))
                .findFirst()
                .ifPresent(orderCombo -> {
                    Combo combo = orderCombo.getCombo();
                    ComboResponse comboResponse = new ComboResponse(combo.getComboId(), combo.getComboName(), combo.getPrice(), orderCombo.getQuantity());
                    comboResponseList.add(comboResponse);
                });
            orderResponse.setCombo(comboResponseList);
            orderResponse.setMovieTile(order.getMovie().getTitle());
            orderResponse.setTotalPrice(order.getTotalPrice());
            orderResponse.setSeatLocations(
                order.getRoomSeatsSet()
                    .stream().map(RoomSeat::getSeatLocation)
                    .collect(Collectors.toList()));
            return orderResponse;
        }).collect(Collectors.toList());
    }
}
