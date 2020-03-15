package com.sentiment.trial.Analysis;

import java.util.ArrayList;

public class InteractionHistory{

    public ArrayList<Integer> interactions;
    public Integer earliestDay;
    public Integer numberOfDays;
    public boolean uniqueAuthor;
    public boolean postsIncluded;
    public boolean reactionsIncluded;
    public boolean valid;

    public InteractionHistory(Integer earliestDay, Integer latestDay, boolean uniqueAuthor, boolean valid) {
        this.uniqueAuthor = uniqueAuthor;
        this.earliestDay = earliestDay;
        this.numberOfDays = latestDay - earliestDay + 1;
        this.interactions = new ArrayList<Integer>(this.numberOfDays);
        this.valid = valid;
    }

}
