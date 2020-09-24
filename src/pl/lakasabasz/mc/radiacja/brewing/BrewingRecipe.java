package pl.lakasabasz.mc.radiacja.brewing;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

public class BrewingRecipe {
	
	private static List<BrewingRecipe> recipes = new ArrayList<BrewingRecipe>();
    private ItemStack ingridient;
    private BrewAction action;
    private boolean perfect;

    public BrewingRecipe(ItemStack ingridient , BrewAction action , boolean perfect) {
        this.ingridient = ingridient;
        this.action = action;
        this.perfect = perfect;
        recipes.add(this);
    }
 
    public BrewingRecipe(Material ingridient , BrewAction action)
    {
        this(new ItemStack(ingridient), action, false);
    }
    
    public ItemStack getIngridient() {
        return ingridient;
    }

    public BrewAction getAction() {
        return action;
    }

    public boolean isPerfect() {
        return perfect;
    }
    
    /**
     * Get the BrewRecipe of the given recipe , will return null if no recipe is found
     * @param inventory The inventory
     * @return The recipe
     */
    @Nullable
    public static BrewingRecipe getRecipe(BrewerInventory inventory)
    {
        for(BrewingRecipe recipe : recipes)
        {
            if(!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngridient().getType())
            {
                return recipe;
            }
            if(recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngridient()))
            {
                return recipe;
            }
        }
        return null;
    }
    
    public void startBrewing(BrewerInventory inventory)
    {
        new BrewClock(this, inventory);
    }

}
