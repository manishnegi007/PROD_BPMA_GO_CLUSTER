package messageimpl;

public class Growth 
{
	public static String growthIntent(String source, String msgChannel, String grth_ovr_lst_yr_paid, String adj_mfyp_sam_ytd_lst_yr, 
			String ytd_inforced_adj_mfyp, String period, String grth_lst_yr_sm_adj_mfyp_mtd, String prev_year_adj_mfyp_mtd, 
			String mtd_inforced_adj_mfyp)
	{
		String finalresponse="";
		if("google".equalsIgnoreCase(source))
		{
			finalresponse = msgChannel+" has witnessed paid Business growth of "+grth_ovr_lst_yr_paid
					+"% on YTD basis, \n\n last year we had clocked "+adj_mfyp_sam_ytd_lst_yr+
					"Cr of paid business, as compared to "+ytd_inforced_adj_mfyp+"Cr today";
		}
		else if("YTD".equalsIgnoreCase(period))
		{
			finalresponse = msgChannel+" has witnessed paid Business growth of "+grth_ovr_lst_yr_paid
					+"% on YTD basis, \n\n last year we had clocked "+adj_mfyp_sam_ytd_lst_yr+
					"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
		}
		else
		{
			finalresponse = msgChannel+" has witnessed paid Business growth of "+grth_lst_yr_sm_adj_mfyp_mtd
					+"% on MTD basis, \n\n last year same month we had clocked "+prev_year_adj_mfyp_mtd+
					"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
			
		}
		return finalresponse.toString();
	}
}
