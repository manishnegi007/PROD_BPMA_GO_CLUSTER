package messageimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WIP 
{
	public static String wipIntent(String channel, String msgChannel, String convertsum4, String convertsum3)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		String finalresponse="";
		if(!"".equalsIgnoreCase(channel))
		{
			finalresponse="Current WIP as of "+dtf.format(now)+
					" for "+msgChannel+" is "+convertsum4+" Policies with "+convertsum3+" "
					+ " Cr. AFYP. Do you wish to see the stage wise snapshot";
		}
		else
		{
			finalresponse="Current WIP as of "+dtf.format(now)+
					" for MLI is "+convertsum4+" Policies with "+convertsum3+" "
					+ "Cr. AFYP. Do you wish to see the stage wise snapshot";
		}
		return finalresponse.toString();
	}
}
