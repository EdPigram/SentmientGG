package com.sentiment.trial.Analysis;

import java.util.ArrayList;
import java.util.HashMap;

import com.sentiment.trial.ingestion.Message.Message;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class DaysSinceEngagement {

    private class Person {
        public ArrayList<Integer> days;
        public Integer earliestDay;
        Person(int day) {
            this.days = new ArrayList<Integer>();
            this.days.add(day);
            this.earliestDay = day;
        }

        public void update(int day) {
            days.add(day);
            this.earliestDay = min(this.earliestDay, day);
        }
    }

    public int[][] interactions;
    public Integer earliestDay;
    public Integer numberOfDays;
    public boolean reactionsIncluded;
    public boolean valid;

    private static final int daysHistory = 31;

    public DaysSinceEngagement(Integer earliestDay, Integer latestDay, boolean valid) {
        this.earliestDay = earliestDay;
        this.numberOfDays = latestDay - earliestDay + 1;

        // add the 2d array of interactions
        this.interactions = new int[daysHistory][this.numberOfDays];

        this.reactionsIncluded = false;
        this.valid = valid;
    }

    public void calculate(ArrayList<Message> messages) {

        // Spill the dates into each person. This could be divided and done in parallel
        HashMap<Long, Person> byPerson = new HashMap<>();

        for (Message m : messages) {

            int day = m.getDaysSinceEpoch();

            if (!byPerson.containsKey(m.getAuthorID()))byPerson.put(m.getAuthorID(), new Person(day));

            byPerson.get(m.getAuthorID()).update(day);
        }

        // go through each person. This could be done in parallel
        for (Person person : byPerson.values()){

            int daysBefore = max(0, this.earliestDay - this.daysHistory);

            // build up a set of which days were interacted over, avoiding the need for sorting
            // as well as simplifying the count process
            int[] interactionSet = new int[this.numberOfDays + daysBefore];

            for (Integer day : person.days) {
                if (day < this.earliestDay - daysBefore || day >= this.earliestDay + this.numberOfDays) continue;
                interactionSet[day - daysBefore] = 1;
            }

            int daysSinceInt = 30;

            // initialise by finding the most recent before the range in question
            for (int i=0; i < daysBefore; i++) {
                daysSinceInt ++;
                if (interactionSet[i] == 1) daysSinceInt = 0;
            }

            // continue for the actual range, recording the days since the most recent interaction
            for (int i=0; i < numberOfDays; i++){
                if (person.earliestDay < this.earliestDay + i) interactions[min(daysSinceInt, this.daysHistory - 1)][i] ++;
                daysSinceInt ++;
                if (interactionSet[i + daysBefore] == 1) daysSinceInt = 0;
            }

        }
    }

}
