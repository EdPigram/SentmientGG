package com.sentiment.trial.Analysis;

import com.sentiment.trial.ingestion.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class EngagementHistory {

    public static class MessageComparatorByDay implements Comparator<Message> {
        @Override
        public int compare(Message m1, Message m2) {
            return m1.getSecondsSinceEpoch().compareTo(m2.getSecondsSinceEpoch());
        }
    }

    public int[][] interactions;
    public Integer earliestDay;
    public Integer numberOfDays;
    public boolean uniqueAuthor;
    public boolean reactionsIncluded;
    public boolean valid;

    private static final int window = 30;
    private static final int[] bounds = {0, 5, 15, 25};

    public EngagementHistory(Integer earliestDay, Integer latestDay, boolean uniqueAuthor, boolean valid) {
        this.uniqueAuthor = uniqueAuthor;
        this.earliestDay = earliestDay;
        this.numberOfDays = latestDay - earliestDay + 1;

        // add the 2d array of interactions
        this.interactions = new int[bounds.length][this.numberOfDays];

        this.reactionsIncluded = false;
        this.valid = valid;
    }

    public void calculate(ArrayList<Message> messages) {

        // sort the interactions in ascending order
        messages.sort(new MessageComparatorByDay());

        // Spill the dates into each person
        HashMap<Long, ArrayList<Integer>> byPerson = new HashMap<Long, ArrayList<Integer>>();

        for (Message m : messages) {

            if (!byPerson.containsKey(m.getAuthorID())) byPerson.put(m.getAuthorID(), new ArrayList<Integer>());

            byPerson.get(m.getAuthorID()).add(m.getDaysSinceEpoch());
        }

        // go through each person, set a sliding window
        for (ArrayList<Integer> daysByPerson : byPerson.values()){

            // build up a set of which days were interacted over (to make a sliding window easier)
            int[] interactionSet = new int[numberOfDays + window];

            for (Integer day : daysByPerson) {
                if (day < earliestDay - window || day > earliestDay + numberOfDays) continue;
                interactionSet[day - window] = 1;
            }

            int count = 0;

            // initialise window by counting from the window days before
            for (int i=0; i < window; i++) count += interactionSet[i];

            // continue onwards until the end, each time adding a value for the count in the corresponding bucket
            for (int i=0; i < numberOfDays; i++){
                count += interactionSet[i + window] - interactionSet[i];

                int bucket = bounds.length - 1;
                while (bucket > 0 && count >= bounds[bucket - 1]) bucket --;

                interactions[bucket][i] ++;
            }

        }
    }

}
