package messageimpl;

public class Recruitment {

	public static String recruitmentIntent(String channel, String period, String user_region, String user_circle, String userzone , String real_tim_timstamp,
			String recruitment_mtd, String recruitment_ytd, String subchannel)

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
			finalresponse= "As of " +real_tim_timstamp+" Recruitment MTD for MLI is " +recruitment_mtd+". Recruitment YTD for MLI is " +recruitment_ytd+
					". If you want to see the channel wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " +real_tim_timstamp+" Recruitment MTD for "+channel+" is " +recruitment_mtd+". Recruitment YTD for "+channel+" is " +recruitment_ytd+
					". If you want to see the zone/region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " +real_tim_timstamp+" Recruitment MTD for Zone "+userzone+" is " +recruitment_mtd+". Recruitment YTD for Zone "+userzone+" is " +recruitment_ytd+
					". If you want to see the region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= "As of " +real_tim_timstamp+" Recruitment MTD for Region "+user_region+" is " +recruitment_mtd+". Recruitment YTD for Region "+user_region+" is " +recruitment_ytd;
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "As of " +real_tim_timstamp+" Recruitment " +period+ " for "+channel+" is " +recruitment_ytd+
						". If you want to see the zone/region wise business numbers, please specify.";
			}else
			{
				finalresponse= "As of " +real_tim_timstamp+" Recruitment " +period+ " for "+channel+" is " +recruitment_mtd+
						". If you want to see the zone/region wise business numbers, please specify.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for "+channel+" is "+recruitment_ytd+
						". If you want to see the region wise business numbers, please specify.";
			}else
			{
				finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for "+channel+" is "+recruitment_mtd+
						". If you want to see the region wise business numbers, please specify.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for "+user_region+" is "+recruitment_ytd;
			}else
			{
				finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for "+user_region+" is "+recruitment_mtd;	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for "+user_region+" is  "+recruitment_ytd;
			}else
			{
				finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for "+user_region+" is "+recruitment_mtd;	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period)){
				finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for MLI is " +recruitment_ytd+
						". If you want to see the channel wise business numbers, please specify.";
			}else
			{
				finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for MLI is " +recruitment_mtd+
						". If you want to see the channel wise business numbers, please specify.";	
			}
		}
	
		return finalresponse.toString();
	}
}
