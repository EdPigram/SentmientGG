package com.sentiment.trial.Analysis;

import com.sentiment.trial.ingestion.Message;
import com.sentiment.trial.ingestion.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/analysis")
public class AnalysisController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(path="/interactionHistory")
    public @ResponseBody InteractionHistory ih(@RequestParam String channelID) {

        Iterable<Message> messages = messageRepository.allMessagesFrom(Long.parseLong(channelID));

        // get the time bounds to decide how many arrays I need
        int earliestDay = Integer.MAX_VALUE, latestDay = 0;
        for (Message m : messages) {
            if (m.getDaysSinceEpoch() > latestDay)   latestDay   = m.getDaysSinceEpoch();
            if (m.getDaysSinceEpoch() < earliestDay) earliestDay = m.getDaysSinceEpoch();
        }

        if (earliestDay > latestDay) return new InteractionHistory(0, 0, true, false);

        int numDays = latestDay - earliestDay + 1;

        // initialize the array of sets
        ArrayList<HashSet<Long>> counts = new ArrayList<HashSet<Long>>(numDays);
        for (int i=0; i<numDays; i++) counts.add(new HashSet<Long>());

        // Now go through, and add the message author id to the set for the corresponding day
        for (Message m : messages) counts.get(m.getDaysSinceEpoch() - earliestDay).add(m.getAuthorID());

        // now return an array containing the number of authors in each day
        InteractionHistory response = new InteractionHistory(earliestDay, latestDay, true, true);
        for (int i=0; i<numDays; i++) response.interactions.add(counts.get(i).size());

        return response;
    }

    @GetMapping(path="/messageHeatMap")
    public @ResponseBody HeatMap mhm(@RequestParam String channelID) {

        HeatMap response = new HeatMap(true);

        Iterable<Message> messages = messageRepository.allMessagesFrom(Long.parseLong(channelID));

        Double increment = 1.0 / ((Collection<Message>)messages).size();

        for (Message m : messages) {

            // epoch was a thursday, so + 4 % 7 is the days past Sunday
            int day = (m.getDaysSinceEpoch() + 4) % 7;
            int hour = (int)((m.getSecondsSinceEpoch()/60/60) % 24);
            System.out.println(day);
            System.out.println(hour);
            response.cells[day][hour] += increment;

        }

        return response;
    }

    @GetMapping(path="/engagementHistory")
    public @ResponseBody EngagementHistory eh(@RequestParam String channelID) {

        // get all the messages
        Iterable<Message> iterableMessages = messageRepository.allMessagesFrom(Long.parseLong(channelID));
        ArrayList<Message> messages = new ArrayList<Message>();
        for (Message m : iterableMessages) messages.add(m);

        // Initialise the response
        EngagementHistory response = new EngagementHistory(10, 20, true, true);

        // build it
        response.calculate(messages);

        return response;
    }

    @GetMapping(path="/daysSinceEngagement")
    public @ResponseBody DaysSinceEngagement idk(@RequestParam String channelID) {

        // get all the messages
        Iterable<Message> iterableMessages = messageRepository.allMessagesFrom(Long.parseLong(channelID));
        ArrayList<Message> messages = new ArrayList<Message>();
        for (Message m : iterableMessages) messages.add(m);

        // Initialise the response
        DaysSinceEngagement response = new DaysSinceEngagement(0, 50, true, true);

        // build it
        response.calculate(messages);

        return response;
    }


}