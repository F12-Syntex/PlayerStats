
package com.playerstats.stats;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Data {

	private Date join;
	private Date leave;
	
	public Data(Date join, Date leave) {
		this.join = join;
		this.leave = leave;
	}

	public Date getJoin() {
		return join;
	}

	public void setJoin(Date join) {
		this.join = join;
	}

	public Date getLeave() {
		return leave;
	}

	public void setLeave(Date leave) {
		this.leave = leave;
	}
	
	public long difference() {
	    long diffInMillies = Math.abs(join.getTime() - leave.getTime());
	    long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    return diff;
	}
	
}
