package messageimpl;

public class CaseSizePercentage {

	public static String caseSizePercentageIntent(String channel, String period, String user_region, String user_circle, 
			String userzone , String real_tim_timstamp,
			String achiev_mtd_case_active_mtd, String achiev_mtd_case_active_ytd, String subchannel)
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
			finalresponse= "MLI Case Size acheivement MTD: "+achiev_mtd_case_active_mtd+"%, YTD " +achiev_mtd_case_active_ytd+"% till "+real_tim_timstamp+
					" If you want to see the channel wise business numbers, please specIfy.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" channel Case Size acheivement MTD: "+achiev_mtd_case_active_mtd+"%, YTD " +achiev_mtd_case_active_ytd+"% till "+real_tim_timstamp+
					" If you want to see the zone/region wise business numbers, please specIfy.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+userzone+" Case Size acheivement MTD: "+achiev_mtd_case_active_mtd+"%, YTD " +achiev_mtd_case_active_ytd+"% till "+real_tim_timstamp+
					" If you want to see the region wise business numbers, please specIfy.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region "+user_region+" Case Size acheivement MTD: "+achiev_mtd_case_active_mtd+"%, YTD " +achiev_mtd_case_active_ytd+"% till "+real_tim_timstamp+
					" If you want to see the region wise business numbers, please specIfy.";

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{	

			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" channel Case Size acheivement "+period+" : "+achiev_mtd_case_active_ytd+"% till "+real_tim_timstamp+
						" If you want to see the zone/region wise business numbers please specIfy. ";
			}else
			{
				finalresponse= channel+" channel Case Size acheivement "+period+" : "+achiev_mtd_case_active_mtd+"% till "+real_tim_timstamp+
						" If you want to see the zone/region wise business numbers please specIfy. ";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" Case Size acheivement "+period+" : "+achiev_mtd_case_active_ytd+"% till "+real_tim_timstamp+
						" If you want to see the region wise business numbers please specIfy";
			}else
			{
				finalresponse= "Zone "+userzone+" Case Size acheivement "+period+" : "+achiev_mtd_case_active_mtd+"% till "+real_tim_timstamp+
						" If you want to see the region wise business numbers please specIfy";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region "+user_region+" Case Size acheivement " +period+" : "+achiev_mtd_case_active_ytd+"% till "+real_tim_timstamp+
						" If you want to see the region wise business numbers please specIfy.";
			}else
			{
				finalresponse= "Region "+user_region+" Case Size acheivement " +period+" : "+achiev_mtd_case_active_mtd+"% till "+real_tim_timstamp+
						" If you want to see the region wise business numbers please specIfy.";
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "MLI Case Size acheivement " +period+" : "+achiev_mtd_case_active_ytd+"% till "+real_tim_timstamp+
						" If you want to see the channel wise business numbers please specIfy.";
			}else
			{
				finalresponse= "MLI Case Size acheivement " +period+" : "+achiev_mtd_case_active_mtd+"% till "+real_tim_timstamp+
						" If you want to see the channel wise business numbers please specIfy.";
			}
		}
		return finalresponse.toString();
	}
}
