package bananablu.staffchat;

import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class StaffChatToggle implements CommandExecutor {
    private final StaffChat plugin;

    public StaffChatToggle(StaffChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(plugin.getConfig().getString("only-players"));
            return false;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("staffchat.toggle"))) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("no-permissions").replace("%player-name%",p.getDisplayName()).replace("%prefix%",plugin.getConfig().getString("prefix"))));
            return false;
        }
        if (!plugin.toggle.contains(p.getPlayer())) {
            plugin.toggle.add(p.getPlayer());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("toggle-true").replace("%player-name%", p.getDisplayName()).replace("%prefix%",plugin.getConfig().getString("prefix"))));
            return false;
        }
        plugin.toggle.remove(p.getPlayer());
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("toggle-false").replace("%player-name%", p.getDisplayName()).replace("%prefix%",plugin.getConfig().getString("prefix"))));

        return false;
    }
}
