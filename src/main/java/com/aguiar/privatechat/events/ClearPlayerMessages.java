package com.aguiar.privatechat.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.aguiar.privatechat.utils.MessageHashMapManager;

public class ClearPlayerMessages implements Listener {

  private final MessageHashMapManager messageManager;

  public ClearPlayerMessages(MessageHashMapManager messageManager) {
    this.messageManager = messageManager;
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    this.messageManager.getRecentMessages().remove(event.getPlayer().getUniqueId());
  }
}
