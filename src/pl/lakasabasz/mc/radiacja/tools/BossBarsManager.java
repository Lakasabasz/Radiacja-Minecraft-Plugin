package pl.lakasabasz.mc.radiacja.tools;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarsManager {

	private static Map<BossBarType, BossBar> bossBarsDB;
	static {
		bossBarsDB = new HashMap<BossBarType, BossBar>();
	}
	public static void addPlayer(BossBarType bbt, Player p) {
		bossBarsDB.get(bbt).addPlayer(p);
	}
	public static void removePlayer(BossBarType bbt, Player p) {
		bossBarsDB.get(bbt).removePlayer(p);
	}
	public static void setBossBar(BossBarType bbt, BossBar bb) {
		bossBarsDB.put(bbt, bb);
	}
}
