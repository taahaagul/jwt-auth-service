package com.taahaagul.tgjwtsecurity.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    TG_READ("tg:read"),
    TG_UPDATE("tg:update"),
    TG_CREATE("tg:create"),
    TG_DELETE("tg:delete"),
    ;

    @Getter
    private final String permission;
}