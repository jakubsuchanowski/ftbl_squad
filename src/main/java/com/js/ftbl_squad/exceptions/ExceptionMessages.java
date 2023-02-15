package com.js.ftbl_squad.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExceptionMessages {
    EVENT_NOT_FOUND("Nie znaleziono wydarzenia!");

    @Getter
    public final String code;
}
