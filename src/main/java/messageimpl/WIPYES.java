package messageimpl;

public class WIPYES 
{
	public static String wipyesIntent(String channel, String msgChannel, String convertsum3, String convertsum4, double hoWIPAFYP,
			double ho_wip_count, double goWIPAFYP, double go_wip_count, double itWIPAFYP, double it_wip_count, double finWIPAFYP, 
			double fin_wip_count, double miscWIPAFYP, double misc_wip_count, double welcomeWIPAFYP, double welcome_wip_count)
	{
		String finalresponse="";
		if(!"".equalsIgnoreCase(channel))
		{
			finalresponse="WIP for "+msgChannel+" AFYP :" +convertsum3+"Cr. and Policies"+convertsum4+" "
					+"\n\n HO WIP AFYP :"+hoWIPAFYP+"Cr. and Policies"+ho_wip_count+" "
					+"\n\n GO WIP AFYP :"+goWIPAFYP+"Cr. and Policies "+go_wip_count+" "
					+"\n\n IT WIP AFYP :"+itWIPAFYP+"Cr. and Policies"+it_wip_count+" "
					+"\n\n FIN WIP AFYP :"+finWIPAFYP+"Cr. and Policies"+fin_wip_count+" "
					+"\n\n MISC WIP AFYP :"+miscWIPAFYP+"Cr. and Policies"+misc_wip_count+" "
					+"\n\n WELCOME WIP AFYP :"+welcomeWIPAFYP+"Cr. and Policies "+welcome_wip_count+"";
		}
		else
		{
			finalresponse="WIP AFYP :" +convertsum3+"Cr."
					+"\n\n HO WIP AFYP :"+hoWIPAFYP+"Cr."
					+"\n\n GO WIP AFYP :"+goWIPAFYP+"Cr."
					+"\n\n IT WIP AFYP :"+itWIPAFYP+"Cr."
					+"\n\n FIN WIP AFYP :"+finWIPAFYP+"Cr."
					+"\n\n MISC WIP AFYP :"+miscWIPAFYP+"Cr."
					+"\n\n WELCOME WIP AFYP :"+welcomeWIPAFYP+"Cr.";
		}
		return finalresponse.toString();
	}

}
