package com.sentiment.trial;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

    // values

    @Id
    private Long messageID;

    private Long channelID;

    private Long secondsSinceEpoch;

    private String author;

    // setters and getters (need getters for api to return the corresponding values)

    public Long getMessageID() {
        return messageID;
    }

    void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public Long getChannelID() {
        return channelID;
    }

    void setChannelID(Long channelID) {
        this.channelID = channelID;
    }

    public Long getSecondsSinceEpoch() {
        return secondsSinceEpoch;
    }

    void setSecondsSinceEpoch(Long secondsSinceEpoch) {
        this.secondsSinceEpoch = secondsSinceEpoch;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

}