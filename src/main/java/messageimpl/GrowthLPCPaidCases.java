package messageimpl;

public class GrowthLPCPaidCases 
{
	public static String growthLPCPaidcasesIntent(String channel,String period,String userzone,String user_region,
			String real_tim_timstamp,String user_circle,String grth_lpc_paid_cases_ytd,
			String prev_lpc_paid_cases_ytd,String lpc_paid_cases_ytd_growth,String grth_lpc_paid_cases_mtd,
	                String prev_lpc_paid_cases_mtd,String lpc_paid_cases_mtd_growth, String subchannel)
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
			finalresponse= "MLI has witnessed LPC paid cases growth of "+grth_lpc_paid_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_paid_cases_ytd+ ". LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_paid_cases_mtd+ "% on MTD basis, last year same month we have clocked "+
					prev_lpc_paid_cases_mtd+" of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed LPC paid cases growth of "+grth_lpc_paid_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_paid_cases_ytd+ ". LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_paid_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_paid_cases_mtd+" of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+userzone+" has witnessed LPC paid cases growth of "+grth_lpc_paid_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_paid_cases_ytd+ ". LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_paid_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_lpc_paid_cases_mtd+" of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region "+user_region+" has witnessed LPC paid cases growth of "+grth_lpc_paid_cases_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_lpc_paid_cases_ytd+ ". LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
					grth_lpc_paid_cases_mtd+ "% on MTD basis, last year same month we have clocked "+
					prev_lpc_paid_cases_mtd+" of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{	
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";

			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(channel))
			{
				finalresponse= "Zone "+userzone+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Zone "+userzone+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region "+channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Region "+channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region "+channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Region "+channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
		}
		else
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}

		}
		return finalresponse.toString();
	}
}
