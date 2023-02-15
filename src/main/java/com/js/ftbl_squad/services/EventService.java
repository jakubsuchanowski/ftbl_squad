package com.js.ftbl_squad.services;

import com.js.ftbl_squad.entities.Event;
import com.js.ftbl_squad.entities.UserDao;
import com.js.ftbl_squad.repositories.EventRepository;
import com.js.ftbl_squad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> showAll(){
        return (List<Event>) eventRepository.findAll();
    }

    public void addEvent(Event event, Principal principal){
        UserDao loggedUser = userRepository.findByUsername(principal.getName());
        Event newEvent  = new Event(0L,event.getName(),event.getDate(),event.getAddress(),event.getPhoneNumber(), loggedUser);
        eventRepository.save(newEvent);
    }

    public void delete(Long id){
        Optional<Event> currentEvent = eventRepository.findById(id);
        eventRepository.delete(currentEvent.get());
    }

}
