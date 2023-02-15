package com.js.ftbl_squad.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EventException extends RuntimeException{
    public EventException(String message){
        super(message);
    }
}
