package com.github.Holyvirus.regionrent;

import org.bukkit.entity.Player;

import com.github.Holyvirus.regionrent.RWGhelper;
import com.sk89q.worldguard.domains.DefaultDomain;

import cosine.boseconomy.BOSEconomy;

public class RMoneyTaker {
	
	private BOSEconomy BE;
	RWGhelper RWGobject = new RWGhelper();
	
	public void takeMoney(){
		for(DefaultDomain owners : RWGobject.obtainRegionOwners()) {
			for(String owner : owners.getPlayers()) {
				double balance = BE.getPlayerMoneyDouble(owner);
				
				if (BE.getPlayerMoneyDouble(owner) > 100) {
					BE.setPlayerMoney(owner, (balance - 100), false);
					Player Player = RegionRent.getPlugin().getServer().getPlayer(owner);
					Player.sendMessage("You have been charged $100 for your shop!");
			    }else{
					Player Player = RegionRent.getPlugin().getServer().getPlayer(owner);
					owners.removePlayer(owner);
					Player.sendMessage("You have lost your shop because you couldent pay the rent!");
			    }
			}
		}
	}
}
