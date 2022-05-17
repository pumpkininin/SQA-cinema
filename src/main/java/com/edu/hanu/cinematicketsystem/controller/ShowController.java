package com.edu.hanu.cinematicketsystem.controller;


import com.edu.hanu.cinematicketsystem.model.RoomSeat;
import com.edu.hanu.cinematicketsystem.model.Show;
import com.edu.hanu.cinematicketsystem.response.AvailableSeat;
import com.edu.hanu.cinematicketsystem.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/staff/show")
@CrossOrigin
public class ShowController {
    @Autowired
    ShowService showService;

    @GetMapping
    public ResponseEntity<List<Show>> getAllShow(){
        return ResponseEntity.ok().body(showService.getAllShow());
    }

    @GetMapping("{showId}")
    public  ResponseEntity<Show> getShowById(@PathVariable long showId) {
        return ResponseEntity.ok().body(showService.getById(showId));
    }

    @GetMapping("/unavailableSeat/{showId}")
    public ResponseEntity<List<AvailableSeat>> getSeatByShowId(@PathVariable long showId){
        return ResponseEntity.ok().body(showService.getAvailableSeatById(showId));
    }
    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return this.showService.addShow(show);
    }

    @PutMapping("{showId}")
    public Show updateShow(@PathVariable Long showId, @RequestBody Show show) {
        return this.showService.updateShow(showId,show);
    }


    @DeleteMapping("{showId}")
    public void deleteRoom(@PathVariable Long showId) {
        this.showService.deleteShow(showId);
    }

}
