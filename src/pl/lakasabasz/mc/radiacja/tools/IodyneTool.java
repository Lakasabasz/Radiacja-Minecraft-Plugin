package pl.lakasabasz.mc.radiacja.tools;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import net.md_5.bungee.api.ChatColor;
import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.brewing.BrewingRecipe;

public class IodyneTool {
	
	public static NamespacedKey getIodyneKey() {
		return new NamespacedKey(Main.getInstance(), "iodyne");
	}
	
	public static NamespacedKey getSweetWaterKey() {
		return new NamespacedKey(Main.getInstance(), "sweetwater");
	}
	
	public static ItemStack getIodynePotion() {
		ItemStack is = new ItemStack(Material.POTION, 1);
		PotionMeta p = (PotionMeta) is.getItemMeta();
		p.setDisplayName(ChatColor.DARK_BLUE + "Jodyna");
		p.setColor(Color.BLACK);
		p.getPersistentDataContainer().set(getIodyneKey(), PersistentDataType.INTEGER, 20*30);
		is.setItemMeta(p);
		return is;
	}
	
	public static ItemStack getSweetWater() {
		ItemStack is = new ItemStack(Material.POTION, 1);
		PotionMeta p = (PotionMeta) is.getItemMeta();
		p.setDisplayName(ChatColor.BLUE + "Słodka woda");
		p.setColor(Color.WHITE);
		p.getPersistentDataContainer().set(getSweetWaterKey(), PersistentDataType.INTEGER, 20*15);
		is.setItemMeta(p);
		return is;
	}
	
	public static boolean testForSweetWater(ItemStack is) {
		return !ItemStackTool.isEmpty(is) && is.isSimilar(getSweetWater()) && is.getItemMeta().getPersistentDataContainer().has(getSweetWaterKey(), PersistentDataType.INTEGER);
	}
	
	public static void registerRecipes() {
		new BrewingRecipe(Material.FERMENTED_SPIDER_EYE, (inventory, item, ingridient) -> {
			if(!testForSweetWater(item)) {
				return;
			}
			item.setItemMeta(getIodynePotion().getItemMeta());
		});
		
		new BrewingRecipe(Material.SUGAR, (inventory, item, ingridient) -> {
			ItemStack is = new ItemStack(Material.POTION);
			PotionMeta pm = (PotionMeta) is.getItemMeta();
			pm.setBasePotionData(new PotionData(PotionType.WATER));
			is.setItemMeta(pm);
			if(!item.isSimilar(is)) return;
			item.setItemMeta(getSweetWater().getItemMeta());
		});
	}

	public static boolean testForIodyne(ItemStack is) {
		return !ItemStackTool.isEmpty(is) && is.isSimilar(getIodynePotion()) && is.getItemMeta().getPersistentDataContainer().has(getIodyneKey(), PersistentDataType.INTEGER);
	}
}
