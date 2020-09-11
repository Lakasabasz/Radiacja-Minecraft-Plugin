package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.command.CommandSender;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class RemoveRegionCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 1) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_TOOLOW_ARGS));
			return;
		} else if(args.length > 2) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_TOOMANY_ARGS));
			return;
		}
		
		if(!Main.getInstance().removeArea(args[1])) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_NOTFOUND_REGION));
		} else {
			Logger.sendInfo(sender, Messages.getMessage(MessagesType.SUCCESS_REMOVE_REGION));
		}
	}

	@Override
	public String getKeyWord() {
		return "remove";
	}

	@Override
	public String getUsing() {
		return "remove <regionID>";
	}

	@Override
	public String getDescription() {
		return "Komenda usuwa region z listy aktywnych";
	}

}
