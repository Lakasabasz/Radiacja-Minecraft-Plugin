package pl.lakasabasz.mc.radiacja.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class Logger {

	public static void sendInfo(String log) {
		CommandSender sender = Bukkit.getConsoleSender();
		sender.sendMessage(Messages.getMessage(MessagesType.INTRO) + ChatColor.GREEN + "[INFO] " + log);
	}

	public static void sendInfo(CommandSender sender, String log) {
		sender.sendMessage(Messages.getMessage(MessagesType.INTRO) + ChatColor.GREEN + "[INFO] " + log);	
	}

	public static void sendWarning(String log) {
		CommandSender sender = Bukkit.getConsoleSender();
		sender.sendMessage(Messages.getMessage(MessagesType.INTRO) + ChatColor.GOLD + "[WARRNING] " + log);
	}

	public static void sendWarning(CommandSender sender, String log) {
		sender.sendMessage(Messages.getMessage(MessagesType.INTRO) + ChatColor.GOLD + "[WARRNING] " + log);	
	}

	public static void sendError(String log) {
		CommandSender sender = Bukkit.getConsoleSender();
		sender.sendMessage(Messages.getMessage(MessagesType.INTRO) + ChatColor.DARK_RED + "[ERROR] " + log);
	}

	public static void sendError(CommandSender sender, String log) {
		sender.sendMessage(Messages.getMessage(MessagesType.INTRO) + ChatColor.DARK_RED + "[ERROR] " + log);
	}

	public static void sendDebug(String log) {
		CommandSender sender = Bukkit.getConsoleSender();
		sender.sendMessage(Messages.getMessage(MessagesType.INTRO) + ChatColor.BLUE + "[DEBUG] " + log);
	}
}
