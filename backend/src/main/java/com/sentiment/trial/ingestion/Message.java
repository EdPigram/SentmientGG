package com.sentiment.trial.ingestion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Message {

    // values

    @Id
    @NotNull
    private Long messageID;

    @NotNull
    private Long channelID;

    @NotNull
    private Long secondsSinceEpoch;

    @NotNull
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

    public Integer getDaysSinceEpoch() {
        return Math.toIntExact(secondsSinceEpoch/60/60/24);
    }

    void setSecondsSinceEpoch(Long secondsSinceEpoch) {
        this.secondsSinceEpoch = secondsSinceEpoch;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public Long getAuthorID() {
        return authorID;
    }

}