package com.tinkoff.web.blackbooks.server.domain.dao.entry;


import lombok.Getter;

@Getter
public class UserProfileEntry extends BaseEntry {

    private String nick;
    private String name;
    private int age;
    private String gender;
    private String location;

    public UserProfileEntry(String nick, String name, int age, String gender, String location) {
        this.nick = nick;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }
}

