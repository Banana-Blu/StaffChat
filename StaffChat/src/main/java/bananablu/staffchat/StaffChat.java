package bananablu.staffchat;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class StaffChat extends JavaPlugin {
    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("You can't access adventure while the plugin is disabled!");
        }
        return this.adventure;
    }

    List toggle = new ArrayList<>();
    @Override
    public void onEnable() {
        this.adventure = BukkitAudiences.create(this);
        getCommand("staffchat").setExecutor(new StaffChatCommand(this));
        getCommand("staffchatoggle").setExecutor(new StaffChatToggle(this));
        getCommand("staffchatreload").setExecutor(new Reload(this));
        saveDefaultConfig();


    }

    @Override
    public void onDisable() {
            if(this.adventure != null) {
                this.adventure.close();
                this.adventure = null;
            }
        }
}
