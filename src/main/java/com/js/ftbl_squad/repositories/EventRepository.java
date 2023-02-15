package com.js.ftbl_squad.repositories;

import com.js.ftbl_squad.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);
}
