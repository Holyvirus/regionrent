package com.github.Holyvirus.regionrent;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.IOException;
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
	  
		public void write(String dir, String file, String... line) {
			try{
				BufferedWriter f = new BufferedWriter(new FileWriter(dir + File.separator + file, true));
				for(String text : line) {
					f.write(text);
					f.newLine();
				}
				f.flush();
				f.close();
			}catch(IOException e) {}
		}
}
