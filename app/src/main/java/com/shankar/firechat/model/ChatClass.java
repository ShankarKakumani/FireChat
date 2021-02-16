package com.shankar.firechat.model;

public class ChatClass {

    public String name;
    public String chatKey;

    public ChatClass(){}

    public ChatClass(String name, String chatKey)
    {
        this.name = name;
        this.chatKey = chatKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }
}
