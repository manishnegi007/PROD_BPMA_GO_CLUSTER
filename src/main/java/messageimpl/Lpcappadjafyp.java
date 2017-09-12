package messageimpl;

public class Lpcappadjafyp {

	public static String lpcAppAdjAfypIntent(String channel, String period, String user_region, String user_circle, String userzone , String real_tim_timstamp,
			String lpc_applied_afyp_mtd, String lpc_applied_afyp_ytd, String subchannel)
	{
		String finalresponse = "";
		if("Agency".equalsIgnoreCase(channel))
		{
			if("MLI".equalsIgnoreCase(channel))
			{channel="";}
			if("Monthly".equalsIgnoreCase(period))
			{period="";}
			else
			{period=period.toUpperCase();}
			if(!"".equalsIgnoreCase(user_circle))
			{user_region="Circle "+user_circle;}
			if(!"".equalsIgnoreCase(subchannel))
			{channel = subchannel;}
			if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business AFYP MTD for MLI is "
						+lpc_applied_afyp_mtd+" Cr. LPC Applied Business AFYP YTD for MLI is "+lpc_applied_afyp_ytd+ 
						"Cr. If you want to see the channel wise business numbers, please specify";
			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business AFYP MTD for "+channel+
						" is "+lpc_applied_afyp_mtd+" Cr. LPC Applied Business AFYP YTD for "+channel+" is "+lpc_applied_afyp_ytd+
						" Cr. If you want to see the zone/region wise business numbers, please specify";
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business AFYP MTD for "+userzone+" zone is "
						+lpc_applied_afyp_mtd+" Cr. LPC Applied Business AFYP YTD for "+userzone+" zone is " +lpc_applied_afyp_ytd+
						" Cr. If you want to see the region wise business numbers, please specify";
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business AFYP MTD for "+user_region+ " is " 
						+lpc_applied_afyp_mtd+ " Cr. LPC Applied Business AFYP YTD for "+user_region+" is "+ lpc_applied_afyp_ytd+" Cr.";

			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of " +real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+channel+ " is "+lpc_applied_afyp_ytd+
							" Cr. If you want to see the zone/region wise business numbers, please specify";
				}
				else
				{
					finalresponse="As of " +real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+channel+ " is "+lpc_applied_afyp_mtd+
							" Cr. If you want to see the zone/region wise business numbers, please specify";
				}
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+ " for "+userzone+" is "+lpc_applied_afyp_ytd+
							". If you want to see the region wise business numbers, please specify";
				}
				else
				{
					finalresponse="As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+ " for "+userzone+" is "+lpc_applied_afyp_mtd+
							" Cr. If you want to see the region wise business numbers, please specify";	
				}
			}
			else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse=" As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+ user_region+ " is "+lpc_applied_afyp_ytd+" Cr.";
				}
				else
				{
					finalresponse=" As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+ user_region+ " is "+lpc_applied_afyp_mtd+" Cr.";	
				}
			}
			else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse=" As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+ user_region+ " is "+lpc_applied_afyp_ytd+" Cr.";
				}
				else
				{
					finalresponse=" As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+ user_region+ " is "+lpc_applied_afyp_mtd+" Cr.";	
				}
			}
			else
			{
				if("YTD".equalsIgnoreCase(period))
				{
					finalresponse="As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for MLI is "+lpc_applied_afyp_ytd+
							" Cr. If you want to see the channel wise business numbers, please specify";
				}
				else
				{
					finalresponse="As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for MLI is "+lpc_applied_afyp_mtd+
							" Cr. If you want to see the channel wise business numbers, please specify";	
				}
			}
		}
		else
		{
			finalresponse="Invalid Channel For LPC";
		}
		return finalresponse.toString();
	}
}
