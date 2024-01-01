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
        for (Double y = -64.0; y < location.getWorld().getMaxHeight(); y++) {
            location.setY(y);
            Material currentMaterial = location.getBlock().getBlockData().getMaterial();
            if (!currentMaterial.equals(Material.AIR)) return currentMaterial.equals(FloatingStoneUtil.floatingStone);
        }
        return false;
    }

    public static boolean isFloatingStone(Block block) {
        return block.getBlockData().getMaterial().equals(FloatingStoneUtil.floatingStone);
    }

    public static boolean isFloatingStonePickaxe(ItemStack itemStack) {
        boolean isSameMaterial = itemStack.getType().equals(FloatingStoneUtil.floatingStonePickaxeMaterial);
        boolean hasSameCustomModelData = itemStack.getItemMeta().getCustomModelData() == FloatingStoneUtil.floatingStonePickaxeCustomModelData;
        return isSameMaterial && hasSameCustomModelData;
    }
}
