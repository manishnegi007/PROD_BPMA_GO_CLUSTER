package messageimpl;
import hello.InnerButton; 
import hello.Facebook; 
import hello.InnerData;
import java.util.ArrayList; 
import java.util.List; 
public class Numbers 
{
	public static String numberIntent(String period, String source, String msgChannel, String mtdAdjustMFYP, String mtdAppliedAFYP,
			String convertsum3, String real_tim_timstamp, String channel, String user_sub_channel, String dailyAdjustMFYP, 
			String ytd_adj_mfyp, String dailyAppliedAFYP, String ytd_applied_afyp)
	{
		List<InnerButton> innerbuttonlist = new ArrayList<InnerButton>();
		Facebook fb = new Facebook();
		InnerData innerData= new InnerData();
		String finalresponse="";
		if("MONTHLY".equalsIgnoreCase(period) ||"MTD".equalsIgnoreCase(period) ||"MONTH".equalsIgnoreCase(period))
		{
			if("google".equalsIgnoreCase(source))
			{
				finalresponse="Current Monthly Update for "+msgChannel+ "  :\n "
						+"Paid Value for Month is, : "+mtdAdjustMFYP+" Cr \n\n"
						+"Applied Value for Month is, : " +mtdAppliedAFYP+" Cr \n\n "
						+"Wip Amount is, : " +convertsum3+" Cr. \n\n ";
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+
						", the Business update for "+msgChannel+ " is :\n"
						+"Adj MFYP MTD : "+mtdAdjustMFYP+" Cr \n\n"
						+"Applied AFYP MTD: " +mtdAppliedAFYP+" Cr \n\n "
						+"WIP Adj MFYP: " +convertsum3+" Cr. \n\n ";

			}
			if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
			{
				if("".equalsIgnoreCase(user_sub_channel))
				{
					finalresponse = finalresponse+" Do you want to see the Data For any Channel. Please Enter the Channel Name like :\n\n Agency, Axis Bank, Banca, CAT, Ecomm, IM, IMF";
					
					
				}
			}
		}
		else if(!"MLI".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(channel))
		{
			if("google".equalsIgnoreCase(source))
			{
				finalresponse="Current Business Update for "+msgChannel+" is : \n Paid Value For the day is, :"+dailyAdjustMFYP+" Cr, \n\n" 
						+"Paid Value For the Month  is, : " +mtdAdjustMFYP+" Cr, \n\n"
						+"Paid Value For the Year is, : "+ytd_adj_mfyp+" Cr, \n\n"
						+"Applied value for the Day is, : " +dailyAppliedAFYP+" Cr, \n\n"
						+"Applied value for the Month is, : " +mtdAppliedAFYP+" Cr, \n\n"
						+"Applied value for the Year is, : "+ytd_applied_afyp+" Cr, \n\n"
						+"WIP Adj MFYP, : " +convertsum3+" Cr.";	
			}
			else
			{
				finalresponse="As of "+real_tim_timstamp+
						", the Business update for "+msgChannel+" is : \n Adj MFYP FTD:"+dailyAdjustMFYP+" Cr, \n\n"
						+"Adj MFYP MTD: " +mtdAdjustMFYP+" Cr, \n\n"
						+"Adj MFYP YTD : "+ytd_adj_mfyp+" Cr, \n\n"
						+"Applied AFYP FTD: " +dailyAppliedAFYP+" Cr, \n\n"
						+"Applied AFYP MTD: " +mtdAppliedAFYP+" Cr, \n\n"
						+"Applied AFYP YTD: "+ytd_applied_afyp+" Cr, \n\n"
						+"WIP Adj MFYP: " +convertsum3+" Cr.";
			}
		}
		else 
		{
			finalresponse="As of "+real_tim_timstamp+
					", the Business update for "+msgChannel+" is : \n Adj MFYP FTD:"+dailyAdjustMFYP+" Cr, \n\n"
					+"Adj MFYP MTD: " +mtdAdjustMFYP+" Cr, \n\n"
					+"Adj MFYP YTD : "+ytd_adj_mfyp+" Cr, \n\n"
					+"Applied AFYP FTD: " +dailyAppliedAFYP+" Cr, \n\n"
					+"Applied AFYP MTD: " +mtdAppliedAFYP+" Cr, \n\n"
					+"Applied AFYP YTD: "+ytd_applied_afyp+" Cr, \n\n"
					+"WIP Adj MFYP: " +convertsum3+" Cr.";
			if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
			{
				if("".equalsIgnoreCase(user_sub_channel))
				{
					finalresponse = finalresponse+" Do you want to see the Data For any Channel. Please Enter the Channel Name like :\n\n Agency, Axis Bank, Banca, CAT, Ecomm, IM, IMF";
					
					
				}
			}
		}
		return finalresponse.toString();
	}

}
