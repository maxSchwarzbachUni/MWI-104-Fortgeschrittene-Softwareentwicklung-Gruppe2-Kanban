package com.KanbanReporting.KanbanReportingService.Domain.Helper;

import java.sql.Date;
import java.time.LocalDateTime;

public final class DateHelper {
	public static Date getCurrentTime() {
		LocalDateTime timeNow = java.time.LocalDateTime.now();
		return java.sql.Date.valueOf(timeNow.toLocalDate());
	}
}
