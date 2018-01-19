package messageimpl;

public class ProductMixADJMFYP {
	
	public static String productMixADJMFYPIntent(String channel,String period,String userzone,String user_region,
			String real_tim_timstamp,String ul_penet_mtd_adj_mfyp,String par_penet_mtd_adj_mfyp,
			String nonpar_penet_mtd_adj_mfyp,String protec_penet_mtd_adj_mfyp,String ul_penet_ytd_adj_mfyp,
			String par_penet_ytd_adj_mfyp, String nonpar_penet_ytd_adj_mfyp,String protec_penet_ytd_adj_mfyp,
			String user_circle, String subchannel, String user_clusters, String user_go)

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
		/*------------------------------------------------*/
		if(!"".equalsIgnoreCase(user_go))
		{
			user_clusters="Office "+user_go;
		}
		/*------------------------------------------------*/
		if(!"".equalsIgnoreCase(subchannel))
		{
			channel = subchannel;
		}

		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period) && "".equalsIgnoreCase(user_clusters))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
					protec_penet_mtd_adj_mfyp+ "%. YTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					protec_penet_ytd_adj_mfyp+"%. If you want to see the channel wise business numbers, please specify.";	
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period) && "".equalsIgnoreCase(user_clusters))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +channel+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
					protec_penet_mtd_adj_mfyp+ "%. YTD product mix ratio on Adj MFYP for " +channel+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					protec_penet_ytd_adj_mfyp+"%. If you want to see the zone/region wise business numbers, please specify.";	
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period) && "".equalsIgnoreCase(user_clusters))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +userzone+ " zone is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
					protec_penet_mtd_adj_mfyp+ "%. YTD product mix ratio on Adj MFYP for " +userzone+ " zone is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					protec_penet_ytd_adj_mfyp+"%. If you want to see the region wise business numbers, please specify.";	
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period) && "".equalsIgnoreCase(user_clusters))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_region+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
					protec_penet_mtd_adj_mfyp+ "%. YTD product mix ratio on Adj MFYP for " +user_region+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					protec_penet_ytd_adj_mfyp+"%. If you want to see the channel wise business numbers, please specify.";	
		}
		/*--------------------------------------------------------------------------------------------------*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period) && !"".equalsIgnoreCase(user_clusters))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
					protec_penet_mtd_adj_mfyp+ "%. YTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					protec_penet_ytd_adj_mfyp+"%. If you want to see the channel wise business numbers, please specify.";	
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
					protec_penet_mtd_adj_mfyp+ "%. YTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					protec_penet_ytd_adj_mfyp+"%. If you want to see the channel wise business numbers, please specify.";	
		}
		/*--------------------------------------------------------------------------------------------------*/
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period) && !"".equalsIgnoreCase(user_clusters))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
					protec_penet_mtd_adj_mfyp+ "%. YTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					protec_penet_ytd_adj_mfyp+"%. If you want to see the channel wise business numbers, please specify.";	
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period) && "".equalsIgnoreCase(user_clusters))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_region+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
					protec_penet_mtd_adj_mfyp+ "%. YTD product mix ratio on Adj MFYP for " +user_region+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					protec_penet_ytd_adj_mfyp+"%. If you want to see the channel wise business numbers, please specify.";	
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +channel+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
						protec_penet_mtd_adj_mfyp+ "%. If you want to see the zone/region wise business numbers, please specify."; 	
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +channel+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
						protec_penet_ytd_adj_mfyp+ "%. If you want to see the zone/region wise business numbers, please specify.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +userzone+ " zone is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
						protec_penet_mtd_adj_mfyp+ "%. If you want to see the region wise business numbers, please specify.";	
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +userzone+ " zone is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
						protec_penet_ytd_adj_mfyp+ "%. If you want to see the region wise business numbers, please specify.";		
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_region+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
						protec_penet_mtd_adj_mfyp+ "%.";	
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +user_region+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
						protec_penet_ytd_adj_mfyp+ "%.";		
			}
		}
		/*-----------------------------------------------start*/
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
						protec_penet_mtd_adj_mfyp+ "%.";	
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
						protec_penet_ytd_adj_mfyp+ "%.";		
			}
		}
		/*-----------------------------------------------end*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_region+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
						protec_penet_mtd_adj_mfyp+ "%.";	
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +user_region+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
						protec_penet_ytd_adj_mfyp+ "%.";		
			}
		}
		
		/*-----------------------------------------------start*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
						protec_penet_mtd_adj_mfyp+ "%.";	
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +user_clusters+ " is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
						protec_penet_ytd_adj_mfyp+ "%.";		
			}
		}
		/*-----------------------------------------------end*/
		else
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
						protec_penet_mtd_adj_mfyp+ "%. If you want to see the channel wise business numbers, please specify.";	
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
						protec_penet_ytd_adj_mfyp+ "%. If you want to see the channel wise business numbers, please specify.";		
			}
		}
		return finalresponse.toString();
	}
}

