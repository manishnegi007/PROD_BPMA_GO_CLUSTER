package messageimpl;

public class NBApplied 
{
	public static String nbAppliedIntent(String channel, String period, String user_circle, String user_region, String userzone, 
			String real_tim_timstamp, String mtdAppliedAFYP, String ytd_applied_afyp, String daily_inforced_count, String subchannel,
			String user_clusters, String user_go)
	{
		String finalresponse="";
		if("MLI".equalsIgnoreCase(channel))
		{
			channel="";
		}
		if("Monthly".equalsIgnoreCase(period))
		{
			period="";
		}else
		{
			period=period.toUpperCase();
		}if(!"".equalsIgnoreCase(user_circle))
		{
			user_region="Circle "+user_circle;
		}
		/*------------------------------------------------*/
		if(!"".equalsIgnoreCase(user_go))
		{
			user_clusters="Go "+user_go;
		}
		if(!"".equalsIgnoreCase(subchannel))
		{
			channel = subchannel;
		}
		
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for MLI is  "
					+ mtdAppliedAFYP + " Cr Applied Business AFYP YTD for MLI is " + ytd_applied_afyp + 
					" If you want to see the channel wise business numbers, please specify";
		}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for " + channel +
					" is " + mtdAppliedAFYP + " Cr Applied Business AFYP YTD for " +channel + " is "+ ytd_applied_afyp +
					" If you want to see the zone/region wise business numbers, please specify";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for " + userzone + " zone is "
					+ mtdAppliedAFYP + " Cr Applied Business AFYP YTD for " + userzone + " zone is " + ytd_applied_afyp +
					" Cr. If you want to see the region wise business numbers, please specify";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for " + user_region + " is " 
					+ mtdAppliedAFYP + " Applied Business AFYP YTD for " + user_region + " is " + ytd_applied_afyp + " Cr";

		}
		/*--------------------------------------------------------------------------------------------------*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for " + user_clusters + " is " 
					+ mtdAppliedAFYP + " Applied Business AFYP YTD for " + user_clusters + " is " + ytd_applied_afyp + " Cr";

		}
		/*--------------------------------------------------------------------------------------------------*/
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Applied Business AFYP " + period + " for " +channel+ " is " +ytd_applied_afyp+
						" Cr. If you want to see the zone/region wise business numbers, please specify";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " Applied Business AFYP " + period + " for " +channel+ " is " +mtdAppliedAFYP+
						" Cr. If you want to see the zone/region wise business numbers, please specify";
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " Applied Business AFYP " + period + " for " +channel+ " is " +daily_inforced_count+
						" Cr. If you want to see the zone/region wise business numbers, please specify";
			}
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) 
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " +userzone+ " zone is " +ytd_applied_afyp+
						" Cr. If you want to see the region wise business numbers, please specify";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " +userzone+ " zone is " +mtdAppliedAFYP+
						" Cr. If you want to see the region wise business numbers, please specify";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " +userzone+ " zone is " +daily_inforced_count+
						" Cr. If you want to see the region wise business numbers, please specify";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) 
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " is " +ytd_applied_afyp+ " Cr";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " is " +mtdAppliedAFYP+ " Cr";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " is " +daily_inforced_count+ " Cr";
			}
		}
		/*--------------------------------------Channel & GO------start----------------------------------------------------------------------*/
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters) 
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_clusters+ " is " +ytd_applied_afyp+ " Cr";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_clusters+ " is " +mtdAppliedAFYP+ " Cr";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_clusters+ " is " +daily_inforced_count+ " Cr";
			}
		}
		/*--------------------------------------Channel & GO----------end------------------------------------------------------------------*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) 
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " is " +ytd_applied_afyp+ " Cr";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " is " +mtdAppliedAFYP+ " Cr";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " is " +daily_inforced_count+ " Cr";
			}
		}
		/*-----------------------------------------start----------------------------------------------------------------------------*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters) 
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_clusters+ " is " +ytd_applied_afyp+ " Cr";
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_clusters+ " is " +mtdAppliedAFYP+ " Cr";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_clusters+ " is " +daily_inforced_count+ " Cr";
			}
		}
		/*-------------------------------------------end--------------------------------------------------------------------------*/
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for MLI is " +ytd_applied_afyp+ "Cr." 
						+ " If you want to see the channel wise business numbers, please specify";
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for MLI is " +ytd_applied_afyp+ "Cr." 
						+ " If you want to see the channel wise business numbers, please specify";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for MLI is " +ytd_applied_afyp+ "Cr." 
						+ " If you want to see the channel wise business numbers, please specify";
			}
		}
		return finalresponse.toString();
	}
}
