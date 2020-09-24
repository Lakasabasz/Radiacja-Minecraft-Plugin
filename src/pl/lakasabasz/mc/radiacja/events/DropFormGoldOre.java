package pl.lakasabasz.mc.radiacja.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import pl.lakasabasz.mc.radiacja.tools.LeadTool;

public class DropFormGoldOre implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerBreakBlock(BlockBreakEvent e) {
		if(e.getBlock().getType().equals(Material.GOLD_ORE)) {
			ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
			if(is.getType().equals(Material.IRON_PICKAXE) || is.getType().equals(Material.DIAMOND_PICKAXE)) {
				Random r = new Random();
				if(is.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
					if(r.nextBoolean() && r.nextBoolean() && r.nextBoolean() && r.nextBoolean()) {
						Bukkit.getWorld("world").dropItemNaturally(e.getBlock().getLocation(), LeadTool.getLeadOre());
					}
				} else if(is.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
					if(r.nextBoolean() && r.nextBoolean() && r.nextBoolean()) {
						Bukkit.getWorld("world").dropItemNaturally(e.getBlock().getLocation(), LeadTool.getLeadOre());
					}
				} else if(is.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
					if(r.nextBoolean() && r.nextBoolean()) {
						Bukkit.getWorld("world").dropItemNaturally(e.getBlock().getLocation(), LeadTool.getLeadOre());
					}
				} else if(is.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
					if(r.nextBoolean()) {
						Bukkit.getWorld("world").dropItemNaturally(e.getBlock().getLocation(), LeadTool.getLeadOre());
					}
				}
			}
		}
	}
}
