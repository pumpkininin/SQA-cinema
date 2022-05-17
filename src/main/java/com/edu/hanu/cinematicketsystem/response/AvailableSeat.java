package com.edu.hanu.cinematicketsystem.response;

import com.edu.hanu.cinematicketsystem.model.SeatStatus;
import com.edu.hanu.cinematicketsystem.model.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSeat {
  private Long id;

  private String seatLocation;

  private SeatType seatType;

  private SeatStatus status;
}
