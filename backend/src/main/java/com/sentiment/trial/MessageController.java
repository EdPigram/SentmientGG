package com.sentiment.trial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewMessage(@RequestParam String messageID,
                                              @RequestParam String channelID,
                                              @RequestParam String secondsSinceEpoch,
                                              @RequestParam String author) {
        Message n = new Message();

        n.setMessageID(Long.parseLong(messageID));
        n.setChannelID(Long.parseLong(channelID));
        n.setSecondsSinceEpoch(Long.parseLong(secondsSinceEpoch));
        n.setAuthor(author);
        messageRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping(path="/oldest")
    public @ResponseBody Message getOldestMessage(@RequestParam String channelID) {
        return messageRepository.oldestMessageFrom(Long.parseLong(channelID));
    }

    @GetMapping(path="/newest")
    public @ResponseBody Message getNewestMessage(@RequestParam String channelID) {
        return messageRepository.newestMessageFrom(Long.parseLong(channelID));
    }
}