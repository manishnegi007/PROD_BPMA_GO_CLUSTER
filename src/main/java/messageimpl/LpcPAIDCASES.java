package messageimpl;

public class LpcPAIDCASES {

	public static String lpcPaidCasesIntent(String channel, String period, String user_region,
     String user_circle, String userzone , String real_tim_timstamp,
     String lpc_paid_cases_mtd, String lpc_paid_cases_ytd, String subchannel )

	{
		String finalresponse="";
		if("MLI".equalsIgnoreCase(channel))
		{channel="";}
		if("Monthly".equalsIgnoreCase(period))
		{period="";}
		else
		{
			period=period.toUpperCase();
		}
		if(!"".equalsIgnoreCase(user_circle))
		{
			user_region="Circle "+user_circle;
		}
		if(!"".equalsIgnoreCase(subchannel))
	        {
                 channel = subchannel;
	        }

		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " LPC Paid cases MTD for MLI is  "
					+ lpc_paid_cases_mtd + ". LPC Paid cases YTD for MLI is  " + lpc_paid_cases_ytd + 
					". If you want to see the channel wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " LPC Paid cases MTD for " + channel +
					" is " + lpc_paid_cases_mtd + ". LPC Paid cases YTD for " +channel + " is "+ lpc_paid_cases_ytd+
					". If you want to see the zone/region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " LPC Paid cases MTD for " + userzone + " zone is "
					+ lpc_paid_cases_mtd + " Cr. LPC Paid cases YTD for " + userzone + " zone is " + lpc_paid_cases_ytd+
					". If you want to see the region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " LPC Paid cases MTD for " +user_region+ " region is " 
					+ lpc_paid_cases_mtd + ". LPC Paid cases YTD for " + user_region + " region is " + lpc_paid_cases_ytd+ " Cr";

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{	
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " LPC Paid cases " + period + " for " +channel+ " is " +lpc_paid_cases_ytd+
						". If you want to see the zone/region wise business numbers, please specify.";
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " LPC Paid cases " + period + " for " +channel+ " is " +lpc_paid_cases_mtd+
						". If you want to see the zone/region wise business numbers, please specify.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " +userzone+ " zone is " +lpc_paid_cases_ytd+
						". If you want to see the region wise business numbers, please specify.";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " +userzone+ " zone is " +lpc_paid_cases_mtd+
						". If you want to see the region wise business numbers, please specify.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " + user_region+ " is " +lpc_paid_cases_ytd+ " Cr";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " + user_region+ " is " +lpc_paid_cases_mtd+ " Cr";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " + user_region+ " is " +lpc_paid_cases_ytd+ " Cr";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " + user_region+ " is " +lpc_paid_cases_mtd+ " Cr";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for MLI is " +lpc_paid_cases_ytd+ "Cr." 
						+ " If you want to see the channel wise business numbers, please specify.";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for MLI is " +lpc_paid_cases_mtd+ "Cr." 
						+ " If you want to see the channel wise business numbers, please specify.";	
			}
		}
		return finalresponse.toString();
	}
}
