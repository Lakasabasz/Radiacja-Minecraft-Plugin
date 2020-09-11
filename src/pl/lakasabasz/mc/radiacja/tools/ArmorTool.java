package pl.lakasabasz.mc.radiacja.tools;

import java.util.Objects;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
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

}
