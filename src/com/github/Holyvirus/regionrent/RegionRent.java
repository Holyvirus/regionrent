package com.github.Holyvirus.regionrent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import cosine.boseconomy.BOSEconomy;

public class RegionRent extends JavaPlugin{

	YamlConfiguration config = null;
	public static final Logger log = Logger.getLogger("Minecraft");
	public static int rent = 100;
	private BOSEconomy bose;
	private WorldGuardPlugin WorldGuard;
	private boolean bBoseconomy = true;
	private boolean bWorldGuard = true;
	private static Server Server;
	private RPluginListener rplugl;
	public static RegionRent RR;
	
	RWGhelper RWGobject = new RWGhelper();
	RMoneyTaker RMTobject = new RMoneyTaker();
	RChecker RCobject = new RChecker();
	RWriter RWobject = new RWriter(null);
	
	public RegionRent() {
		setRR();
	}
	public void setRR() {
		RR = this;
	}
	public Server getSrvr(){
		return getServer();
	}
	public void onDisable(){
		log.log(Level.INFO, "[RegionRent] Disabled.");
	}
	public void onEnable(){
		Server = getServer();
		this.rplugl = new RPluginListener(this);
	    PluginManager localPluginManager = getServer().getPluginManager();
	    localPluginManager.registerEvents(this.rplugl, this);
	    Object localObject;
	    if (this.bBoseconomy == true)
	    {
	      if (this.bose == null)
	      {
	        localObject = (BOSEconomy)Server.getPluginManager().getPlugin("BOSEconomy");
	        if ((localObject != null) && (((Plugin)localObject).isEnabled()))
	        {
	          setBose((BOSEconomy)localObject);
	          if (this.bose == null)
	            log.log(Level.WARNING, "[RegionRent] Failed to hook into BOSEconomy!");
	          else
	            log.log(Level.INFO, "[RegionRent] Succesfully hooked into BOSEconomy!");
	        }
	      }
	    }
	    if ((this.bWorldGuard == true) && (this.WorldGuard == null))
	    {
	      localObject = (WorldGuardPlugin)Server.getPluginManager().getPlugin("iConomy");
	      if ((localObject != null) && (((Plugin)localObject).isEnabled()))
	      {
	        setWorldGuard((WorldGuardPlugin)localObject);
	        if (this.WorldGuard == null)
	          log.log(Level.WARNING, "[RegionRent] Failed to hook into iConomy!");
	        else
	          log.log(Level.INFO, "[RegionRent] Succesfully hooked into iConomy!");
	      }
	    }
	    log.log(Level.INFO, "[RegionRent] Enabled.");
	  }
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("rent")) {
			if(args.length >= 1) {
				 if (args[0].equalsIgnoreCase("collect")){
					 if (sender.hasPermission("regionrent.collect")) {
						 RWGobject.obtainRegionOwners();
						 if(RWGobject.obtainRegionOwners().size() >= 1) {
							 RMTobject.takeMoney();
							 log.log(Level.INFO, "Rent has been collected by: " + sender.getName() + "!");
							 sender.sendMessage(ChatColor.AQUA + "Rent has been successfully collected!");
						 }else{
							 log.log(Level.SEVERE, "[RegionRent] No regions detected!");
							 sender.sendMessage(ChatColor.YELLOW + "Your server does not have any regions!");
						 }
					 }else{
						 sender.sendMessage(ChatColor.RED + "You do not have permission to collect rent!");
					 }
				 }else{
					 sender.sendMessage(ChatColor.YELLOW + "The correct command to collect region rent is ''/rent collect''!");
				 }
			}else{
				 sender.sendMessage(ChatColor.LIGHT_PURPLE + "     RegionRent 1.0");
				 sender.sendMessage(ChatColor.LIGHT_PURPLE + "     By: AlienArtificial");
				 sender.sendMessage(ChatColor.LIGHT_PURPLE + "     Enjoy! :)");
			}
		}
		return true;
	}
	public boolean getBoseconomyState() {
		return this.bBoseconomy;
	}
	 public void setBose(BOSEconomy paramBOSEconomy)
	  {
	    this.bose = paramBOSEconomy;
	  }
	 public BOSEconomy getBose()
	  {
	    return this.bose;
	  }
	public boolean getWorldGuardState() {
		return this.bWorldGuard;
	}
	 public void setWorldGuard(WorldGuardPlugin paramWorldGuard)
	  {
	    this.WorldGuard = paramWorldGuard;
	  }
	 public WorldGuardPlugin getWorldGuard()
	  {
	    return this.WorldGuard;
	  }
	public static RegionRent getRR() {
	    return RR;
    }
}