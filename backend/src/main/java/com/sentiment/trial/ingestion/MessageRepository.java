package com.sentiment.trial.ingestion;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "select m from Message m where m.channelID = ?1")
    Iterable<Message> allMessagesFrom(Long channelID);

    @Query(value = "select m from Message m where m.channelID = ?1 and m.secondsSinceEpoch = " +
                            "(select MAX(secondsSinceEpoch) from Message where channelID = ?1)")
    Message newestMessageFrom(Long channelID);

    @Query(value = "select m from Message m where m.channelID = ?1 and m.secondsSinceEpoch = " +
                            "(select MIN(secondsSinceEpoch) from Message where channelID = ?1)")
    Message oldestMessageFrom(Long channelID);
}