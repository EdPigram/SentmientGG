package com.sentiment.trial.Keyword;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Counts {

    // values

    @Id
    @NotNull
    private Long id;

    @NotNull
    private Long channelID;

    @NotNull
    private String keyword;

    // the start of the day for which the counts are given
    @NotNull
    private Long date;

    // setters and getters (need getters for api to return the corresponding values)

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

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

    public Long getDate() {
        return date;
    }

    void setDate(Long date) {
        this.date = date;
    }

}