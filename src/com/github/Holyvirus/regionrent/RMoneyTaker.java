package com.github.Holyvirus.regionrent;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.github.Holyvirus.regionrent.RWGhelper;
import com.sk89q.worldguard.domains.DefaultDomain;

import cosine.boseconomy.BOSEconomy;

public class RMoneyTaker {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	
	public void takeMoney(){
		BOSEconomy BE = RegionRent.getRR().getBose();
		RWGhelper RWGobject = new RWGhelper();
		for(DefaultDomain owners : RWGobject.obtainRegionOwners()) {
			for(String owner : owners.getPlayers()) {
				double balance = BE.getPlayerMoneyDouble(owner);
				if (BE.getPlayerMoneyDouble(owner) >= 100) {
					BE.setPlayerMoney(owner, (balance - 100), false);
					Player Player = RegionRent.getRR().getServer().getPlayer(owner);
					if (Player != null) {
						Player.sendMessage(ChatColor.GREEN + "You have been charged $100 for your shop!");
					}
			    }else{
					Player Player = RegionRent.getRR().getServer().getPlayer(owner);
					owners.removePlayer(owner);
					if(Player != null) {
						Player.sendMessage(ChatColor.DARK_RED + "You have lost your shop because you couldent pay the weekly rent!");
						Player.sendMessage(ChatColor.RED + "Contact AlienArtificial for more info!");
					}
			    }
			}
		}
	}
}