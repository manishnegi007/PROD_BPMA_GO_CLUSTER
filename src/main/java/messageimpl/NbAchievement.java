package messageimpl;

public class NbAchievement 
{
	public static String achievementIntent(String channel,String period, String userzone,String user_region,String real_tim_timstamp,
		String achiev_mtd_adj_mfyp, String achiev_mtd_paid_case,String mtd_adj_afyp_act,
		String mtd_adj_afyp_pln,String mtd_paid_case_act,String mtd_paid_case_pln, String achiev_ytd_adj_mfyp,
		String achiev_ytd_paid_case,String ytd_adj_mfyp_act, String ytd_adj_mfyp_pln,String ytd_paid_case_act,
		String ytd_paid_case_pln, String user_circle)
	{

		String finalresponse="";
		if("MLI".equalsIgnoreCase(channel))
		{channel="";}
		if("Monthly".equalsIgnoreCase(period))
		{period="";}
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse= " MLI MTD acheivement for Adj MFYP is "+achiev_mtd_adj_mfyp+" % , Paid case acheivement is "+achiev_mtd_paid_case+" % MTD acheivement for Adj MFYP is "+
					mtd_adj_afyp_act+" Cr against "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+" till "+real_tim_timstamp+" At YTD level MLI has achieved "+
					achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFPY & "+achiev_ytd_paid_case+" % for Paid Cases , YTD acheivement for Adj MFYP is "+ytd_adj_mfyp_act+" Cr against "+ytd_adj_mfyp_pln+
					" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+" till "+real_tim_timstamp+" If you want to see the channel wise business numbers, please specify ";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="At MTD level "+channel+" has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
					" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+"At YTD level "+channel+" has achieved "+achiev_ytd_adj_mfyp+
					" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Our YTD plan is "+ytd_adj_mfyp_pln+
					" for Adj MFYP and till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
					" If you want to see the zone/region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="At MTD level "+userzone+" zone has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
					" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+"At YTD level "+userzone+" zone has achieved "+achiev_ytd_adj_mfyp+
					" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Our YTD plan is "+ytd_adj_mfyp_pln+
					" for Adj MFYP and till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
					" If you want to see the zone/region wise business numbers, please specify";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
		{
			finalresponse="At MTD level "+user_region+" region has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
					" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+"At YTD level "+user_region+" region has achieved "+achiev_ytd_adj_mfyp+
					" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Our YTD plan is "+ytd_adj_mfyp_pln+
					" for Adj MFYP and till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
					" If you want to see the zone/region wise business numbers, please specify";	
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="At MTD level "+channel+" has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+
						" If you want to see the zone/region wise business numbers, please specify";
			}
			else
			{
				finalresponse="At YTD level "+channel+" has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+ytd_adj_mfyp_pln+
						" till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
						" If you want to see the zone/region wise business numbers, please specify";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="At MTD level "+userzone+" zone has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+
						" If you want to see the region wise business numbers, please specify";	
			}
			else
			{
				finalresponse="At YTD level "+userzone+" zone has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+ytd_adj_mfyp_pln+
						" till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
						" If you want to see the region wise business numbers, please specify";		
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="At MTD level "+user_region+" region has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+
						"";
			}
			else
			{
				finalresponse="At YTD level "+user_region+" region has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+ytd_adj_mfyp_pln+
						" till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
						"";	
			}
		}
		else
		{
			if("MTD".equalsIgnoreCase(period))
			{
				finalresponse="At MTD level MLI has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+
						" If you want to see the channel wise business numbers, please specify";
			}
			else
			{
				finalresponse="At YTD level MLI has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+ytd_adj_mfyp_pln+
						" till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
						" If you want to see the channel wise business numbers, please specify";	
			}
		}
		return finalresponse.toString();
	}
}
