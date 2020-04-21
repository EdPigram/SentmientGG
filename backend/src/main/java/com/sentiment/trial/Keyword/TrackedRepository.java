package com.sentiment.trial.Keyword;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TrackedRepository extends CrudRepository<com.sentiment.trial.Keyword.Tracked, Integer> {

    @Query(value = "select k from Keyword m where m.channelID = ?1")
    Iterable<com.sentiment.trial.Keyword.Tracked> getTrackedKeywords(Long channelID);

}