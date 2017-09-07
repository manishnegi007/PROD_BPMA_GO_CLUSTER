package messageimpl;

public class GrowthCaseSize 
{
	public static String growthCaseSizeIntent(String channel,String period,String userzone,String user_region,String real_tim_timstamp,String user_circle,
				String grth_case_size_afyp_ytd,String prev_case_size_afyp_ytd,String case_size_afyp_ytd_growth,String grth_case_size_afyp_mtd,
				String prev_case_size_afyp_mtd,String case_size_afyp_mtd,String case_size_afyp_mtd_growth)
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
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "MLI has witnessed case size growth of "+grth_case_size_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_case_size_afyp_ytd+ "K of Case Size as compared to " +case_size_afyp_ytd_growth+ "K today MTD business Growth of "+ 
					grth_case_size_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_case_size_afyp_mtd+" of Case Size as compared to " +case_size_afyp_mtd+ "K today."
					+ " If you want to see the Channel wise business numbers, please specIfy the same";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed case size growth of "+grth_case_size_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_case_size_afyp_ytd+ "K of Case Size as compared to " +case_size_afyp_ytd_growth+ "K today MTD business Growth of "+ 
					grth_case_size_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_case_size_afyp_mtd+" of Case Size as compared to " +case_size_afyp_mtd+ "K today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+userzone+" has witnessed case size growth of "+grth_case_size_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_case_size_afyp_ytd+ "K of Case Size as compared to " +case_size_afyp_ytd_growth+ "K today MTD business Growth of "+ 
					grth_case_size_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_case_size_afyp_mtd+" of Case Size as compared to " +case_size_afyp_mtd+ "K today."
					+ " If you want to see region wise business numbers, please specIfy the same";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region"+user_region+" MLI has witnessed case size growth of "+grth_case_size_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_case_size_afyp_ytd+ "K of Case Size as compared to " +case_size_afyp_ytd_growth+ "K today MTD business Growth of "+ 
					grth_case_size_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_case_size_afyp_mtd+" of Case Size as compared to " +case_size_afyp_mtd+ "K today.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{	
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed case size growth of " +grth_case_size_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_case_size_afyp_ytd+ " of cases size as compared to " +case_size_afyp_ytd_growth+ "K today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
			else
			{
				finalresponse= channel+" has witnessed case size growth of " +grth_case_size_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_case_size_afyp_mtd+ " of cases size as compared to " +case_size_afyp_mtd_growth+ "K today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" has witnessed case size growth of " +grth_case_size_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_case_size_afyp_ytd+ " K of cases size as compared to " +case_size_afyp_ytd_growth+ "K today "
						+ " If you want to see the region wise business numbers, please specIfy the same";
			}
			else
			{
				finalresponse= "Zone "+userzone+" has witnessed case size growth of " +grth_case_size_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_case_size_afyp_mtd+ " K of cases size as compared to " +case_size_afyp_mtd_growth+ "K today "
						+ " If you want to see the region wise business numbers, please specIfy the same";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region"+user_region+" has witnessed case size growth of " +grth_case_size_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_case_size_afyp_ytd+ " of cases size as compared to " +case_size_afyp_ytd_growth+ "K today ";
			}
			else
			{
				finalresponse= "Region"+user_region+" has witnessed case size growth of " +grth_case_size_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_case_size_afyp_mtd+ " of cases size as compared to " +case_size_afyp_mtd_growth+ "K today ";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed case size growth of " +grth_case_size_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_case_size_afyp_ytd+ " of cases size as compared to " +case_size_afyp_ytd_growth+ "K today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
			else
			{
				finalresponse= channel+" has witnessed case size growth of " +grth_case_size_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_case_size_afyp_mtd+ " of cases size as compared to " +case_size_afyp_mtd_growth+ "K today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
			}
		}
		return finalresponse.toString();
	}

}
