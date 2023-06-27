package com.js.backend.service;


import com.js.backend.event.Event;
import com.js.backend.exceptions.ExceptionMessages;
import com.js.backend.repository.EventRepo;
import com.js.backend.repository.UserRepo;
import com.js.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Event> showAll(){
        return (List<Event>) eventRepo.findAll();
    }
    public List<Event> showAllForUser(Principal principal){
        Optional<User> loggedUser = userRepo.findByEmail(principal.getName());

        return (List<Event>) eventRepo.findAllByUser(loggedUser.get());

    }

    public void createEvent(Event event, Principal principal){
        Optional<User> loggedUser = userRepo.findByEmail(principal.getName());
        Event newEvent = new Event(0L, event.getName(), event.getDate(),event.getPhoneNumber(),event.getAddress(),loggedUser.get());
        eventRepo.save(newEvent);
    }

    public void deleteEvent(Long id, Principal principal) throws Exception {
        Optional<User> loggedUser = userRepo.findByEmail(principal.getName());
        Optional<Event> eventToDelete = eventRepo.findById(id);
        if(eventToDelete.get().getUser().equals(loggedUser.get())) {
            eventRepo.delete(eventToDelete.get());
        }else throw new Exception(ExceptionMessages.USER_DOES_NOT_HAVE_ACCESS.getCode());
    }

    public void editEvent(Long id, Event event, Principal principal) throws Exception {
        Optional<User> loggedUser = userRepo.findByEmail(principal.getName());
        Optional<Event> eventToEdit = eventRepo.findById(id);
        if(eventToEdit.get().getUser().equals(loggedUser.get())){
            if(eventToEdit.isPresent()){
                eventToEdit.get().setName(event.getName());
                eventToEdit.get().setDate(event.getDate());
                eventToEdit.get().setPhoneNumber(event.getPhoneNumber());
                eventToEdit.get().setAddress(event.getAddress());
                eventRepo.save(eventToEdit.get());
            }
            else throw new Exception(ExceptionMessages.EVENT_DOES_NOT_EXIST.getCode());
        }
        else throw new Exception(ExceptionMessages.USER_DOES_NOT_HAVE_ACCESS.getCode());
    }
}
