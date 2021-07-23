package com.playerstats.stats;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.playerstats.groups.Group;
import com.playerstats.main.PlayerStats;
import com.playerstats.utils.Time;

public class Stats {

	private UUID uuid;
	private Group rank;
	private List<Data> data;
	private Date Joined;
	private Date firstJoined;
	
	public Stats(UUID uuid, List<Data> data, Group rank) {
		this.uuid = uuid;
		this.data = data;
		this.rank = rank;
		if(!data.isEmpty()) {
			firstJoined = data.get(0).getJoin();
		}
	}
	
	public Stats(UUID uuid, List<Data> data) {
		this.uuid = uuid;
		this.data = data;
		this.rank = PlayerStats.getInstance().configManager.ranks.defualtGroup.get();
		if(!data.isEmpty()) {
			firstJoined = data.get(0).getJoin();
		}
	}

	public long getPlayTime(int days) {
		
		List<Data> filtered = new ArrayList<Data>();
		
		this.data.forEach(i -> {
			
			Date join = i.getJoin();
			
			LocalDate startDate = LocalDate.now();
			LocalDate endDate = startDate.minusDays(days);
			
			ZoneId defaultZoneId = ZoneId.systemDefault();
	        Date date = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
		
	       if(date.before(join)) {
	    	   filtered.add(i);
	       }
	    	
		});
		
		long seconds = 0;
		
		for(Data o : filtered) {
			seconds+=o.difference();
		}
		
		if(this.Joined == null) {
			this.Joined = Time.getCurrentTimeInDate();
		}
		
		seconds += Time.difference(Joined, Time.getCurrentTimeInDate());
		
		return seconds;
	}
	
	public long getPlayTime() {
		
		long seconds = 0;
		
		for(Data o : this.getData()) {
			seconds+=o.difference();
		}
		
		if(this.Joined == null) {
			this.Joined = Time.getCurrentTimeInDate();
		}
		
		seconds += Time.difference(Joined, Time.getCurrentTimeInDate());
		
		return seconds;
	}
	
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public Group getRank() {
		return rank;
	}

	public void setRank(Group rank) {
		this.rank = rank;
	}

	public Date getJoined() {
		return Joined;
	}

	public void setJoined(Date joined) {
		Joined = joined;
	}

	public Date getFirstJoined() {
		return firstJoined;
	}

	public void setFirstJoined(Date firstJoined) {
		this.firstJoined = firstJoined;
	}
	
	
}
