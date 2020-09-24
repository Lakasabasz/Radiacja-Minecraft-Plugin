package pl.lakasabasz.mc.radiacja.schedulers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.tools.BossBarType;
import pl.lakasabasz.mc.radiacja.tools.BossBarsManager;

public class IodyneEffect implements Runnable {

	public static Map<Player, Integer> iodyneAffected;
	static {
		iodyneAffected = new HashMap<Player, Integer>();
	}
	
	@Override
	public void run() {
		for(Player p : iodyneAffected.keySet()) {
			if(iodyneAffected.get(p) == 0) {
				p.getPersistentDataContainer().remove(getIodyneKey());
				iodyneAffected.remove(p);
				BossBarsManager.removePlayerPrivate(p);
				continue;
			}
			int time = iodyneAffected.get(p) - 1;
			p.getPersistentDataContainer().set(getIodyneKey(), PersistentDataType.INTEGER, time);
			BossBarsManager.setValuePrivate(p, time);
			iodyneAffected.put(p, time);
		}

	}

	public static NamespacedKey getIodyneKey() {
		return new NamespacedKey(Main.getInstance(), "iodyne-affeted");
	}

	public static void addPlayer(Player player, int duration) {
		if(iodyneAffected.containsKey(player)) {
			int time = iodyneAffected.get(player);
			iodyneAffected.replace(player, time+duration);
			BossBarsManager.setValuePrivate(player, iodyneAffected.get(player));
			player.getPersistentDataContainer().set(getIodyneKey(), PersistentDataType.INTEGER, time+duration);
		} else {
			iodyneAffected.put(player, duration);
			BossBarsManager.addPlayerPrivate(BossBarType.IODYNE_EFFECT, player, duration);
			player.getPersistentDataContainer().set(getIodyneKey(), PersistentDataType.INTEGER, duration);
		}
	}

}
