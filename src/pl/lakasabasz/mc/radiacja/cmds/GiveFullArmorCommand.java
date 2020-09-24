package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.lakasabasz.mc.radiacja.tools.ArmorTool;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class GiveFullArmorCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_ONLY_PLAYER));
			return;
		}
		
		if(args.length == 1) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_TOOLOW_ARGS));
			return;
		} else if(args.length > 2) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_TOOMANY_ARGS));
			return;
		}
		
		ItemStack helmet, chestplate, leggings, boots;
		if(args[1].equalsIgnoreCase("żółty")) {
			helmet = ArmorTool.getArmorHelmet(false);
			chestplate = ArmorTool.getArmorChestplate(false);
			leggings = ArmorTool.getArmorLeggings(false);
			boots = ArmorTool.getArmorBoots(false);
		} else if(args[1].equalsIgnoreCase("biały")) {
			helmet = ArmorTool.getArmorHelmet(true);
			chestplate = ArmorTool.getArmorChestplate(true);
			leggings = ArmorTool.getArmorLeggings(true);
			boots = ArmorTool.getArmorBoots(true);
		} else {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_WRONG_ARG));
			return;
		}
		
		((HumanEntity) sender).getInventory().addItem(helmet);
		((HumanEntity) sender).getInventory().addItem(chestplate);
		((HumanEntity) sender).getInventory().addItem(leggings);
		((HumanEntity) sender).getInventory().addItem(boots);
	}

	@Override
	public String getKeyWord() {
		// TODO Auto-generated method stub
		return "givefullarmor";
	}

	@Override
	public String getUsing() {
		// TODO Auto-generated method stub
		return "givefullarmor <biały/żółty>";
	}

	@Override
	public String getDescription() {
		return "Daje pełną zbroję ołowiową";
	}

}
