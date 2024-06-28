package com.aguiar.privatechat.utils;

import java.util.HashMap;
import java.util.UUID;

public class MessageHashMapManager {
  private HashMap<UUID, UUID> recentMessages;

  public HashMap<UUID, UUID> getRecentMessages() {
    return recentMessages;
  }

  public MessageHashMapManager() {
    this.recentMessages = new HashMap<>();
  }

  public void addMessage(UUID key, UUID value) {
    this.recentMessages.put(key, value);
  }

}
