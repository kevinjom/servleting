package com.github.kevinjom.servleting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboxService {
    private final InboxRepository inboxRepository;

    @Autowired
    public InboxService(InboxRepository inboxRepository) {
        this.inboxRepository = inboxRepository;
    }

    public List<String> getAll(String consumerId) {
        return inboxRepository.getAll(consumerId );
    }
}
