package messageimpl;

public class PaidCases 
{
	public static String paidCasesIntent(String channel, String period, String user_circle, String user_region, String userzone,
			String real_tim_timstamp, String mtd_inforced_count, String ytd_inforced_count, double daily_inforced_count1, String subchannel,
			String user_clusters, String user_go)
			
	{
		System.out.println("check---"+user_clusters);
		String finalresponse="";
		if("MLI".equalsIgnoreCase(channel))
		{
			channel="";
		}if("Monthly".equalsIgnoreCase(period))
		{
			period="";
		}else
		{
			period=period.toUpperCase();
		}if(!"".equalsIgnoreCase(user_circle))
		{
			user_region="Circle "+user_circle;
		}if(!"".equalsIgnoreCase(user_go))
		{
			user_clusters="Office "+user_go;
		}
		if(!"".equalsIgnoreCase(subchannel))
		{
			channel = subchannel;
		}
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)  && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " Paid cases MTD for MLI is "
					+ mtd_inforced_count+ ". Paid cases YTD for MLI is " + ytd_inforced_count + 
					". If you want to see the channel wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) 
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " Paid cases MTD for " + channel + " is " + mtd_inforced_count +
					". Paid cases YTD for " +channel + " is " + ytd_inforced_count;
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) 
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " + real_tim_timstamp + " Paid cases MTD for " + userzone + " zone is " + mtd_inforced_count + ". Paid cases YTD for " +
					userzone + " zone is " + ytd_inforced_count; 
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp + " Paid cases MTD for " + user_region + " is " + mtd_inforced_count +
					". Paid cases YTD for " + user_region + " is " + ytd_inforced_count;
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)&& !"".equalsIgnoreCase(user_clusters) 
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp + " Paid cases MTD for " + user_clusters + " is " + mtd_inforced_count +
					". Paid cases YTD for " + user_clusters + " is " + ytd_inforced_count;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp + " Paid cases MTD for " + user_clusters + " is " + mtd_inforced_count +
					". Paid cases YTD for " + user_clusters + " is " + ytd_inforced_count;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)&& !"".equalsIgnoreCase(user_clusters) 
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp + " Paid cases MTD for " + user_clusters + " is " + mtd_inforced_count +
					". Paid cases YTD for " + user_clusters + " is " + ytd_inforced_count;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)&& "".equalsIgnoreCase(user_clusters) 
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp + " Paid cases MTD for " + user_region + " is " + mtd_inforced_count +
					". Paid cases YTD for " + user_region + " is " + ytd_inforced_count;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + ytd_inforced_count;
						
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + mtd_inforced_count;
						
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + daily_inforced_count1;
							
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + ytd_inforced_count;
						
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + mtd_inforced_count;
						
			}else
			{
				finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + daily_inforced_count1;
						
			}

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)&& "".equalsIgnoreCase(user_clusters) 
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{	
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " is " + ytd_inforced_count;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " is " + mtd_inforced_count;
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " is " + daily_inforced_count1;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)&& !"".equalsIgnoreCase(user_clusters) 
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{	
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_clusters + " is " + ytd_inforced_count;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_clusters + " is " + mtd_inforced_count;
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_clusters + " is " + daily_inforced_count1;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) 
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{	
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " is " + ytd_inforced_count;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " is " + mtd_inforced_count;
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " is " + daily_inforced_count1;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{	
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_clusters + " is " + ytd_inforced_count;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_clusters + " is " + mtd_inforced_count;
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_clusters + " is " + daily_inforced_count1;
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + ytd_inforced_count +
						". If you want to see the channel wise business numbers, please specify.";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + mtd_inforced_count +
						". If you want to see the channel wise business numbers, please specify.";
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + daily_inforced_count1 +
						". If you want to see the channel wise business numbers, please specify.";
			}
		}
		return finalresponse.toString();
	}
}

