package com.sentiment.trial.Keyword;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Tracked {

    // values

    @Id
    @NotNull
    private Long id;

    @NotNull
    private Long channelID;

    @NotNull
    private String keyword;

    // setters and getters (need getters for api to return the corresponding values)

    public Long getChannelID() {
        return channelID;
    }

    void setChannelID(Long channelID) {
        this.channelID = channelID;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(Long authorID) {
        this.keyword = keyword;
    }

}