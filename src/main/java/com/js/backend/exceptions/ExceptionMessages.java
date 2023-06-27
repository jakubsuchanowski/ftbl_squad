package com.js.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExceptionMessages {
    USER_ALREADY_EXIST("User already exists!"),
    USER_DOES_NOT_EXIST("Users does not exists!"),
    USER_DOES_NOT_HAVE_ACCESS("User does not have access to this action"),
    EVENT_DOES_NOT_EXIST("Event does not exist");

    @Getter
    public final String code;
}
