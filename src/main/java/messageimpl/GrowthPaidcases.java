package messageimpl;

public class GrowthPaidcases 
{
	public static String growthPaidcasesIntent(String channel,String period,String userzone,String user_region,
			String real_tim_timstamp,String grth_lst_yr_inforced_cnt_ytd, String prev_year_inforced_cnt_ytd,
			String ytd_inforced_cnt,String grth_lst_yr_inforced_cnt_mtd,String prev_year_inforced_cnt_mtd,
			String mtd_inforced_cnt, String user_circle, String subchannel, String user_clusters, String user_go)
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
			finalresponse= "MLI has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
					grth_lst_yr_inforced_cnt_mtd+ "% on MTD basis, last year same month we have clocked "+
					prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
					grth_lst_yr_inforced_cnt_mtd+ "% on MTD basis, last year same month we have clocked "+
					prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)  
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+userzone+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
					grth_lst_yr_inforced_cnt_mtd+ "% on MTD basis, last year same month we have clocked "+
					prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse=  ""+user_region+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
					grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse=  ""+user_clusters+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
					grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today.";
					
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse=  ""+user_clusters+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
					grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today.";
					
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse=  ""+user_clusters+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
					grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today.";
					
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && "".equalsIgnoreCase(period))
		{
			finalresponse=  ""+user_region+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+"% on YTD basis, last year same time we had clocked "+
					prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
					grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{	
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}

		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= "Zone "+userzone+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}

		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_region+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= ""+user_region+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}

		}
		/*----------------------------------------------------------------------------------------start*/
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_clusters+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today.";
						
			}
			else
			{
				finalresponse= ""+user_clusters+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today.";
						
			}
		}
		/*----------------------------------------------------------------------------------------start*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)
			&& "".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_region+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
			else
			{
				finalresponse= ""+user_region+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}

		}
		/*---------------------------------------------------------------------start*/
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) 
			&& !"".equalsIgnoreCase(user_clusters) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= ""+user_clusters+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today.";
						
			}
			else
			{
				finalresponse= ""+user_clusters+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today.";
						
			}

		}
		/*---------------------------------------------------------------------End*/
		else
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+"% on "+period+" basis, last year same time we had clocked "+
						prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
						+ ". If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}
		}
		return finalresponse.toString();
	}
}
