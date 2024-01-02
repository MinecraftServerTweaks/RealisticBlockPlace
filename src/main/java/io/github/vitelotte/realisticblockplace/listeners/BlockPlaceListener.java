package io.github.vitelotte.realisticblockplace.listeners;

import io.github.vitelotte.realisticblockplace.RealisticBlockPlace;
import io.github.vitelotte.realisticblockplace.utils.FloatingStoneUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
public class BlockPlaceListener implements Listener {
    private RealisticBlockPlace plugin;

    public BlockPlaceListener(RealisticBlockPlace plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(event.getBlockPlaced().getLocation().toString());
        if (true || !player.isOp()) {
            Block block = event.getBlock();
            if (FloatingStoneUtil.isFloatingStone(block)) {
                player.sendMessage(
                        Component.text("[")
                                .append(plugin.chatPrefixComponent)
                                .append(Component.text("] "))
                                .append(Component.text("부유석", NamedTextColor.LIGHT_PURPLE))
                                .append(Component.text("에서 나오는 신비로운 기운이 자신의 위에 있는 블록들을 지지합니다!"))
                );
            }
            else if (!FloatingStoneUtil.existsFloatingStone(block.getLocation())) {
                event.setCancelled(true);
                player.sendMessage(
                        Component.text("[")
                                .append(plugin.chatPrefixComponent)
                                .append(Component.text("] 공중에 "))
                                .append(Component.text("부유석", NamedTextColor.LIGHT_PURPLE))
                                .append(Component.text("을 제외한 블럭을 설치할 수 없습니다!"))
                );
            }
        }
    }

}
