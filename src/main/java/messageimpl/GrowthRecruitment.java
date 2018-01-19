package messageimpl;

public class GrowthRecruitment {
	
	public static String growthRecruitmentIntent(String channel,String period,String userzone,String user_region,String real_tim_timstamp,
			String grth_recruitment_ytd,
			String prev_recruitment_ytd,String recruitment_ytd_growth,String grth_recruitment_mtd,String prev_recruitment_mtd,
			String recruitment_mtd_growth, String recruitment_ytd,String user_circle, String subchannel, 
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
				period="YTD";
			}else
			{
				period=period.toUpperCase();
			}
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
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
		   	&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= "MLI has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
		}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			 && "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			 && "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+userzone+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
					+ " If you want to see region wise business numbers, please specIfy the same";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			 && "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region "+user_region+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today.";

		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_clusters+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today.";

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_clusters+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today.";

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_clusters+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today.";

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= ""+user_region+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
					grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today.";

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{	
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
			else
			{
				finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			 && "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";

			}else
			{
				finalresponse= "Zone "+userzone+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_region+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}else
			{
				finalresponse= ""+user_region+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}
		/*------------------------------------------------start*/
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_clusters+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today. ";
						
			}else
			{
				finalresponse= ""+user_clusters+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today. ";
						
			}
		}
		/*------------------------------------------------end*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_region+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}else
			{
				finalresponse= ""+user_region+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}
		/*---------------------------------------------start*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_clusters+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today. ";
						
			}else
			{
				finalresponse= ""+user_clusters+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today. ";
						
			}
		}
		/*---------------------------------------------End*/
		else
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}else{
				finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
						prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}
		return finalresponse.toString();
	}

}
