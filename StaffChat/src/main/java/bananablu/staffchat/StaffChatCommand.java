package bananablu.staffchat;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.permission.PermissionChecker;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
    public class StaffChatCommand implements CommandExecutor {

        private final StaffChat plugin;

        public StaffChatCommand(StaffChat plugin) {
            this.plugin = plugin;
        }
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                Bukkit.getConsoleSender().sendMessage(plugin.getConfig().getString("only-players"));
                return false;
            }

            Player p = (Player) sender;

            if (!(p.hasPermission("staffchat.sendmessage"))) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("no-permissions").replace("%prefix%",ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix")))).replace("%player-name%",ChatColor.translateAlternateColorCodes('&', p.getDisplayName())));
                return false;
            }
            if (args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("correct-syntax").replace("%prefix%",ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix")))).replace("%player-name%",ChatColor.translateAlternateColorCodes('&', p.getDisplayName())));
                return false;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            Audience sc = Audience.audience(plugin.toggle).filterAudience(audience -> audience.get(PermissionChecker.POINTER).map(checker -> checker.test("staffchat.viewmessage")).orElse(false));
            sc.sendMessage(Component.text(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("staffchat-format").replace("%player%", p.getDisplayName()).replace("%prefix%",ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix")))) + sb));

            return false;
        }

    }


