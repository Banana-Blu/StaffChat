package bananablu.staffchat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
public class Reload implements CommandExecutor {

    private final StaffChat plugin;

    public Reload(StaffChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("staffchat.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("no-permissions").replace("%prefix%",ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix")))));
            return false;
        }
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("reload").replace("%prefix%",ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix")))));
        return false;
    }
}
