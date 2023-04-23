package com.example.medonation;

public class messagesModel {
    String senderName,chat,Date,sender,receiver;

    public messagesModel() {
    }

    public messagesModel(String senderName, String chat, String date,String sender,String receiver) {
        this.senderName = senderName;
        this.chat = chat;
        Date = date;
        this.sender=sender;
        this.receiver=receiver;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
