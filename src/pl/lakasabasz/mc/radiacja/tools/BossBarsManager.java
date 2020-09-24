package pl.lakasabasz.mc.radiacja.tools;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarsManager {

	private static Map<BossBarType, BossBar> bossBarsDB;
	private static Map<Player, BossBar> privateBossBarsDB;
	static {
		bossBarsDB = new HashMap<BossBarType, BossBar>();
		privateBossBarsDB = new HashMap<Player, BossBar>();
	}
	public static void addPlayer(BossBarType bbt, Player p) {
		bossBarsDB.get(bbt).addPlayer(p);
	}
	public static void removePlayer(BossBarType bbt, Player p) {
		bossBarsDB.get(bbt).removePlayer(p);
	}
	public static void addBossBar(BossBarType bbt, BossBar bb) {
		bossBarsDB.put(bbt, bb);
	}
	
	public static void addPlayerPrivate(BossBarType bbt, Player player, int value) {
		BossBar tmp = bossBarsDB.get(bbt);
		privateBossBarsDB.put(player, Bukkit.createBossBar(tmp.getTitle(), tmp.getColor(), tmp.getStyle(), BarFlag.DARKEN_SKY));
		privateBossBarsDB.get(player).addPlayer(player);
		privateBossBarsDB.get(player).setProgress((value/12000.0>1.0)?1.0:(value/12000.0));
	}
	
	public static void setValuePrivate(Player player, int value) {
		privateBossBarsDB.get(player).setProgress((value/12000.0>1.0)?1.0:(value/12000.0));
	}
	
	public static void removePlayerPrivate(Player player) {
		privateBossBarsDB.get(player).removeAll();
	}
}
