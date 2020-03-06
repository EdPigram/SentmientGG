//package com.sentiment.trial;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(path="/data")
//public class DataController {
//
//    @Autowired
//    private MessageRepository messageRepository;
//
//    @GetMapping(path="/uniquePosts")
//    public @ResponseBody String addNewMessage(@RequestParam String channelID) {
//        Iterable<Message> messages = messageRepository.findAll();
//
//        n.setMessageID(Long.parseLong(messageID));
//        n.setChannelID(Long.parseLong(channelID));
//        n.setSecondsSinceEpoch(Long.parseLong(secondsSinceEpoch));
//        n.setAuthor(author);
//        messageRepository.save(n);
//        return "Saved";
//    }
//
//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<Message> getAllMessages() {
//        return messageRepository.findAll();
//    }
//}