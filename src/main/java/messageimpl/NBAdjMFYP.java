package messageimpl;

public class NBAdjMFYP 
{
	public static String nbAdjMFYPIntent(String channel, String period, String user_circle, String user_region, String userzone, 
			String real_tim_timstamp, String mtdAdjustMFYP, String ytd_adj_mfyp, String mtd_adj_mfyp, String daily_adj_mfyp)
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
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for MLI is  "
					+ mtdAdjustMFYP+ " Paid AdjMFYP YTD for MLI is " +ytd_adj_mfyp+ 
					" If you want to see the channel wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +channel+ " is " +mtdAdjustMFYP+
					" Paid cases YTD for " +channel+ " is " +ytd_adj_mfyp+
					" If you want to see the zone/region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +userzone+ " zone is " + mtdAdjustMFYP+ " Paid Adj MFYP YTD for " +
					userzone+ " zone is " +ytd_adj_mfyp+ " If you want to see the region wise business numbers, please specify";

		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +user_region+ " region is " +mtdAdjustMFYP+
					" Paid Adj MFYP YTD for "+userzone+ " zone is " +ytd_adj_mfyp;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + ytd_adj_mfyp+
						"If you want to see the zone/region wise business numbers, please specify";
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + mtd_adj_mfyp+
						"If you want to see the zone/region wise business numbers, please specify";
			}
			else
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + daily_adj_mfyp+
						"If you want to see the zone/region wise business numbers, please specify";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+ytd_adj_mfyp+
						" If you want to see the region wise business numbers, please specify";
			}
			else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+mtd_adj_mfyp+
						" If you want to see the region wise business numbers, please specify";
			}
			else
			{
				finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+daily_adj_mfyp+
						" If you want to see the region wise business numbers, please specify";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" region is "+ytd_adj_mfyp;
			}else if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" region is "+mtd_adj_mfyp;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" region is "+daily_adj_mfyp;
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
