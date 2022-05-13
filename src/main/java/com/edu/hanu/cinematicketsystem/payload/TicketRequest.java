package com.edu.hanu.cinematicketsystem.payload;

import java.util.List;


import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TicketRequest {
  private Long movieId;
  private Long showId;
  private Long roomIds;
  private int quantity;
  private List<Long> seatId;
  private Map<Long, Integer> comboMap;
}
