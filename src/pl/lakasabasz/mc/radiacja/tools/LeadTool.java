package pl.lakasabasz.mc.radiacja.tools;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;
import pl.lakasabasz.mc.radiacja.Main;

public class LeadTool {

	public static ItemStack getLeadNugget() {
		ItemStack is = new ItemStack(Material.IRON_NUGGET, 1);
		ItemMeta im = is.getItemMeta();
		im.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "lead"), PersistentDataType.INTEGER, 1);
		im.addEnchant(Enchantment.LUCK, 1, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.setDisplayName(ChatColor.RED + "Sztabka ołowiu");
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack getLeadOre() {
		ItemStack is = new ItemStack(Material.GOLD_ORE, 1);
		ItemMeta im = is.getItemMeta();
		im.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "lead-ore"), PersistentDataType.INTEGER, 1);
		im.addEnchant(Enchantment.LUCK, 1, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.setDisplayName(ChatColor.DARK_RED + "Zanieczyszczona ruda ołowiu");
		is.setItemMeta(im);
		return is;
	}
	
	public static boolean testForLeadOre(ItemStack is) {
		return is.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "lead-ore"), PersistentDataType.INTEGER);
	}
	
	public static void registerRecipes() {
		BlastingRecipe br = new BlastingRecipe(new NamespacedKey(Main.getInstance(), "lead-nugget"), getLeadNugget(), getLeadOre().getType(), 1, 5*20);
		Bukkit.addRecipe(br);
	}
	
	public static void unregisterRecipes() {
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "lead-nugget"));
	}

	public static boolean testForLeadIngot(ItemStack is) {
		return !Objects.isNull(is) && is.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getInstance(), "lead"), PersistentDataType.INTEGER);
	}
}
