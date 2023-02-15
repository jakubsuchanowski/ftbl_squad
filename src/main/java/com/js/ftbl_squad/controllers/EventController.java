package com.js.ftbl_squad.controllers;

import com.js.ftbl_squad.entities.Event;
import com.js.ftbl_squad.services.EventService;
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
    public ResponseEntity<List<Event>> showAll(){
        return ResponseEntity.ok().body(eventService.showAll());
    }

    @PostMapping("/events/add")
    public ResponseEntity<?> addEvent(@RequestBody Event event, Principal principal){
        eventService.addEvent(event,principal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/events/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable Long id){
        eventService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/events/edit/{id}")
    public ResponseEntity editEvent(@PathVariable Long id, @RequestBody Event event){
        eventService.edit(id, event);
        return ResponseEntity.ok().build();
    }

}
