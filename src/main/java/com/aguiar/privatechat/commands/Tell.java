package com.aguiar.privatechat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aguiar.privatechat.PrivateChat;
import com.aguiar.privatechat.utils.MessageHashMapManager;

public class Tell implements CommandExecutor {

  private MessageHashMapManager messageManager;

  public Tell(MessageHashMapManager messageManager) {
    this.messageManager = messageManager;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "This is for players only");
      return false;
    }

    if (args.length < 2) {
      sender.sendMessage(String.format("%s[PrivateChat]%s - /tell <player> <message>", ChatColor.GOLD, ChatColor.RED));
      return false;
    }

    Player target = Bukkit.getPlayerExact(args[0]);

    if (target == null) {
      sender.sendMessage(String.format("%s[PrivateChat]%s - This player is not online", ChatColor.GOLD, ChatColor.RED));
      return false;
    }

    Player player = (Player) sender;
    StringBuilder playersMessage = new StringBuilder();

    for (int index = 1; index < args.length; index++) {
      playersMessage.append(args[index]).append(" ");
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
