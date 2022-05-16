package com.edu.hanu.cinematicketsystem.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private Long showId;
    private Date showDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long roomId;
    private String roomName;
    private List<Long> seatIds;
    private List<String> seatLocation;
    private List<ComboResponse> comboIds;

    private double calculateOrderPrice(){
        return 1;
    }
}
