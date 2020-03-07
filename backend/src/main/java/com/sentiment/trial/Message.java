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

    private Long authorID;

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

    Integer getDaysSinceEpoch() {
        return Math.toIntExact(secondsSinceEpoch/60/60/24);
    }

    void setSecondsSinceEpoch(Long secondsSinceEpoch) {
        this.secondsSinceEpoch = secondsSinceEpoch;
    }

    void setAuthor(Long authorID) {
        this.authorID = authorID;
    }

    Long getAuthor() {
        return authorID;
    }

}