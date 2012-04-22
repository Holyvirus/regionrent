package com.github.Holyvirus.regionrent;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.World;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RChecker {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	
	public void logemptyRegions(){
		World world = RegionRent.getRR().getServer().getWorld("world");
		WorldGuardPlugin WG = RegionRent.getRR().getWorldGuard();
		RegionManager rm = WG.getRegionManager(world);
		Map<String, ProtectedRegion> regions = rm.getRegions();
		for(Map.Entry<String, ProtectedRegion> entry : regions.entrySet()) { 
			ProtectedRegion p = entry.getValue();
			p.getOwners();
			if (p.getOwners() == null) {
				ProtectedRegion unowned = entry.getValue();
				this.unowned = unowned;
				this.remotefile();
				log.log(Level.SEVERE, "IMHERE1");
			}
		}
	}
	
	public ProtectedRegion unowned;
	RWriter RWobject = new RWriter(unowned);
	
	public void remotefile(){
		RWobject.add2File();
	}
}
