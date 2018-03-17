package messageimpl;

public class Renewal 
{
	public static String renewalIntent(String daily_adj_mfyp, String mtd_adj_mfyp, String ytd_adj_mfyp, String real_tim_timstamp)
	{
		String finalresponse="";
		finalresponse="Renewal Income for MLI is FTD : "+daily_adj_mfyp+" Cr, "
				+ "MTD : "+mtd_adj_mfyp+" Cr and YTD : "+ytd_adj_mfyp+" Cr.";
		return finalresponse;
	}
}
