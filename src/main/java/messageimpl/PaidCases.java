package messageimpl;

public class PaidCases 
{
	public static String paidCasesIntent(String channel, String period, String user_circle, String user_region, String userzone,
			String real_tim_timstamp, String mtd_inforced_count, String ytd_inforced_count, double daily_inforced_count1, String subchannel)
			
	{
		String finalresponse="";

		if("MLI".equalsIgnoreCase(channel))
		{channel="";}
		if("Monthly".equalsIgnoreCase(period))
		{period="";}
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
			finalresponse= "As of " + real_tim_timstamp + " Paid cases MTD for MLI is "
					+ mtd_inforced_count+ " Paid cases YTD for MLI is " + ytd_inforced_count + 
					" If you want to see the channel wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " Paid cases MTD for " + channel + " is " + mtd_inforced_count +
					" Paid cases YTD for " +channel + " is " + ytd_inforced_count +
					" If you want to see the zone/region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of " + real_tim_timstamp + " Paid cases MTD for " + userzone + " zone is " + mtd_inforced_count + " Paid cases YTD for " +
					userzone + " zone is " + ytd_inforced_count + " If you want to see the region wise business numbers, please specify";

		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp + " Paid cases MTD for " + user_region + " is " + mtd_inforced_count +
					" Paid cases YTD for " + user_region + " is " + ytd_inforced_count;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + ytd_inforced_count +
						" If you want to see the zone/region wise business numbers, please specify";
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + mtd_inforced_count +
						" If you want to see the zone/region wise business numbers, please specify";
			}
			else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + daily_inforced_count1 +
						" If you want to see the zone/region wise business numbers, please specify";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + ytd_inforced_count +
						" If you want to see the region wise business numbers, please specify";
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + mtd_inforced_count +
						" If you want to see the region wise business numbers, please specify";
			}
			else
			{
				finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + daily_inforced_count1 +
						" If you want to see the region wise business numbers, please specify";
			}

		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{	
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " region is " + ytd_inforced_count;
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " region is " + mtd_inforced_count;
			}
			else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " region is " + daily_inforced_count1;
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + ytd_inforced_count +
						" If you want to see the channel wise business numbers, please specify";
			}
			else if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + mtd_inforced_count +
						" If you want to see the channel wise business numbers, please specify";
			}
			else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + daily_inforced_count1 +
						" If you want to see the channel wise business numbers, please specify";
			}
		}
		return finalresponse.toString();
	}
}


