package com.aguiar.privatechat.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aguiar.privatechat.utils.MessageHashMapManager;

public class Reply implements CommandExecutor {

  private final MessageHashMapManager messageManager;

  public Reply(MessageHashMapManager messageManager) {
    this.messageManager = messageManager;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    if (args.length < 1) {
      return false;
    }

    if (!this.messageManager.getRecentMessages().containsKey(player.getUniqueId())) {
      player.sendMessage(String.format("%sYou got no messages to reply", ChatColor.RED));
      return false;
    }

    UUID uuid = this.messageManager.getRecentMessages().get(player.getUniqueId());

    if (Bukkit.getPlayer(uuid) == null) {
      player.sendMessage(String.format("%sThe person you messaged is offline ", ChatColor.RED));
      return false;
    }

    Player target = Bukkit.getPlayer(uuid);

    StringBuilder playersMessage = new StringBuilder();

    for (String arg : args) {
      playersMessage.append(arg).append(" ");
    }

    player.sendMessage(
        String.format(
            "%s[You -> %s]: %s%s",
            ChatColor.GOLD, target.getName(), ChatColor.GREEN, playersMessage.toString()));

    target.sendMessage(
        String.format(
            "%s[%s]: %s%s",
            ChatColor.GOLD, player.getName(), ChatColor.GREEN, playersMessage.toString()));

    this.messageManager.addMessage(player.getUniqueId(), target.getUniqueId());

    return true;
  }

}
