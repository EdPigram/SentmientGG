package com.sentiment.trial.Analysis;

import java.util.ArrayList;
import java.util.HashMap;

import com.sentiment.trial.ingestion.Message.Message;

import static java.lang.Integer.min;

public class InteractionHistory {

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

    private static final int window = 7;
    private static final int[] bounds = {6, 4, 1, 0};

    public InteractionHistory(Integer earliestDay, Integer latestDay, boolean valid) {
        this.earliestDay = earliestDay;
        this.numberOfDays = latestDay - earliestDay + 1;

        // add the 2d array of interactions
        this.interactions = new int[bounds.length][this.numberOfDays];

        this.reactionsIncluded = false;
        this.valid = valid;
    }

    public void calculate(ArrayList<Message> messages) {


        // Spill the dates into each person
        HashMap<Long, Person> byPerson = new HashMap<>();

        for (Message m : messages) {

            int day = m.getDaysSinceEpoch() - earliestDay;

            if (!byPerson.containsKey(m.getAuthorID())) byPerson.put(m.getAuthorID(), new Person(day));

            byPerson.get(m.getAuthorID()).update(day);
        }

        // go through each person, set a sliding window
        for (Person person : byPerson.values()){

            // build up a set of which days were interacted over (to make a sliding window easier)
            int[] interactionSet = new int[numberOfDays + window];

            for (int day : person.days) {
                if (day < 0 || day >= numberOfDays + window) continue;
                interactionSet[day] = 1;
            }

            int rollingSum = 0;

            // initialise window by counting from the window days before
            for (int i=0; i < window; i++) rollingSum += interactionSet[i];

            // continue onwards until the end, each time adding a value for the count in the corresponding bucket
            for (int i=0; i < numberOfDays; i++){

                // increment the corresponding bucket, if the person is considered already in the channel
                if (person.earliestDay < i) {
                    int bucket = bounds.length - 1;
                    while (bucket > 0 && rollingSum >= bounds[bucket - 1]) bucket--;
//                    System.out.printf("%d, %d \n", rollingSum, bucket);
                    interactions[bucket][i]++;
                }

                // update the window count
                rollingSum += interactionSet[i + window] - interactionSet[i];
            }

        }
    }

}
