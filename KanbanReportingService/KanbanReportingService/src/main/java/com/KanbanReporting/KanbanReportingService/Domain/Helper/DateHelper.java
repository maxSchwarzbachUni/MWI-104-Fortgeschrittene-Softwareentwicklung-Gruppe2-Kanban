package com.KanbanReporting.KanbanReportingService.Domain.Helper;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class DateHelper {
	public static Date getCurrentTime() {
		LocalDateTime timeNow = java.time.LocalDateTime.now();
		return java.sql.Date.valueOf(timeNow.toLocalDate());
	}
	
	// Referenz: https://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances
	public static Map<TimeUnit,Long> ComputeDateDifference(Date date1, Date date2) {
	    long differenceInMilliseconds = date2.getTime() - date1.getTime();
	    List<TimeUnit> timeUnitList = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
	    Collections.reverse(timeUnitList);

	    //create the result map of TimeUnit and difference
	    Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
	    long milliesRest = differenceInMilliseconds;
	    for ( TimeUnit unit : timeUnitList ) {
	        long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
	        long diffInMilliesForUnit = unit.toMillis(diff);
	        //calculate difference in millisecond 
	        milliesRest = milliesRest - diffInMilliesForUnit;
	        result.put(unit,diff);
	    }
	    return result;
	}
	
	public static long GetDateDifferenceSimple(Date date1, Date date2, TimeUnit timeUnit) {
	    long differenceInMilliseconds = date2.getTime() - date1.getTime();
	    return timeUnit.convert(differenceInMilliseconds,TimeUnit.MILLISECONDS);
	}
}
