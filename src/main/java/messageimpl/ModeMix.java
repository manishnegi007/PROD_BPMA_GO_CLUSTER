package messageimpl;

public class ModeMix {
	public static String modeMixIntent(String channel, String period, String userzone, String user_region, String real_tim_timstamp,
			String annual_adj_mfyp_mtd, String semi_annual_adj_mfyp_mtd, String quarterly_adj_mfyp_mtd, String monthly_adj_mfyp_mtd,
			String single_adj_mfyp_mtd, String annual_adj_mfyp_ytd, String semi_annual_adj_mfyp_ytd, String quarterly_adj_mfyp_ytd,
			String monthly_adj_mfyp_ytd, String single_adj_mfyp_ytd, String user_circle, String subchannel)
	{
		System.out.println("Inside ModeMix");
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
		{user_region="Circle "+user_circle;}
		if(!"".equalsIgnoreCase(subchannel))
	        {
                channel = subchannel;
	         }
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of  "+real_tim_timstamp+" MTD Mode mix ratio for MLI is Annual:"+
					annual_adj_mfyp_mtd+ "% , Semi Annual: " +semi_annual_adj_mfyp_mtd+ " %, Quarterly: "+ 
					quarterly_adj_mfyp_mtd+ " % & Monthly: "+
					monthly_adj_mfyp_mtd+" , Single: " +single_adj_mfyp_mtd+ "%."+"YTD Mode mix ratio for MLI is Annual:"+
					annual_adj_mfyp_ytd+"%, Semi Annual:"+semi_annual_adj_mfyp_ytd+"%, Quarterly:"+
					quarterly_adj_mfyp_ytd+"% & Monthly:"+monthly_adj_mfyp_ytd+", Single:"+single_adj_mfyp_ytd+"%."
					+ "If you want to see the channel wise business numbers, please specify";
		}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of  "+real_tim_timstamp+" MTD Mode mix ratio for "+channel+" channel is Annual:"+
					annual_adj_mfyp_mtd+ "% , Semi Annual: " +semi_annual_adj_mfyp_mtd+ " %, Quarterly: "+ 
					quarterly_adj_mfyp_mtd+ " % & Monthly: "+
					monthly_adj_mfyp_mtd+" , Single: " +single_adj_mfyp_mtd+ "%."+"YTD Mode mix ratio for "+channel+" channel is Annual:"+
					annual_adj_mfyp_ytd+"%, Semi Annual:"+semi_annual_adj_mfyp_ytd+"%, Quarterly:"+
					quarterly_adj_mfyp_ytd+"% & Monthly:"+monthly_adj_mfyp_ytd+", Single:"+single_adj_mfyp_ytd+"%."
					+ "If you want to see the zone/region wise business numbers, please specify";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of  "+real_tim_timstamp+" MTD Mode mix ratio for Zone "+userzone+" is Annual:"+
					annual_adj_mfyp_mtd+ "% , Semi Annual: " +semi_annual_adj_mfyp_mtd+ " %, Quarterly: "+ 
					quarterly_adj_mfyp_mtd+ " % & Monthly: "+
					monthly_adj_mfyp_mtd+" , Single: " +single_adj_mfyp_mtd+ "%."+"YTD Mode mix ratio for Zone "+userzone+" is Annual:"+
					annual_adj_mfyp_ytd+"%, Semi Annual:"+semi_annual_adj_mfyp_ytd+"%, Quarterly:"+
					quarterly_adj_mfyp_ytd+"% & Monthly:"+monthly_adj_mfyp_ytd+", Single:"+single_adj_mfyp_ytd+"%."
					+ "If you want to see the region wise business numbers, please specify";
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of  "+real_tim_timstamp+" MTD Mode mix ratio for "+user_region+" is Annual:"+
					annual_adj_mfyp_mtd+ "% , Semi Annual: " +semi_annual_adj_mfyp_mtd+ " %, Quarterly: "+ 
					quarterly_adj_mfyp_mtd+ " % & Monthly: "+
					monthly_adj_mfyp_mtd+" , Single: " +single_adj_mfyp_mtd+ "%." +"YTD Mode mix ratio for "+user_region+" is Annual:"+
					annual_adj_mfyp_ytd+"%, Semi Annual:"+semi_annual_adj_mfyp_ytd+"%, Quarterly:"+
					quarterly_adj_mfyp_ytd+"% & Monthly:"+monthly_adj_mfyp_ytd+", Single:"+single_adj_mfyp_ytd+"%.";

		}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{	
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" MTD Mode mix ratio for "+channel+" channel is Annual:"+
						annual_adj_mfyp_mtd+ "%, Semi Annual: "+semi_annual_adj_mfyp_mtd+ "%, Quarterly:"+
						quarterly_adj_mfyp_mtd+"& Monthly: "
						+monthly_adj_mfyp_mtd+", Single: "+single_adj_mfyp_mtd+ "%."
						+ " If you want to see the zone/region wise business numbers, please specify";
			}
			else
			{
				finalresponse= "As of "+real_tim_timstamp+" YTD Mode mix ratio for zone "+userzone+" is Annual:"+
						annual_adj_mfyp_ytd+ "%, Semi Annual: "+semi_annual_adj_mfyp_ytd+ "%, Quarterly:"+
						quarterly_adj_mfyp_ytd+"& Monthly: "
						+monthly_adj_mfyp_ytd+", Single: "+single_adj_mfyp_ytd+ "%."
						+ " If you want to see the zone/region wise business numbers, please specify";
			}
		}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" MTD Mode mix ratio for zone "+userzone+" is Annual:"+
						annual_adj_mfyp_mtd+ "%, Semi Annual: "+semi_annual_adj_mfyp_mtd+ "%, Quarterly:"+
						quarterly_adj_mfyp_mtd+"& Monthly: "
						+monthly_adj_mfyp_mtd+", Single: "+single_adj_mfyp_mtd+ "%."
						+ " If you want to see the region wise business numbers, please specify";

			}else
			{
				finalresponse= "As of "+real_tim_timstamp+" YTD Mode mix ratio for zone "+userzone+" is Annual:"+
						annual_adj_mfyp_ytd+ "%, Semi Annual: "+semi_annual_adj_mfyp_ytd+ "%, Quarterly:"+
						quarterly_adj_mfyp_ytd+"& Monthly: "
						+monthly_adj_mfyp_ytd+", Single: "+single_adj_mfyp_ytd+ "%."
						+ " If you want to see the region wise business numbers, please specify";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" MTD Mode mix ratio for "+user_region+" is Annual:"+
						annual_adj_mfyp_mtd+ "%, Semi Annual: "+semi_annual_adj_mfyp_mtd+ "%, Quarterly:"+
						quarterly_adj_mfyp_mtd+"& Monthly: "
						+monthly_adj_mfyp_mtd+", Single: "+single_adj_mfyp_mtd+ "%.";
			}else
			{
				finalresponse= "As of "+real_tim_timstamp+" YTD Mode mix ratio for "+user_region+" is Annual:"+
						annual_adj_mfyp_ytd+ "%, Semi Annual: "+semi_annual_adj_mfyp_ytd+ "%, Quarterly:"+
						quarterly_adj_mfyp_ytd+"& Monthly: "
						+monthly_adj_mfyp_ytd+", Single: "+single_adj_mfyp_ytd+ "%.";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" MTD Mode mix ratio for "+user_region+" is Annual:"+
						annual_adj_mfyp_mtd+ "%, Semi Annual: "+semi_annual_adj_mfyp_mtd+ "%, Quarterly:"+
						quarterly_adj_mfyp_mtd+"& Monthly: "
						+monthly_adj_mfyp_mtd+", Single: "+single_adj_mfyp_mtd+ "%.";
			}else
			{
				finalresponse= "As of "+real_tim_timstamp+" YTD Mode mix ratio for "+user_region+" is Annual:"+
						annual_adj_mfyp_ytd+ "%, Semi Annual: "+semi_annual_adj_mfyp_ytd+ "%, Quarterly:"+
						quarterly_adj_mfyp_ytd+"& Monthly: "
						+monthly_adj_mfyp_ytd+", Single: "+single_adj_mfyp_ytd+ "%.";
			}
		}
		else
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse= "As of "+real_tim_timstamp+" MTD Mode mix ratio for "+period+" is Annual:"+
						annual_adj_mfyp_mtd+ "%, Semi Annual: "+semi_annual_adj_mfyp_mtd+ "%, Quarterly:"+
						quarterly_adj_mfyp_mtd+"& Monthly: "
						+monthly_adj_mfyp_mtd+", Single: "+single_adj_mfyp_mtd+ "%."
						+ " If you want to see the channel wise business numbers, please specify";
			}else{
				finalresponse= "As of "+real_tim_timstamp+" YTD Mode mix ratio for "+period+" is Annual:"+
						annual_adj_mfyp_ytd+ "%, Semi Annual: "+semi_annual_adj_mfyp_ytd+ "%, Quarterly:"+
						quarterly_adj_mfyp_ytd+"& Monthly: "
						+monthly_adj_mfyp_ytd+", Single: "+single_adj_mfyp_ytd+ "%."
						+ " If you want to see the channel wise business numbers, please specify";
			}
		}
		System.out.println("OutSide ModeMix");
		return finalresponse.toString();
	}
}
