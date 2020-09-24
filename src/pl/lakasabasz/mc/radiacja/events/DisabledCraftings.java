package pl.lakasabasz.mc.radiacja.events;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import pl.lakasabasz.mc.radiacja.tools.LeadTool;

public class DisabledCraftings implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCraftItem(PrepareItemCraftEvent e) {
		if(Objects.isNull(e.getRecipe())) return;
		if(e.getRecipe().getResult().getType().equals(Material.LANTERN)) {
			if(e.getInventory().contains(LeadTool.getLeadNugget())) {
				e.getInventory().setResult(new ItemStack(Material.AIR));
			}
		} else if(e.getRecipe().getResult().getType().equals(Material.IRON_INGOT)) {
			if(e.getInventory().contains(LeadTool.getLeadNugget())) {
				e.getInventory().setResult(new ItemStack(Material.AIR));
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemSmelt(FurnaceSmeltEvent e) {
		if(e.getBlock().getType().equals(Material.BLAST_FURNACE)) {
			ItemStack is = e.getSource();
			boolean lead = LeadTool.testForLeadOre(is);
			if(lead) e.setResult(LeadTool.getLeadNugget());
			else e.setResult(new ItemStack(Material.GOLD_INGOT));
		} else if(e.getBlock().getType().equals(Material.FURNACE) || e.getBlock().getType().equals(Material.SMOKER)) {
			ItemStack is = e.getSource();
			boolean lead = LeadTool.testForLeadOre(is);
			if(lead) e.setResult(new ItemStack(Material.AIR));
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemBurn(FurnaceBurnEvent e) {
		Furnace f = (Furnace) e.getBlock().getState();
		if(Objects.isNull(f.getInventory().getResult())) {
			return;
		}
		boolean lead = LeadTool.testForLeadOre(f.getInventory().getSmelting());
		if(lead ^ LeadTool.testForLeadIngot(f.getInventory().getResult())) {
			e.setCancelled(true);
		}
	}
	
}
