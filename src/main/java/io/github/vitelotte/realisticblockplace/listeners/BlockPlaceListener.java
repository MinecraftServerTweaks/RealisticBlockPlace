package io.github.vitelotte.realisticblockplace.listeners;

import io.github.vitelotte.realisticblockplace.RealisticBlockPlace;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class BlockPlaceListener implements Listener {
    private RealisticBlockPlace plugin;

    public BlockPlaceListener(RealisticBlockPlace plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(event.getBlockPlaced().getLocation().toString());
        if (!player.isOp()) {
            Block block = event.getBlock();
            if (!this.hasSupportingBlock(block.getLocation()) && !block.getBlockData().getMaterial().equals(plugin.supportingBlock)) {
                event.setCancelled(true);
                player.sendMessage(
                        Component.text("[")
                                .append(plugin.chatPrefixComponent)
                                .append(Component.text("] 공중에 "))
                                .append(Component.text("부유석", NamedTextColor.LIGHT_PURPLE))
                                .append(Component.text("을 제외한 블럭을 설치할 수 없습니다!"))
                );
            }
            else if (block.getBlockData().getMaterial().equals(plugin.supportingBlock)) {
                player.sendMessage(
                        Component.text("[")
                                .append(plugin.chatPrefixComponent)
                                .append(Component.text("] "))
                                .append(Component.text("부유석", NamedTextColor.LIGHT_PURPLE))
                                .append(Component.text("에서 나오는 신비로운 기운이 자신의 위에 있는 블록들을 지지합니다!"))
                );
            }
        }
    }

    private Boolean hasSupportingBlock(Location currentLocation) {
        for (Double y = -64.0; y < currentLocation.getWorld().getMaxHeight(); y++) {
            currentLocation.setY(y);
            Material currentMaterial = currentLocation.getBlock().getBlockData().getMaterial();
            if (!currentMaterial.equals(Material.AIR)) return currentMaterial.equals(plugin.supportingBlock);
        }
        return false;
    }
}
