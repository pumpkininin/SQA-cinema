package com.edu.hanu.cinematicketsystem.payload;

import java.util.List;


import java.util.Map;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
  private Long showId;
  private List<Long> seatIds;
  private Map<Long, Integer> combo;
  private String process;

  @Override
  public String toString() {
    return super.toString();
  }
}
