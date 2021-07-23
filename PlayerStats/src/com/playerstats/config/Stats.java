package com.playerstats.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.playerstats.groups.Group;
import com.playerstats.main.PlayerStats;
import com.playerstats.stats.Data;
import com.playerstats.utils.Time;

public class Stats extends Config{
	
	private List<com.playerstats.stats.Stats> data;
	public boolean saving = false;

	public Stats(String name, double version) {
		super(name, version);
		
	}

	@Override
	public Configuration configuration() {
		// TODO Auto-generated method stub
		return Configuration.STATS;
	}
	
	@Override
	public void initialize() {

		this.setData(new ArrayList<com.playerstats.stats.Stats>());
		
		List<com.playerstats.stats.Stats> statistics = new ArrayList<com.playerstats.stats.Stats>();
		
		if(!this.getConfiguration().isConfigurationSection("Stats.players")) {
			return;
		}
		
		this.getConfiguration().getConfigurationSection("Stats.players").getKeys(false).forEach(i -> {
			
			UUID uuid = java.util.UUID.fromString(i);
			ConfigurationSection section = this.getConfiguration().getConfigurationSection("Stats.players." + i);
			List<Data> data = new ArrayList<Data>();
			
			Group group = null;
			
			for(String o : section.getKeys(false)) {
				
				if(o.equals("Rank")) {

					int id = section.getInt(o);
					
					group = PlayerStats.getInstance().configManager.ranks.getGroup(id);
					
				}else {
					
					ConfigurationSection dates = section.getConfigurationSection("Stats");
					
					for(String u : dates.getKeys(false)) {
						
						Date join = Time.convertStringToDate(dates.getString(u + ".join"));
						Date leave = Time.convertStringToDate(dates.getString(u + ".leave"));
					
						data.add(new Data(join, leave));
						
					}
					
				}
				
			}
			
			com.playerstats.stats.Stats stats = new com.playerstats.stats.Stats(uuid, data, group);
			statistics.add(stats);
			
		});
		
		this.setData(statistics);
		
	}
	
	public void logJoin(Player player) {
		this.getStats(player).setJoined(Time.getCurrentTimeInDate());
	}

	public void logLeave(Player player) {
		
		if(this.getStats(player).getJoined() == null) {
			this.getStats(player).setJoined(Time.getCurrentTimeInDate());
		}
		
		Data data = new Data(this.getStats(player).getJoined(), Calendar.getInstance().getTime());
		
		this.getStats(player).getData().add(data);
		this.refresh();
	}

	public void refresh() {
		
		 for(com.playerstats.stats.Stats stats : this.data) {
			 this.getConfiguration().set("Stats.players." + stats.getUuid().toString() + ".Rank", stats.getRank().ID);
			 
			 int count = 1;
			 
			 for(Data data : stats.getData()) {
				 this.getConfiguration().set("Stats.players." + stats.getUuid().toString() + ".Stats."+count+".join", Time.ConvertDateToString(data.getJoin()));
				 this.getConfiguration().set("Stats.players." + stats.getUuid().toString() + ".Stats."+count+".leave", Time.ConvertDateToString(data.getLeave()));
				 count++;
			 }
		
		 }
		
		 this.save();
		
	}
	
	public com.playerstats.stats.Stats getStats(Player player){
	
		for(com.playerstats.stats.Stats stats : this.data) {
			if(stats.getUuid().compareTo(player.getUniqueId()) == 0) {
				return stats;
			}
		}
		
		com.playerstats.stats.Stats stats = new com.playerstats.stats.Stats(player.getUniqueId(), new ArrayList<Data>());
		this.data.add(stats);
		return stats;
		
	}
	
	public List<com.playerstats.stats.Stats> getData() {
		return data;
	}

	public void setData(List<com.playerstats.stats.Stats> data) {
		this.data = data;
	}

	
}
