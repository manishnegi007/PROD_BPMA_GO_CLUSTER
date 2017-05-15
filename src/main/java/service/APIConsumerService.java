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
	public WebhookResponse getWipDataAll(String action, String channel, String period, String productType)
	{
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
					if(channel.equalsIgnoreCase("MLI") ||  channel.equalsIgnoreCase(""))
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
				if(channel.equalsIgnoreCase("MLI") || channel.equalsIgnoreCase("Axis") || channel.equalsIgnoreCase(""))
				{
					if(channel.equalsIgnoreCase("MLI") ||  channel.equalsIgnoreCase(""))
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
				if(channel.equalsIgnoreCase("") || channel.equalsIgnoreCase("Axis") || channel.equalsIgnoreCase("MLI"))
				{
					if(channel.equalsIgnoreCase("") || channel.equalsIgnoreCase("MLI"))
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
				if(channel.equalsIgnoreCase("MLI") || channel.equalsIgnoreCase("") || channel.equalsIgnoreCase("Axis"))
				{
					if(channel.equalsIgnoreCase("MLI") || channel.equalsIgnoreCase(""))
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
			String serviceurl = res.getString("serviceurl");
			URL url = new URL(serviceurl);
			if(DevMode!=null && !DevMode.equalsIgnoreCase("") && DevMode.equalsIgnoreCase("Y"))
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
			//			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuilder requestdata=new StringBuilder();

			Long currenttm = System.currentTimeMillis();

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
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(requestdata.toString());
			writer.flush();
			try {writer.close(); } catch (Exception e1) {}

			int apiResponseCode = conn.getResponseCode();
			System.out.println(apiResponseCode);
			if(apiResponseCode == 200)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
				conn.disconnect();
				br.close();
				try
				{
					DecimalFormat df = new DecimalFormat("####0.00");
					System.out.println(result.toString());
					JSONObject object = new JSONObject(result.toString());
					String finalresponse="";
					double dailyAdjustMFYP1=0;  double mtdAdjustMFYP1=0;    double dailyAppliedAFYP1=0;
					double mtdAppliedAFYP1=0;	double wipAFYP=0;           double hoWIPAFYP=0;
					double goWIPAFYP=0;			double itWIPAFYP=0;	   	    double finWIPAFYP=0;
					double miscWIPAFYP=0;		double welcomeWIPAFYP=0;	double wip_count=0;
					double ho_wip_count=0;		double go_wip_count=0;		double it_wip_count=0;
					double fin_wip_count=0;		double misc_wip_count=0;	double welcome_wip_count=0;
					double sum = 0; double sum2=0;

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();

					try	{
						dailyAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("daily_adj_mfyp").toString());
					}
					catch(Exception ex)	{}
					String dailyAdjustMFYP =df.format(dailyAdjustMFYP1);
					try
					{
						mtdAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("mtd_adj_mfyp").toString());
					}catch(Exception ex){}
					String mtdAdjustMFYP = df.format(mtdAdjustMFYP1);
					try{
						dailyAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("appliedData").get("daily_applied_afyp").toString());
					}catch(Exception e){}
					String dailyAppliedAFYP = df.format(dailyAppliedAFYP1);
					try{
						mtdAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("appliedData").get("mtd_applied_afyp").toString());
					}catch(Exception e){}
					String mtdAppliedAFYP = df.format(mtdAppliedAFYP1);

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

					sum = sum+wipAFYP+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
					String convertsum  =  df.format(sum);

					sum2=sum2+wip_count+ho_wip_count+go_wip_count+it_wip_count+fin_wip_count+misc_wip_count+welcome_wip_count;
					String convertsum2  =  df.format(sum2);

					if("NUMBERS".equalsIgnoreCase(action))
					{
						if("MONTHLY".equalsIgnoreCase(period))
						{
							finalresponse="As of "+dtf.format(now)+
									"the business update for channel is :"
									+"MLI Paid Business : "
									+" Adj MFYP MTD is: "+mtdAdjustMFYP+" Cr "
									+" Applied Data: \n"
									+" Applied AFYP MTD is: " +mtdAppliedAFYP+" Cr "
									+ " || WIP Data "
									+      "  WIP AFYP is: " +convertsum+" Cr."
									+" Do you want to see the Data Channel Wise like : Agency, Axis Bank, Banca, Cat";
						}
						else if(!"MLI".equalsIgnoreCase(channel))
						{
							finalresponse="As of "+dtf.format(now)+
									", the business update for< "+channel+">is MLI paid business : Adj MFYP FTD is ,"+dailyAdjustMFYP+" Cr,"
									+" Adj MFYP MTD is: " +mtdAdjustMFYP+" Cr ||"
									+"  Applied Business AFYP: "
									+ "AFYP FTD is: " +dailyAppliedAFYP+" Cr"
									+", AFYP MTD is: " +mtdAppliedAFYP+" Cr"
									+ " || WIP Data"
									+      "  WIP AFYP is: " +convertsum+" Cr.";
						}
						else if("MONTHLY".equalsIgnoreCase(period) && channel.equalsIgnoreCase(channel))
						{
							finalresponse="As of "+dtf.format(now)+
									"the business update for channel is :"
									+" Adj MFYP MTD is: " +mtdAdjustMFYP+" Cr ||"
									+" Applied Data: "
									+" Applied AFYP MTD is: " +mtdAppliedAFYP+" Cr"
									+ " || WIP Data"
									+      "  WIP AFYP is: " +convertsum+" Cr.";
						}
						else 
						{
							finalresponse="As of "+dtf.format(now)+
									", the business update for< "+channel+">is MLI paid business : Adj MFYP FTD is," +dailyAdjustMFYP+" Cr,"
									+" Adj MFYP MTD is: " +mtdAdjustMFYP+" Cr ||"
									+"  Applied Business AFYP: "
									+ "AFYP FTD is: " +dailyAppliedAFYP+" Cr"
									+", AFYP MTD is: " +mtdAppliedAFYP+" Cr"
									+ " || WIP Data"
									+      "  WIP AFYP is: " +convertsum+" Cr."
									+" Do you want to see the Data Channel Wise like : Agency, Axis Bank, Banca, Cat";
						}
					}
					else if("AdjMFYP".equalsIgnoreCase(action))
					{
						if("MONTHLY".equalsIgnoreCase(period))
						{
							finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business" +
									" is : "+mtdAdjustMFYP+" Cr";
						}
						else if(channel.equalsIgnoreCase(channel))
						{
							finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business for <"+channel+">"+" is" +mtdAdjustMFYP+" Cr.";
						}
						else if("MONTHLY".equalsIgnoreCase(period) && channel.equalsIgnoreCase(channel))
						{
							finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business" +
									" is : "+mtdAdjustMFYP+" Cr";
						}
						else {
							finalresponse="As of "+dtf.format(now)+" paid AdjMFYP Business"+
									" is FTD : " +dailyAdjustMFYP+" Cr,"
									+" MTD is: " +mtdAdjustMFYP+" Cr";
						}
					}

					else if("WIP".equalsIgnoreCase(action))
					{
						if(channel!="")
						{
							finalresponse="Curent WIP as of "+dtf.format(now)+
									"for< "+channel+">"+" is "+convertsum2+" Policies with "+convertsum+" "
									+ " Cr. AFYP. Do you wish to see the stage wise snapshot";
						}
						else
						{
							finalresponse="Curent WIP as of "+dtf.format(now)+
									" for MLI is "+convertsum2+" Policies with "+convertsum+" "
									+ "Cr. AFYP. Do you wish to see the stage wise snapshot";
						}
					}
					else if("WIP.YES".equalsIgnoreCase(action))
					{
						finalresponse="wipAFYP :" +wipAFYP+
								", hoWIPAFYP :"+hoWIPAFYP+
								", goWIPAFYP :"+goWIPAFYP+
								", itWIPAFYP :"+itWIPAFYP+
								", finWIPAFYP :"+finWIPAFYP+
								", miscWIPAFYP :"+miscWIPAFYP+
								", welcomeWIPAFYP :"+welcomeWIPAFYP+"";
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
							finalresponse="As of "+dtf.format(now)+" Applied AFYP Business" +
									" is : "+mtdAppliedAFYP+" Cr for "+channel+"";
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
									+", MTD is: " +mtdAppliedAFYP+" Cr";
						}
					}
					else if("Growth".equalsIgnoreCase(action))
					{
						if("Agency".equalsIgnoreCase(channel)){
							finalresponse=""+"< "+channel+"'s> has witnessed paid business \n growth of "+mtd.getString("MTD_GWT_Agency")
							+"% on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_Agency")+"Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}else if("Axis".equalsIgnoreCase(channel)){
							finalresponse=""+"< "+channel+"'s> has witnessed paid business growth of "+mtd.getString("MTD_GWT_Axis_Bank")
							+" % on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_Axis_Bank")+" Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}else if("Banca".equalsIgnoreCase(channel)){
							finalresponse=""+"< "+channel+"'s> has witnessed paid business growth of "+mtd.getString("MTD_GWT_BancAssurance")
							+"% on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_Banc_Assurance")+" Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}else if("CAT".equalsIgnoreCase(channel)){
							finalresponse=""+"< "+channel+"'s> has witnessed paid business growth of "+mtd.getString("MTD_GWT_CAT")
							+" % on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_CAT")+" Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}else if("IM".equalsIgnoreCase(channel)){
							finalresponse=""+"< "+channel+"'s> has witnessed paid business growth of "+mtd.getString("MTD_GWT_IM")
							+" % on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_IM_Channel")+" Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}else if("IMF".equalsIgnoreCase(channel)){
							finalresponse=""+"< "+channel+"'s> has witnessed paid business growth of "+mtd.getString("MTD_GWT_IMF")
							+"% on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_IMF")+" Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}else if("INTERNETSALES".equalsIgnoreCase(channel)){
							finalresponse=""+"< "+channel+"'s> has witnessed paid business growth of "+mtd.getString("MTD_GWT_Internet_Sales")
							+"% on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_Internet_Sales")+" Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}else if("PD".equalsIgnoreCase(channel)){
							finalresponse=""+"< "+channel+"'s> has witnessed paid business growth of "+mtd.getString("MTD_GWT_PD")
							+"% on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_PD")+" Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}else{
							finalresponse=""+"<"+channel+"'s> has witnessed paid business growth of "+mtd.getString("MTD_GWT_MLI")
							+"% on MTD basis last month we had clocked "+mtd.getString("ADJ_CHANNEL_MLI")+" Cr of Adj MFYP as compared to "+mtdAdjustMFYP+" today";
						}
					}
					else if("Achievement".equalsIgnoreCase(action))
					{
						if("Agency".equalsIgnoreCase(channel)){
							finalresponse=" Mr. Alok,"+"< "+channel+" is "+mtd.getString("MTD_ACH_Agency")+"% away from Management Plan, Your monthly plan is "
									+mtd.getString("MTD_PLAN_Agency")+" and till date "+dtf.format(now)+" You have achieved "+mtdAdjustMFYP+" Cr.";
						}else if("Axis".equalsIgnoreCase(channel)){
							finalresponse=" Mr. Alok,"+"< "+channel+" is "+mtd.getString("MTD_ACH_Axis_Bank")+"% away from Management Plan, Your monthly plan is "
									+mtd.getString("MTD_PLAN_Axis_Bank")+" and till date "+dtf.format(now)+" You have achieved "+mtdAdjustMFYP+" Cr.";
						}else if("Banca".equalsIgnoreCase(channel)){
							finalresponse=" Mr. Alok,"+"< "+channel+" is "+mtd.getString("MTD_ACH_BancAssurance")+"% away from Management Plan, Your monthly plan is "
									+mtd.getString("MTD_PLAN_BancAssurance")+" and till date "+dtf.format(now)+" You have achieved "+mtdAdjustMFYP+" Cr.";
						}else if("CAT".equalsIgnoreCase(channel)){
							finalresponse=" Mr. Alok,"+"< "+channel+" is "+mtd.getString("MTD_ACH_CAT")+"% away from Management Plan, Your monthly plan is "
									+mtd.getString("MTD_PLAN_CAT")+" and till date "+dtf.format(now)+" You have achieved"+mtdAdjustMFYP+" Cr.";
						}else if("IMF".equalsIgnoreCase(channel)){
							finalresponse=" Mr. Alok,"+"< "+channel+" is "+mtd.getString("MTD_ACH_IMF")+"% away from Management Plan, Your monthly plan is "
									+mtd.getString("MTD_PLAN_IMF")+" and till date "+dtf.format(now)+" You have achieved "+mtdAdjustMFYP+" Cr.";
						}else if("INTERNETSALES".equalsIgnoreCase(channel)){
							finalresponse=" Mr. Alok,"+"< "+channel+" is "+mtd.getString("MTD_ACH_Internet_Sales")+"% away from Management Plan, Your monthly plan is "
									+mtd.getString("MTD_PLAN_Internet_Sales")+"and till date"+dtf.format(now)+" You have achieved"+mtdAdjustMFYP+" Cr.";
						}else if("PD".equalsIgnoreCase(channel)){
							finalresponse=" Mr. Alok,"+"< "+channel+" is "+mtd.getString("MTD_ACH_PD")+"% away from Management Plan, Your monthly plan is "
									+mtd.getString("MTD_PLAN_PD")+" and till date "+dtf.format(now)+" You have achieved "+mtdAdjustMFYP+" Cr.";
						}else{
							finalresponse=" Mr. Alok,"+"< "+channel+" is "+mtd.getString("MTD_ACH_MLI")+"% away from Management Plan, Your monthly plan is "
									+mtd.getString("MTD_PLAN_MLI")+" and till date "+dtf.format(now)+" You have achieved"+mtdAdjustMFYP+" Cr.";
						}
					}
					else if("Penetration".equalsIgnoreCase(action))
					{
						if("Agency".equalsIgnoreCase(channel)){
							if("ULIP".equalsIgnoreCase(productType)){
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_ULIP_Agency")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("ULIP_PENETRATION_Agency")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Agency")+ "Cr of paid business Adj MFYP YTD ";
							}else if("TRAD".equalsIgnoreCase(productType))
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_TRAD_Agency")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("TRAD_PENETRATION_Agency")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Agency")+ " Cr of paid business Adj MFYP YTD";
							}
							else
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_PROTECTION_Agency")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("PRO_PENETRATION_Agency")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Agency")+ " Cr of paid business Adj MFYP YTD";
							}

						}else if("Axis Bank".equalsIgnoreCase(channel)){
							if("ULIP".equalsIgnoreCase(productType)){
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_ULIP_Axis_Bank")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("ULIP_PENETRATION_Axis_Bank")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Axis_Bank")+ " Cr of paid business Adj MFYP YTD ";
							}else if("TRAD".equalsIgnoreCase(productType))
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_TRAD_Axis_Bank")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("TRAD_PENETRATION_Axis_Bank")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Axis_Bank")+ " Cr of paid business Adj MFYP YTD ";
							}
							else
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_PROTECTION_Axis_Bank")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("PRO_PENETRATION_Axis_Bank")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Axis_Bank")+ " Cr of paid business Adj MFYP YTD";
							}
						}else if("Banca".equalsIgnoreCase(channel)){
							if("ULIP".equalsIgnoreCase(productType)){
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_ULIP_BancAssurance")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("ULIP_PENETRATION_BancAssurance")+" % of "
										+ytd.getString("YTD_ADJ_MFYP_BancAssurance")+ " Cr of paid business Adj MFYP YTD";
							}else if("TRAD".equalsIgnoreCase(productType))
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_TRAD_BancAssurance")+" % of"+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and"+ytd.getString("TRAD_PENETRATION_BancAssurance")+"% of"
										+ytd.getString("YTD_ADJ_MFYP_BancAssurance")+ "Cr of paid business Adj MFYP YTD";
							}
							else
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_PROTECTION_BancAssurance")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("PRO_PENETRATION_BancAssurance")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_BancAssurance")+ " Cr of paid business Adj MFYP YTD";
							}
						}else if("CAT".equalsIgnoreCase(channel)){
							if("ULIP".equalsIgnoreCase(productType)){
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_ULIP_CAT")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("ULIP_PENETRATION_CAT")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_CAT")+ " Cr of paid business Adj MFYP YTD";
							}else if("TRAD".equalsIgnoreCase(productType))
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_TRAD_CAT")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("TRAD_PENETRATION_CAT")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_CAT")+ " Cr of paid business Adj MFYP YTD";
							}
							else
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_PROTECTION_CAT")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("PRO_PENETRATION_CAT")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_CAT")+ " Cr of paid business Adj MFYP YTD";
							}
						}else if("IMF".equalsIgnoreCase(channel)){
							if("ULIP".equalsIgnoreCase(productType)){
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_ULIP_IMF")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("ULIP_PENETRATION_IMF")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_IMF")+ " Cr of paid business Adj MFYP YTD";
							}else if("TRAD".equalsIgnoreCase(productType))
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_TRAD_IMF")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("TRAD_PENETRATION_IMF")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_IMF")+ " Cr of paid business Adj MFYP YTD";
							}
							else
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_PROTECTION_IMF")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("PRO_PENETRATION_IMF")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_IMF")+ " Cr of paid business Adj MFYP YTD";
							}
						}else if("INTERNETSALES".equalsIgnoreCase(channel))
						{
							if("ULIP".equalsIgnoreCase(productType)){
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_ULIP_Internet")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("ULIP_PENETRATION_Internet_Sales")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Internet_Sales")+ " Cr of paid business Adj MFYP YTD";
							}else if("TRAD".equalsIgnoreCase(productType))
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_TRAD_Internet_Sales")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("TRAD_PENETRATION_Internet_Sales")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Internet_Sales")+ " Cr of paid business Adj MFYP YTD";
							}
							else
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_PROTECTION_Internet_Sales")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("PRO_PENETRATION_Internet_Sales")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_Internet_Sales")+ " Cr of paid business Adj MFYP YTD";
							}
						}else if("PD".equalsIgnoreCase(channel)){
							if("ULIP".equalsIgnoreCase(productType)){
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_ULIP_PD")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("ULIP_PENETRATION_PD")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_PD")+ " Cr of paid business Adj MFYP YTD";
							}else if("TRAD".equalsIgnoreCase(productType))
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_TRAD_PD")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("TRAD_PENETRATION_PD")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_PD")+ " Cr of paid business Adj MFYP YTD";
							}
							else
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_PROTECTION_PD")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("PRO_PENETRATION_PD")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_PD")+ " Cr of paid business Adj MFYP YTD";
							}
						}else{
							if("ULIP".equalsIgnoreCase(productType)){
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_ULIP_MLI")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("ULIP_PENETRATION_MLI")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_MLI")+ " Cr of paid business Adj MFYP YTD";
							}else if("TRAD".equalsIgnoreCase(productType))
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_TRAD_MLI")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("TRAD_PENETRATION_MLI")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_MLI")+ " Cr of paid business Adj MFYP YTD";
							}
							else
							{
								finalresponse="<"+channel+"'s "+productType+" Penetration is "+mtd.getString("MTD_PROTECTION_MLI")+" % of "+mtdAdjustMFYP
										+" Cr of paid business Adj MFYP MTD and "+ytd.getString("PRO_PENETRATION_MLI")+"% of "
										+ytd.getString("YTD_ADJ_MFYP_MLI")+ " Cr of paid business Adj MFYP YTD";
							}
						}
					}
					else
					{
						finalresponse="Something gets wrong in service or might be no condition matched as per input "
								+ "Please share your input to the concern team to avoid the same on further.";
					}
					//response.setSpeech(finalresponse);
					//response.setDisplayText(finalresponse);
					speech=finalresponse;
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception>>>>>>>>>>>>"+ex);
		}
		WebhookResponse responseObj = new WebhookResponse(speech, speech);
		System.out.println("*******"+responseObj);
		return responseObj;
	}

}

