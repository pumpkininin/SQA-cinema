package com.edu.hanu.cinematicketsystem.payload;

import com.edu.hanu.cinematicketsystem.response.ComboResponse;

import java.util.ArrayList;
import java.util.List;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
  private Long showId;
  private List<Long> seatIds;
  @JsonProperty(value = "comboIds")
  private List<ComboResponse> combo = new ArrayList<>();
  private String process;

  @Override
  public String toString() {
    return super.toString();
  }
}
