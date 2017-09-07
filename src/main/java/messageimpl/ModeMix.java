package messageimpl;

public class ModeMix {
	public static String modeMixIntent(String channel,String period,String userzone,String user_region,
				String real_tim_timstamp,String grth_recruitment_ytd,
				String prev_recruitment_ytd,String recruitment_ytd_growth,String grth_recruitment_mtd,String prev_recruitment_mtd,
				String recruitment_mtd_growth,String recruitment_ytd)
	{
		String finalresponse="";
		 
		if("MLI".equalsIgnoreCase(channel))
		{channel="";}
		if("Monthly".equalsIgnoreCase(period))
		{period="";}
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "MLI has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
		}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+userzone+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
					+ " If you want to see region wise business numbers, please specIfy the same";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region "+user_region+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today.";

		}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{	
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
			else
			{
				finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";

			}else
			{
				finalresponse= "Zone "+userzone+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "Region "+channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}else
			{
				finalresponse= "Region "+channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}
		else
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}else{
				finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd+ " today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}
	
		return finalresponse.toString();
	}
}
