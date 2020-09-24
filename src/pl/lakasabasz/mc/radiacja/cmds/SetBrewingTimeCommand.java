package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.lakasabasz.mc.radiacja.Main;

public class SetBrewingTimeCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		Block b = p.getTargetBlockExact(5);
		BrewingStand bs = (BrewingStand) b.getState();
		int old = bs.getBrewingTime();
		bs.setBrewingTime(Integer.parseInt(args[1]));
		sender.sendMessage(bs.update(true) + "\t" + bs.getPersistentDataContainer().getKeys());
		sender.sendMessage("I seted " + bs.getBrewingTime() + " (old) " + old);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Block b = p.getTargetBlockExact(5);
				BrewingStand bs = (BrewingStand) b.getState();
				int old = bs.getBrewingTime();
				bs.setBrewingTime(Integer.parseInt(args[1]));
				sender.sendMessage(bs.update(true) + "\t" + bs.getPersistentDataContainer().getKeys());
				sender.sendMessage("I seted " + bs.getBrewingTime() + " (old) " + old);
			}
		}, 1);
	}

	@Override
	public String getKeyWord() {
		return "sbt";
	}

	@Override
	public String getUsing() {
		return "sbt <time>";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Komenda ustawia brewing time";
	}

}
