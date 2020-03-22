package com.sentiment.trial.Analysis;

import com.sentiment.trial.ingestion.Message;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class DaysSinceEngagement {

    public int[][] interactions;
    public Integer earliestDay;
    public Integer numberOfDays;
    public boolean uniqueAuthor;
    public boolean reactionsIncluded;
    public boolean valid;

    private static final int daysHistory = 31;

    public DaysSinceEngagement(Integer earliestDay, Integer latestDay, boolean uniqueAuthor, boolean valid) {
        this.uniqueAuthor = uniqueAuthor;
        this.earliestDay = earliestDay;
        this.numberOfDays = latestDay - earliestDay + 1;

        // add the 2d array of interactions
        this.interactions = new int[daysHistory][this.numberOfDays];

        this.reactionsIncluded = false;
        this.valid = valid;
    }

    public void calculate(ArrayList<Message> messages) {

        // Spill the dates into each person. This could be divided and done in parallel
        HashMap<Long, ArrayList<Integer>> byPerson = new HashMap<Long, ArrayList<Integer>>();

        for (Message m : messages) {

            if (!byPerson.containsKey(m.getAuthorID())) byPerson.put(m.getAuthorID(), new ArrayList<Integer>());

            byPerson.get(m.getAuthorID()).add(m.getDaysSinceEpoch());
        }

        // go through each person. This could be done in parallel
        for (ArrayList<Integer> daysByPerson : byPerson.values()){

            int daysBefore = max(0, this.earliestDay - this.daysHistory);

            // build up a set of which days were interacted over, avoiding the need for sorting
            // as well as simplifying the count process
            int[] interactionSet = new int[this.numberOfDays + daysBefore];

            for (Integer day : daysByPerson) {
                if (day < this.earliestDay - daysBefore || day >= this.earliestDay + this.numberOfDays) continue;
                interactionSet[day - daysBefore] = 1;
            }

            int daysSinceInt = 0;

            // initialise by finding the most recent before the range in question
            for (int i=0; i < daysBefore; i++) {
                daysSinceInt ++;
                if (interactionSet[i] == 1) daysSinceInt = 0;
            }

            // continue for the actual range, recording the days since the most recent interaction
            for (int i=0; i < numberOfDays; i++){
                interactions[min(daysSinceInt, this.daysHistory - 1)][i] ++;
                daysSinceInt ++;
                if (interactionSet[i + daysBefore] == 1) daysSinceInt = 0;
            }

        }
    }

}
