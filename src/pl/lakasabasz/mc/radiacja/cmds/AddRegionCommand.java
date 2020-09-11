package pl.lakasabasz.mc.radiacja.cmds;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import pl.lakasabasz.mc.radiacja.Main;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class AddRegionCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 1) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_TOOLOW_ARGS));
			return;
		} else if(args.length > 2) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_TOOMANY_ARGS));
			return;
		}
		
		String regionID = args[1];
		ProtectedRegion pr = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(Bukkit.getWorld("world"))).getRegion(regionID);
		if(Objects.isNull(pr)) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_NOTEXIST_REGION));
			return;
		}
		
		if(!Main.getInstance().addRegion(pr)) {
			Logger.sendWarning(sender, Messages.getMessage(MessagesType.WARRNING_ADDEXISTING_REGION));
			return;
		}
		Logger.sendInfo(sender, Messages.getMessage(MessagesType.SUCCESS_ADDED_REGION));
	}

	@Override
	public String getKeyWord() {
		return "add";
	}

	@Override
	public String getUsing() {
		return "add <regionID>";
	}

	@Override
	public String getDescription() {
		return "Dodaje region do listy aktywnych";
	}

}
