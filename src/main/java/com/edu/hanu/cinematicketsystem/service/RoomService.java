package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.Room;
import com.edu.hanu.cinematicketsystem.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    //get all rooms
    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }
    // create a new room
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    //update a room
    public Room updateRoom(Long id, Room room) {


        //get existing room in db
        Room existingRoom = this.roomRepository.getById(id);
        //copy attributes from new room to existingRoom
        BeanUtils.copyProperties(room, existingRoom, "id");
        return this.roomRepository.saveAndFlush(existingRoom);
    }

    //delete room
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    //get room by id
    public Room getById(Long id) {
        return this.roomRepository.getById(id);
    }

}
