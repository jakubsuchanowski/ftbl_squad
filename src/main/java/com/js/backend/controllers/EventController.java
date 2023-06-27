package com.js.backend.controllers;


import com.js.backend.event.Event;
import com.js.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAll(){
        return ResponseEntity.ok().body(eventService.showAll());
    }

    @GetMapping("/user/events")
    public ResponseEntity<List<Event>> getAllForUser(Principal principal){
        return ResponseEntity.ok().body(eventService.showAllForUser(principal));

    }

    @PostMapping("/events/add")
    public ResponseEntity<?> addEvent(@RequestBody Event event, Principal principal){
        eventService.createEvent(event,principal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/events/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable(value = "id") Long id, Principal principal){
        try {
            eventService.deleteEvent(id, principal);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/events/update/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable(value = "id") Long id, @RequestBody Event event, Principal principal){
        try{
            eventService.editEvent(id,event,principal);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
