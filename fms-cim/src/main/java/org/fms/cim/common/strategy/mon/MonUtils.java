/**
 * Author : chizf
 * Date : 2020年10月22日 上午8:52:54
 * Title : org.fms.cim.common.strategy.mon.MonUtils.java
 *
**/
package org.fms.cim.common.strategy.mon;

import java.util.Calendar;
import java.util.List;

import com.riozenc.titanTool.common.date.DateUtil;

public class MonUtils {

	public static String getMon() {
		return DateUtil.getDate("yyyyMM");
	}

	public static String getDateString(String format) {
		if (format == null) {
			return DateUtil.getDate("yyyyMM");
		}
		return DateUtil.getDate(format);
	}

	public static String getLastMon(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4)) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, -1);
		return DateUtil.getDate(calendar.getTime(), "yyyyMM");
	}

	public static String getNextMon(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4)) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return DateUtil.getDate(calendar.getTime(), "yyyyMM");
	}

	public static List<Integer> getMons(int startMon, int endMon, List<Integer> mons) {
		int nextMon = Integer.parseInt(MonUtils.getNextMon(String.valueOf(startMon)));
		mons.add(nextMon);
		if (nextMon != endMon) {
			return getMons(nextMon, endMon, mons);
		}
		return mons;
	}

}
