package messageimpl;

public class AppliedADJIFYP 
{
	public static String appliedAdjIfydIntent(String channel, String period, String user_region, String user_circle, String userzone , String real_tim_timstamp,
			String mtd_applied_adj_ifyp , String ytd_applied_adj_ifyp, String subchannel,String user_clusters, String user_go )

	{
		String finalresponse="";
		if("MLI".equalsIgnoreCase(channel))
		{channel="";}
		if("Monthly".equalsIgnoreCase(period))
		{period="";}
		else
		{
			if("FTD".equalsIgnoreCase(period))
			{
				period="MTD";
			}
			else
			{
				period=period.toUpperCase();
			}
		}
		if(!"".equalsIgnoreCase(user_circle))
		{
			user_region="Circle "+user_circle;
		}
		if(!"".equalsIgnoreCase(user_go))
		{
			user_clusters="Office "+user_go;
		}
		if(!"".equalsIgnoreCase(subchannel))
		{
		        channel = subchannel;
		}
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
		  && "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of "+real_tim_timstamp+" Applied Business Adj IFYP MTD for MLI "
					+ mtd_applied_adj_ifyp+"Cr. Applied Business Adj IFYP YTD for MLI "+ytd_applied_adj_ifyp+ 
					"Cr. If you want to see the channel wise business numbers, please specify";
		}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of "+real_tim_timstamp+" Applied Business Adj IFYP MTD for "+channel+" is "+mtd_applied_adj_ifyp+
					"Cr. Applied Business Adj IFYP YTD for "+channel+" is "+ytd_applied_adj_ifyp+
					"Cr. If you want to see the zone/region wise business numbers, please specify";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP MTD for "+userzone+ " zone is "+ mtd_applied_adj_ifyp+
					"Cr. Applied Business Adj IFYP YTD for "+userzone+" zone is "+ytd_applied_adj_ifyp+
					"Cr. If you want to see the region wise business numbers, please specify";

		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+ " Applied Business Adj IFYP MTD for "+user_region+" is "+mtd_applied_adj_ifyp+
					"Cr. Applied Business Adj IFYP YTD for "+user_region+" is "+ytd_applied_adj_ifyp+" Cr.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+ " Applied Business Adj IFYP MTD for "+user_clusters+" is "+mtd_applied_adj_ifyp+
					"Cr. Applied Business Adj IFYP YTD for "+user_clusters+" is "+ytd_applied_adj_ifyp+" Cr.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+ " Applied Business Adj IFYP MTD for "+user_clusters+" is "+mtd_applied_adj_ifyp+
					"Cr. Applied Business Adj IFYP YTD for "+user_clusters+" is "+ytd_applied_adj_ifyp+" Cr.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+ " Applied Business Adj IFYP MTD for "+user_clusters+" is "+mtd_applied_adj_ifyp+
					"Cr. Applied Business Adj IFYP YTD for "+user_clusters+" is "+ytd_applied_adj_ifyp+" Cr.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+ " Applied Business Adj IFYP MTD for "+user_region+" is "+mtd_applied_adj_ifyp+
					"Cr. Applied Business Adj IFYP YTD for "+user_region+" is "+ytd_applied_adj_ifyp+" Cr.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of" +real_tim_timstamp+ " Applied Business Adj IFYP " +period+" for "+channel+ "is"+ ytd_applied_adj_ifyp+
						"Cr. If you want to see the zone/region wise business numbers, please specify";
			}else
			{
				finalresponse="As of" +real_tim_timstamp+ " Applied Business Adj IFYP " +period+" for "+channel+ "is"+ mtd_applied_adj_ifyp+
						"Cr. If you want to see the zone/region wise business numbers, please specify";
			}
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			 && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+userzone+" zone is "+ytd_applied_adj_ifyp+
						". If you want to see the region wise business numbers, please specify";
			}else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+userzone+" zone is "+mtd_applied_adj_ifyp+
						". If you want to see the region wise business numbers, please specify";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_region+" is "+ytd_applied_adj_ifyp+" Cr";
			}else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_region+" is "+mtd_applied_adj_ifyp+" Cr";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_clusters+" is "+ytd_applied_adj_ifyp+" Cr";
			}else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_clusters+" is "+mtd_applied_adj_ifyp+" Cr";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_region+" is "+ytd_applied_adj_ifyp+" Cr";
			}else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_region+" is "+mtd_applied_adj_ifyp+" Cr";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_clusters+" is "+ytd_applied_adj_ifyp+" Cr";
			}else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_clusters+" is "+mtd_applied_adj_ifyp+" Cr";
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+"  Applied Business Adj IFYP "+period+ " for MLI is "+ytd_applied_adj_ifyp+
						" Cr. If you want to see the channel wise business numbers, please specify";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+"  Applied Business Adj IFYP "+period+ " for MLI is "+mtd_applied_adj_ifyp+
						" Cr. If you want to see the channel wise business numbers, please specify";
			}
		}
		return finalresponse.toString();
	}
}
