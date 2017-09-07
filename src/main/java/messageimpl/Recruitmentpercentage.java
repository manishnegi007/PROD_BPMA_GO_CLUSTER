package com.qc.service.messageimpl;

public class Recruitmentpercentage {

	public static String recruitmentPercentageIntent(String channel, String period, String user_region, String user_circle, String userzone , String real_tim_timstamp,
			String achiev_mtd_recruitment, String achiev_ytd_recruitment)
	{
		String finalresponse="";
		if("MLI".equalsIgnoreCase(channel))
		{channel="";}
		if("Monthly".equalsIgnoreCase(period))
		{period="";}
		if("AXIS".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(user_circle))
		{
			user_region="Circle "+user_circle;
		}
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "MLI recruitment acheivement MTD: "+achiev_mtd_recruitment+" % YTD "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
					" If you want to see the channel wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" recruitment acheivement MTD: "+achiev_mtd_recruitment+" % YTD "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
					" If you want to see the zone/region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone " +userzone+" recruitment acheivement MTD: "+achiev_mtd_recruitment+"% YTD "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
					" If you want to see the region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region "+user_region+" recruitment acheivement MTD: "+achiev_mtd_recruitment+" % YTD "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
					" If you want to see the region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= channel+" channel recruitment acheivement "+period+" : "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
						" If you want to see the zone/region wise business numbers please specify";
			}else
			{
				finalresponse= channel+" channel recruitment acheivement "+period+" : "+achiev_mtd_recruitment+" % till "+real_tim_timstamp+
						" If you want to see the zone/region wise business numbers please specify";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "Zone "+userzone+" recruitment acheivement " +period+" : "+achiev_ytd_recruitment+" % till " +real_tim_timstamp+
						" If you want to see the region wise business numbers please specify";
			}
			else
			{
				finalresponse= "Zone "+userzone+" recruitment acheivement " +period+" : "+achiev_mtd_recruitment+" % till " +real_tim_timstamp+
						" If you want to see the region wise business numbers please specify";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "Region "+user_region+" recruitment acheivement " +period+" : "+achiev_ytd_recruitment+" % till " +real_tim_timstamp+
						" If you want to see the region wise business numbers please specify";
			}else
			{
				finalresponse= "Region "+user_region+" recruitment acheivement " +period+" : "+achiev_mtd_recruitment+" % till " +real_tim_timstamp+
						" If you want to see the region wise business numbers please specify";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "MLI recruitment acheivement " +period+" : "+achiev_ytd_recruitment+" % till " +real_tim_timstamp+
						" If you want to see the channel wise business numbers please specify";
			}else
			{
				finalresponse= "MLI recruitment acheivement " +period+" : "+achiev_mtd_recruitment+" % till " +real_tim_timstamp+
						" If you want to see the channel wise business numbers please specify";	
			}
		}
	
		return finalresponse.toString();
	}
}
