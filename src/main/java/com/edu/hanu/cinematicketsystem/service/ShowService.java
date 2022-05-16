package com.edu.hanu.cinematicketsystem.service;


import com.edu.hanu.cinematicketsystem.model.Room;
import com.edu.hanu.cinematicketsystem.model.RoomSeat;
import com.edu.hanu.cinematicketsystem.model.Show;
import com.edu.hanu.cinematicketsystem.model.ShowSeat;
import com.edu.hanu.cinematicketsystem.repository.RoomRepository;
import com.edu.hanu.cinematicketsystem.repository.ShowRepository;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

  @Autowired
  private ShowRepository showRepository;


  public List<Show> getAllShow() {
    return showRepository.findAll();
  }

  public Show addShow(Show show) {
    return showRepository.save(show);
  }

  public Show updateShow(Long id, Show show) {
    Show existingShow = this.showRepository.getById(id);
    BeanUtils.copyProperties(show, existingShow, "id");
    return this.showRepository.saveAndFlush(existingShow);
  }

  public void deleteShow(Long id) {
    showRepository.deleteById(id);
  }

  public Show getById(Long id) {
    return this.showRepository.getById(id);
  }

  public List<RoomSeat> getUnavailableSeatById(long showId) {
    Show show = showRepository.findById(showId).get();
    return show.getShowSeats().stream().map(ShowSeat::getRoomSeat)
        .collect(Collectors.toList());
  }
}
