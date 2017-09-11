package messageimpl;

public class NBGROWTHAPLCASES 
{
	public static String nbGROWTHAPLCASESIntent(String channel, String period, String user_circle, String user_region,
			String userzone, String prev_applied_cases_ytd, String grth_applied_cases_ytd, String grth_applied_cases_mtd, 
			String applied_cases_ytd, String prev_applied_cases_mtd, String applied_cases_mtd, String subchannel)
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
		if(!"".equalsIgnoreCase(subchannel))
	        {
                channel = subchannel;
	         }
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "MLI has witnessed applied Business growth of "+grth_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_applied_cases_ytd+ " Cr of Applied Cases as compared to " +applied_cases_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_applied_cases_mtd+" of Applied Cases as compared to " +applied_cases_mtd+ " Cr today."
					+ " If you want to see the Channel wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed applied Business growth of "+grth_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_applied_cases_ytd+ " Cr of Applied Cases as compared to " +applied_cases_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_applied_cases_mtd+" of Applied Cases as compared to " +applied_cases_mtd+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+ userzone+" "+channel+" has witnessed applied Business growth of "+grth_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_applied_cases_ytd+ " Cr of Applied Cases as compared to " +applied_cases_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_applied_cases_mtd+" of Applied Cases as compared to " +applied_cases_mtd+ " Cr today."
					+ " If you want to see region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region "+ user_region+" "+channel+ " has witnessed applied Business growth of "+grth_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_applied_cases_ytd+ " Cr of Applied Cases as compared to " +applied_cases_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_applied_cases_mtd+" of Applied Cases as compared to " +applied_cases_mtd+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";					            
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Zone "+userzone+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region " +user_region+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Region " +user_region+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region " +user_region+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Region " +user_region+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "MLI has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
						+ ". If you want to see the Channel wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "MLI has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
						+ ". If you want to see the Channel wise business numbers, please specIfy the same.";	
			}
		}
		return finalresponse.toString();
	}
}
