package com.github.Holyvirus.regionrent;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.World;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RWGhelper {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	private static final World world = null;
	private WorldGuardPlugin WG = RegionRent.getPlugin().getWorldGuard();
	RegionManager rm = WG.getRegionManager(world);
	Map<String, ProtectedRegion> regions = rm.getRegions();
	public ArrayList<DefaultDomain> obtainRegionOwners(){
		ArrayList<DefaultDomain> owners = new ArrayList<DefaultDomain>();
		for(Map.Entry<String, ProtectedRegion> entry : regions.entrySet()) { 
			ProtectedRegion p = entry.getValue();
			owners.add(p.getOwners());
		}		
		return owners;
	}
}
