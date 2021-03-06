package messageimpl;

public class Applied 
{
	public static String appliedIntent(String period, String source, String msgChannel, String mtdAppliedAFYP, String real_tim_timstamp,
			String channel, String dailyAppliedAFYP, String ytd_applied_afyp,String mtd_applied_adj_ifyp, String ytd_applied_adj_ifyp)
	{
		String finalresponse="";

		if("MONTHLY".equalsIgnoreCase(period) ||"MTD".equalsIgnoreCase(period) ||"MONTH".equalsIgnoreCase(period))
		{
			if("google".equalsIgnoreCase(source))
			{
				finalresponse=" Monthly Applied Business for "+msgChannel+
						" as of now is : "+mtdAppliedAFYP+" Cr. "+"Applied Business Adj IFYP MTD is "+mtd_applied_adj_ifyp+" Cr.";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied Business AFYP MTD For "+msgChannel+
						": "+mtdAppliedAFYP+" Cr. "+"Applied Business Adj IFYP MTD is "+mtd_applied_adj_ifyp+" Cr.";
			}
		}
		else if(!"".equalsIgnoreCase(channel))
		{
			if("google".equalsIgnoreCase(source))
			{
				finalresponse="Current Update for Applied Business of "+msgChannel+" : "
						+ " For the day is, : " +dailyAppliedAFYP+" Cr"
						+", For the month is, : " +mtdAppliedAFYP+" Cr"
						+", For the year is, : " +ytd_applied_afyp+" Cr";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" Applied AFYP For "+msgChannel+"  is: "
						+ " FTD : " +dailyAppliedAFYP+" Cr"
						+", MTD : " +mtdAppliedAFYP+" Cr"
						+", YTD : " +ytd_applied_afyp+" Cr."
						+" Applied Business Adj IFYP"
						+" MTD : "+mtd_applied_adj_ifyp+" Cr"
						+", YTD :"+ytd_applied_adj_ifyp+" Cr.";
			}
		}
		else
		{
			finalresponse="As of "+real_tim_timstamp+" Applied AFYP"+
					"  is: "
					+ " FTD : " +dailyAppliedAFYP+" Cr"
					+", MTD : " +mtdAppliedAFYP+" Cr"
					+", YTD : " +ytd_applied_afyp+" Cr."
					+" Applied Business Adj IFYP"
					+" MTD : "+mtd_applied_adj_ifyp+" Cr"
					+", YTD :"+ytd_applied_adj_ifyp+" Cr.";
		}
		return finalresponse.toString();
	}
}

