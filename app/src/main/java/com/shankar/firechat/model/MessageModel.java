package com.shankar.firechat.model;

public class MessageModel {

    String message;
    String time;
    String strDate;
    Boolean fromServer;

    public MessageModel()
    {

    }
    public MessageModel(String message, String time, String strDate, Boolean fromServer)
    {
        this.message = message;
        this.time= time;
        this.strDate= strDate;
        this.fromServer = fromServer;
    }

    public Boolean getFromServer() {
        return fromServer;
    }

    public void setFromServer(Boolean fromServer) {
        this.fromServer = fromServer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }
}
