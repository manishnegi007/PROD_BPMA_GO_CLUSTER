package messageimpl;

public class ProductMixPaidCase {
	
	public static String productMixPaidCaseIntent(String channel, String period,String userzone,
			String user_region,String real_tim_timstamp,String ul_penet_mtd_pol_cnt,String par_penet_mtd_pol_cnt,
			String ul_penet_ytd_pol_cnt,String par_penet_ytd_pol_cnt,String nonpar_penet_mtd_pol_cnt,
			String nonpar_penet_ytd_pol_cnt, String protec_penet_mtd_pol_cnt,String protec_penet_ytd_pol_cnt,
			String user_circle, String subchannel)

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
		}if(!"".equalsIgnoreCase(subchannel))
		{
       	 	 channel = subchannel;
		}

		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for MLI is ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
					"%. YTD product mix ratio on Paid Cases for MLI is ULIP:  "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
					". If you want to see the channel wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+channel+" is ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
					"%. YTD product mix ratio on Paid Cases for "+channel+" is ULIP:  "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
					"%. If you want to see the zone/region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+userzone+" zone is ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
					"%. YTD product mix ratio on Paid Cases for "+userzone+" zone is ULIP:  "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
					"%. If you want to see the region wise business numbers, please specify.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+user_region+" is ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
					"%. YTD product mix ratio on Paid Cases for "+user_region+" is ULIP:  "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
					"%.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+channel+" is ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
						"%. If you want to see the zone/region wise business numbers, please specify.";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for "+channel+" is ULIP: "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
						"%. If you want to see the zone/region wise business numbers, please specify.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+userzone+" zone is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
						"%. If you want to see the region wise business numbers, please specify.";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for "+userzone+" zone is ULIP: "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
						"%. If you want to see the region wise business numbers, please specify.";
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+user_region+" is ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
						"%.";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for "+user_region+" is ULIP: "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
						"%.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+user_region+" is ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
						"%.";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for "+user_region+" is ULIP: "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
						"%.";	
			}
		}
		else
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for MLI is ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
						"%. If you want to see the channel wise business numbers, please specify.";	
			}else
			{
				finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for MLI is ULIP: "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
						"%. If you want to see the channel wise business numbers, please specify.";		
			}
		}
		return finalresponse.toString();
	}

}
