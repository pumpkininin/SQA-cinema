package com.edu.hanu.cinematicketsystem.repository;

import com.edu.hanu.cinematicketsystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
