package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import common.Commons;
import common.XTrustProvider;
import hello.WebhookResponse;
@Component
public class APIConsumerService {
	
	public WebhookResponse getWipDataAll(String action, String channel, String period, String productType, String planType)
	{
		System.out.println("getWipDataAll API START");
		ResourceBundle res = ResourceBundle.getBundle("errorMessages");
		ResourceBundle mtd = ResourceBundle.getBundle("MTD");
		ResourceBundle ytd = ResourceBundle.getBundle("YTD");
		String output = new String();
		StringBuilder result = new StringBuilder();	
		String DevMode = "N";
		HttpURLConnection conn = null;
		String segment="";
		String serviceChannel="";
		String speech="";

     		try 
		{
			if("NUMBERS".equalsIgnoreCase(action))
			{
				if("".equalsIgnoreCase(channel) || "MLI".equalsIgnoreCase(channel) || "Axis".equalsIgnoreCase(channel))
				{
					if("MLI".equalsIgnoreCase(channel) ||  "".equalsIgnoreCase(channel))
					{
						segment = "paid,wip,applied";
						serviceChannel = "";
					}
					else
					{
						segment = "paid,wip,applied";
						serviceChannel = "Axis Bank";
					}
				}
				else
				{
					segment = "paid,wip,applied";
					serviceChannel = channel;
				}
			}
			else if("AdjMFYP".equalsIgnoreCase(action) || "Growth".equalsIgnoreCase(action) 
					|| "Achievement".equalsIgnoreCase(action)|| "Penetration".equalsIgnoreCase(action))
			{
				if("MLI".equalsIgnoreCase(channel) || "Axis".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
				{
					if("MLI".equalsIgnoreCase(channel) ||  "".equalsIgnoreCase(channel))
					{
						segment="paid";
						serviceChannel = "";
					}
					else
					{
						segment="paid";
						serviceChannel = "Axis Bank";
					}
				}
				else
				{
					segment="paid";
					serviceChannel = channel;
				}
			}
			else if("WIP".equalsIgnoreCase(action)||"WIP.YES".equalsIgnoreCase(action))
			{
				if("".equalsIgnoreCase(channel) || "Axis".equalsIgnoreCase(channel) || "MLI".equalsIgnoreCase(channel))
				{
					if("".equalsIgnoreCase(channel) || "MLI".equalsIgnoreCase(channel))
					{
						segment="wip";
						serviceChannel = "";
					}else
					{
						segment="wip";
						serviceChannel = "Axis Bank";
					}
				}
				else
				{
					segment="wip";
					serviceChannel = channel;
				}
			}
			else if("APPLIED".equalsIgnoreCase(action))
			{
				if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel) || "Axis".equalsIgnoreCase(channel))
				{
					if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
					{
						segment="applied";
						serviceChannel = "";
					}
					else
					{
						segment="applied";
						serviceChannel = "Axis Bank";
					}
				}
				else
				{
					segment="applied";
					serviceChannel = channel;
				}
			}
			XTrustProvider trustProvider=new XTrustProvider();
			trustProvider.install();
			StringBuilder requestdata=new StringBuilder();
			if("NUMBERS".equalsIgnoreCase(action) || "AdjMFYP".equalsIgnoreCase(action) 
					|| "WIP".equalsIgnoreCase(action) || "APPLIED".equalsIgnoreCase(action)||"WIP.YES".equalsIgnoreCase(action))
			{
				System.out.println("First  API START Call");
				String serviceurl = res.getString("serviceurl");
				URL url = new URL(serviceurl);
				if(DevMode!=null && !"".equalsIgnoreCase(DevMode) && "Y".equalsIgnoreCase(DevMode))
				{
					Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
					conn = (HttpURLConnection) url.openConnection(proxy);
				}
				else
				{
					conn = (HttpURLConnection) url.openConnection();
				}
				HttpsURLConnection.setFollowRedirects(true);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				requestdata.append("	{	");
				requestdata.append("	  \"header\": {	");
				requestdata.append("	    \"correlationId\": \"1234567890\",	");
				requestdata.append("	    \"msgVersion\": \"\",	");
				requestdata.append("	    \"appId\": \"\",	");
				requestdata.append("	    \"userId\": \"\",	");
				requestdata.append("	    \"password\": \"\",	");
				requestdata.append("	    \"rollId\":\"\"	");
				requestdata.append("	  },	");
				requestdata.append("	  \"payload\": {	");
				requestdata.append("	    \"segment\": \""+segment+"\",	");
				requestdata.append("	    \"channel\": \""+serviceChannel+"\"	");
				requestdata.append("	  }	");
				requestdata.append("	}	");
				System.out.println("External API Call : START");
				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(requestdata.toString());
				writer.flush();
				try {writer.close(); } catch (Exception e1) {}
				int apiResponseCode = conn.getResponseCode();
				if(apiResponseCode == 200)
				{
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					while ((output = br.readLine()) != null) 
					{
						result.append(output);
					}
					conn.disconnect();
					br.close();
					System.out.println("First  API END Call");

				}
			}
			else
			{
				String serviceurl2 = res.getString("serviceurl2");
				URL url2 = new URL(serviceurl2);
				conn = (HttpURLConnection) url2.openConnection();
				HttpsURLConnection.setFollowRedirects(true);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				requestdata.append("	{	");
				requestdata.append("	  \"header\": {	");
				requestdata.append("	    \"correlationId\": \"1234567890\",	");
				requestdata.append("	    \"msgVersion\": \"\",	");
				requestdata.append("	    \"appId\": \"\",	");
				requestdata.append("	    \"userId\": \"\",	");
				requestdata.append("	    \"password\": \"\",	");
				requestdata.append("	    \"rollId\":\"\"	");
				requestdata.append("	  },	");
				requestdata.append("	  \"payload\": {	");
				requestdata.append("	    \"segment\": \""+action+"\",	");
				requestdata.append("	    \"channel\": \""+serviceChannel+"\",	");
				requestdata.append("	    \"planType\": \""+planType+"\"	");
				requestdata.append("	  }	");
				requestdata.append("	}	");
				
				OutputStreamWriter writer2 = new OutputStreamWriter(conn.getOutputStream());
				writer2.write(requestdata.toString());
				writer2.flush();
				try {writer2.close(); } 
				catch (Exception e1) 
				{
					
				}
				int apiResponseCode2 = conn.getResponseCode();
				if(apiResponseCode2 == 200)
				{
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					while ((output = br.readLine()) != null) 
					{
						result.append(output);
					}
					conn.disconnect();
					br.close();
					

				}
				else
				{
					
				}
			}
			try
			{

				DecimalFormat df = new DecimalFormat("####0.00");
				DecimalFormat df1 = new DecimalFormat("####");
				JSONObject object = new JSONObject(result.toString());
				String finalresponse="";
				double dailyAdjustMFYP1=0;  double mtdAdjustMFYP1=0;    double dailyAppliedAFYP1=0;
				double mtdAppliedAFYP1=0;	double wipAFYP=0;           double hoWIPAFYP=0;
				double goWIPAFYP=0;			double itWIPAFYP=0;	   	    double finWIPAFYP=0;
				double miscWIPAFYP=0;		double welcomeWIPAFYP=0;	double wip_count=0;
				double ho_wip_count=0;		double go_wip_count=0;		double it_wip_count=0;
				double fin_wip_count=0;		double misc_wip_count=0;	double welcome_wip_count=0;
				double ytd_inforced_afyp1=0;double ytd_applied_afyp1=0; double mtd_inforced_afyp1=0;
				double sum = 0; double sum2=0; double sum3 = 0; double sum4 = 0;
				String 	ul_penet_mtd_afyp="";	String 	ul_penet_ytd_afyp="";  String 	ul_penet_mtd_pol_cnt="";   String ul_penet_ytd_pol_cnt="";
				String 	ul_mtd_afyp="";	String 	ul_ytd_afyp="";	String 	ul_mtd_pol_cnt="";	String 	ul_ytd_pol_cnt="";	String 	trad_penet_mtd_afyp="";
				String 	trad_penet_ytd_afyp="";    String trad_penet_mtd_pol_cnt="";	String trad_penet_ytd_pol_cnt=""; String trad_mtd_afyp="";
				String 	trad_ytd_afyp="";   String trad_mtd_pol_cnt="";	String 	trad_ytd_pol_cnt=""; String	protec_penet_mtd_afyp="";
				String 	protec_penet_ytd_afyp="";	String 	protec_penet_mtd_pol_cnt="";	String 	protec_penet_ytd_pol_cnt="";
				String 	protec_mtd_afyp="";	String 	protec_ytd_afyp=""; String	protec_mtd_pol_cnt="";	String 	protec_ytd_pol_cnt="";
				String mtd_inforced_afyp=""; String mtd_inforced_count=""; String ytd_inforced_afyp=""; String	ytd_inforced_count="";
				String grth_paid_adj_mfyp=""; String adj_mfyp_lst_mn=""; String mtd_inforced_adj_mfyp=""; String grth_ovr_lst_yr_paid="";
				String adj_mfyp_sam_ytd_lst_yr=""; String ytd_inforced_adj_mfyp=""; String achiev_mtd_adj_mfyp=""; String pln_mtd_basis_adj_mfyp="";
				String achiev_ytd_adj_mfyp=""; String pln_ytd_basis_adj_mfyp=""; String mtd_inforced_adj_mfyp_achi="";
				String ytd_inforced_adj_mfyp_achi="";

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
				LocalDateTime now = LocalDateTime.now();

				try	{
					dailyAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("daily_adj_mfyp").toString());
				}
				catch(Exception ex)	{}
				String dailyAdjustMFYP =df.format(dailyAdjustMFYP1);
				try	{
					mtd_inforced_afyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("mtd_inforced_afyp").toString());
				}
				catch(Exception ex)	{}
				String mtd_inforced_afyp_enforce =df.format(mtd_inforced_afyp1);
				try
				{
					mtdAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("mtd_adj_mfyp").toString());
				}catch(Exception ex){}
				String mtdAdjustMFYP = df.format(mtdAdjustMFYP1);
				try
				{
					ytd_inforced_afyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("ytd_inforced_afyp").toString());
				}catch(Exception ex){}
				String ytd_inforced_afyp_enforce = df.format(ytd_inforced_afyp1);
				try{
					dailyAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("appliedData").get("daily_applied_afyp").toString());
				}catch(Exception e){}
				String dailyAppliedAFYP = df.format(dailyAppliedAFYP1);
				try{
					mtdAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("appliedData").get("mtd_applied_afyp").toString());
				}catch(Exception e){}
				String mtdAppliedAFYP = df.format(mtdAppliedAFYP1);
				try{
					ytd_applied_afyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("appliedData").get("ytd_applied_afyp").toString());
				}catch(Exception e){}
				String ytd_applied_afyp = df.format(ytd_applied_afyp1);
				try{
					wipAFYP = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("wip_afyp").toString());
				}catch(Exception e){}
				try{
					hoWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("ho_wip_afyp").toString());
				}catch(Exception e){}
				try{
					goWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("go_wip_afyp").toString());
				}catch(Exception e){}
				try{
					itWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("it_wip_afyp").toString());
				}catch(Exception e){}
				try{
					finWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("fin_wip_afyp").toString());
				}catch(Exception e){}
				try{
					miscWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("misc_wip_afyp").toString());
				}catch(Exception e){}
				try{
					welcomeWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("welcome_wip_afyp").toString());
				}catch(Exception e){}
				try{
					wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("wip_count").toString());
				}catch(Exception e){}
				try{
					ho_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("ho_wip_count").toString());
				}catch(Exception e){}
				try{
					go_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("go_wip_count").toString());
				}catch(Exception e){}
				try{
					it_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("it_wip_count").toString());
				}catch(Exception e){}
				try{
					fin_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("fin_wip_count").toString());
				}catch(Exception e){}
				try{
					misc_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("misc_wip_count").toString());
				}catch(Exception e){}
				try{
					welcome_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("welcome_wip_count").toString());
				}catch(Exception e){}
				/////// new penetration////////////////////
				try{
					mtd_inforced_afyp	 = (object.getJSONObject("payload").getJSONObject("penetration").get("mtd_inforced_afyp").toString());
				}catch(Exception e){}
				try{
					mtd_inforced_count = (object.getJSONObject("payload").getJSONObject("penetration").get("mtd_inforced_count").toString());
				}catch(Exception e){}
				try{
					ytd_inforced_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("ytd_inforced_afyp").toString());
				}catch(Exception e){}
				try{
					ytd_inforced_count = (object.getJSONObject("payload").getJSONObject("penetration").get("ytd_inforced_count").toString());
				}catch(Exception e){}

				try{
					ul_penet_mtd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_penet_mtd_afyp").toString());
				}catch(Exception e){}
				try{
					ul_penet_ytd_afyp =(object.getJSONObject("payload").getJSONObject("penetration").get("ul_penet_ytd_afyp").toString());
				}catch(Exception e){}
				try{
					ul_penet_mtd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_penet_mtd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					ul_penet_ytd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_penet_ytd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					ul_mtd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_mtd_afyp").toString());
				}catch(Exception e){}
				try{
					ul_ytd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_ytd_afyp").toString());
				}catch(Exception e){}
				try{
					ul_mtd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_mtd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					ul_ytd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_ytd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					trad_penet_mtd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("trad_penet_mtd_afyp").toString());
				}catch(Exception e){}
				try{
					trad_penet_ytd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("trad_penet_ytd_afyp").toString());
				}catch(Exception e){}
				try{
					trad_penet_mtd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("trad_penet_mtd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					trad_penet_ytd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("trad_penet_ytd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					trad_mtd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("trad_mtd_afyp").toString());
				}catch(Exception e){}
				try{
					trad_ytd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("trad_ytd_afyp").toString());
				}catch(Exception e){}
				try{
					trad_mtd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("trad_mtd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					trad_ytd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("trad_ytd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					protec_penet_mtd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_penet_mtd_afyp").toString());
				}catch(Exception e){}
				try{
					protec_penet_ytd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_penet_ytd_afyp").toString());
				}catch(Exception e){}
				try{
					protec_penet_mtd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_penet_mtd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					protec_penet_ytd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_penet_ytd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					protec_mtd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_mtd_afyp").toString());
				}catch(Exception e){}
				try{
					protec_ytd_afyp = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_ytd_afyp").toString());
				}catch(Exception e){}
				try{
					protec_mtd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_mtd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					protec_ytd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_ytd_pol_cnt").toString());
				}catch(Exception e){}
				//Growth
				try{
					grth_paid_adj_mfyp = (object.getJSONObject("payload").getJSONObject("growth").get("grth_paid_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					adj_mfyp_lst_mn = (object.getJSONObject("payload").getJSONObject("growth").get("adj_mfyp_lst_mn").toString());
				}catch(Exception e){}
				try{
					mtd_inforced_adj_mfyp = (object.getJSONObject("payload").getJSONObject("growth").get("mtd_inforced_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					grth_ovr_lst_yr_paid = (object.getJSONObject("payload").getJSONObject("growth").get("grth_ovr_lst_yr_paid").toString());
				}catch(Exception e){}
				try{
					adj_mfyp_sam_ytd_lst_yr = (object.getJSONObject("payload").getJSONObject("growth").get("adj_mfyp_sam_ytd_lst_yr").toString());
				}catch(Exception e){}
				try{
					ytd_inforced_adj_mfyp = (object.getJSONObject("payload").getJSONObject("growth").get("ytd_inforced_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					achiev_mtd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("achievement").get("achiev_mtd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					pln_mtd_basis_adj_mfyp = (object.getJSONObject("payload").getJSONObject("achievement").get("pln_mtd_basis_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					achiev_ytd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("achievement").get("achiev_ytd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					pln_ytd_basis_adj_mfyp = (object.getJSONObject("payload").getJSONObject("achievement").get("pln_ytd_basis_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					mtd_inforced_adj_mfyp_achi = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_inforced_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					ytd_inforced_adj_mfyp_achi = (object.getJSONObject("payload").getJSONObject("achievement").get("ytd_inforced_adj_mfyp").toString());
				}catch(Exception e){}

				sum3 = sum+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
				sum = sum+wipAFYP+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
				String convertsum  =  df.format(sum);
				String convertsum3  =  df.format(sum3);

				sum2=sum2+wip_count+ho_wip_count+go_wip_count+it_wip_count+fin_wip_count+misc_wip_count+welcome_wip_count;
				sum4=sum4+ho_wip_count+go_wip_count+it_wip_count+fin_wip_count+misc_wip_count+welcome_wip_count;
				String convertsum2  =  df1.format(sum2);
				String convertsum4  =  df1.format(sum4);
				

				if("NUMBERS".equalsIgnoreCase(action))
				{
					if("MONTHLY".equalsIgnoreCase(period))
					{
						finalresponse="As of "+dtf.format(now)+
								", the business update for "+channel+ " is :\n"
								+"Adj MFYP MTD : "+mtdAdjustMFYP+" Cr \n\n"
								+"Applied AFYP MTD: " +mtdAppliedAFYP+" Cr \n\n "
								+"WIP AFYP: " +convertsum3+" Cr. \n\n ";
						if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
						{
							finalresponse = finalresponse+" Do you want to see the Data Channel Wise like :\n\n Agency, Axis Bank, Banca, Cat";
						}
					}

					else if(!"MLI".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(channel))
					{
						finalresponse="As of "+dtf.format(now)+
								", the business update for "+channel+" is : \n Adj MFYP FTD:"+dailyAdjustMFYP+" Cr, \n\n"
								+"Adj MFYP MTD: " +mtdAdjustMFYP+" Cr \n\n"
								+"AFYP FTD: " +dailyAppliedAFYP+" Cr, \n\n"
								+"AFYP MTD: " +mtdAppliedAFYP+" Cr \n\n"
								+"WIP AFYP: " +convertsum+" Cr.";
					}
					else 
					{
						finalresponse="As of "+dtf.format(now)+
								", the business update for "+channel+" is \n MLI paid business : \n\n Adj MFYP FTD :"+dailyAdjustMFYP+" Cr, \n\n"
								+"Adj MFYP MTD : " +mtdAdjustMFYP+" Cr \n\n"
								+"Applied Business AFYP: \n\n"
								+"AFYP FTD: " +dailyAppliedAFYP+" Cr, \n\n"
								+"AFYP MTD: " +mtdAppliedAFYP+" Cr \n\n"
								+"WIP Data:\n\n WIP AFYP is: " +convertsum+" Cr."; 
						if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
						{
							finalresponse = finalresponse+" Do you want to see the Data Channel Wise like :\n\n Agency, Axis Bank, Banca, Cat";
						}
					}
				}
				else if("AdjMFYP".equalsIgnoreCase(action))
				{
					if("MONTHLY".equalsIgnoreCase(period))
					{
						finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business" +
								" is : "+mtd_inforced_afyp_enforce+" Cr";
					}
					else if(channel.equalsIgnoreCase(channel))
					{
						finalresponse="As of "+dtf.format(now)+" Monthly Applied AFYP Business" +
								" is : "+mtd_inforced_afyp_enforce+" Cr and Yearly Applied AFYP Business is : "+ytd_inforced_afyp_enforce+ " for "+channel+"";
					}
					else if("MONTHLY".equalsIgnoreCase(period) && channel.equalsIgnoreCase(channel))
					{
						finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business" +
								" is : "+mtd_inforced_afyp_enforce+" Cr";
					}
					else {
						finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business"+
								" FTD : " +dailyAdjustMFYP+" Cr,"
								+" MTD : " +mtd_inforced_afyp_enforce+" Cr"
								+" YTD : " +ytd_inforced_afyp_enforce+" Cr";
					}
				}

				else if("WIP".equalsIgnoreCase(action))
				{
					if(!"".equalsIgnoreCase(channel))
					{
						finalresponse="Current WIP as of "+dtf.format(now)+
								" for "+channel+" is "+convertsum4+" Policies with "+convertsum3+" "
								+ " Cr. AFYP. Do you wish to see the stage wise snapshot";
					}
					else
					{
						finalresponse="Current WIP as of "+dtf.format(now)+
								" for MLI is "+convertsum4+" Policies with "+convertsum3+" "
								+ "Cr. AFYP. Do you wish to see the stage wise snapshot";
					}
				}
				else if("WIP.YES".equalsIgnoreCase(action))
				{
					finalresponse="WIP AFYP :" +convertsum3+
							"\n\n HO WIP AFYP :"+hoWIPAFYP+
							"\n\n GO WIP AFYP :"+goWIPAFYP+
							"\n\n IT WIP AFYP :"+itWIPAFYP+
							"\n\n FIN WIP AFYP :"+finWIPAFYP+
							"\n\n MISC WIP AFYP :"+miscWIPAFYP+
							"\n\n WELCOME WIP AFYP :"+welcomeWIPAFYP+"";
				}
				else if("APPLIED".equalsIgnoreCase(action))
				{
					if("MONTHLY".equalsIgnoreCase(period))
					{
						finalresponse="As of "+dtf.format(now)+" Applied AFYP Business" +
								" is : "+mtdAppliedAFYP+" Cr for "+channel+"";
					}
					else if(channel.equalsIgnoreCase(channel))
					{
						finalresponse="As of "+dtf.format(now)+" Monthly Applied AFYP Business" +
								" is : "+mtdAppliedAFYP+" Cr and Yearly Applied AFYP Business is : "+ytd_applied_afyp+ " for "+channel+"";
					}
					else if("MONTHLY".equalsIgnoreCase(period) && channel.equalsIgnoreCase(channel))
					{
						finalresponse="As of"+dtf.format(now)+" Applied AFYP Business" +
								" is : "+mtdAppliedAFYP+" Cr for "+channel+"";
					}
					else
					{
						finalresponse="As of "+dtf.format(now)+" Applied AFYP"+
								"  is: "
								+ " FTD : " +dailyAppliedAFYP+" Cr"
								+", MTD is: " +mtdAppliedAFYP+" Cr"
								+", YTD is: " +ytd_applied_afyp+" Cr";
					}
				}
				else if("Growth".equalsIgnoreCase(action))
				{
					if(("Agency".equalsIgnoreCase(channel)))
					{
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}
					else if("Axis".equalsIgnoreCase(channel)){
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}else if("Banca".equalsIgnoreCase(channel)){
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}else if("CAT".equalsIgnoreCase(channel)){
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}else if("IM".equalsIgnoreCase(channel)){
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}else if("IMF".equalsIgnoreCase(channel)){
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}else if("INTERNETSALES".equalsIgnoreCase(channel)){
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}else if("PD".equalsIgnoreCase(channel)){
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}else{
						if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)){
							finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
						}else
						{
							finalresponse = channel+" has witnessed paid business growth of "+grth_ovr_lst_yr_paid
									+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_sam_ytd_lst_yr+"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
						}
					}
				}
				else if("Achievement".equalsIgnoreCase(action))
				{
					if("Agency".equalsIgnoreCase(channel)){
						finalresponse=channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Your monthly plan is "
								+pln_mtd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+mtd_inforced_adj_mfyp_achi+" Cr and "
								+channel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Your yearly plan is "
								+pln_ytd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";

					}else if("Axis".equalsIgnoreCase(channel)){
						finalresponse=channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Your monthly plan is "
								+pln_mtd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+mtd_inforced_adj_mfyp_achi+" Cr and "
								+channel+" has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan, Your yearly plan is "
								+pln_ytd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";
					}else if("Banca".equalsIgnoreCase(channel)){
						finalresponse=channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Your monthly plan is "
								+pln_mtd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+mtd_inforced_adj_mfyp_achi+" Cr and "
								+channel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Your yearly plan is "
								+pln_ytd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";
					}else if("CAT".equalsIgnoreCase(channel)){
						finalresponse=channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Your monthly plan is "
								+pln_mtd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+mtd_inforced_adj_mfyp_achi+" Cr and "
								+channel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Your yearly plan is "
								+pln_ytd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";
					}else if("IMF".equalsIgnoreCase(channel)){
						finalresponse=channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Your monthly plan is "
								+pln_mtd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+mtd_inforced_adj_mfyp_achi+" Cr and "
								+channel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Your yearly plan is "
								+pln_ytd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";
					}else if("INTERNETSALES".equalsIgnoreCase(channel)){
						finalresponse=channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Your monthly plan is "
								+pln_mtd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+mtd_inforced_adj_mfyp_achi+" Cr and "
								+channel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Your yearly plan is "
								+pln_ytd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";
					}else if("PD".equalsIgnoreCase(channel)){
						finalresponse=channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Your monthly plan is "
								+pln_mtd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+mtd_inforced_adj_mfyp_achi+" Cr and "
								+channel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Your yearly plan is "
								+pln_ytd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";
					}else{
						finalresponse=channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Your monthly plan is "
								+pln_mtd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+mtd_inforced_adj_mfyp_achi+" Cr and "
								+channel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Your yearly plan is "
								+pln_ytd_basis_adj_mfyp+" and till date "+dtf.format(now)+" You have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";
					}
				}
				else if("Penetration".equalsIgnoreCase(action))
				{
					if("Agency".equalsIgnoreCase(channel)){
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
					}

					else if("IM".equalsIgnoreCase(channel))
					{
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}

					}
					else if("Axis Bank".equalsIgnoreCase(channel)){
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
					}else if("Banca".equalsIgnoreCase(channel)){
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
					}else if("CAT".equalsIgnoreCase(channel)){
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
					}else if("IMF".equalsIgnoreCase(channel)){
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
					}else if("INTERNETSALES".equalsIgnoreCase(channel))
					{
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
					}else if("PD".equalsIgnoreCase(channel)){
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
					}else{
						if("ULIP".equalsIgnoreCase(productType)){
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis and "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
					}
				}
				else
				{
					finalresponse="Something gets wrong between action & channel";
				}
			//	response.setSpeech(finalresponse);
			//	response.setDisplayText(finalresponse);
				speech=finalresponse;
				System.out.println("Exception>>>>>>>>>>>>"+speech);
			}
			catch(Exception e)
			{
				
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception>>>>>>>>>>>>"+ex);
		}
		WebhookResponse responseObj = new WebhookResponse(speech, speech);
		System.out.println("End : Controller: Webhook");
		return responseObj;
	}

}

