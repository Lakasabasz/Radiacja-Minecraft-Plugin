package pl.lakasabasz.mc.radiacja.cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class SaveConfigCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		Main plugin = Main.getInstance();
		List<String> areasID = new ArrayList<String>();
		for(ProtectedRegion pr : plugin.getAreas()) {
			areasID.add(pr.getId());
			Logger.sendDebug(pr.getId());
		}
		plugin.getConfig().set("exceptAreas", areasID);
		plugin.getConfig().set("everyAreaIsSafe", plugin.getEveryAreaIsSafe());
		plugin.saveConfig();
		Logger.sendInfo(sender, Messages.getMessage(MessagesType.SUCCESS_SAVE_CONFIG));
	}

	@Override
	public String getKeyWord() {
		return "saveconfig";
	}

	@Override
	public String getUsing() {
		return "saveconfig";
	}

	@Override
	public String getDescription() {
		return "Komenda wymuszająca natychmiastowe zapisanie konfiguracji pluginu";
	}

}
