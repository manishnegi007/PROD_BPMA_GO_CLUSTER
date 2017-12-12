package messageimpl;
import hello.InnerButton; 
import hello.Facebook; 
import hello.InnerData;

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
						+"WIP AFYP: " +convertsum3+" Cr. \n\n ";

			}
			if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
			{
				if("".equalsIgnoreCase(user_sub_channel))
				{
					finalresponse = finalresponse+" Do you want to see the Data For any Channel. Please Enter the Channel Name like :\n\n Agency, Axis Bank, Banca, CAT, Ecomm, IM, IMF";
					
					InnerButton innerButton1 = new InnerButton();
					innerButton1.setText("Axis Bank");
					innerButton1.setPostback("AxisBank");
							//For Second button
					InnerButton innerButton2 = new InnerButton();
					innerButton2.setText("Agency");
					innerButton2.setPostback("Agency");
							
					InnerButton innerButton3 = new InnerButton();
					innerButton3.setText("Banca");
					innerButton3.setPostback("Banca");
							
					InnerButton innerButton4 = new InnerButton();
					innerButton4.setText("CAT");
					innerButton4.setPostback("CAT");
					
					InnerButton innerButton5 = new InnerButton();
					innerButton4.setText("Ecomm");
					innerButton4.setPostback("Ecomm");
					
					InnerButton innerButton6 = new InnerButton();
					innerButton4.setText("IM");
					innerButton4.setPostback("IM");
					
					InnerButton innerButton7 = new InnerButton();
					innerButton4.setText("IMF");
					innerButton4.setPostback("IMF");
							
					innerbuttonlist.add(innerButton1);
					innerbuttonlist.add(innerButton2);
					innerbuttonlist.add(innerButton3);
					innerbuttonlist.add(innerButton4);
					innerbuttonlist.add(innerButton5);
					innerbuttonlist.add(innerButton6);
					innerbuttonlist.add(innerButton7);
					fb.setButtons(innerbuttonlist);
					fb.setTitle("MLIChatBot");
					fb.setPlatform("API.AI");
					fb.setType("Chatbot");
					fb.setImageUrl("BOT");
					innerData.setFacebook(fb);
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
						+"WIP AFYP, : " +convertsum3+" Cr.";	
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
						+"WIP AFYP: " +convertsum3+" Cr.";
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
					+"WIP AFYP: " +convertsum3+" Cr.";
			if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
			{
				if("".equalsIgnoreCase(user_sub_channel))
				{
					finalresponse = finalresponse+" Do you want to see the Data For any Channel. Please Enter the Channel Name like :\n\n Agency, Axis Bank, Banca, CAT, Ecomm, IM, IMF";
					
					InnerButton innerButton1 = new InnerButton();
					innerButton1.setText("Axis Bank");
					innerButton1.setPostback("AxisBank");
							//For Second button
					InnerButton innerButton2 = new InnerButton();
					innerButton2.setText("Agency");
					innerButton2.setPostback("Agency");
							
					InnerButton innerButton3 = new InnerButton();
					innerButton3.setText("Banca");
					innerButton3.setPostback("Banca");
							
					InnerButton innerButton4 = new InnerButton();
					innerButton4.setText("CAT");
					innerButton4.setPostback("CAT");
					
					InnerButton innerButton5 = new InnerButton();
					innerButton4.setText("Ecomm");
					innerButton4.setPostback("Ecomm");
					
					InnerButton innerButton6 = new InnerButton();
					innerButton4.setText("IM");
					innerButton4.setPostback("IM");
					
					InnerButton innerButton7 = new InnerButton();
					innerButton4.setText("IMF");
					innerButton4.setPostback("IMF");
							
					innerbuttonlist.add(innerButton1);
					innerbuttonlist.add(innerButton2);
					innerbuttonlist.add(innerButton3);
					innerbuttonlist.add(innerButton4);
					innerbuttonlist.add(innerButton5);
					innerbuttonlist.add(innerButton6);
					innerbuttonlist.add(innerButton7);
					fb.setButtons(innerbuttonlist);
					fb.setTitle("MLIChatBot");
					fb.setPlatform("API.AI");
					fb.setType("Chatbot");
					fb.setImageUrl("BOT");
					innerData.setFacebook(fb);
				}
			}
		}
		return finalresponse.toString();
	}

}
