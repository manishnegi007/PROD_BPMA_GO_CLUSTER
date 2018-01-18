package messageimpl;

public class GrowthLPCAPLADJIFYP {

	public static String growthLPCAPLADJIFYPIntent(String channel,String period,String userzone,
			String user_region,String real_tim_timstamp,String user_circle,
			String grth_lpc_applied_adj_ifyp_ytd,String prev_lpc_applied_adj_ifyp_ytd,
			String lpc_applied_adj_ifyp_ytd_growth,String grth_lpc_applied_adj_ifyp_mtd,
			String prev_lpc_applied_adj_ifyp_mtd,String lpc_applied_adj_ifyp_mtd_growth,
			String lpc_paid_adj_mfyp_ytd_growth,String lpc_paid_adj_mfyp_mtd_growth,
			String grth_lpc_paid_adj_mfyp_ytd,String prev_lpc_paid_adj_mfyp_ytd,
			String prev_lpc_paid_adj_mfyp_mtd,String grth_lpc_paid_adj_mfyp_mtd, String subchannel,
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
		if(!"".equalsIgnoreCase(subchannel))
		{
			channel = subchannel;
		}

		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= "MLI has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
					+ " If you want to see the Channel wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= channel+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
					+ " If you want to see region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			finalresponse= "Cluster "+user_clusters+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			finalresponse= "Cluster "+user_clusters+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Cluster "+user_clusters+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Cluster "+user_clusters+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Cluster "+user_clusters+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Cluster "+user_clusters+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_paid_adj_mfyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_adj_mfyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_paid_adj_mfyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_adj_mfyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}

		return finalresponse.toString();
	}
}
