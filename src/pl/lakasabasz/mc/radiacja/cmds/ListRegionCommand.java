package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.command.CommandSender;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.tools.Logger;

public class ListRegionCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		String list = "Dostępne regiony:\n";
		for(int i = 0; i < Main.getInstance().getAreas().size(); i++) {
			list += (i+1) + ". " + Main.getInstance().getAreas().get(i).getId() + "\n";
		}
		Logger.sendInfo(sender, list);
	}

	@Override
	public String getKeyWord() {
		return "list";
	}

	@Override
	public String getUsing() {
		return "list";
	}

	@Override
	public String getDescription() {
		return "Wyświetla listę aktywnych regionów";
	}

}
