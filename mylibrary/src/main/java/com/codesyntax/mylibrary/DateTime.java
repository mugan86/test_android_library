package com.codesyntax.mylibrary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTime {


	//To get correct data
	public static String  getCorrectDataTime (String data_osoa, int horas){

		//Extract details data
		int year = Integer.parseInt(data_osoa.substring(0, 4));
		int month = Integer.parseInt(data_osoa.substring(5,7))-1;
		int day = Integer.parseInt(data_osoa.substring(8,10));
		int hour = Integer.parseInt(data_osoa.substring(11,13));
		int min = Integer.parseInt(data_osoa.substring(14,16));
		int sg = Integer.parseInt(data_osoa.substring(17,19));
		System.out.println(year+"/"+month+"/"+day+" "+hour+":"+min+":"+sg);


		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, min, sg);

		// Configure take data
		System.out.println("Take Data: "+calendar.getTime());
		calendar.add(Calendar.HOUR, horas);  // number of hours to add, or quit (if hour <0)

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);

		System.out.println("Get Data: "+ dateFormat.format(calendar.getTime()));
		//System.out.println(dateFormat.format(cal.getTime()));
		return dateFormat.format(calendar.getTime());
	}

	public static String getCurrentData ()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());

	}

    public static String  getYesterDayData ()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }
}
