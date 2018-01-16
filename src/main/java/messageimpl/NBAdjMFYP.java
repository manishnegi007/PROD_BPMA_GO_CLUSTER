package messageimpl;

public class NBAdjMFYP 
{
	public static String nbAdjMFYPIntent(String channel, String period, String user_circle, String user_region, String userzone, 
			String real_tim_timstamp, String mtdAdjustMFYP, String ytd_adj_mfyp, String mtd_adj_mfyp, String daily_adj_mfyp, String subchannel,
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
		if(!"".equalsIgnoreCase(user_go))
		{
			user_clusters="Office "+user_go;
		}
		if(!"".equalsIgnoreCase(user_clusters))
		{
			user_clusters="Cluster "+user_clusters;
		}
		if(!"".equalsIgnoreCase(subchannel))
		{
			channel = subchannel;
		}
		
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) 
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for MLI is  "
					+ mtdAdjustMFYP+ " Paid AdjMFYP YTD for MLI is " +ytd_adj_mfyp+ 
					" If you want to see the channel wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +channel+ " is " +mtdAdjustMFYP+
					" Paid cases YTD for " +channel+ " is " +ytd_adj_mfyp;
					
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +userzone+ " zone is " + mtdAdjustMFYP+ " Paid Adj MFYP YTD for " +
					userzone+ " zone is " +ytd_adj_mfyp;

		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +user_region+ " is " +mtdAdjustMFYP+
					" Paid Adj MFYP YTD for "+userzone+ " zone is " +ytd_adj_mfyp;
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +user_clusters+ " is " +mtdAdjustMFYP+
					" Paid Adj MFYP YTD for "+userzone+ " zone is " +ytd_adj_mfyp;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + ytd_adj_mfyp;
						
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + mtd_adj_mfyp;
			}
			else
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + daily_adj_mfyp;
						
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+ytd_adj_mfyp;
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+mtd_adj_mfyp;
			}
			else
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+daily_adj_mfyp;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)&& "".equalsIgnoreCase(user_clusters)
				 && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" is "+ytd_adj_mfyp;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" is "+mtd_adj_mfyp;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" is "+daily_adj_mfyp;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_clusters+" is "+ytd_adj_mfyp;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_clusters+" is "+mtd_adj_mfyp;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_clusters+" is "+daily_adj_mfyp;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" is "+ytd_adj_mfyp;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" is "+mtd_adj_mfyp;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" is "+daily_adj_mfyp;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_clusters+" is "+ytd_adj_mfyp;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_clusters+" is "+mtd_adj_mfyp;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_clusters+" is "+daily_adj_mfyp;
			}
		}
		else
		{
			if ("YTD".equalsIgnoreCase(period)){
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP "+period+ " for MLI  is " +ytd_adj_mfyp+
						" If you want to see the channel wise business numbers, please specify";
			}else if ("MTD".equalsIgnoreCase(period)){
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP "+period+ " for MLI  is " +mtd_adj_mfyp+
						" If you want to see the channel wise business numbers, please specify";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP "+period+ " for MLI  is " +daily_adj_mfyp+
						" If you want to see the channel wise business numbers, please specify";
			}
		}
		return finalresponse.toString();
	}
}
