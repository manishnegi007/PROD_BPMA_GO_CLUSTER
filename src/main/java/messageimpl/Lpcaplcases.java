package messageimpl;

public class Lpcaplcases 
{

	public static String lpcAplCasesIntent(String channel, String period, String user_region, String user_circle, String userzone ,
			String real_tim_timstamp,
			String lpc_applied_cases_mtd, String lpc_applied_cases_ytd, String subchannel,
			String user_clusters, String user_go)
	{
		String finalresponse="";
		if("Agency".equalsIgnoreCase(channel))
		{
			if("MLI".equalsIgnoreCase(channel))
			{channel="";}
			if("Monthly".equalsIgnoreCase(period))
			{
				period="";
			}else
			{
				if("FTD".equalsIgnoreCase(period))
				{
					period="MTD";
				}else
				{
					period=period.toUpperCase();
				}
			}
			if(!"".equalsIgnoreCase(user_circle))
			{user_region="Circle "+user_circle;}
			if(!"".equalsIgnoreCase(user_go))
			{
				user_clusters="Office "+user_go;
			}
			if(!"".equalsIgnoreCase(subchannel))
			{
				channel = subchannel;
			}
			if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
			   	&& "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD for MLI is  "
						+ lpc_applied_cases_mtd + ". LPC Applied cases YTD for MLI is  " + lpc_applied_cases_ytd + 
						". If you want to see the channel wise business numbers, please specify";
			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
				&& "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD for " + channel +
						" is " + lpc_applied_cases_mtd + ". LPC Applied cases YTD for " +channel + " is "+ lpc_applied_cases_ytd+
						". If you want to see the zone/region wise business numbers, please specify";
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
				&& "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD for" + userzone + " zone is "
						+ lpc_applied_cases_mtd + ". LPC Applied cases YTD for " + userzone + " zone is " + lpc_applied_cases_ytd+
						". If you want to see the region wise business numbers, please specify";
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
				&& "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD " + user_region + " is " 
						+ lpc_applied_cases_mtd + ". LPC Applied cases YTD for " + user_region + " is " + lpc_applied_cases_ytd+ "";

			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
				&& "".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
			{
				finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD " + user_clusters + " is " 
						+ lpc_applied_cases_mtd + ". LPC Applied cases YTD for " + user_clusters + " is " + lpc_applied_cases_ytd+ "";

			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
				&& "".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
			{
				finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD " + user_clusters + " is " 
						+ lpc_applied_cases_mtd + ". LPC Applied cases YTD for " + user_clusters + " is " + lpc_applied_cases_ytd+ "";

			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
				&& "".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
			{
				finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD " + user_clusters + " is " 
						+ lpc_applied_cases_mtd + ". LPC Applied cases YTD for " + user_clusters + " is " + lpc_applied_cases_ytd+ "";

			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
				&& "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD " + user_region + " is " 
						+ lpc_applied_cases_mtd + ". LPC Applied cases YTD for " + user_region + " is " + lpc_applied_cases_ytd+ "";

			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
				&& !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of " + real_tim_timstamp + " LPC Applied cases " + period + " for " +channel+ " is " +lpc_applied_cases_ytd+
							". If you want to see the zone/region wise business numbers, please specify";
				}
				else
				{
					finalresponse="As of " + real_tim_timstamp + " LPC Applied cases " + period + " for " +channel+ " is " +lpc_applied_cases_mtd+
							". If you want to see the zone/region wise business numbers, please specify";	
				}
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
				&& !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " +userzone+ " zone is " +lpc_applied_cases_ytd+
							". If you want to see the region wise business numbers, please specify";
				}
				else
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " +userzone+ " zone is " +lpc_applied_cases_mtd+
							". If you want to see the region wise business numbers, please specify";	
				}
			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)
				&& !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_region+ " is " +lpc_applied_cases_ytd+ "";
				}else
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_region+ " is " +lpc_applied_cases_mtd+ "";	
				}
			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
				&& !"".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_clusters+ " is " +lpc_applied_cases_ytd+ "";
				}else
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_clusters+ " is " +lpc_applied_cases_mtd+ "";	
				}
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_region+ " is " +lpc_applied_cases_ytd+ "";
				}else
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_region+ " is " +lpc_applied_cases_mtd+ "";	
				}
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
				&& !"".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_clusters+ " is " +lpc_applied_cases_ytd+ "";
				}else
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_clusters+ " is " +lpc_applied_cases_mtd+ "";	
				}
			}
			else
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for MLI is " +lpc_applied_cases_ytd+ "" 
							+ ". If you want to see the channel wise business numbers, please specify";
				}else
				{
					finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for MLI is " +lpc_applied_cases_mtd+ "" 
							+ ". If you want to see the channel wise business numbers, please specify";	
				}
			}
		}
		else
		{
			finalresponse="Invalid Channel For LPC ! Only Agency Accepted";
		}
		return finalresponse.toString();
	}
}
