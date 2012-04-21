package com.github.Holyvirus.regionrent;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import cosine.boseconomy.BOSEconomy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

public class RPluginListener implements Listener{
	 
	public RPluginListener(RegionRent paramRegionRent)
	  {
	    this.plugin = paramRegionRent;
	  }
	
	public static final Logger log = Logger.getLogger("Minecraft");
	BOSEconomy BOSE = null;
	WorldGuardPlugin WGP = null;
	private final RegionRent plugin;

	@EventHandler
	  public void onPluginsEnable(PluginEnableEvent paramPluginEnableEvent)
	  {
	    Object localObject;
	    if  ((paramPluginEnableEvent.getPlugin().getDescription().getName().equals("BOSEconomy")) && (this.plugin.getBoseconomyState()))
	    {
	      localObject = (BOSEconomy)paramPluginEnableEvent.getPlugin();
	      if ((localObject != null) && (((Plugin)localObject).isEnabled()))
	      {
	        this.plugin.setBose((BOSEconomy)localObject);
	        this.plugin.getLogger().log(Level.INFO, "attached to BOSEconomy.");
	      }
	    }
	    if  ((paramPluginEnableEvent.getPlugin().getDescription().getName().equals("WorldGuard")) && (this.plugin.getWorldGuardState()))
	    {
	      localObject = (BOSEconomy)paramPluginEnableEvent.getPlugin();
	      if ((localObject != null) && (((Plugin)localObject).isEnabled()))
	      {
	        this.plugin.setBose((BOSEconomy)localObject);
	        this.plugin.getLogger().log(Level.INFO, "attached to WorldGuard.");
	      }
	    }
	  }
	@EventHandler
	  public void onPluginsDisable(PluginDisableEvent paramPluginDisableEvent)
	  {
	    if (paramPluginDisableEvent.getPlugin().getDescription().getName().equals("BOSEconomy") && (this.plugin.getBoseconomyState()))
	    {
	      this.plugin.setBose(null);
	      this.plugin.getLogger().log(Level.INFO, "lost connection to BOSEconomy.");
	    }
	    if (paramPluginDisableEvent.getPlugin().getDescription().getName().equals("WorldGuard") && (this.plugin.getWorldGuardState())) {
	    	this.plugin.setBose(null);
		      this.plugin.getLogger().log(Level.INFO, "lost connection to WorldGuard.");
	    }
	  }
}