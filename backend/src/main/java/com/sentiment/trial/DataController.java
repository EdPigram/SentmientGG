package com.sentiment.trial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(path="/data")
public class DataController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(path="/interactionHistory")
    public @ResponseBody InteractionHistory addNewMessage(@RequestParam String channelID) {

        Iterable<Message> messages = messageRepository.allMessagesFrom(Long.parseLong(channelID));

        // get the time bounds to decide how many arrays I need
        int earliestDay = Integer.MAX_VALUE, latestDay = 0;
        for (Message m : messages) {
            if (m.getDaysSinceEpoch() > latestDay) latestDay = m.getDaysSinceEpoch();
            if (m.getDaysSinceEpoch() < earliestDay) earliestDay = m.getDaysSinceEpoch();
        }

        if (earliestDay > latestDay) return new InteractionHistory(0, 0, true, false);

        int numDays = latestDay - earliestDay + 1;

        // initialize the array of sets
        ArrayList<HashSet<Long>> counts = new ArrayList<HashSet<Long>>(numDays);
        for (int i=0; i<numDays; i++) counts.add(new HashSet<Long>());

        // Now go through, and add the message author id to the set for the corresponding day
        for (Message m : messages) counts.get(m.getDaysSinceEpoch() - earliestDay).add(m.getAuthor());

        // now return an array containing the number of authors in each day
        InteractionHistory response = new InteractionHistory(earliestDay, latestDay, true, true);
        for (int i=0; i<numDays; i++) response.interactions.add(counts.get(i).size());

        return response;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}