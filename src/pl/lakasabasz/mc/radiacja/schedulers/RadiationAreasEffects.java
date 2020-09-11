package pl.lakasabasz.mc.radiacja.schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.tools.ArmorTool;
import pl.lakasabasz.mc.radiacja.tools.BossBarType;
import pl.lakasabasz.mc.radiacja.tools.BossBarsManager;
import pl.lakasabasz.mc.radiacja.tools.Logger;

public class RadiationAreasEffects implements Runnable {
	
	private static List<Player> knownPlayersInRadiation;
	static {
		knownPlayersInRadiation = new ArrayList<Player>();
	}
	
	@Override
	public void run() {
		List<Player> playersInRadiation = new ArrayList<Player>();
		for(Player p : Bukkit.getWorld("world").getPlayers()) {
			for(ProtectedRegion pr : Main.getInstance().getAreas()) {
				if(pr.contains(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ())) {
					playersInRadiation.add(p);
					break;
				}
			}
		}
		
		for(Player p : knownPlayersInRadiation) {
			if(!playersInRadiation.contains(p)) {
				BossBarsManager.removePlayer(BossBarType.RADIATION_AREA, p);
			}
		}
		
		for(Player p : playersInRadiation) {
			if(!knownPlayersInRadiation.contains(p)) {
				BossBarsManager.addPlayer(BossBarType.RADIATION_AREA, p);
			}
			
			int impactLvl = 4;
			if(ArmorTool.test(p.getInventory().getHelmet())) {
				impactLvl--;
				ItemMeta im = p.getInventory().getHelmet().getItemMeta();
				Logger.sendDebug("dim.getDamage() " + ((Damageable) im).getDamage());
				((Damageable) im).setDamage(((Damageable) im).getDamage()+1);
				p.getInventory().getHelmet().setItemMeta(im);
			}
			if(ArmorTool.test(p.getInventory().getChestplate())) {
				impactLvl--;
				((Damageable) p.getInventory().getChestplate().getItemMeta()).setDamage(((Damageable) p.getInventory().getChestplate().getItemMeta()).getDamage()-1);
			}
			if(ArmorTool.test(p.getInventory().getLeggings())) {
				impactLvl--;
				((Damageable) p.getInventory().getLeggings().getItemMeta()).setDamage(((Damageable) p.getInventory().getLeggings().getItemMeta()).getDamage()-1);
			}
			if(ArmorTool.test(p.getInventory().getBoots())) {
				((Damageable) p.getInventory().getBoots().getItemMeta()).setDamage(((Damageable) p.getInventory().getBoots().getItemMeta()).getDamage()-1);
				impactLvl--;
			}
			if(impactLvl == 0) continue;
			
			PotionEffect pe = p.getPotionEffect(PotionEffectType.WITHER);
			if(Objects.isNull(pe)) pe = new PotionEffect(PotionEffectType.WITHER, 0, impactLvl-1);
			if(pe.getAmplifier() > (impactLvl-1)) {
				pe = new PotionEffect(PotionEffectType.WITHER, pe.getDuration()+(2*20), pe.getAmplifier());
			} else {
				pe = new PotionEffect(PotionEffectType.WITHER, pe.getDuration()+(2*20), impactLvl-1);
			}
			p.removePotionEffect(PotionEffectType.WITHER);
			p.addPotionEffect(pe);
		}
		
		knownPlayersInRadiation = playersInRadiation;
	}

}
