package com.edu.hanu.cinematicketsystem.controller;


import com.edu.hanu.cinematicketsystem.model.Movie;
import com.edu.hanu.cinematicketsystem.model.Room;
import com.edu.hanu.cinematicketsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/staff/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRoom(){
        return ResponseEntity.ok().body(roomService.getAllRoom());
    }

    @GetMapping("{roomId}")
    public Room getRoomById(@PathVariable long roomId) {
        return this.roomService.getById(roomId);
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return this.roomService.addRoom(room);
    }

    @PutMapping("{roomId}")
    public Room updateRoom(@PathVariable Long roomId, @RequestBody Room room) {
        return this.roomService.updateRoom(roomId,room);
    }


    @DeleteMapping("{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        this.roomService.deleteRoom(roomId);
    }

}
