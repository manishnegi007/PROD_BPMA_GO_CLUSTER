package messageimpl;

public class Appliedcases 
{
	public static String appliedCasesIntent(String channel, String period, String user_region, String user_circle, String userzone , String real_tim_timstamp,
			String mtd_applied_count, String ytd_applied_count, String daily_applied_count, String subchannel )
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
			finalresponse= "As of "+real_tim_timstamp+" Applied cases MTD for MLI is "
					+mtd_applied_count+". Applied cases YTD for MLI is "+ytd_applied_count+ 
					". If you want to see the channel wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of "+real_tim_timstamp+" Applied cases MTD for "+channel+
					" is "+mtd_applied_count+". Applied cases YTD for "+channel+" is "+ytd_applied_count+
					". If you want to see the zone/region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of "+real_tim_timstamp+" Applied cases MTD for "+userzone+" is "
					+mtd_applied_count+". Applied cases YTD for "+userzone+" is " +ytd_applied_count+
					". If you want to see the region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of "+real_tim_timstamp+" Applied cases MTD for "+user_region+ " is " 
					+mtd_applied_count+ ". Applied cases YTD for "+user_region+" is "+ ytd_applied_count;

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Applied cases "+period+" for "+channel+ " is "+ytd_applied_count+
						". If you want to see the zone/region wise business numbers, please specify";
			}
			else if("MTD".equalsIgnoreCase(period)){
				finalresponse="As of " +real_tim_timstamp+" Applied cases "+period+" for "+channel+ " is "+mtd_applied_count+
						". If you want to see the zone/region wise business numbers, please specify";
			}
			else
			{
				finalresponse="As of " +real_tim_timstamp+" Applied cases "+period+" for "+channel+ " is "+daily_applied_count+
						". If you want to see the zone/region wise business numbers, please specify";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+ " for "+userzone+" zone is "+ytd_applied_count+
						". If you want to see the region wise business numbers, please specify";
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+ " for "+userzone+" zone is "+mtd_applied_count+
						". If you want to see the region wise business numbers, please specify";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+ " for "+userzone+" zone is "+daily_applied_count+
						". If you want to see the region wise business numbers, please specify";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" is "+ytd_applied_count+" ";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" is "+mtd_applied_count+" ";
			}else
			{
				finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" is "+daily_applied_count+" ";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" is "+ytd_applied_count+" ";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" is "+mtd_applied_count+" ";
			}else
			{
				finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" is "+daily_applied_count+" ";
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+" for MLI is "+ytd_applied_count+
						". If you want to see the channel wise business numbers, please specify";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+" for MLI is "+mtd_applied_count+
						". If you want to see the channel wise business numbers, please specify";
			}else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+" for MLI is "+daily_applied_count+
						". If you want to see the channel wise business numbers, please specify";
			}
		}
	
		return finalresponse.toString();
	}
}
