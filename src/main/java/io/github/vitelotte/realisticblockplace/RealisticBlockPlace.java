package io.github.vitelotte.realisticblockplace;

import io.github.vitelotte.realisticblockplace.listeners.BlockPlaceListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class RealisticBlockPlace extends JavaPlugin {
    public TextComponent chatPrefixComponent = Component.text("Server", NamedTextColor.AQUA);

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
