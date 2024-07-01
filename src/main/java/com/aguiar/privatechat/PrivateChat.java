package com.aguiar.privatechat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.aguiar.privatechat.commands.Reply;
import com.aguiar.privatechat.commands.Tell;
import com.aguiar.privatechat.events.ClearPlayerMessages;
import com.aguiar.privatechat.utils.MessageHashMapManager;

public final class PrivateChat extends JavaPlugin {

    private final MessageHashMapManager messageManager = new MessageHashMapManager();

    @Override
    public void onEnable() {
        if (!this.setupCommands()) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getPluginManager().registerEvents(new ClearPlayerMessages(messageManager), this);

        Bukkit.getConsoleSender().sendMessage("[PrivateChat] - Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[PrivateChat] - Disabled");
    }

    private boolean setupCommands() {
        try {
            getCommand("tell").setExecutor(new Tell(messageManager));
            getCommand("reply").setExecutor(new Reply(messageManager));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
