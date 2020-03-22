package com.sentiment.trial.ingestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/ingestion")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

//    @Autowired
//    private MessageRepository reactionRepository;

    @PostMapping(path="/uploadMessages", consumes = "application/json")
    public @ResponseBody String addMessages(@RequestBody List<Message> messages) {
        for (Message m : messages) messageRepository.save(m);
        return "Saved succesfully.";
    }

//    @PostMapping(path="/uploadReactions", consumes = "application/json")
//    public @ResponseBody String addReactions(@RequestBody Reactions reactions) {
//        for (Message m : reactions.getReactions()) {
//            messageRepository.save(m);
//        }
//        return "Saved";
//    }

    @GetMapping(path="/getAll")
    public @ResponseBody Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}