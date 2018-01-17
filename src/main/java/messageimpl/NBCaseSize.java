package messageimpl;

public class NBCaseSize 
{
	public static String nbCaseSizeIntent(String channel, String period, String user_circle, String user_region, String userzone, 
			String real_tim_timstamp, String case_size_afyp_mtd, String case_size_afyp_ytd, String subchannel,
			String user_clusters, String user_go)
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
			finalresponse= "As of " +real_tim_timstamp+ " Case Size MTD for MLI is "
					+ case_size_afyp_mtd+ " Case Size YTD for MLI is " +case_size_afyp_ytd+ 
					". If you want to see the channel wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " +real_tim_timstamp+ " Case Size MTD for "+channel+" is "+case_size_afyp_mtd+
					" Case Size YTD for "+channel+" is "+case_size_afyp_ytd+
					". If you want to see the zone/region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp+" Case Size MTD for "+userzone+ " zone is "+ case_size_afyp_mtd+" Case Size YTD for "+
					userzone+" zone is "+case_size_afyp_ytd+". If you want to see the region wise business numbers, please specify.";

		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp+ " Case Size MTD for "+user_region+"  is "+case_size_afyp_mtd+
					" Case Size YTD for"+user_region+" is "+case_size_afyp_ytd;
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters)
				&& "".equalsIgnoreCase(period))
		{
			finalresponse="As of " +real_tim_timstamp+ " Case Size MTD for "+user_clusters+" is "+case_size_afyp_mtd+
					" Case Size YTD for"+user_clusters+" is "+case_size_afyp_ytd;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+ " Case Size " +period+" for "+channel+ " is "+ case_size_afyp_ytd+
						". If you want to see the zone/region wise business numbers, please specify.";
			}else 
			{
				finalresponse="As of " +real_tim_timstamp+ " Case Size " +period+" for "+channel+ " is "+ case_size_afyp_mtd+
						". If you want to see the zone/region wise business numbers, please specify.";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+userzone+" zone is "+case_size_afyp_ytd+
						". If you want to see the region wise business numbers, please specify.";
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+userzone+" zone is "+case_size_afyp_mtd+
						". If you want to see the region wise business numbers, please specify.";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_region+" is "+case_size_afyp_ytd;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_region+" is "+case_size_afyp_mtd;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_clusters+" is "+case_size_afyp_ytd;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_clusters+" is "+case_size_afyp_mtd;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_region+" is "+case_size_afyp_ytd;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_region+" is "+case_size_afyp_mtd;
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters)
				&& !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_clusters+" is "+case_size_afyp_ytd;
			}else
			{
				finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_clusters+" is "+case_size_afyp_mtd;
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse="As of " +real_tim_timstamp+"  Case Size "+period+ " for MLI is "+case_size_afyp_ytd+
						". If you want to see the channel wise business numbers, please specify.";
			}else 
			{
				finalresponse="As of " +real_tim_timstamp+"  Case Size "+period+ " for MLI is "+case_size_afyp_mtd+
						". If you want to see the channel wise business numbers, please specify.";
			}
		}
		return finalresponse.toString();
	}
}
