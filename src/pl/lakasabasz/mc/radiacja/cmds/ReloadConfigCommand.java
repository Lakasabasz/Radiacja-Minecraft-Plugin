package pl.lakasabasz.mc.radiacja.cmds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class ReloadConfigCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		boolean eais = Main.getInstance().getConfig().getBoolean("everyAreaIsSafe");
		if(!Objects.isNull(eais)) {
			Main.getInstance().setEveryAreaIsSafe(eais);
		} else {
			Logger.sendError(Messages.getMessage(MessagesType.ERROR_LOAD_EAIS));
		}
		List<String> savedRegions = Main.getInstance().getConfig().getStringList("exceptAreas");
		if(Objects.isNull(savedRegions)) {
			Logger.sendError(Messages.getMessage(MessagesType.ERROR_LOAD_REGIONS));
			savedRegions = new ArrayList<String>();
		}
		RegionManager wgrm = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
		Main.getInstance().setRegions(new ArrayList<ProtectedRegion>());
		for(String area : savedRegions) {
			ProtectedRegion rgn = wgrm.getRegion(area);
			if(Objects.isNull(rgn)) {
				Logger.sendWarning(Messages.getMessage(MessagesType.WARRNING_CHECK_REGIONS));
			} else {
				Main.getInstance().addRegion(rgn);
			}
		}
		if(Main.getInstance().getAreas().isEmpty()) {
			Logger.sendWarning(Messages.getMessage(MessagesType.WARRNING_EMPTY_REGIONS));
		}
		
		Logger.sendInfo(sender, Messages.getMessage(MessagesType.SUCCESS_RELOAD_CONFIG));
	}

	@Override
	public String getKeyWord() {
		return "reloadconfig";
	}

	@Override
	public String getUsing() {
		// TODO Auto-generated method stub
		return "reloadconfig";
	}

	@Override
	public String getDescription() {
		return "Komenda wymuszająca ponowne załadowanie danych z config.yml";
	}

}
