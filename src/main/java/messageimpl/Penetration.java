package messageimpl;

public class Penetration 
{
	public static String penetrationIntent(String period, String productType, String source, String msgChannel, 
			String ul_penet_mtd_afyp, String mtd_inforced_afyp, String ul_penet_mtd_pol_cnt, 
			String mtd_inforced_count, String trad_penet_mtd_afyp, String trad_penet_mtd_pol_cnt, 
			String protec_penet_mtd_afyp, String protec_penet_mtd_pol_cnt, String ul_penet_ytd_afyp, 
			String ytd_inforced_afyp, String ul_penet_ytd_pol_cnt, String ytd_inforced_count, 
			String trad_penet_ytd_afyp, String trad_penet_ytd_pol_cnt, String protec_penet_ytd_afyp, 
			String protec_penet_ytd_pol_cnt)
	{
		String finalresponse="";
		if(!"Monthly".equalsIgnoreCase(period))
		{
			period=period.toUpperCase();
		}
		if("FTD".equalsIgnoreCase(period))
		{
			period="YTD";
		}
		if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)||"MONTH".equalsIgnoreCase(period))
		{
			if("ULIP".equalsIgnoreCase(productType))
			{
				if("google".equalsIgnoreCase(source))
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
							+" Cr of paid Business and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
							+ "  issued in this month ";	
				}
				else
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
							+" Cr of paid Business AFYP "+period+" and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
							+ " issued on "+period+" basis";
				}
			}
			else if("TRAD".equalsIgnoreCase(productType))
			{
				if("google".equalsIgnoreCase(source))
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
							+" Cr of paid Business, and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
							+ " issued in this month";
				}
				else
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
							+" Cr of paid Business AFYP "+period+" and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
							+ " issued on "+period+" basis";
				}
			}
			else
			{
				if("google".equalsIgnoreCase(source))
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
							+" Cr of paid Business  and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
							+ " issued in this month ";
				}
				else
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
							+" Cr of paid Business AFYP "+period+" and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
							+ " issued on "+period+" basis";
				}
			}
		}
		else
		{
			if("ULIP".equalsIgnoreCase(productType))
			{
				if("google".equalsIgnoreCase(source))
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
							" Cr of paid Business and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
							+ " issued on this year";
				}
				else
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
							" Cr of paid Business AFYP "+period+" and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
							+ " issued on "+period+" basis";
				}

			}else if("TRAD".equalsIgnoreCase(productType))
			{
				if("google".equalsIgnoreCase(source))
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
							" Cr of paid business, and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
							+ " issued in this year";
				}
				else
				{
					finalresponse=msgChannel+" "+productType+" Penetration is "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
							" Cr of paid Business AFYP "+period+" and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
							+ " issued on "+period+" basis";
				}
			}else
			{
				finalresponse=msgChannel+" "+productType+" Penetration is "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
						" Cr of paid Business AFYP "+period+" and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
						+ " issued on "+period+" basis";
			}
		}
		return finalresponse.toString();
	}
}
