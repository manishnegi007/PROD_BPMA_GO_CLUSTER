package messageimpl;

public class NBGrowth {

	public static String growthIntent(String channel, String period, String user_region, String user_circle, String userzone , String real_tim_timstamp,
			String prev_year_adj_mfyp_ytd, String grth_lst_yr_sm_adj_mfyp_ytd, String ytd_inforced_adj_mfyp, String grth_lst_yr_sm_adj_mfyp_mtd,
			String prev_year_adj_mfyp_mtd, String mtd_inforced_adj_mfyp, String subchannel)
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
		if(!"".equalsIgnoreCase(subchannel))
	        {
                channel = subchannel;
	         }
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "MLI has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today MTD business Growth of "+ 
					grth_lst_yr_sm_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_adj_mfyp_mtd+" Cr  of Adj MFYP as compared to " + mtd_inforced_adj_mfyp+ " Cr today."
					+ " If you want to see the Channel wise business numbers, please specIfy the same";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= channel+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today MTD business Growth of "+ 
					grth_lst_yr_sm_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_adj_mfyp_mtd+" Cr of Adj MFYP as compared to " + mtd_inforced_adj_mfyp+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Zone "+ userzone+" has witnessed paid Business growth of  " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today MTD business Growth of "+ 
					grth_lst_yr_sm_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_adj_mfyp_mtd+" Cr of Adj MFYP as compared to " + mtd_inforced_adj_mfyp+ " Cr today."
					+ " If you want to see region wise business numbers, please specIfy the same";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "Region "+ user_region+ " has witnessed paid Business growth of   " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
					prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today MTD business Growth of "+ 
					grth_lst_yr_sm_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					prev_year_adj_mfyp_mtd+" Cr of Adj MFYP as compared to " + mtd_inforced_adj_mfyp+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= channel+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
						prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}else
			{
				finalresponse= channel+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_mtd+" % on MTD basis, last year same time we had clocked "+
						prev_year_adj_mfyp_mtd+ " Cr of Adj MFYP as compared to " +mtd_inforced_adj_mfyp+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "Zone "+userzone+" has witnessed paid Business growth of  " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
						prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";

			}else
			{
				finalresponse= "Zone "+userzone+" has witnessed paid Business growth of  " +grth_lst_yr_sm_adj_mfyp_mtd+" % on MTD basis, last year same time we had clocked "+
						prev_year_adj_mfyp_mtd+ " Cr of Adj MFYP as compared to " +mtd_inforced_adj_mfyp+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "Region " +user_region+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
						prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}else
			{
				finalresponse= "Region " +user_region+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_mtd+" % on MTD basis, last year same time we had clocked "+
						prev_year_adj_mfyp_mtd+ " Cr of Adj MFYP as compared to " +mtd_inforced_adj_mfyp+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "MLI has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
						prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
			}else
			{
				finalresponse= "MLI has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_mtd+" % on YTD basis, last year same time we had clocked "+
						prev_year_adj_mfyp_mtd+ " Cr of Adj MFYP as compared to " +mtd_inforced_adj_mfyp+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
			}
		}
	
		return finalresponse.toString();
	}
}
