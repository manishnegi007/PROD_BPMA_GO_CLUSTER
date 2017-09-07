package messageimpl;

public class Achievement 
{
	public static String achievementIntent(String source, String msgChannel, String achiev_mtd_adj_mfyp, String mtd_adj_mfyp_pln, 
			String mtd_adj_mfyp_act, String achiev_ytd_adj_mfyp, String ytd_adj_mfyp_pln, String ytd_adj_mfyp_act, String real_tim_timstamp)
	{
		String finalresponse="";
		if("google".equalsIgnoreCase(source))
		{
			finalresponse="At Monthly level "+msgChannel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Our plan is "
					+mtd_adj_mfyp_pln+" Cr, and till now we have achieved "+mtd_adj_mfyp_act+" Cr, At Yearly level "
					+msgChannel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Our plan is "
					+ytd_adj_mfyp_pln+"Cr and till now we have achieved "+ytd_adj_mfyp_act+" Cr";
		}
		else
		{
			finalresponse="At MTD level "+msgChannel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Our monthly plan is "
					+mtd_adj_mfyp_pln+" Cr and till "+real_tim_timstamp+" We have achieved "+mtd_adj_mfyp_act+" Cr, At YTD level "
					+msgChannel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Our YTD plan is "
					+ytd_adj_mfyp_pln+" Cr and till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr";
		}
		return finalresponse.toString();
	}
}
