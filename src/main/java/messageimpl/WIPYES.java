package messageimpl;

public class WIPYES 
{
	public static String wipyesIntent(String channel, String msgChannel, String convertsum3, String convertsum4, double hoWIPAFYP,
			double ho_wip_count, double goWIPAFYP, double go_wip_count, double itWIPAFYP, double it_wip_count, double finWIPAFYP, 
			double fin_wip_count, double miscWIPAFYP, double misc_wip_count, double welcomeWIPAFYP, double welcome_wip_count,
			double ho_wip_adj_mfyp, double go_wip_adj_mfyp, double it_wip_adj_mfyp, double fin_wip_adj_mfyp, 
			double misc_wip_adj_mfyp, double welcome_wip_adj_mfyp )
	{
		String finalresponse="";
		if(!"".equalsIgnoreCase(channel))
		{
			finalresponse="WIP for "+msgChannel+" Adj MFYP :" +convertsum3+"Cr. and Policies"+convertsum4+" "
					+"\n\n HO WIP Adj MFYP :"+ho_wip_adj_mfyp+"Cr. and Policies"+ho_wip_count+" "
					+"\n\n GO WIP Adj MFYP :"+go_wip_adj_mfyp+"Cr. and Policies "+go_wip_count+" "
					+"\n\n IT WIP Adj MFYP :"+it_wip_adj_mfyp+"Cr. and Policies"+it_wip_count+" "
					+"\n\n FIN WIP Adj MFYP :"+fin_wip_adj_mfyp+"Cr. and Policies"+fin_wip_count+" "
					+"\n\n MISC WIP Adj MFYP :"+misc_wip_adj_mfyp+"Cr. and Policies"+misc_wip_count+" "
					+"\n\n WELCOME WIP Adj MFYP :"+welcome_wip_adj_mfyp+"Cr. and Policies "+welcome_wip_count+"";
		}
		else
		{
			finalresponse="WIP Adj MFYP :" +convertsum3+"Cr."
					+"\n\n HO WIP Adj MFYP :"+ho_wip_adj_mfyp+"Cr."
					+"\n\n GO WIP Adj MFYP :"+go_wip_adj_mfyp+"Cr."
					+"\n\n IT WIP Adj MFYP :"+it_wip_adj_mfyp+"Cr."
					+"\n\n FIN WIP Adj MFYP :"+fin_wip_adj_mfyp+"Cr."
					+"\n\n MISC WIP Adj MFYP :"+misc_wip_adj_mfyp+"Cr."
					+"\n\n WELCOME WIP Adj MFYP :"+welcome_wip_adj_mfyp+"Cr.";
		}
		return finalresponse.toString();
	}

}
