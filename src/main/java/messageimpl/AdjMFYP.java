package messageimpl;

public class AdjMFYP 
{
	public static String adjMFYPIntent(String period, String source, String msgChannel, String mtdAdjustMFYP, String real_tim_timstamp,
			String channel, String dailyAdjustMFYP, String ytd_adj_mfyp)
	{
		String finalresponse="";
		if("MONTHLY".equalsIgnoreCase(period) ||"MTD".equalsIgnoreCase(period) ||"MONTH".equalsIgnoreCase(period))
		{
			if("google".equalsIgnoreCase(source))
			{
				finalresponse=" Current Update for Monthly Paid Business of "+msgChannel+
						" as of now is : "+mtdAdjustMFYP+" Cr";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+" Paid AdjMFYP Business MTD for "+msgChannel+
						" is : "+mtdAdjustMFYP+" Cr";
			}
		}
		else if(!"".equalsIgnoreCase(channel))
		{
			if("google".equalsIgnoreCase(source))
			{
				finalresponse=" Current Update for Paid Business of "+msgChannel
						+" For the day : " +dailyAdjustMFYP+" Cr,"
						+" For the Month : " +mtdAdjustMFYP+" Cr"
						+" For the Year : " +ytd_adj_mfyp+" Cr";

			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+"  Paid AdjMFYP Business for "+msgChannel+ " is :"+
						" FTD : " +dailyAdjustMFYP+" Cr,"
						+" MTD : " +mtdAdjustMFYP+" Cr"
						+" YTD : " +ytd_adj_mfyp+" Cr";
			}
		}
		else {
			finalresponse="As of "+real_tim_timstamp+" paid AdjMFYP Business"+
					" FTD : " +dailyAdjustMFYP+" Cr,"
					+" MTD : " +mtdAdjustMFYP+" Cr"
					+" YTD : " +ytd_adj_mfyp+" Cr";
		}
		return finalresponse.toString();
	}

}
