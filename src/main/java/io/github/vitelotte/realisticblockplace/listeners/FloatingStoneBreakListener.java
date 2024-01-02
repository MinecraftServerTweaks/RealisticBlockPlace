package io.github.vitelotte.realisticblockplace.listeners;

import io.github.vitelotte.realisticblockplace.utils.FloatingStoneUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class FloatingStoneBreakListener implements Listener {

    @EventHandler
    public void onFloatingStoneBreak(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action.isLeftClick()) {
            ItemStack tool = event.getItem();
            if (!Objects.isNull(tool) && FloatingStoneUtil.isFloatingStonePickaxe(tool)) {
                Block block = event.getClickedBlock();
                if (!Objects.isNull(block) && FloatingStoneUtil.isFloatingStone(block)) {
                    Player player = event.getPlayer();
                    block.breakNaturally();
                    ItemStack floatingStone = new ItemStack(Material.BEDROCK);
                    block.getLocation().getWorld().dropItem(block.getLocation(), floatingStone);
                    FloatingStoneUtil.breakNonPlaceableBlocks(block.getLocation());
                }

            }

        }
    }
}
