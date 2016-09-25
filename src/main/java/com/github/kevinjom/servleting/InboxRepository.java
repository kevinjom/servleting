package com.github.kevinjom.servleting;

import java.util.Arrays;
import java.util.List;

public class InboxRepository {
    public List<String> getAll(String userId) {
        if (!"kevin".equals(userId)) {
            return Arrays.asList("welcome to inbox");
        }

        return Arrays.asList("we are having servlet session", "would you like it");
    }
}
