package messageimpl;

public class GrowthLPCAPLAFYP 
{
	public static String growthLPCAPLAFYPIntent(String channel,String period,String userzone,
	String user_region,String real_tim_timstamp,String user_circle,
	String grth_lpc_applied_afyp_ytd,String prev_lpc_applied_afyp_ytd,
	 String curr_lpc_applied_afyp_ytd,String grth_lpc_applied_afyp_mtd,
	String prev_lpc_applied_afyp_mtd,String curr_lpc_applied_afyp_mtd, String subchannel)
	{
		String finalresponse="";
		
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
			finalresponse= "MLI has witnessed LPC applied business growth of "+grth_lpc_applied_afyp_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_afyp_ytd+ "Cr LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_afyp_mtd+ "% on YTD basis, last year same month we have clocked "+
					prev_lpc_applied_afyp_mtd+"Cr of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed LPC applied business growth of "+grth_lpc_applied_afyp_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_afyp_ytd+ "Cr LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_afyp_mtd+ "% on YTD basis, last year same month we have clocked "+
					prev_lpc_applied_afyp_mtd+"Cr of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of "+grth_lpc_applied_afyp_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_afyp_ytd+ "Cr LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_afyp_mtd+ "% on YTD basis, last year same month we have clocked "+
					prev_lpc_applied_afyp_mtd+"Cr of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today."
					+ " If you want to see region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of "+grth_lpc_applied_afyp_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_applied_afyp_ytd+ "Cr LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today MTD business Growth of "+ 
					grth_lpc_applied_afyp_mtd+ "% on YTD basis, last year same month we have clocked "+
					prev_lpc_applied_afyp_mtd+"Cr of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{	
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
	
		
		return finalresponse.toString();
	}
}
