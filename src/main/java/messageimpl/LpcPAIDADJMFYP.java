package messageimpl;

public class LpcPAIDADJMFYP {

	public static String lpcPaidAdjMfypIntent(String channel, String period, String user_region, String user_circle, String userzone , String real_tim_timstamp,
			String lpc_paid_adj_mfyp_mtd, String lpc_paid_adj_mfyp_ytd, String subchannel )

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
			finalresponse= "As of " + real_tim_timstamp + " LPC Paid Adj MFYP MTD for MLI is  "
					+ lpc_paid_adj_mfyp_mtd + " Cr. LPC Paid Adj MFYP YTD for MLI is  " + lpc_paid_adj_mfyp_ytd + 
					". If you want to see the channel wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " LPC Paid Adj MFYP MTD for " + channel +
					" is " + lpc_paid_adj_mfyp_mtd + " Cr. LPC Paid Adj MFYP YTD for " +channel + " is "+ lpc_paid_adj_mfyp_ytd+
					". If you want to see the zone/region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " LPC Paid Adj MFYP MTD for " + userzone + " zone is "
					+ lpc_paid_adj_mfyp_mtd + " Cr. LPC Paid Adj MFYP YTD for " + userzone + " zone is " + lpc_paid_adj_mfyp_ytd+
					" Cr. If you want to see the region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " + real_tim_timstamp + " LPC Paid Adj MFYP MTD for " +user_region+ " is " 
					+ lpc_paid_adj_mfyp_mtd + ". LPC Paid Adj MFYP YTD for " + user_region + " is " + lpc_paid_adj_mfyp_ytd+ " Cr";

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " + real_tim_timstamp + " LPC Paid Adj MFYP " + period + " for " +channel+ " is " +lpc_paid_adj_mfyp_ytd+
						" Cr. If you want to see the zone/region wise business numbers, please specify.";
			}else
			{
				finalresponse="As of " + real_tim_timstamp + " LPC Paid Adj MFYP " + period + " for " +channel+ " is " +lpc_paid_adj_mfyp_mtd+
						" Cr. If you want to see the zone/region wise business numbers, please specify.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " +userzone+ " zone is " +lpc_paid_adj_mfyp_ytd+
						" Cr. If you want to see the region wise business numbers, please specify.";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " +userzone+ " zone is " +lpc_paid_adj_mfyp_mtd+
						" Cr. If you want to see the region wise business numbers, please specify.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " + user_region+ " is " +lpc_paid_adj_mfyp_ytd+ " Cr";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " + user_region+ " is " +lpc_paid_adj_mfyp_mtd+ " Cr";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " + user_region+ " is " +lpc_paid_adj_mfyp_ytd+ " Cr";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " + user_region+ " is " +lpc_paid_adj_mfyp_mtd+ " Cr";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for MLI is " +lpc_paid_adj_mfyp_ytd+ "Cr." 
						+ ". If you want to see the channel wise business numbers, please specify.";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for MLI is " +lpc_paid_adj_mfyp_mtd+ "Cr." 
						+ ". If you want to see the channel wise business numbers, please specify.";	
			}
		}
	
		return finalresponse.toString();
	}
}
