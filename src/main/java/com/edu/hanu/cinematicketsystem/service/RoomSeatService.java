package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.Room;
import com.edu.hanu.cinematicketsystem.model.RoomSeat;
import com.edu.hanu.cinematicketsystem.repository.RoomRepository;
import com.edu.hanu.cinematicketsystem.repository.RoomSeatRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomSeatService {

  @Autowired
  private RoomSeatRepository seatRepository;
  @Autowired
  private RoomRepository roomRepository;


  public RoomSeat getById(Long id) {
    return seatRepository.getById(id);
  }

  public List<RoomSeat> getListByIds(List<Long> ids) {
    List<RoomSeat> seats = new ArrayList<>();
    for (Long id : ids) {
      seats.add(seatRepository.getById(id));
    }
    return seats;
  }

  public List<String> getLocationByIds(List<Long> ids) {
    return getListByIds(ids).stream().map(roomSeat -> roomSeat.getSeatLocation())
        .collect(Collectors.toList());
  }
  public List<RoomSeat> getListByRoomId(Long roomId){
    return new ArrayList<>(roomRepository.findById(roomId).get().getRoomSeats());
  }

}
