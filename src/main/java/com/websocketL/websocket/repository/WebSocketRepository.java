package com.websocketL.websocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websocketL.websocket.entity.Sensor;

@Repository
public interface WebSocketRepository extends JpaRepository<Sensor, Long> {

}
