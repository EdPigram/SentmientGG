package com.sentiment.trial.Keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/keywords")
public class KeywordController {

    @Autowired
    private CountsRepository countsRepository;

    @Autowired
    private TrackedRepository trackedRepository;

    @PostMapping(path="/set_counts", consumes = "application/json")
    public @ResponseBody String setCounts(@RequestBody List<com.sentiment.trial.Keyword.Counts> counts) {
        for (com.sentiment.trial.Keyword.Counts c : counts) countsRepository.save(c);
        return "Saved successfully.";
    }

//    @PostMapping(path="/uploadReactions", consumes = "application/json")
//    public @ResponseBody String addReactions(@RequestBody Reactions reactions) {
//        for (Message m : reactions.getReactions()) {
//            messageRepository.save(m);
//        }
//        return "Saved";
//    }

    @GetMapping(path="/getTrackedKeywords")
    public @ResponseBody Iterable<String> getTrackedKeywords() {
        return trackedRepository.findAll();
    }
}