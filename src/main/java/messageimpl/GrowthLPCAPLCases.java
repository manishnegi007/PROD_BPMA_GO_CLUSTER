package messageimpl;

public class GrowthLPCAPLCases 
{
	public static String growthLPCAPLCasesIntent(String channel,String period,String userzone,String user_region,
			String real_tim_timstamp,String user_circle,
			String grth_lpc_applied_cases_ytd,String prev_lpc_applied_cases_ytd,String lpc_applied_cases_ytd_growth,
			String grth_lpc_applied_cases_mtd,
			String prev_lpc_applied_cases_mtd,String lpc_applied_cases_mtd_growth, String subchannel,
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
			}else
			{
				period=period.toUpperCase();
			}
		}
		if(!"".equalsIgnoreCase(user_circle))
		{user_region="Circle "+user_circle;}
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
			finalresponse= "MLI has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_region+" MLI has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_clusters+" MLI has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today.";
					
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_clusters+" MLI has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today.";
					
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_clusters+" MLI has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today.";
					
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_clusters+" MLI has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
						+ ".";
			}
			else
			{
				finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_region+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
						+ ".";
			}
			else
			{
				finalresponse= ""+user_region+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
						+ ".";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
			&& !"".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_clusters+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today.";
						
			}
			else
			{
				finalresponse= ""+user_clusters+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today.";
						
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_region+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= ""+user_region+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_clusters+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today.";
						
			}
			else
			{
				finalresponse= ""+user_clusters+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today. ";
						
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		return finalresponse.toString();
	}
}
