package io.github.vitelotte.realisticblockplace.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class PlaceUtil {

    private static Material floatingStone = Material.BEDROCK;
    public PlaceUtil() {

    }

    public static boolean existsFloatingStone(Location location) {
        for (Double y = -64.0; y < location.getWorld().getMaxHeight(); y++) {
            location.setY(y);
            Material currentMaterial = location.getBlock().getBlockData().getMaterial();
            if (!currentMaterial.equals(Material.AIR)) return currentMaterial.equals(PlaceUtil.floatingStone);
        }
        return false;
    }

    public static boolean isFloatingStone(Block block) {
        return block.getBlockData().getMaterial().equals(PlaceUtil.floatingStone);
    }
}
