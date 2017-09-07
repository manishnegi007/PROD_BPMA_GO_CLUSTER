package messageimpl;

public class Growth 
{
	public static String growthIntent(String source, String msgChannel, String grth_ovr_lst_yr_paid, String adj_mfyp_sam_ytd_lst_yr, 
			String ytd_inforced_adj_mfyp)
	{
		String finalresponse="";
		if("google".equalsIgnoreCase(source))
		{
			finalresponse = msgChannel+" has witnessed paid Business growth of "+grth_ovr_lst_yr_paid
					+"% on YTD basis, \n\n last year we had clocked "+adj_mfyp_sam_ytd_lst_yr+
					"Cr of paid business, as compared to "+ytd_inforced_adj_mfyp+"Cr today";
		}
		else
		{
			finalresponse = msgChannel+" has witnessed paid Business growth of "+grth_ovr_lst_yr_paid
					+"% on YTD basis, \n\n last year we had clocked "+adj_mfyp_sam_ytd_lst_yr+
					"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
		}
		return finalresponse.toString();
	}
}
