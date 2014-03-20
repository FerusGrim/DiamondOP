package io.github.ferusgrim.DiamondOP;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DiamondOP extends JavaPlugin {
	
	public boolean areDiamondsOP = true;

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BlockManager(this), this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String command = cmd.getName().toLowerCase();
		if(command.equals("diamondop")){
			return CommandHandler(sender);
		}
		return true;
	}
	
	public boolean CommandHandler(CommandSender sender){
		Player player = null;
		boolean hasPerm = false;
		if(sender instanceof Player){
			player = (Player) sender;
			if(player.hasPermission("diamondop.set")){
				hasPerm = true;
			}else{
				hasPerm = false;
			}
		}else{
			hasPerm = true;
		}
		if(hasPerm){
			if(areDiamondsOP){
				areDiamondsOP = false;
				sender.sendMessage("[DiamondOP] Diamond Pickaxes can now take damage!");
				return true;
			}else{
				areDiamondsOP = true;
				sender.sendMessage("[DiamondOP] Diamond Pickaxes no longer take damage!");
				return true;
			}
		}
		sender.sendMessage("Sorry! You do not have permission to do this!");
		return true;
	}
	
	public void setDurability(Player player){
		ItemStack item = player.getItemInHand();
		if(areDiamondsOP){
			if(item.getType() == Material.DIAMOND_PICKAXE){
				item.setDurability((short) 0);
			}
		}
	}
}
