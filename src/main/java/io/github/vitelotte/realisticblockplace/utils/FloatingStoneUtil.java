package io.github.vitelotte.realisticblockplace.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class FloatingStoneUtil {

    private static Material floatingStone = Material.BEDROCK;
    private static Material floatingStonePickaxeMaterial = Material.DEBUG_STICK;
    private static int floatingStonePickaxeCustomModelData = 100000;

    public FloatingStoneUtil() {

    }

    public static boolean existsFloatingStone(Location location) {
        Location checkingLocation = new Location(location.getWorld(), location.x(), location.y(), location.z());
        for (Double y = (double) location.getWorld().getMinHeight(); y < location.y(); y++) {
            checkingLocation.setY(y);
            Material currentMaterial = checkingLocation.getBlock().getBlockData().getMaterial();
            if (currentMaterial.equals(FloatingStoneUtil.floatingStone)) return true;
        }
        return false;
    }

    public static boolean isFloatingStone(Block block) {
        return block.getBlockData().getMaterial().equals(FloatingStoneUtil.floatingStone);
    }

    public static boolean isFloatingStonePickaxe(ItemStack itemStack) {
        boolean isSameMaterial = itemStack.getType().equals(FloatingStoneUtil.floatingStonePickaxeMaterial);
        boolean hasSameCustomModelData = itemStack.getItemMeta().hasCustomModelData() && itemStack.getItemMeta().getCustomModelData() == FloatingStoneUtil.floatingStonePickaxeCustomModelData;
        return isSameMaterial && hasSameCustomModelData;
    }

    public static void breakNonPlaceableBlocks(Location location) {
        if (!FloatingStoneUtil.existsFloatingStone(location)) {
            Location checkingLocation = new Location(location.getWorld(), location.x(), location.y(), location.z());
            for (Double y = location.y(); y <= location.getWorld().getMaxHeight(); y++) {
                checkingLocation.setY(y);
                Block currentBlock = checkingLocation.getBlock();
                if (!isFloatingStone(currentBlock)) {
                    currentBlock.breakNaturally();
                }
                else {
                    return;
                }
            }
        }
    }
}
