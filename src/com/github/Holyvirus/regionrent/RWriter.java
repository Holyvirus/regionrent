package com.github.Holyvirus.regionrent;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RWriter {
	
	private ProtectedRegion region;
	private String Date;

	public static final Logger log = Logger.getLogger("Minecraft");
	
	public RWriter(ProtectedRegion unowned){
		region=unowned;
	}
	  public void getDate(String arg[]) {
	  Calendar currentDate = Calendar.getInstance();
	  SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
	  String Date = formatter.format(currentDate.getTime());
	  this.Date = Date;
	  }
	  
	  private Formatter file;
	  
	  public void openFile() {
		  try {
			  file = new Formatter("/plugins/RegionRent/deceased.txt");
		  }
		  catch (Exception e) {
			  log.log(Level.WARNING, "File creation error!");
		  }
	  }
	  
	  public void add2File() {
		  file.format("%s\t\t%s\n", Date, region );
	  }
}
