package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.model.Combo;
import com.edu.hanu.cinematicketsystem.model.RoomSeat;
import com.edu.hanu.cinematicketsystem.model.Show;
import com.edu.hanu.cinematicketsystem.payload.TicketRequest;
import com.edu.hanu.cinematicketsystem.response.ComboResponse;
import com.edu.hanu.cinematicketsystem.response.TicketResponse;
import com.edu.hanu.cinematicketsystem.service.ComboService;
import com.edu.hanu.cinematicketsystem.service.RoomSeatService;
import com.edu.hanu.cinematicketsystem.service.RoomService;
import com.edu.hanu.cinematicketsystem.service.ShowService;
import io.jsonwebtoken.lang.Collections;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    private RoomSeatService seatService;
    @Autowired
    private ComboService comboService;

    @PostMapping
    public ResponseEntity<TicketResponse> fulfilTicketRequest(@RequestBody TicketRequest request){
        Long showID = request.getShowId();
        List<Long> seatIDs = request.getSeatIds();
        Map<Long, Integer> comboIds = request.getCombo();
        TicketResponse response = new TicketResponse();
        if (Objects.nonNull(showID)){
            Show chosenShow = showService.getById(showID);
            response.setShowId(showID);
            response.setEndTime(chosenShow.getEndTime());
            response.setShowDate(chosenShow.getDate());
            response.setStartTime(chosenShow.getStartTime());
            response.setRoomId(chosenShow.getRoom().getId());
            response.setRoomName(chosenShow.getRoom().getRoomName());
        }
        if (Objects.nonNull(seatIDs) && !Collections.isEmpty(seatIDs)){
            response.setSeatIds(seatIDs);
            response.setSeatLocation(seatService.getLocationByIds(seatIDs));
        }
        if (Objects.nonNull(comboIds) && !Collections.isEmpty(comboIds) ){
            List<ComboResponse> comboRes = comboService.getByMaps(comboIds);
            response.setComboIds(comboRes);
        }
        return ResponseEntity.ok().body(response);
    }
}
