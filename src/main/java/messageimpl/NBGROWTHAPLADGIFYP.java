package messageimpl;

public class NBGROWTHAPLADGIFYP 
{
	public static String nbGROWTHAPLADGIFYPIntent(String channel, String period, String user_circle, String user_region, String userzone, 
			String grth_applied_adj_ifyp_ytd, String rpev_applied_adj_ifyp_ytd, String applied_adj_ifyp_ytd, String grth_applied_adj_ifyp_mtd,
			String rpev_applied_adj_ifyp_mtd, String applied_adj_ifyp_mtd, String subchannel,String user_clusters, String user_go)
	{
		String finalresponse="";
		if("MLI".equalsIgnoreCase(channel))
		{channel="";}
		if("Monthly".equalsIgnoreCase(period))
		{period="";}
		else
		{
			if("FTD".equalsIgnoreCase(period))
			{
				period="MTD";
			}else
			{
				period=period.toUpperCase();
			}
		}
		if(!"".equalsIgnoreCase(user_circle))
		{
			user_region="Circle "+user_circle;
		}
		if(!"".equalsIgnoreCase(user_go))
		{
			user_clusters="Circle "+user_go;
		}
		if(!"".equalsIgnoreCase(subchannel))
	        {
                channel = subchannel;
	         }
		if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= "MLI has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					rpev_applied_adj_ifyp_mtd+" Cr  of Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
					+ " If you want to see the Channell wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= channel+" has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= "Zone "+ userzone+" has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
					+ " If you want to see region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= ""+ user_region+ " has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			finalresponse= ""+ user_clusters+ " has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			finalresponse= ""+ user_clusters+ " has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			finalresponse= ""+ user_region+ " has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
					rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
					grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
					rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
					+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= channel+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today "
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= channel+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "Zone "+userzone+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= "Zone "+userzone+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "" +user_region+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= "" +user_region+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "" +user_clusters+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= "" +user_clusters+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& "".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "" +user_region+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= "" +user_region+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period)&& !"".equalsIgnoreCase(user_clusters))
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "" +user_clusters+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= "" +user_clusters+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today."
						+ " If you want to see the Zone/region wise business numbers, please specIfy the same.";	
			}
		}
		else
		{
			if("YTD".equalsIgnoreCase(period))
			{
				finalresponse= "MLI has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today."
						+ " If you want to see the Channel wise business numbers, please specIfy the same.";
			}else
			{
				finalresponse= "MLI has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
						rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today."
						+ " If you want to see the Channel wise business numbers, please specIfy the same.";	
			}
		}
		return finalresponse.toString();
	}
}
