package pl.lakasabasz.mc.radiacja.tools;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;
import pl.lakasabasz.mc.radiacja.Main;

public class ArmorTool {

	public static boolean test(ItemStack armor) {
		NamespacedKey nk = new NamespacedKey(Main.getInstance(), "lead-armor");
		return !Objects.isNull(armor)
				&& armor.getItemMeta() instanceof LeatherArmorMeta
				&& (((LeatherArmorMeta) armor.getItemMeta()).getColor().asRGB() == 0xFFFFFF || ((LeatherArmorMeta) armor.getItemMeta()).getColor().asRGB() == 0xFFFF00)
				&& armor.getItemMeta().getPersistentDataContainer().has(nk, PersistentDataType.INTEGER);
	}
	
	public static ItemStack getArmorHelmet(boolean white) {
		ItemStack is = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta im = is.getItemMeta();
		LeatherArmorMeta lam = (LeatherArmorMeta) im;
		if(white) lam.setColor(Color.fromRGB(0xFFFFFF));
		else lam.setColor(Color.fromRGB(0xFFFF00));
		lam.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "lead-armor"), PersistentDataType.INTEGER, 1);
		lam.setDisplayName(ChatColor.RED + "Hełm ołowiowy");
		is.setItemMeta(lam);
		return is;
	}

	public static ItemStack getArmorChestplate(boolean white) {
		ItemStack is = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta im = is.getItemMeta();
		LeatherArmorMeta lam = (LeatherArmorMeta) im;
		if(white) lam.setColor(Color.fromRGB(0xFFFFFF));
		else lam.setColor(Color.fromRGB(0xFFFF00));
		lam.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "lead-armor"), PersistentDataType.INTEGER, 1);
		lam.setDisplayName(ChatColor.RED + "Klata ołowiowy");
		is.setItemMeta(lam);
		return is;
	}

	public static ItemStack getArmorLeggings(boolean white) {
		ItemStack is = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemMeta im = is.getItemMeta();
		LeatherArmorMeta lam = (LeatherArmorMeta) im;
		if(white) lam.setColor(Color.fromRGB(0xFFFFFF));
		else lam.setColor(Color.fromRGB(0xFFFF00));
		lam.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "lead-armor"), PersistentDataType.INTEGER, 1);
		lam.setDisplayName(ChatColor.RED + "Nogawice ołowiowy");
		is.setItemMeta(lam);
		return is;
	}

	public static ItemStack getArmorBoots(boolean white) {
		ItemStack is = new ItemStack(Material.LEATHER_BOOTS);
		ItemMeta im = is.getItemMeta();
		LeatherArmorMeta lam = (LeatherArmorMeta) im;
		if(white) lam.setColor(Color.fromRGB(0xFFFFFF));
		else lam.setColor(Color.fromRGB(0xFFFF00));
		lam.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "lead-armor"), PersistentDataType.INTEGER, 1);
		lam.setDisplayName(ChatColor.RED + "Buty ołowiowy");
		is.setItemMeta(lam);
		return is;
	}
	
	public static void registerCraftings() {
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta) helmet.getItemMeta();
		lam.setColor(Color.fromRGB(0xF9FFFE));
		helmet.setItemMeta(lam);
		ShapedRecipe sr = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "white-lead-helmet"), getArmorHelmet(true));
		sr = sr.shape("ooo", "oho", "ooo").setIngredient('o', LeadTool.getLeadNugget()).setIngredient('h', helmet);
		Bukkit.addRecipe(sr);
		
		lam.setColor(Color.fromRGB(0xFED83D));
		helmet.setItemMeta(lam);
		sr = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "yellow-lead-helmet"), getArmorHelmet(false));
		sr = sr.shape("ooo", "oho", "ooo").setIngredient('o', LeadTool.getLeadNugget()).setIngredient('h', helmet);
		Bukkit.addRecipe(sr);
		
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		lam = (LeatherArmorMeta) helmet.getItemMeta();
		lam.setColor(Color.fromRGB(0xF9FFFE));
		chestplate.setItemMeta(lam);
		sr = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "white-lead-chestplate"), getArmorChestplate(true));
		sr = sr.shape("ooo", "oho", "ooo").setIngredient('o', LeadTool.getLeadNugget()).setIngredient('h', chestplate);
		Bukkit.addRecipe(sr);
		
		lam.setColor(Color.fromRGB(0xFED83D));
		chestplate.setItemMeta(lam);
		sr = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "yellow-lead-chestplate"), getArmorChestplate(false));
		sr = sr.shape("ooo", "oho", "ooo").setIngredient('o', LeadTool.getLeadNugget()).setIngredient('h', chestplate);
		Bukkit.addRecipe(sr);
		
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		lam = (LeatherArmorMeta) leggings.getItemMeta();
		lam.setColor(Color.fromRGB(0xF9FFFE));
		leggings.setItemMeta(lam);
		sr = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "white-lead-leggings"), getArmorLeggings(true));
		sr = sr.shape("ooo", "oho", "ooo").setIngredient('o', LeadTool.getLeadNugget()).setIngredient('h', leggings);
		Bukkit.addRecipe(sr);
		
		lam.setColor(Color.fromRGB(0xFED83D));
		leggings.setItemMeta(lam);
		sr = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "yellow-lead-leggings"), getArmorLeggings(false));
		sr = sr.shape("ooo", "oho", "ooo").setIngredient('o', LeadTool.getLeadNugget()).setIngredient('h', leggings);
		Bukkit.addRecipe(sr);
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
		lam = (LeatherArmorMeta) boots.getItemMeta();
		lam.setColor(Color.fromRGB(0xF9FFFE));
		boots.setItemMeta(lam);
		sr = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "white-lead-boots"), getArmorBoots(true));
		sr = sr.shape("ooo", "oho", "ooo").setIngredient('o', LeadTool.getLeadNugget()).setIngredient('h', boots);
		Bukkit.addRecipe(sr);
		
		lam.setColor(Color.fromRGB(0xFED83D));
		boots.setItemMeta(lam);
		sr = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "yellow-lead-boots"), getArmorBoots(false));
		sr = sr.shape("ooo", "oho", "ooo").setIngredient('o', LeadTool.getLeadNugget()).setIngredient('h', boots);
		Bukkit.addRecipe(sr);
	}

	public static void unregisterCrafting() {
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "white-lead-helmet"));
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "yellow-lead-helmet"));
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "white-lead-chestplate"));
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "yellow-lead-chestplate"));
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "white-lead-leggings"));
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "yellow-lead-leggings"));
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "white-lead-boots"));
		Bukkit.removeRecipe(new NamespacedKey(Main.getInstance(), "yellow-lead-boots"));
	}

}
