package study.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {

	public static void main(String[] args) {

		// 1、准备
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date start = null;
		Date end = null;
		Calendar ca = Calendar.getInstance();

		// 2、设置月份
		ca.add(Calendar.MONTH, -30);

		// 3、开始时间
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), 1, 0, 0, 0);
		ca.set(Calendar.MILLISECOND, 0);
		start = ca.getTime();

		// 4、结束时间
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
		ca.set(Calendar.MILLISECOND, 999);
		end = ca.getTime();

		// 5、显示
		System.out.println(format.format(start) + "..." + start.getTime());
		System.out.println(format.format(end) + "..." + end.getTime());
	}

}
