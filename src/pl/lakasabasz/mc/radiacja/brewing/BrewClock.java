package pl.lakasabasz.mc.radiacja.brewing;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.tools.ItemStackTool;

public class BrewClock implements Runnable {

	private BrewerInventory inventory;
    private BrewingRecipe recipe;
    private ItemStack ingridient;
    private BrewingStand stand;
    private int time = 400; //Like I said the starting time is 400

    private static Map<Location, BukkitTask> timers;
    static {
    	timers = new HashMap<Location, BukkitTask>();
    }
 
    public BrewClock(BrewingRecipe recipe , BrewerInventory inventory) {
        this.recipe = recipe;
        this.inventory = inventory;
        this.ingridient = inventory.getIngredient();
        this.stand = inventory.getHolder();
        if(timers.containsKey(inventory.getHolder().getBlock().getLocation())) return;
        
        timers.put(inventory.getHolder().getBlock().getLocation(), Bukkit.getScheduler().runTaskTimer(Main.getInstance(), this, 0, 1));
    }
 
    public void run() {
        if(time <= 0)
        {
            inventory.setIngredient(new ItemStack(Material.AIR));
            for(int i = 0; i < 3 ; i ++)
            {
                if(inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR)
                    continue;
                recipe.getAction().brew(inventory, inventory.getItem(i), ingridient);
            }
            cancelIt(inventory.getHolder().getBlock().getLocation());;
        }
        boolean ingredientTest = !ItemStackTool.isEmpty(inventory.getIngredient()) && inventory.getIngredient().isSimilar(ingridient);
        boolean fullEmptyTest = (ItemStackTool.isEmpty(inventory.getItem(0)) && ItemStackTool.isEmpty(inventory.getItem(1)) && ItemStackTool.isEmpty(inventory.getItem(2)));
        if(!ingredientTest || fullEmptyTest)
        {
            stand.setBrewingTime(400); //Reseting everything
            cancelIt(inventory.getHolder().getBlock().getLocation());
            return;
        }
        time--;
        stand.setBrewingTime(time);
        stand.update();
    }

	private void cancelIt(Location location) {
		if(!timers.containsKey(location)) return;
		timers.get(location).cancel();
		timers.remove(location);
	}
}

