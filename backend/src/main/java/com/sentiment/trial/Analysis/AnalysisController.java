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

    @GetMapping(path="/interactionHistory")
    public @ResponseBody InteractionHistory eh(@RequestParam String channelID) {

        // get all the messages
        Iterable<Message> iterableMessages = messageRepository.allMessagesFrom(Long.parseLong(channelID));
        ArrayList<Message> messages = new ArrayList<Message>();
        for (Message m : iterableMessages) messages.add(m);

        // Initialise the response
        InteractionHistory response = new InteractionHistory(0, 50, true);

        // build it
        response.calculate(messages);

        return response;
    }

//    @GetMapping(path="/daysSinceEngagement")
//    public @ResponseBody DaysSinceEngagement idk(@RequestParam String channelID) {
//
//        // get all the messages
//        Iterable<Message> iterableMessages = messageRepository.allMessagesFrom(Long.parseLong(channelID));
//        ArrayList<Message> messages = new ArrayList<Message>();
//        for (Message m : iterableMessages) messages.add(m);
//
//        // Initialise the response
//        DaysSinceEngagement response = new DaysSinceEngagement(0, 50, true);
//
//        // build it
//        response.calculate(messages);
//
//        return response;
//    }


}