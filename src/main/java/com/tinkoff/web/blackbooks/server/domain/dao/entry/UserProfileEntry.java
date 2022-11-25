package com.tinkoff.web.blackbooks.server.domain.dao.entry;


import lombok.Getter;

// What the purpose of these classes -
// we will have the same domain entry classes across all projects in a team, will we?
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

