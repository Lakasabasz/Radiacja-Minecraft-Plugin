package pl.lakasabasz.mc.radiacja.tools;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackTool {
	public static boolean isEmpty(ItemStack is) {
		return Objects.isNull(is) || is.getType().equals(Material.AIR);
	}
}
