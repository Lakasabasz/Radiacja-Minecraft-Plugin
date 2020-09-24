package pl.lakasabasz.mc.radiacja.events;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.brewing.BrewingRecipe;
import pl.lakasabasz.mc.radiacja.schedulers.IodyneEffect;
import pl.lakasabasz.mc.radiacja.tools.IodyneTool;

public class PotionsEffects implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
    public void potionItemPlacer(final InventoryClickEvent e) {
		if(!Main.getAdvencedBrwingStand()) return;
		
        if (e.getClickedInventory() == null)
            return;
        if (e.getClickedInventory().getType() != InventoryType.BREWING)
            return;
        if(Objects.isNull(e.getCursor())) //Make we sure that we placing item
        	return;
        ItemStack is = e.getCurrentItem(); //We want to get the item in the slot
        ItemStack is2 = e.getCursor().clone(); //And the item in the cursor
        if(testForNormalIngridients(is) || testForNormalIngridients(is2))
        	return;
        if(is2 == null || is2.getType().equals(Material.AIR)) //We make sure we got something in the cursor
            return;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
            	ItemStack is3 = is;
            	if(e.getClick().equals(ClickType.RIGHT) && (Objects.isNull(is3) || is3.getType().equals(Material.AIR) || is3.isSimilar(is2))) {
            		if(is2.getAmount() > 0) {
            			if(Objects.isNull(is3) || is3.getType().equals(Material.AIR)) {
            				is3 = is2.clone();
            				is2.setAmount(1);
            			} else if(is3.isSimilar(is2) && is3.getAmount() < 64) {
            				int isIn = is3.getAmount();
            				int isCur = is2.getAmount();
            				is3.setAmount(isCur);
            				is2.setAmount(isIn);
            			}
            			is3.setAmount(is3.getAmount()-1);
            		}
            	} else if(e.getClick().equals(ClickType.LEFT) && is3.isSimilar(is2)) {
            		if(is2.getAmount() + is3.getAmount() <= 64) {
            			is2.setAmount(is3.getAmount());
            			is3.setAmount(0);
            		}
            	}
            	e.getWhoClicked().setItemOnCursor(is3); //Now we make the switch
                e.getClickedInventory().setItem(e.getSlot(), is2);
                ((Player) e.getView().getPlayer()).updateInventory();
            }
        }, 1);//(Delay in 1 tick)
    }
	
	private boolean testForNormalIngridients(ItemStack is) {
		Material i = is.getType();
		return i == Material.NETHER_WART || i == Material.GUNPOWDER || i == Material.SPIDER_EYE || i == Material.FERMENTED_SPIDER_EYE || i == Material.RABBIT_FOOT
				|| i == Material.BLAZE_POWDER || i == Material.GOLDEN_CARROT || i == Material.GLISTERING_MELON_SLICE || i == Material.SUGAR || i == Material.REDSTONE_WIRE
				|| i == Material.MAGMA_CREAM || i == Material.TURTLE_HELMET || i == Material.PHANTOM_MEMBRANE || i == Material.GLOWSTONE || i == Material.PUFFERFISH
				|| i == Material.GHAST_TEAR;
	}

	@EventHandler(priority = EventPriority.NORMAL)
    public void potionListener(InventoryClickEvent e){
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				if(e.getClickedInventory() == null)
		            return;
		        if(e.getClickedInventory().getType() != InventoryType.BREWING)
		            return;
		        if(((BrewerInventory) e.getInventory()).getIngredient() == null)
		            return;
		        if(((BrewerInventory) e.getInventory()).getHolder().getFuelLevel() == 0)
		        	return;
		        BrewingRecipe recipe = BrewingRecipe.getRecipe((BrewerInventory) e.getClickedInventory());
		        if(recipe == null)
		            return;
		        recipe.startBrewing((BrewerInventory) e.getClickedInventory());
			}
		}, 2);
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void standardPotionWatcher(BrewEvent e) {
		if(e.getContents().getIngredient().getType().equals(Material.SUGAR)) {
			ItemStack is = new ItemStack(Material.POTION);
			PotionMeta pm = (PotionMeta) is.getItemMeta();
			pm.setBasePotionData(new PotionData(PotionType.WATER));
			is.setItemMeta(pm);
			boolean complicatedSolver = (e.getContents().getItem(0) != null && e.getContents().getItem(0).isSimilar(is))
									|| (e.getContents().getItem(1) != null && e.getContents().getItem(1).isSimilar(is))
									|| (e.getContents().getItem(2) != null && e.getContents().getItem(2).isSimilar(is));
			if(complicatedSolver) e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerDrinkPotion(PlayerItemConsumeEvent e) {
		if(!e.getItem().getType().equals(Material.POTION)) {
			if(e.getItem().getType().equals(Material.MILK_BUCKET)) {
				if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.MILK_BUCKET)) {
					e.getPlayer().getInventory().setItemInMainHand(e.getReplacement());
					e.getPlayer().updateInventory();
					PotionEffect pe = e.getPlayer().getPotionEffect(PotionEffectType.WITHER);
					for(PotionEffect pee : e.getPlayer().getActivePotionEffects()) {
						if(pe.equals(pee)) continue;
						e.getPlayer().removePotionEffect(pee.getType());
					}
					e.setCancelled(true);
				} else if(e.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.MILK_BUCKET)) {
					e.getPlayer().getInventory().setItemInOffHand(e.getReplacement());
					e.getPlayer().updateInventory();
					PotionEffect pe = e.getPlayer().getPotionEffect(PotionEffectType.WITHER);
					for(PotionEffect pee : e.getPlayer().getActivePotionEffects()) {
						if(pe.equals(pee)) continue;
						e.getPlayer().removePotionEffect(pee.getType());
					}
					e.setCancelled(true);
				}
			}
			return;
		}
		
		if(IodyneTool.testForSweetWater(e.getItem())) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,
											e.getItem().getItemMeta().getPersistentDataContainer().get(IodyneTool.getSweetWaterKey(), PersistentDataType.INTEGER),
											1));
			e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 8);
		} else if(IodyneTool.testForIodyne(e.getItem())) {
			IodyneEffect.addPlayer(e.getPlayer(), e.getItem().getItemMeta().getPersistentDataContainer().get(IodyneTool.getIodyneKey(), PersistentDataType.INTEGER));
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void playerRadiationDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player && e.getCause().equals(DamageCause.WITHER)) {
			if(e.getEntity().getPersistentDataContainer().has(IodyneEffect.getIodyneKey(), PersistentDataType.INTEGER)) {
				e.setCancelled(true);
			}
		}
	}
}
