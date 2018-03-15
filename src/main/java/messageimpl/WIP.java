package messageimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WIP 
{
	public static String wipIntent(String channel, String msgChannel, String convertsum4, String convertsum3)
	{
		Calendar cal = Calendar.getInstance(); // creates calendar
	   	cal.setTime(new Date()); // sets calendar time/date
	   	cal.add(Calendar.HOUR_OF_DAY, 5); // adds one hour
	   	cal.add(Calendar.MINUTE, 30);
	   	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String finalresponse="";
		if(!"".equalsIgnoreCase(channel))
		{
			finalresponse="Current WIP as of "+dtf.format(now)+
					" for "+msgChannel+" is "+convertsum4+" Policies with "+convertsum3+" "
					+ " Cr. Adj MFYP. Do you wish to see the stage wise snapshot.";
		}
		else
		{
			finalresponse="Current WIP as of "+dtf.format(now)+
					" for MLI is "+convertsum4+" Policies with "+convertsum3+" "
					+ "Cr. Adj MFYP. Do you wish to see the stage wise snapshot.";
		}
		return finalresponse.toString();
	}
}
