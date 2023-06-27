package com.js.backend.repository;

import com.js.backend.event.Event;
import com.js.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    List<Event> findAllByUser(User user);
}
