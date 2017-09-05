package service;

import java.util.ArrayList;
import java.util.List;
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
import hello.InnerButton;
import hello.Facebook;
import hello.InnerData;

@Component
public class APIConsumerService {
	
	public WebhookResponse getWipDataAll(String action, String channel, String period, String productType, String planType,
				String user_ssoid, String user_sub_channel, String user_designation_desc, 
				String userzone, String user_region, String user_circle, String user_clusters, String user_go, String user_cmo, 
				String user_amo, String sessionId, String source)
	{
		List<InnerButton> innerbuttonlist = new ArrayList<InnerButton>();
		Facebook fb = new Facebook();
		InnerData innerData= new InnerData();
		String msgChannel="";
		if("".equalsIgnoreCase(channel))
		{channel="MLI";}
		if("".equalsIgnoreCase(productType))
		{productType="Protection";}
		if("".equalsIgnoreCase(period))
		{period="MONTHLY";}

		for(int i=0; i<=0; i++)
		{
			if(!"".equalsIgnoreCase(user_circle))
			{
				msgChannel=user_circle;
				msgChannel="Circle "+msgChannel;
				break;
			}
			else if(!"".equalsIgnoreCase(user_region))
			{
				msgChannel=user_region;
				msgChannel="Region "+msgChannel;
				break;
			}
			else if(!"".equalsIgnoreCase(userzone))
			{
				msgChannel=userzone;
				msgChannel="Zone "+msgChannel;
				break;
			}
			else if(!"".equalsIgnoreCase(user_sub_channel))
			{
				if("google".equalsIgnoreCase(source)){
					msgChannel=user_sub_channel;
					break;
				}
				else{
					msgChannel=user_sub_channel;
					msgChannel="SubChannel "+msgChannel;
					break;
				}
			}
			else{
				msgChannel = channel;
			}
		}		
		if("MLI".equalsIgnoreCase(channel)||"CAT".equalsIgnoreCase(channel))
		{
			channel=channel.toUpperCase();
		}
		else
		{
			channel=convertToCamelCase(channel);
		}
		ResourceBundle res = ResourceBundle.getBundle("errorMessages");
		String output = new String();
		StringBuilder result = new StringBuilder();	
		String DevMode = "N";
		HttpURLConnection conn = null;
		String segment="";
		String serviceChannel="";
		String speech="";
		String finalresponse="";
     		try 
		{
			if("NUMBERS".equalsIgnoreCase(action))
			{
				if("".equalsIgnoreCase(channel) || "MLI".equalsIgnoreCase(channel) || "Axis".equalsIgnoreCase(channel))
				{
					if("MLI".equalsIgnoreCase(channel) ||  "".equalsIgnoreCase(channel)){
						segment = "paid,wip,applied";
						serviceChannel = "";
					}else
					{
						segment = "paid,wip,applied";
						serviceChannel = "Axis Bank";
					}
				}else
				{
					segment = "paid,wip,applied";
					serviceChannel = channel;
				}
			}
			else if("AdjMFYP".equalsIgnoreCase(action) || "NB.Paidcases".equalsIgnoreCase(action) 
					|| "NB.AdjMFYP".equalsIgnoreCase(action))
			{
				if("MLI".equalsIgnoreCase(channel) || "Axis".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
				{
					if("MLI".equalsIgnoreCase(channel) ||  "".equalsIgnoreCase(channel))
					{
						segment="paid";
						serviceChannel = "";
					}else
					{
						segment="paid";
						serviceChannel = "Axis Bank";
					}
				}else
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
				}else{
					segment="wip";
					serviceChannel = channel;
				}
			}
			else if("APPLIED".equalsIgnoreCase(action) || "NB.Applied".equalsIgnoreCase(action) ||"NB.AppliedAdjIFYP".equalsIgnoreCase(action)
					||"NB.AppliedCases".equalsIgnoreCase(action))
			{

				if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel) || "Axis".equalsIgnoreCase(channel))
				{
					if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
					{
						segment="applied";
						serviceChannel = "";
					}else
					{
						segment="applied";
						serviceChannel = "Axis Bank";
					}
				}else
				{
					segment="applied";
					serviceChannel = channel;
				}
			}
			else if("Penetration".equalsIgnoreCase(action) ||"NB.ProductMix".equalsIgnoreCase(action) 
					|| "NB.ProductMixADJMFYP".equalsIgnoreCase(action) || "NB.Productmixpaidcase".equalsIgnoreCase(action))
			{
				if("MLI".equalsIgnoreCase(channel))
				{
					segment="penetration";
					serviceChannel = "";
				}else
				{
					if("Axis".equalsIgnoreCase(channel))
					{
						segment="penetration";
						serviceChannel = "Axis Bank";
					}else
					{
						segment="penetration";
						serviceChannel = channel;
					}
				}
			}
			else if("Achievement".equalsIgnoreCase(action) || "NB.casesize%".equalsIgnoreCase(action) ||"NB.Recruitment%".equalsIgnoreCase(action)
					||"NB.Achievement".equalsIgnoreCase(action))
			{
				if("MLI".equalsIgnoreCase(channel))
				{
					segment="achievement";
					serviceChannel = "";
				}else
				{
					if("Axis".equalsIgnoreCase(channel))
					{
						segment="achievement";
						serviceChannel = "Axis Bank";
					}else
					{
						segment="achievement";
						serviceChannel = channel;
					}
				}
			}
			else if("Growth".equalsIgnoreCase(action) || "NB.Growth".equalsIgnoreCase(action) ||"NB.GROWTHAPLADGIFYP".equalsIgnoreCase(action)||"NB.GROWTHAPLAFYP".equalsIgnoreCase(action)
					||"NB.GROWTHAPLCASES".equalsIgnoreCase(action) ||"NB.GROWTHCASESIZE".equalsIgnoreCase(action)||"NB.GROWTHLPCADJMFYP".equalsIgnoreCase(action) 
					||"NB.GROWTHLPCAPLADJIFYP".equalsIgnoreCase(action)	||"NB.GROWTHLPCAPLAFYP".equalsIgnoreCase(channel)||"NB.GROWTHLPCAPLCASES".equalsIgnoreCase(channel)
					||"GROWTHLPCPAIDCASES".equalsIgnoreCase(channel)||"NB.GROWTHPAIDCASES".equalsIgnoreCase(action)||"NB.GROWTHRECRUITMENT".equalsIgnoreCase(action))
			{
				if("MLI".equalsIgnoreCase(channel))
				{
					segment="growth";
					serviceChannel = "";
				}else
				{
					if("Axis".equalsIgnoreCase(channel))
					{
						segment="growth";
						serviceChannel="Axis Bank";
					}else
					{
						segment="growth";
						serviceChannel = channel;
					}
				}
			}
			else if("NB.casesize".equalsIgnoreCase(action))
			{
				if("MLI".equalsIgnoreCase(channel)){
					segment="CASE_SIZE";
					serviceChannel = "";
				}else
				{
					if("Axis".equalsIgnoreCase(channel)){
						segment="CASE_SIZE";
						serviceChannel = "Axis Bank";
					}else
					{
						segment="CASE_SIZE";
						serviceChannel = channel;
					}
				}
			}
			else if("NB.LPCAPPADJIFYP".equalsIgnoreCase(action) || "NB.LPCAPPADJAFYP".equalsIgnoreCase(action) 
					|| "NB.LPCAPLCASES".equalsIgnoreCase(action) || "NB.LPCPAIDADJMFYP".equalsIgnoreCase(action)
					|| "NB.LPCPAIDCASES".equalsIgnoreCase(action))
			{
				if("MLI".equalsIgnoreCase(channel)){
					segment="LPC_PERFORMANCE";
					serviceChannel = "";
				}else
				{
					if("Axis".equalsIgnoreCase(channel)){
						segment="LPC_PERFORMANCE";
						serviceChannel = "Axis Bank";
					}else
					{
						segment="LPC_PERFORMANCE";
						serviceChannel = channel;
					}
				}
			}
			else if("NB.Recruitment".equalsIgnoreCase(action)) 
			{
				if("MLI".equalsIgnoreCase(channel)){
					segment="REC";
					serviceChannel = "";
				}else
				{
					if("Axis".equalsIgnoreCase(channel)){
						segment="REC";
						serviceChannel = "Axis Bank";
					}else
					{
						segment="REC";
						serviceChannel = channel;
					}
				}
			}
			else if("NB.MODEMIX".equalsIgnoreCase(action)) 
			{
				if("MLI".equalsIgnoreCase(channel)){
					segment="MODE_MIX";
					serviceChannel = "";
				}else
				{
					if("Axis".equalsIgnoreCase(channel)){
						segment="MODE_MIX";
						serviceChannel = "Axis Bank";
					}else
					{
						segment="MODE_MIX";
						serviceChannel = channel;
					}
				}
			}
			else
			{
				finalresponse="Invalid Intent Called by User";
			}
			XTrustProvider trustProvider=new XTrustProvider();
			trustProvider.install();
			StringBuilder requestdata=new StringBuilder();
			if("WIP".equalsIgnoreCase(action) || "WIP.YES".equalsIgnoreCase(action) || "NUMBERS".equalsIgnoreCase(action)
					|| "AdjMFYP".equalsIgnoreCase(action) || "APPLIED".equalsIgnoreCase(action) 
					|| "Achievement".equalsIgnoreCase(action) ||"Growth".equalsIgnoreCase(action) || "Penetration".equalsIgnoreCase(action)
					|| "NB.Paidcases".equalsIgnoreCase(action)|| "NB.AdjMFYP".equalsIgnoreCase(action)|| "NB.Applied".equalsIgnoreCase(action)
					|| "NB.casesize".equalsIgnoreCase(action) ||"NB.AppliedAdjIFYP".equalsIgnoreCase(action)||"NB.AppliedCases".equalsIgnoreCase(action)
					|| "NB.LPCAPPADJIFYP".equalsIgnoreCase(action) || "NB.LPCAPPADJAFYP".equalsIgnoreCase(action)|| "NB.LPCAPLCASES".equalsIgnoreCase(action) 
					|| "NB.LPCPAIDADJMFYP".equalsIgnoreCase(action)	|| "NB.LPCPAIDCASES".equalsIgnoreCase(action) || "NB.casesize%".equalsIgnoreCase(action) 
					||"NB.Growth".equalsIgnoreCase(action) || "NB.Recruitment".equalsIgnoreCase(action) ||"NB.Recruitment%".equalsIgnoreCase(action)
					||"NB.GROWTHAPLADGIFYP".equalsIgnoreCase(action)||"NB.GROWTHAPLAFYP".equalsIgnoreCase(action)||"NB.GROWTHAPLCASES".equalsIgnoreCase(action)
					||"NB.GROWTHLPCADJMFYP".equalsIgnoreCase(action)||"NB.GROWTHLPCAPLADJIFYP".equalsIgnoreCase(action)||"NB.GROWTHLPCAPLAFYP".equalsIgnoreCase(channel)
					||"NB.GROWTHLPCAPLCASES".equalsIgnoreCase(channel)||"GROWTHLPCPAIDCASES".equalsIgnoreCase(channel)||"NB.GROWTHPAIDCASES".equalsIgnoreCase(action)
					||"NB.GROWTHRECRUITMENT".equalsIgnoreCase(action)||"NB.Achievement".equalsIgnoreCase(action) ||"NB.MODEMIX".equalsIgnoreCase(action) 
					||"NB.ProductMix".equalsIgnoreCase(action))
			{
				System.out.println("External Java Service Called: ");
				user_designation_desc="";
				String serviceurl = res.getString("servicegetUserDetail");
				URL url = new URL(serviceurl);
				if(DevMode!=null && !"".equalsIgnoreCase(DevMode) && "Y".equalsIgnoreCase(DevMode))
				{
					Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
					conn = (HttpURLConnection) url.openConnection(proxy);
				}else{
					conn = (HttpURLConnection) url.openConnection();
				}
				HttpsURLConnection.setFollowRedirects(true);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				requestdata.append("	{	");
				requestdata.append("	  \"header\": {	");
				requestdata.append("	    \"correlationId\": \"12345\",	");
				requestdata.append("	    \"msgVersion\": \"\",	");
				requestdata.append("	    \"appId\": \"\",	");
				requestdata.append("	    \"userId\": \"\",	");
				requestdata.append("	    \"password\": \"\",	");
				requestdata.append("	    \"rollId\":\"\"	");
				requestdata.append("	  },	");
				requestdata.append("	  \"payload\": {	");
				requestdata.append("	    \"segment\": \""+segment+"\",	");
				requestdata.append("	    \"designationDesc\": \""+user_designation_desc+"\",");
				requestdata.append("	    \"channel\": \""+serviceChannel+"\",");
				requestdata.append("	    \"subChannel\": \""+user_sub_channel+"\",");
				requestdata.append("	    \"zone\": \""+userzone+"\",");
				requestdata.append("	    \"region\": \""+user_region+"\",");
				requestdata.append("	    \"circle\": \""+user_circle+"\",");
				requestdata.append("	    \"cluster\": \""+user_clusters+"\",");
				requestdata.append("	    \"go\": \""+user_go+"\",");
				requestdata.append("	    \"cmo\": \""+user_cmo+"\",");
				requestdata.append("	    \"amo\": \""+user_amo+"\",");
				requestdata.append("	    \"planType\": \""+planType+"\"");
				requestdata.append("	  }	");
				requestdata.append("	}	");
				System.out.println(requestdata.toString());
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
					{result.append(output);}
					conn.disconnect();
					br.close();
					System.out.println("External API Call : END");
				}else{
					System.out.println("Unable to call External API Java Service");
				}
			}
			else{
				finalresponse="Invalid Intent Called by User";
			}
			try
			   {
				DecimalFormat df = new DecimalFormat("####0.00");
				DecimalFormat df1 = new DecimalFormat("####");
				JSONObject object = new JSONObject(result.toString());
				double dailyAdjustMFYP1=0; 	double mtdAdjustMFYP1=0;    double dailyAppliedAFYP1=0;
				double mtdAppliedAFYP1=0;	double wipAFYP=0;           double hoWIPAFYP=0;
				double goWIPAFYP=0; 		double itWIPAFYP=0;	    double finWIPAFYP=0;
				double miscWIPAFYP=0;		double welcomeWIPAFYP=0;    double wip_count=0;
				double ho_wip_count=0;		double go_wip_count=0;	    double it_wip_count=0;
				double fin_wip_count=0;		double misc_wip_count=0;    double welcome_wip_count=0;
				double ytd_inforced_afyp1=0;double ytd_applied_afyp1=0; double mtd_inforced_afyp1=0;
				double ytd_adj_mfyp1=0; double daily_inforced_count_aaplied=0; double mtd_adj_mfyp1=0;
				double daily_adj_mfyp1=0; double daily_applied_count1=0;
				double daily_inforced_count1=0, ytd_applied_adj_ifyp1=0, mtd_applied_adj_ifyp1=0, mtd_applied_count1=0, ytd_applied_count1=0,
				       mtd_inforced_count1=0,ytd_inforced_count1=0;
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
				String ytd_inforced_adj_mfyp_achi=""; String real_tim_timstamp="";
				String case_size_afyp_mtd="", case_size_afyp_ytd="", lpc_applied_adj_ifyp_mtd="", lpc_applied_adj_ifyp_ytd="", lpc_applied_afyp_mtd="",
				       		lpc_applied_afyp_ytd="", lpc_applied_cases_mtd="", lpc_applied_cases_ytd="", lpc_paid_adj_mfyp_mtd="", lpc_paid_adj_mfyp_ytd="",
						lpc_paid_cases_mtd="", lpc_paid_cases_ytd="", achiev_mtd_case_active_mtd="", achiev_mtd_case_active_ytd="",
						grth_lst_yr_sm_adj_mfyp_mtd="", grth_lst_yr_sm_adj_mfyp_ytd="", prev_year_adj_mfyp_ytd="", prev_year_adj_mfyp_mtd="",
						recruitment_mtd="",recruitment_ytd="", achiev_ytd_recruitment="", achiev_mtd_recruitment="",grth_applied_adj_ifyp_ytd="",
						rpev_applied_adj_ifyp_ytd="",applied_adj_ifyp_ytd="", grth_applied_adj_ifyp_mtd="", rpev_applied_adj_ifyp_mtd="", applied_adj_ifyp_mtd="",
						grth_applied_afyp_ytd="",prev_applied_afyp_ytd="",applied_afyp_ytd="",grth_applied_afyp_mtd="",prev_applied_afyp_mtd="",
						applied_afyp_mtd="", grth_applied_cases_ytd="", prev_applied_cases_ytd="", applied_cases_ytd="", grth_applied_cases_mtd="",
						prev_applied_cases_mtd="", applied_cases_mtd="", grth_case_size_afyp_ytd="",prev_case_size_afyp_ytd="",case_size_afyp_ytd_growth="",
						grth_case_size_afyp_mtd="",prev_case_size_afyp_mtd="",case_size_afyp_mtd_growth="", grth_lpc_paid_adj_mfyp_ytd="",prev_lpc_paid_adj_mfyp_ytd="",
						lpc_paid_adj_mfyp_ytd_growth="",grth_lpc_paid_adj_mfyp_mtd="",prev_lpc_paid_adj_mfyp_mtd="",lpc_paid_adj_mfyp_mtd_growth="",
						grth_lpc_applied_adj_ifyp_ytd="",prev_lpc_applied_adj_ifyp_ytd="",lpc_applied_adj_ifyp_ytd_growth="",grth_lpc_applied_adj_ifyp_mtd="",
						prev_lpc_applied_adj_ifyp_mtd="",lpc_applied_adj_ifyp_mtd_growth="", grth_lpc_applied_afyp_ytd="",prev_lpc_applied_afyp_ytd="",curr_lpc_applied_afyp_ytd="",grth_lpc_applied_afyp_mtd="",
						prev_lpc_applied_afyp_mtd="",curr_lpc_applied_afyp_mtd="", grth_lpc_applied_cases_ytd="",prev_lpc_applied_cases_ytd="",lpc_applied_cases_ytd_growth="",grth_lpc_applied_cases_mtd="",
						prev_lpc_applied_cases_mtd="",lpc_applied_cases_mtd_growth="", grth_lpc_paid_cases_ytd="",prev_lpc_paid_cases_ytd="",lpc_paid_cases_ytd_growth="",grth_lpc_paid_cases_mtd="",
						prev_lpc_paid_cases_mtd="",lpc_paid_cases_mtd_growth="", grth_lst_yr_inforced_cnt_ytd="",prev_year_inforced_cnt_ytd="",ytd_inforced_cnt="",grth_lst_yr_inforced_cnt_mtd="",
						prev_year_inforced_cnt_mtd="",mtd_inforced_cnt="", grth_recruitment_ytd="",prev_recruitment_ytd="",recruitment_ytd_growth="",grth_recruitment_mtd="",
						prev_recruitment_mtd="",recruitment_mtd_growth="",mtd_afyp_act="",ytd_afyp_act="", mtd_afyp_pln="",ytd_afyp_pln="", ytd_adj_mfyp_pln="",mtd_adj_mfyp_pln="",
						mtd_adj_mfyp_act="", ytd_adj_mfyp_act="",achiev_mtd_paid_case="", mtd_adj_afyp_act="", mtd_adj_afyp_pln="",	mtd_paid_case_act="", mtd_paid_case_pln="", achiev_ytd_paid_case="",
						ytd_paid_case_act="", ytd_paid_case_pln="",	ul_penet_mtd_adj_mfyp="", par_penet_mtd_adj_mfyp="", nonpar_penet_mtd_adj_mfyp="", protec_penet_mtd_adj_mfyp="", par_penet_mtd_pol_cnt="",
						nonpar_penet_mtd_pol_cnt="", ul_penet_ytd_adj_mfyp="",	par_penet_ytd_adj_mfyp="",	nonpar_penet_ytd_adj_mfyp="",protec_penet_ytd_adj_mfyp="",par_penet_ytd_pol_cnt="",	
						nonpar_penet_ytd_pol_cnt="";
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
				LocalDateTime now = LocalDateTime.now();
				for(int i=0;i<1;i++){
					try{
						real_tim_timstamp = object.getJSONObject("payload").getJSONObject("paid").get("real_tim_timstamp").toString();
						if(real_tim_timstamp!=null){break;}
					}catch(Exception ex){}
					try{
						real_tim_timstamp = object.getJSONObject("payload").getJSONObject("applied").get("real_tim_timstamp").toString();
						if(real_tim_timstamp!=null){break;}
					}catch(Exception ex){}
					try{
						real_tim_timstamp= object.getJSONObject("payload").getJSONObject("penetration").get("real_tim_timstamp").toString();
						if(real_tim_timstamp!=null){break;}
					}catch(Exception ex){}
					try{
						real_tim_timstamp = (object.getJSONObject("payload").getJSONObject("growth").get("real_tim_timstamp").toString());
						if(real_tim_timstamp!=null){break;}
					}catch(Exception e){}
					try{
						real_tim_timstamp = (object.getJSONObject("payload").getJSONObject("achievement").get("real_tim_timstamp").toString());
						if(real_tim_timstamp!=null){break;}
					}catch(Exception e){}
				}
				try	{
					dailyAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("daily_adj_mfyp").toString());
				}
				catch(Exception ex)	{}
				String dailyAdjustMFYP =df.format(dailyAdjustMFYP1);

				try	{
					mtd_inforced_afyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("mtd_inforced_afyp").toString());
				}
				catch(Exception ex)	{}
				String mtd_inforced_afyp_enforce =df.format(mtd_inforced_afyp1);
				try
				{
					mtdAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("mtd_adj_mfyp").toString());
				}catch(Exception ex){}
				String mtdAdjustMFYP = df.format(mtdAdjustMFYP1);
				try
				{
					ytd_inforced_afyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("ytd_inforced_afyp").toString());
				}catch(Exception ex){}
				try
				{
					daily_inforced_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("daily_inforced_count").toString());
				}catch(Exception ex){}
				try
				{
					mtd_inforced_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("mtd_inforced_count").toString());
					mtd_inforced_count = df.format(mtd_inforced_count1);
				}catch(Exception ex){}
				try
				{
					ytd_inforced_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("ytd_inforced_count").toString());
					ytd_inforced_count = df.format(ytd_inforced_count1);
				}catch(Exception ex){}
				try{
					ytd_adj_mfyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("ytd_adj_mfyp").toString());
				}catch(Exception ex){}
				String ytd_adj_mfyp = df.format(ytd_adj_mfyp1);
				try{
					dailyAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("daily_applied_afyp").toString());
				}catch(Exception e){}
				String dailyAppliedAFYP = df.format(dailyAppliedAFYP1);
				try{
					mtdAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("mtd_applied_afyp").toString());
				}catch(Exception e){}
				String mtdAppliedAFYP = df.format(mtdAppliedAFYP1);
				try{
					ytd_applied_afyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("ytd_applied_afyp").toString());
				}catch(Exception e){}
				String ytd_applied_afyp = df.format(ytd_applied_afyp1);
				try{
					mtd_adj_mfyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("mtd_adj_mfyp").toString());
				}catch(Exception e){}
				String mtd_adj_mfyp = df.format(mtd_adj_mfyp1);
				try{
					daily_adj_mfyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("daily_adj_mfyp").toString());
				}catch(Exception e){}
				String daily_adj_mfyp = df.format(daily_adj_mfyp1);
				try{
					ytd_applied_adj_ifyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("ytd_applied_adj_ifyp").toString());
				}catch(Exception e){}
				String ytd_applied_adj_ifyp = df.format(ytd_applied_adj_ifyp1);
				try{
					mtd_applied_adj_ifyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("mtd_applied_adj_ifyp").toString());
				}catch(Exception e){}
				String mtd_applied_adj_ifyp = df.format(mtd_applied_adj_ifyp1);
				try{
					mtd_applied_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("mtd_applied_count").toString());
				}catch(Exception e){}
				String mtd_applied_count = df.format(mtd_applied_count1);
				try{
					ytd_applied_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("ytd_applied_count").toString());
				}catch(Exception e){}
				String ytd_applied_count = df.format(ytd_applied_count1);
				try{
					daily_applied_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("daily_applied_count").toString());
				}catch(Exception e){}
				String daily_applied_count = df.format(daily_applied_count1);
				try{
					daily_inforced_count_aaplied = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("daily_inforced_count").toString());
				}catch(Exception e){}
				String daily_inforced_count = df.format(daily_inforced_count_aaplied);
				try{
					wipAFYP = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("wip_afyp").toString());
				}catch(Exception e){}
				try{
					hoWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("ho_wip_afyp").toString());
				}catch(Exception e){}
				try{
					goWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("go_wip_afyp").toString());
				}catch(Exception e){}
				try{
					itWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("it_wip_afyp").toString());
				}catch(Exception e){}
				try{
					finWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("fin_wip_afyp").toString());
				}catch(Exception e){}
				try{
					miscWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("misc_wip_afyp").toString());
				}catch(Exception e){}
				try{
					welcomeWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("welcome_wip_afyp").toString());
				}catch(Exception e){}
				try{
					wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("wip_count").toString());
				}catch(Exception e){}
				try{
					ho_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("ho_wip_count").toString());
				}catch(Exception e){}
				try{
					go_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("go_wip_count").toString());
				}catch(Exception e){}
				try{
					it_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("it_wip_count").toString());
				}catch(Exception e){}
				try{
					fin_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("fin_wip_count").toString());
				}catch(Exception e){}
				try{
					misc_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("misc_wip_count").toString());
				}catch(Exception e){}
				try{
					welcome_wip_count = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wip").get("welcome_wip_count").toString());
				}catch(Exception e){}
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
				try{
					ul_penet_mtd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_penet_mtd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					par_penet_mtd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("penetration").get("par_penet_mtd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					nonpar_penet_mtd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("penetration").get("nonpar_penet_mtd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					protec_penet_mtd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_penet_mtd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					par_penet_mtd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("par_penet_mtd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					nonpar_penet_mtd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("nonpar_penet_mtd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					ul_penet_ytd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("penetration").get("ul_penet_ytd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					par_penet_ytd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("penetration").get("par_penet_ytd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					nonpar_penet_ytd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("penetration").get("nonpar_penet_ytd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					protec_penet_ytd_adj_mfyp = (object.getJSONObject("payload").getJSONObject("penetration").get("protec_penet_ytd_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					par_penet_ytd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("par_penet_ytd_pol_cnt").toString());
				}catch(Exception e){}
				try{
					nonpar_penet_ytd_pol_cnt = (object.getJSONObject("payload").getJSONObject("penetration").get("nonpar_penet_ytd_pol_cnt").toString());
				}catch(Exception e){}
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
					grth_lpc_applied_afyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_applied_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_applied_afyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_applied_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					curr_lpc_applied_afyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("curr_lpc_applied_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					grth_lpc_applied_afyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_applied_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_applied_afyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_applied_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					curr_lpc_applied_afyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("curr_lpc_applied_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					grth_lpc_applied_adj_ifyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_applied_adj_ifyp_ytd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_applied_adj_ifyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_applied_adj_ifyp_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_adj_ifyp_ytd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("lpc_applied_adj_ifyp_ytd").toString());
				}catch(Exception e){}
				try{
					grth_lpc_applied_adj_ifyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_applied_adj_ifyp_mtd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_applied_adj_ifyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_applied_adj_ifyp_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_adj_ifyp_mtd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("lpc_applied_adj_ifyp_mtd").toString());
				}catch(Exception e){}
				try{
					grth_case_size_afyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_case_size_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					prev_case_size_afyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_case_size_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					case_size_afyp_ytd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("case_size_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					grth_case_size_afyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_case_size_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					prev_case_size_afyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_case_size_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					case_size_afyp_mtd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("case_size_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					grth_ovr_lst_yr_paid = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lst_yr_sm_adj_mfyp_ytd").toString());
				}catch(Exception e){}
				try{
					grth_recruitment_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_recruitment_ytd").toString());
				}catch(Exception e){}
				try{
					prev_recruitment_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_recruitment_ytd").toString());
				}catch(Exception e){}
				try{
					recruitment_ytd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("recruitment_ytd").toString());
				}catch(Exception e){}
				try{
					grth_recruitment_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_recruitment_mtd").toString());
				}catch(Exception e){}
				try{
					prev_recruitment_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_recruitment_mtd").toString());
				}catch(Exception e){}
				try{
					recruitment_mtd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("recruitment_mtd").toString());
				}catch(Exception e){}
				try{
					grth_lst_yr_inforced_cnt_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lst_yr_inforced_cnt_ytd").toString());
				}catch(Exception e){}
				try{
					prev_year_inforced_cnt_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_year_inforced_cnt_ytd").toString());
				}catch(Exception e){}
				try{
					ytd_inforced_cnt = (object.getJSONObject("payload").getJSONObject("growth").get("ytd_inforced_cnt").toString());
				}catch(Exception e){}
				try{
					grth_lst_yr_inforced_cnt_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lst_yr_inforced_cnt_mtd").toString());
				}catch(Exception e){}
				try{
					prev_year_inforced_cnt_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_year_inforced_cnt_mtd").toString());
				}catch(Exception e){}
				try{
					mtd_inforced_cnt = (object.getJSONObject("payload").getJSONObject("growth").get("mtd_inforced_cnt").toString());
				}catch(Exception e){}
				try{
					grth_lpc_paid_cases_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_paid_cases_ytd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_paid_cases_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_paid_cases_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_paid_cases_ytd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("lpc_paid_cases_ytd").toString());
				}catch(Exception e){}
				try{
					grth_lpc_paid_cases_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_paid_cases_mtd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_paid_cases_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_paid_cases_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_paid_cases_mtd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("lpc_paid_cases_mtd").toString());
				}catch(Exception e){}
				try{
					grth_lpc_applied_cases_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_applied_cases_ytd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_applied_cases_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_applied_cases_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_cases_ytd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("lpc_applied_cases_ytd").toString());
				}catch(Exception e){}
				try{
					grth_lpc_applied_cases_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_applied_cases_mtd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_applied_cases_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_applied_cases_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_cases_mtd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("lpc_applied_cases_mtd").toString());
				}catch(Exception e){}
				try{
					grth_lst_yr_sm_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lst_yr_sm_adj_mfyp_mtd").toString());
				}catch(Exception e){}
				try{
					grth_lst_yr_sm_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lst_yr_sm_adj_mfyp_ytd").toString());
				}catch(Exception e){}
				try{
					prev_year_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_year_adj_mfyp_mtd").toString());
				}catch(Exception e){}
				try{
					prev_year_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_year_adj_mfyp_ytd").toString());
				}catch(Exception e){}
				try{
					grth_applied_adj_ifyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_applied_adj_ifyp_ytd").toString());
				}catch(Exception e){}
				try{
					rpev_applied_adj_ifyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("rpev_applied_adj_ifyp_ytd").toString());
				}catch(Exception e){}
				try{
					applied_adj_ifyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("applied_adj_ifyp_ytd").toString());
				}catch(Exception e){}
				try{
					grth_applied_adj_ifyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_applied_adj_ifyp_mtd").toString());
				}catch(Exception e){}
				try{
					rpev_applied_adj_ifyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("rpev_applied_adj_ifyp_mtd").toString());
				}catch(Exception e){}
				try{
					grth_applied_afyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_applied_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					prev_applied_afyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_applied_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					applied_afyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("applied_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					grth_applied_afyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_applied_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					prev_applied_afyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_applied_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					applied_afyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("applied_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					grth_applied_cases_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_applied_cases_ytd").toString());
				}catch(Exception e){}
				try{
					prev_applied_cases_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_applied_cases_ytd").toString());
				}catch(Exception e){}
				try{
					applied_cases_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("applied_cases_ytd").toString());
				}catch(Exception e){}
				try{
					grth_applied_cases_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_applied_cases_mtd").toString());
				}catch(Exception e){}
				try{
					prev_applied_cases_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_applied_cases_mtd").toString());
				}catch(Exception e){}
				try{
					applied_cases_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("applied_cases_mtd").toString());
				}catch(Exception e){}
				try{
					grth_lpc_paid_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_paid_adj_mfyp_ytd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_paid_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_paid_adj_mfyp_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_paid_adj_mfyp_ytd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("lpc_paid_adj_mfyp_ytd").toString());
				}catch(Exception e){}
				try{
					grth_lpc_paid_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("grth_lpc_paid_adj_mfyp_mtd").toString());
				}catch(Exception e){}
				try{
					prev_lpc_paid_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("growth").get("prev_lpc_paid_adj_mfyp_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_paid_adj_mfyp_mtd_growth = (object.getJSONObject("payload").getJSONObject("growth").get("lpc_paid_adj_mfyp_mtd").toString());
				}catch(Exception e){}
				try{
					adj_mfyp_sam_ytd_lst_yr = (object.getJSONObject("payload").getJSONObject("growth").get("prev_year_adj_mfyp_ytd").toString());
				}catch(Exception e){}
				try{
					ytd_inforced_adj_mfyp = (object.getJSONObject("payload").getJSONObject("growth").get("ytd_inforced_adj_mfyp").toString());
				}catch(Exception e){}
				try{
					mtd_adj_mfyp_act = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_adj_mfyp_act").toString());
				}catch(Exception e){}
				try{
					ytd_adj_mfyp_act = (object.getJSONObject("payload").getJSONObject("achievement").get("ytd_adj_mfyp_act").toString());
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
				try{
					mtd_afyp_act = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_afyp_act").toString());
				}catch(Exception e){}
				try{
					ytd_afyp_act = (object.getJSONObject("payload").getJSONObject("achievement").get("ytd_afyp_act").toString());
				}catch(Exception e){}
				try{
					mtd_afyp_pln = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_afyp_pln").toString());
				}catch(Exception e){}
				try{
					ytd_afyp_pln = (object.getJSONObject("payload").getJSONObject("achievement").get("ytd_afyp_pln").toString());
				}catch(Exception e){}
				try{
					achiev_mtd_case_active_mtd = (object.getJSONObject("payload").getJSONObject("achievement").get("achiev_mtd_case_active_mtd").toString());
				}catch(Exception e){}
				try{
					achiev_mtd_case_active_ytd = (object.getJSONObject("payload").getJSONObject("achievement").get("achiev_mtd_case_active_ytd").toString());
				}catch(Exception e){}
				try{
					achiev_mtd_recruitment = (object.getJSONObject("payload").getJSONObject("achievement").get("achiev_mtd_recruitment").toString());
				}catch(Exception e){}
				try{
					achiev_ytd_recruitment = (object.getJSONObject("payload").getJSONObject("achievement").get("achiev_ytd_recruitment").toString());
				}catch(Exception e){}
				try{
					achiev_mtd_paid_case = (object.getJSONObject("payload").getJSONObject("achievement").get("achiev_mtd_paid_case").toString());
				}catch(Exception e){}
				try{
					mtd_adj_afyp_act = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_adj_afyp_act").toString());
				}catch(Exception e){}
				try{
					mtd_adj_afyp_pln = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_adj_afyp_pln").toString());
				}catch(Exception e){}
				try{
					mtd_paid_case_act = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_paid_case_act").toString());
				}catch(Exception e){}
				try{
					mtd_paid_case_pln = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_paid_case_pln").toString());
				}catch(Exception e){}
				try{
					achiev_ytd_paid_case = (object.getJSONObject("payload").getJSONObject("achievement").get("achiev_ytd_paid_case").toString());
				}catch(Exception e){}
				try{
					ytd_adj_mfyp_act = (object.getJSONObject("payload").getJSONObject("achievement").get("ytd_adj_mfyp_act").toString());
				}catch(Exception e){}
				try{
					ytd_adj_mfyp_pln = (object.getJSONObject("payload").getJSONObject("achievement").get("ytd_adj_mfyp_pln").toString());
				}catch(Exception e){}
				try{
					mtd_adj_mfyp_pln = (object.getJSONObject("payload").getJSONObject("achievement").get("mtd_adj_mfyp_pln").toString());
				}catch(Exception e){}
				try{
					ytd_paid_case_act = (object.getJSONObject("payload").getJSONObject("achievement").get("ytd_paid_case_act").toString());
				}catch(Exception e){}
				try{
					ytd_paid_case_pln = (object.getJSONObject("payload").getJSONObject("achievement").get("ytd_paid_case_pln").toString());
				}catch(Exception e){}
				try{
					case_size_afyp_mtd = (object.getJSONObject("payload").getJSONObject("casesize").get("case_size_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					case_size_afyp_ytd = (object.getJSONObject("payload").getJSONObject("casesize").get("case_size_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_adj_ifyp_mtd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_applied_adj_ifyp_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_adj_ifyp_ytd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_applied_adj_ifyp_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_afyp_mtd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_applied_afyp_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_afyp_ytd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_applied_afyp_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_cases_mtd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_applied_cases_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_applied_cases_ytd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_applied_cases_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_paid_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_paid_adj_mfyp_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_paid_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_paid_adj_mfyp_ytd").toString());
				}catch(Exception e){}
				try{
					lpc_paid_cases_mtd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_paid_cases_mtd").toString());
				}catch(Exception e){}
				try{
					lpc_paid_cases_ytd = (object.getJSONObject("payload").getJSONObject("lpcperformance").get("lpc_paid_cases_ytd").toString());
				}catch(Exception e){}
				try{
					recruitment_mtd = (object.getJSONObject("payload").getJSONObject("rec").get("recruitment_mtd").toString());
				}catch(Exception e){}
				try{
					recruitment_ytd = (object.getJSONObject("payload").getJSONObject("rec").get("recruitment_ytd").toString());
				}catch(Exception e){}


				sum3 = sum+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
				sum = sum+wipAFYP+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
				String convertsum  =  df.format(sum);
				String convertsum3  =  df.format(sum3);
				sum4=sum4+ho_wip_count+go_wip_count+it_wip_count+fin_wip_count+misc_wip_count+welcome_wip_count;
				String convertsum4  =  df1.format(sum4);

				if("NUMBERS".equalsIgnoreCase(action))
				{
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
								finalresponse = finalresponse+" Do you want to see the Data For any Channel. Please Enter the Channel Name like :\n\n Agency, Axis Bank, Banca, Cat, Ecomm, IM, IMF";
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
						}else
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
					}else 
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
								finalresponse = finalresponse+" Do you want to see the Data For any Channel. Please Enter the Channel Name like :\n\n Agency, Axis Bank, Banca, Cat, Ecomm, IM, IMF";
							}
						}
					}
				}
				else if("AdjMFYP".equalsIgnoreCase(action))
				{
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

						}else
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
				}
				else if("WIP".equalsIgnoreCase(action))
				{
					if(!"".equalsIgnoreCase(channel))
					{
						finalresponse="Current WIP as of "+dtf.format(now)+
								" for "+msgChannel+" is "+convertsum4+" Policies with "+convertsum3+" "
								+ " Cr. AFYP. Do you wish to see the stage wise snapshot";
					}else
					{
						finalresponse="Current WIP as of "+dtf.format(now)+
								" for MLI is "+convertsum4+" Policies with "+convertsum3+" "
								+ "Cr. AFYP. Do you wish to see the stage wise snapshot";
					}
				}
				else if("WIP.YES".equalsIgnoreCase(action))
				{
					if(!"".equalsIgnoreCase(channel))
					{
						finalresponse="WIP for "+msgChannel+" AFYP :" +convertsum3+"Cr. and Policies"+convertsum4+" "
								+"\n\n HO WIP AFYP :"+hoWIPAFYP+"Cr. and Policies"+ho_wip_count+" "
								+"\n\n GO WIP AFYP :"+goWIPAFYP+"Cr. and Policies "+go_wip_count+" "
								+"\n\n IT WIP AFYP :"+itWIPAFYP+"Cr. and Policies"+it_wip_count+" "
								+"\n\n FIN WIP AFYP :"+finWIPAFYP+"Cr. and Policies"+fin_wip_count+" "
								+"\n\n MISC WIP AFYP :"+miscWIPAFYP+"Cr. and Policies"+misc_wip_count+" "
								+"\n\n WELCOME WIP AFYP :"+welcomeWIPAFYP+"Cr. and Policies "+welcome_wip_count+"";
					}else
					{
						finalresponse="WIP AFYP :" +convertsum3+
								"\n\n HO WIP AFYP :"+hoWIPAFYP+
								"\n\n GO WIP AFYP :"+goWIPAFYP+
								"\n\n IT WIP AFYP :"+itWIPAFYP+
								"\n\n FIN WIP AFYP :"+finWIPAFYP+
								"\n\n MISC WIP AFYP :"+miscWIPAFYP+
								"\n\n WELCOME WIP AFYP :"+welcomeWIPAFYP+"";
					}
				}
				else if("APPLIED".equalsIgnoreCase(action))
				{
					if("MONTHLY".equalsIgnoreCase(period) ||"MTD".equalsIgnoreCase(period) ||"MONTH".equalsIgnoreCase(period))
					{
						if("google".equalsIgnoreCase(source))
						{
							finalresponse=" Monthly Applied Business for "+msgChannel+
									" as of now is : "+mtdAppliedAFYP+" Cr "+"";
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" Applied Business AFYP MTD For "+msgChannel+
									": "+mtdAppliedAFYP+" Cr "+"";
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
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" Applied AFYP For "+msgChannel+"  is: "
									+ " FTD : " +dailyAppliedAFYP+" Cr"
									+", MTD : " +mtdAppliedAFYP+" Cr"
									+", YTD : " +ytd_applied_afyp+" Cr";
						}
						
					}
					else
					{
						finalresponse="As of "+real_tim_timstamp+" Applied AFYP"+
								"  is: "
								+ " FTD : " +dailyAppliedAFYP+" Cr"
								+", MTD : " +mtdAppliedAFYP+" Cr"
								+", YTD : " +ytd_applied_afyp+" Cr";
					}
				}
				else if("Growth".equalsIgnoreCase(action))
				{
					if("google".equalsIgnoreCase(source))
					{
						finalresponse = msgChannel+" has witnessed paid Business growth of "+grth_ovr_lst_yr_paid
								+"% on YTD basis, \n\n last year we had clocked "+adj_mfyp_sam_ytd_lst_yr+
								"Cr of paid business, as compared to "+ytd_inforced_adj_mfyp+"Cr today";
					}else
					{
						finalresponse = msgChannel+" has witnessed paid Business growth of "+grth_ovr_lst_yr_paid
								+"% on YTD basis, \n\n last year we had clocked "+adj_mfyp_sam_ytd_lst_yr+
								"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";
					}

				}
				else if("Achievement".equalsIgnoreCase(action))
				{
					if("google".equalsIgnoreCase(source))
					{
						finalresponse="At Monthly level "+msgChannel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Our plan is "
								+mtd_adj_mfyp_pln+" Cr, and till now we have achieved "+mtd_adj_mfyp_act+" Cr, At Yearly level "
								+msgChannel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Our plan is "
								+ytd_adj_mfyp_pln+"Cr and till now we have achieved "+ytd_adj_mfyp_act+" Cr";
					}else
					{
						finalresponse="At MTD level "+msgChannel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Our monthly plan is "
								+mtd_adj_mfyp_pln+" Cr and till "+real_tim_timstamp+" We have achieved "+mtd_adj_mfyp_act+" Cr, At YTD level "
								+msgChannel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Our YTD plan is "
								+ytd_adj_mfyp_pln+" Cr and till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr";
					}
				}
				else if("Penetration".equalsIgnoreCase(action))
				{
					if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)||"MONTH".equalsIgnoreCase(period))
					{
						if("ULIP".equalsIgnoreCase(productType))
						{
							if("google".equalsIgnoreCase(source))
							{
								finalresponse=msgChannel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
										+" Cr of paid Business and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
										+ "  issued in this month ";	
							}else
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
							}else
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
							}else
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
							}else
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
							}else
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
				}
				/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
				//				Sprint-2
				/*-----*/
				else if("NB.Paidcases".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " Paid cases MTD for MLI is "
								+ mtd_inforced_count+ " Paid cases YTD for MLI is " + ytd_inforced_count + 
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " Paid cases MTD for " + channel + " is " + mtd_inforced_count +
								" Paid cases YTD for " +channel + " is " + ytd_inforced_count +
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of " + real_tim_timstamp + " Paid cases MTD for " + userzone + " zone is " + mtd_inforced_count + " Paid cases YTD for " +
								userzone + " zone is " + ytd_inforced_count + " If you want to see the region wise business numbers, please specify";

					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of " +real_tim_timstamp + " Paid cases MTD for " + user_region + " is " + mtd_inforced_count +
								" Paid cases YTD for " + user_region + " is " + ytd_inforced_count;
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + ytd_inforced_count +
									" If you want to see the zone/region wise business numbers, please specify";
						}
						else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + mtd_inforced_count +
									" If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + channel + " is " + daily_inforced_count1 +
									" If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + ytd_inforced_count +
									" If you want to see the region wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + mtd_inforced_count +
									" If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp + " Paid cases " + period + " for " + userzone+ " zone is " + daily_inforced_count1 +
									" If you want to see the region wise business numbers, please specify";
						}

					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{	
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " region is " + ytd_inforced_count;
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " region is " + mtd_inforced_count;
						}else
						{
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for " + user_region + " region is " + daily_inforced_count1;
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + ytd_inforced_count +
									" If you want to see the channel wise business numbers, please specify";
						}else if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + mtd_inforced_count +
									" If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of " + real_tim_timstamp + " Paid cases " + period + " for MLI is " + daily_inforced_count1 +
									" If you want to see the channel wise business numbers, please specify";
						}
					}
				}
				else if("NB.Applied".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for MLI is  "
								+ mtdAppliedAFYP + " Cr Applied Business AFYP YTD for MLI is " + ytd_applied_afyp + 
								" If you want to see the channel wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for " + channel +
								" is " + mtdAppliedAFYP + " Cr Applied Business AFYP YTD for " +channel + " is "+ ytd_applied_afyp +
								" If you want to see the zone/region wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for " + userzone + " zone is "
								+ mtdAppliedAFYP + " Cr Applied Business AFYP YTD for " + userzone + " zone is " + ytd_applied_afyp +
								" Cr. If you want to see the region wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " Applied Business AFYP MTD for " + user_region + " region is " 
								+ mtdAppliedAFYP + " Applied Business AFYP YTD for " + user_region + " region is " + ytd_applied_afyp + " Cr";

					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " Applied Business AFYP " + period + " for " +channel+ " is " +ytd_applied_afyp+
									" Cr. If you want to see the zone/region wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " Applied Business AFYP " + period + " for " +channel+ " is " +mtdAppliedAFYP+
									" Cr. If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " + real_tim_timstamp + " Applied Business AFYP " + period + " for " +channel+ " is " +daily_inforced_count+
									" Cr. If you want to see the zone/region wise business numbers, please specify";
						}
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " +userzone+ " zone is " +ytd_applied_afyp+
									" Cr. If you want to see the region wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " +userzone+ " zone is " +mtdAppliedAFYP+
									" Cr. If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " +userzone+ " zone is " +daily_inforced_count+
									" Cr. If you want to see the region wise business numbers, please specify";
						}
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " region is " +ytd_applied_afyp+ " Cr";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " region is " +mtdAppliedAFYP+ " Cr";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for " + user_region+ " region is " +daily_inforced_count+ " Cr";
						}
					}else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for MLI is " +ytd_applied_afyp+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for MLI is " +ytd_applied_afyp+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " Applied Business AFYP " +period+ " for MLI is " +ytd_applied_afyp+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";
						}
					}
				}
				else if("NB.AdjMFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for MLI is  "
								+ mtdAdjustMFYP+ " Paid AdjMFYP YTD for MLI is " +ytd_adj_mfyp+ 
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +channel+ " is " +mtdAdjustMFYP+
								" Paid cases YTD for " +channel+ " is " +ytd_adj_mfyp+
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +userzone+ " zone is " + mtdAdjustMFYP+ " Paid Adj MFYP YTD for " +
								userzone+ " zone is " +ytd_adj_mfyp+ " If you want to see the region wise business numbers, please specify";

					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP MTD for " +user_region+ " region is " +mtdAdjustMFYP+
								" Paid Adj MFYP YTD for "+userzone+ " zone is " +ytd_adj_mfyp;
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + ytd_adj_mfyp+
									"If you want to see the zone/region wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + mtd_adj_mfyp+
									"If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +channel+ "is " + daily_adj_mfyp+
									"If you want to see the zone/region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+ytd_adj_mfyp+
									" If you want to see the region wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+mtd_adj_mfyp+
									" If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP " +period+ " for " +userzone+" zone is "+daily_adj_mfyp+
									" If you want to see the region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" region is "+ytd_adj_mfyp;
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" region is "+mtd_adj_mfyp;
						}else
						{
							finalresponse="As of " +real_tim_timstamp+" Paid Adj MFYP "+period+" for "+user_region+" region is "+daily_adj_mfyp;
						}
					}
					else
					{
						if ("YTD".equalsIgnoreCase(period)){
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP "+period+ " for MLI  is " +ytd_adj_mfyp+
									" If you want to see the channel wise business numbers, please specify";
						}else if ("MTD".equalsIgnoreCase(period)){
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP "+period+ " for MLI  is " +mtd_adj_mfyp+
									" If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " Paid Adj MFYP "+period+ " for MLI  is " +daily_adj_mfyp+
									" If you want to see the channel wise business numbers, please specify";
						}
					}
				}
				else if("NB.casesize".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " +real_tim_timstamp+ " Case Size MTD for MLI is "
								+ case_size_afyp_mtd+ " Case Size YTD for MLI is " +case_size_afyp_ytd+ 
								" If you want to see the channel wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " +real_tim_timstamp+ " Case Size MTD for "+channel+" is "+case_size_afyp_mtd+
								" Case Size YTD for "+channel+" is "+case_size_afyp_ytd+
								" If you want to see the zone/region wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of " +real_tim_timstamp+" Case Size MTD for "+userzone+ " zone is "+ case_size_afyp_mtd+" Case Size YTD for "+
								userzone+" zone is "+case_size_afyp_ytd+" If you want to see the region wise business numbers, please specify";

					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of " +real_tim_timstamp+ " Case Size MTD for "+userzone+" zone is "+case_size_afyp_mtd+
								" Case Size YTD for"+userzone+" zone is "+case_size_afyp_ytd;
					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " Case Size " +period+" for "+channel+ " is "+ case_size_afyp_ytd+
									" If you want to see the zone/region wise business numbers, please specify";
						}else 
						{
							finalresponse="As of " +real_tim_timstamp+ " Case Size " +period+" for "+channel+ " is "+ case_size_afyp_mtd+
									" If you want to see the zone/region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+userzone+" zone is "+case_size_afyp_ytd+
									" If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+userzone+" zone is "+case_size_afyp_mtd+
									" If you want to see the region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_region+" region is "+case_size_afyp_ytd;
						}else
						{
							finalresponse="As of " +real_tim_timstamp+" Case Size "+period+" for "+user_region+" region is "+case_size_afyp_mtd;
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+"  Case Size "+period+ " for MLI is "+case_size_afyp_ytd+
									" If you want to see the channel wise business numbers, please specify";
						}else 
						{
							finalresponse="As of " +real_tim_timstamp+"  Case Size "+period+ " for MLI is "+case_size_afyp_mtd+
									" If you want to see the channel wise business numbers, please specify";
						}
					}
				}
				else if("NB.AppliedAdjIFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}

					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" Applied Business Adj IFYP MTD for MLI "
								+ mtd_applied_adj_ifyp+"Cr. Applied Business Adj IFYP YTD for MLI "+ytd_applied_adj_ifyp+ 
								"Cr. If you want to see the channel wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" Applied Business Adj IFYP MTD for "+channel+" is "+mtd_applied_adj_ifyp+
								"Cr. Applied Business Adj IFYP YTD for "+channel+" is "+ytd_applied_adj_ifyp+
								"Cr. If you want to see the zone/region wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP MTD for "+userzone+ " zone is "+ mtd_applied_adj_ifyp+
								"Cr. Applied Business Adj IFYP YTD for "+userzone+" zone is "+ytd_applied_adj_ifyp+
								"Cr. If you want to see the region wise business numbers, please specify";

					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of "+real_tim_timstamp+ " Applied Business Adj IFYP MTD for "+user_region+" region is "+mtd_applied_adj_ifyp+
								"Cr. Applied Business Adj IFYP YTD for "+user_region+" region is "+ytd_applied_adj_ifyp+" Cr.";
					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of" +real_tim_timstamp+ " Applied Business Adj IFYP " +period+" for "+channel+ "is"+ ytd_applied_adj_ifyp+
									"Cr. If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of" +real_tim_timstamp+ " Applied Business Adj IFYP " +period+" for "+channel+ "is"+ mtd_applied_adj_ifyp+
									"Cr. If you want to see the zone/region wise business numbers, please specify";
						}
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+userzone+" zone is "+ytd_applied_adj_ifyp+
									"If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+userzone+" zone is "+mtd_applied_adj_ifyp+
									"If you want to see the region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_region+" region is "+ytd_applied_adj_ifyp+" Cr";
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" Applied Business Adj IFYP "+period+" for "+user_region+" region is "+mtd_applied_adj_ifyp+" Cr";
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+"  Applied Business Adj IFYP "+period+ " for MLI is "+ytd_applied_adj_ifyp+
									" Cr. If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+"  Applied Business Adj IFYP "+period+ " for MLI is "+mtd_applied_adj_ifyp+
									" Cr. If you want to see the channel wise business numbers, please specify";
						}
					}
				}
				else if("NB.AppliedCases".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}

					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" Applied cases MTD for MLI is "
								+mtd_applied_count+" Applied cases YTD for MLI is "+ytd_applied_count+ 
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" Applied cases MTD for "+channel+
								" is "+mtd_applied_count+" Applied cases YTD for "+channel+" is "+ytd_applied_count+
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" Applied cases MTD for "+user_region+" is "
								+mtd_applied_count+" Applied cases YTD for "+user_region+" is " +ytd_applied_count+
								" If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" Applied cases MTD for "+user_region+ " is " 
								+mtd_applied_count+ " Applied cases YTD for for "+user_region+" is "+ ytd_applied_count;

					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+" Applied cases "+period+" for "+channel+ " is "+ytd_applied_count+
									" If you want to see the zone/region wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period)){
							finalresponse="As of " +real_tim_timstamp+" Applied cases "+period+" for "+channel+ " is "+mtd_applied_count+
									" If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+" Applied cases "+period+" for "+channel+ " is "+daily_applied_count+
									" If you want to see the zone/region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+ " for "+userzone+" zone is "+ytd_applied_count+
									" If you want to see the region wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+ " for "+userzone+" zone is "+mtd_applied_count+
									" If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+ " for "+userzone+" zone is "+daily_applied_count+
									" If you want to see the region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" Region is "+ytd_applied_count+" Cr";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" Region is "+mtd_applied_count+" Cr";
						}else
						{
							finalresponse=" As of "+real_tim_timstamp+" Applied cases "+period+" for "+ user_region+" Region is "+daily_applied_count+" Cr";
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+" for MLI is "+ytd_applied_count+
									" If you want to see the channel wise business numbers, please specify";
						}else if("MTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+" for MLI is "+mtd_applied_count+
									" If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" Applied cases "+period+" for MLI is "+daily_applied_count+
									" If you want to see the channel wise business numbers, please specify";
						}
					}
				}
				else if("NB.LPCAPPADJIFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}           

					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business Adj IFYP MTD for MLI is "
								+lpc_applied_adj_ifyp_mtd+" LPC Applied Business Adj IFYP YTD for MLI is "+lpc_applied_adj_ifyp_ytd+ 
								" If you want to see the channel wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business Adj IFYP MTD for "+channel+
								" is "+lpc_applied_adj_ifyp_mtd+" LPC Applied Business Adj IFYP YTD for "+channel+" is "+lpc_applied_adj_ifyp_ytd+
								" If you want to see the zone/region wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business Adj IFYP MTD for "+userzone+" zone is "
								+lpc_applied_adj_ifyp_mtd+" LPC Applied Business Adj IFYP YTD for "+userzone+" zone is " +lpc_applied_adj_ifyp_ytd+
								" If you want to see the region wise business numbers, please specify";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business Adj IFYP MTD for "+user_region+ " region is " 
								+lpc_applied_adj_ifyp_mtd+ " LPC Applied Business Adj IFYP YTD for "+user_region+" region is "+ lpc_applied_adj_ifyp_ytd;

					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+" LPC Applied Business Adj IFYP "+period+" for "+channel+ " is "+lpc_applied_adj_ifyp_ytd+
									" If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+" LPC Applied Business Adj IFYP "+period+" for "+channel+ " is "+lpc_applied_adj_ifyp_mtd+
									" If you want to see the zone/region wise business numbers, please specify";		
						}
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" LPC Applied Business Adj IFYP "+period+ " for "+userzone+" zone is "+lpc_applied_adj_ifyp_ytd+
									" If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" LPC Applied Business Adj IFYP "+period+ " for "+userzone+" zone is "+lpc_applied_adj_ifyp_mtd+
									" If you want to see the region wise business numbers, please specify";
						}
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse=" As of "+real_tim_timstamp+" LPC Applied Business Adj IFYP "+period+" for "+ user_region+" region is "+lpc_applied_adj_ifyp_ytd+" Cr";
						}else
						{
							finalresponse=" As of "+real_tim_timstamp+" LPC Applied Business Adj IFYP "+period+" for "+ user_region+" region is "+lpc_applied_adj_ifyp_mtd+" Cr";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of"+real_tim_timstamp+" LPC Applied Business Adj IFYP "+period+" for MLI is "+lpc_applied_adj_ifyp_ytd+
									" If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of"+real_tim_timstamp+" LPC Applied Business Adj IFYP "+period+" for MLI is "+lpc_applied_adj_ifyp_ytd+
									" If you want to see the channel wise business numbers, please specify";	
						}
					}
				}
				else if("NB.LPCAPPADJAFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}

					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business AFYP MTD for MLI is "
								+lpc_applied_afyp_mtd+" LPC Applied Business AFYP YTD for MLI is "+lpc_applied_afyp_ytd+ 
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business AFYP MTD for "+channel+
								" is "+lpc_applied_afyp_mtd+" LPC Applied Business AFYP YTD for "+channel+" is "+lpc_applied_afyp_ytd+
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business AFYP MTD for "+userzone+" zone is "
								+lpc_applied_afyp_mtd+" LPC Applied Business AFYP YTD for "+userzone+" zone is " +lpc_applied_afyp_ytd+
								" If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of "+real_tim_timstamp+" LPC Applied Business AFYP MTD for "+user_region+ " region is " 
								+lpc_applied_afyp_mtd+ " LPC Applied Business AFYP YTD for "+user_region+" region is "+ lpc_applied_afyp_ytd;

					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+channel+ " is "+lpc_applied_afyp_ytd+
									" If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+channel+ " is "+lpc_applied_afyp_mtd+
									" If you want to see the zone/region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+ " for "+userzone+" is "+lpc_applied_afyp_ytd+
									" If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+ " for "+userzone+" is "+lpc_applied_afyp_mtd+
									" If you want to see the region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse=" As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+ user_region+ " region is "+lpc_applied_afyp_ytd+" Cr";
						}else
						{
							finalresponse=" As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for "+ user_region+ " region is "+lpc_applied_afyp_mtd+" Cr";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for MLI is "+lpc_applied_afyp_ytd+
									" If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of "+real_tim_timstamp+" LPC Applied Business AFYP "+period+" for MLI is "+lpc_applied_afyp_mtd+
									" If you want to see the channel wise business numbers, please specify";	
						}
					}
				}
				else if("NB.LPCAPLCASES".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD for MLI is  "
								+ lpc_applied_cases_mtd + " Cr LPC Applied cases YTD for MLI is  " + lpc_applied_cases_ytd + 
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD for " + channel +
								" is " + lpc_applied_cases_mtd + " Cr LPC Applied cases YTD for " +channel + " is "+ lpc_applied_cases_ytd+
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD for" + userzone + " zone is "
								+ lpc_applied_cases_mtd + " Cr LPC Applied cases YTD for " + userzone + " zone is " + lpc_applied_cases_ytd+
								" Cr. If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Applied cases MTD " + user_region + " region is " 
								+ lpc_applied_cases_mtd + " LPC Applied cases YTD for " + user_region + " region is " + lpc_applied_cases_ytd+ " Cr";

					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " LPC Applied cases " + period + " for " +channel+ " is " +lpc_applied_cases_ytd+
									" Cr. If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " + real_tim_timstamp + " LPC Applied cases " + period + " for " +channel+ " is " +lpc_applied_cases_mtd+
									" Cr. If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " +userzone+ " zone is " +lpc_applied_cases_ytd+
									" Cr. If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " +userzone+ " zone is " +lpc_applied_cases_mtd+
									" Cr. If you want to see the region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_region+ " region is " +lpc_applied_cases_ytd+ " Cr";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for " + user_region+ " region is " +lpc_applied_cases_mtd+ " Cr";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for MLI is " +lpc_applied_cases_ytd+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Applied cases " +period+ " for MLI is " +lpc_applied_cases_mtd+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";	
						}
					}
				}
				else if("NB.LPCPAIDADJMFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Paid Adj MFYP MTD for MLI is  "
								+ lpc_paid_adj_mfyp_mtd + " Cr LPC Paid Adj MFYP YTD for MLI is  " + lpc_paid_adj_mfyp_ytd + 
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Paid Adj MFYP MTD for " + channel +
								" is " + lpc_paid_adj_mfyp_mtd + " Cr LPC Paid Adj MFYP YTD for " +channel + " is "+ lpc_paid_adj_mfyp_ytd+
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Paid Adj MFYP MTD for " + userzone + " zone is "
								+ lpc_paid_adj_mfyp_mtd + " Cr LPC Paid Adj MFYP YTD for " + userzone + " zone is " + lpc_paid_adj_mfyp_ytd+
								" Cr. If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Paid Adj MFYP MTD for " +user_region+ " region is " 
								+ lpc_paid_adj_mfyp_mtd + " LPC Paid Adj MFYP YTD for " + user_region + " region is " + lpc_paid_adj_mfyp_ytd+ " Cr";

					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " LPC Paid Adj MFYP " + period + " for " +channel+ " is " +lpc_paid_adj_mfyp_ytd+
									" Cr. If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " + real_tim_timstamp + " LPC Paid Adj MFYP " + period + " for " +channel+ " is " +lpc_paid_adj_mfyp_mtd+
									" Cr. If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " +userzone+ " zone is " +lpc_paid_adj_mfyp_ytd+
									" Cr. If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " +userzone+ " zone is " +lpc_paid_adj_mfyp_mtd+
									" Cr. If you want to see the region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " + user_region+ " region is " +lpc_paid_adj_mfyp_ytd+ " Cr";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for " + user_region+ " region is " +lpc_paid_adj_mfyp_mtd+ " Cr";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for MLI is " +lpc_paid_adj_mfyp_ytd+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid Adj MFYP " +period+ " for MLI is " +lpc_paid_adj_mfyp_mtd+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";	
						}
					}
				}
				else if("NB.LPCPAIDCASES".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Paid cases MTD for MLI is  "
								+ lpc_paid_cases_mtd + " LPC Paid cases YTD for MLI is  " + lpc_paid_cases_ytd + 
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Paid cases MTD for " + channel +
								" is " + lpc_paid_cases_mtd + " LPC Paid cases YTD for " +channel + " is "+ lpc_paid_cases_ytd+
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Paid cases MTD for " + userzone + " zone is "
								+ lpc_paid_cases_mtd + " Cr LPC Paid cases YTD for " + userzone + " zone is " + lpc_paid_cases_ytd+
								" Cr. If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " + real_tim_timstamp + " LPC Paid cases MTD for " +user_region+ " region is " 
								+ lpc_paid_cases_mtd + " LPC Paid cases YTD for " + user_region + " region is " + lpc_paid_cases_ytd+ " Cr";

					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " + real_tim_timstamp + " LPC Paid cases " + period + " for " +channel+ " is " +lpc_paid_cases_ytd+
									" Cr. If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " + real_tim_timstamp + " LPC Paid cases " + period + " for " +channel+ " is " +lpc_paid_cases_mtd+
									" Cr. If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " +userzone+ " zone is " +lpc_paid_cases_ytd+
									" Cr. If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " +userzone+ " zone is " +lpc_paid_cases_mtd+
									" Cr. If you want to see the region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " + user_region+ " region is " +lpc_paid_cases_ytd+ " Cr";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for " + user_region+ " region is " +lpc_paid_cases_mtd+ " Cr";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for MLI is " +lpc_paid_cases_ytd+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse="As of " +real_tim_timstamp+ " LPC Paid cases " +period+ " for MLI is " +lpc_paid_cases_mtd+ "Cr." 
									+ " If you want to see the channel wise business numbers, please specify";	
						}
					}
				}
				else if("NB.casesize%".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI Case Size acheivement MTD: "+achiev_mtd_case_active_mtd+" %, YTD " +achiev_mtd_case_active_ytd+" % till "+real_tim_timstamp+
								" If you want to see the channel wise business numbers, please specIfy.";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Agency channel Case Size acheivement MTD: "+achiev_mtd_case_active_mtd+" %, YTD " +achiev_mtd_case_active_ytd+" % till "+real_tim_timstamp+
								" If you want to see the zone/region wise business numbers, please specIfy.";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone North Case Size acheivement MTD: "+achiev_mtd_case_active_mtd+" %, YTD " +achiev_mtd_case_active_ytd+" % till "+real_tim_timstamp+
								" If you want to see the region wise business numbers, please specIfy.";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region North1 Case Size acheivement MTD: "+achiev_mtd_case_active_mtd+" %, YTD " +achiev_mtd_case_active_ytd+" % till "+real_tim_timstamp+
								" If you want to see the region wise business numbers, please specIfy.";

					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	

						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Agency channel Case Size acheivement "+period+" : "+achiev_mtd_case_active_ytd+" % till "+real_tim_timstamp+
									" If you want to see the zone/region wise business numbers please specIfy. ";
						}else
						{
							finalresponse= "Agency channel Case Size acheivement "+period+" : "+achiev_mtd_case_active_mtd+" % till "+real_tim_timstamp+
									" If you want to see the zone/region wise business numbers please specIfy. ";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone North Case Size acheivement "+period+" : "+achiev_mtd_case_active_ytd+" % till "+real_tim_timstamp+
									" If you want to see the region wise business numbers please specIfy";
						}else
						{
							finalresponse= "Zone North Case Size acheivement "+period+" : "+achiev_mtd_case_active_mtd+" % till "+real_tim_timstamp+
									" If you want to see the region wise business numbers please specIfy";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region North1 Case Size acheivement " +period+" : "+achiev_mtd_case_active_ytd+" % till "+real_tim_timstamp+
									" If you want to see the region wise business numbers please specIfy.";
						}else
						{
							finalresponse= "Region North1 Case Size acheivement " +period+" : "+achiev_mtd_case_active_mtd+" % till "+real_tim_timstamp+
									" If you want to see the region wise business numbers please specIfy.";
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "MLI Case Size acheivement " +period+" : "+achiev_mtd_case_active_ytd+" % till "+real_tim_timstamp+
									" If you want to see the channel wise business numbers please specIfy.";
						}else
						{
							finalresponse= "MLI Case Size acheivement " +period+" : "+achiev_mtd_case_active_mtd+" % till "+real_tim_timstamp+
									" If you want to see the channel wise business numbers please specIfy.";
						}
					}
				}
				else if("NB.Growth".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today MTD business Growth of "+ 
								grth_lst_yr_sm_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_year_adj_mfyp_mtd+" Cr  of Adj MFYP as compared to " + mtd_inforced_adj_mfyp+ " Cr today."
								+ " If you want to see the Channel wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today MTD business Growth of "+ 
								grth_lst_yr_sm_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_year_adj_mfyp_mtd+" Cr of Adj MFYP as compared to " + mtd_inforced_adj_mfyp+ " Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+ userzone+" has witnessed paid Business growth of  " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today MTD business Growth of "+ 
								grth_lst_yr_sm_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_year_adj_mfyp_mtd+" Cr of Adj MFYP as compared to " + mtd_inforced_adj_mfyp+ " Cr today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+ user_region+ " has witnessed paid Business growth of   " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today MTD business Growth of "+ 
								grth_lst_yr_sm_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_year_adj_mfyp_mtd+" Cr of Adj MFYP as compared to " + mtd_inforced_adj_mfyp+ " Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= channel+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
									prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_mtd+" % on MTD basis, last year same time we had clocked "+
									prev_year_adj_mfyp_mtd+ " Cr of Adj MFYP as compared to " +mtd_inforced_adj_mfyp+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "Zone "+userzone+" has witnessed paid Business growth of  " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
									prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";

						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed paid Business growth of  " +grth_lst_yr_sm_adj_mfyp_mtd+" % on MTD basis, last year same time we had clocked "+
									prev_year_adj_mfyp_mtd+ " Cr of Adj MFYP as compared to " +mtd_inforced_adj_mfyp+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "Region " +user_region+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
									prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Region " +user_region+" has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_mtd+" % on MTD basis, last year same time we had clocked "+
									prev_year_adj_mfyp_mtd+ " Cr of Adj MFYP as compared to " +mtd_inforced_adj_mfyp+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "MLI has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
									prev_year_adj_mfyp_ytd+ " Cr of Adj MFYP as compared to " +ytd_inforced_adj_mfyp+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "MLI has witnessed paid Business growth of " +grth_lst_yr_sm_adj_mfyp_mtd+" % on YTD basis, last year same time we had clocked "+
									prev_year_adj_mfyp_mtd+ " Cr of Adj MFYP as compared to " +mtd_inforced_adj_mfyp+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("NB.Recruitment".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " +real_tim_timstamp+" Recruitment MTD for MLI is Rs." +recruitment_mtd+" Recruitment YTD for MLI is Rs. " +recruitment_ytd+
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " +real_tim_timstamp+" Recruitment MTD for "+channel+" is Rs. " +recruitment_mtd+" Recruitment YTD for "+channel+" is Rs." +recruitment_ytd+
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " +real_tim_timstamp+" Recruitment MTD for Zone "+userzone+" is Rs." +recruitment_mtd+" Recruitment YTD for Zone "+userzone+" is Rs." +recruitment_ytd+
								" If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "As of " +real_tim_timstamp+" Recruitment MTD for Region "+user_region+" is Rs. " +recruitment_mtd+" Recruitment YTD for Region "+user_region+" is Rs. " +recruitment_ytd;
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "As of " +real_tim_timstamp+" Recruitment " +period+ " for "+channel+" is Rs." +recruitment_ytd+
									" If you want to see the zone/region wise business numbers, please specify";
						}else
						{
							finalresponse= "As of " +real_tim_timstamp+" Recruitment " +period+ " for "+channel+" is Rs." +recruitment_mtd+
									" If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for "+channel+" is Rs. "+recruitment_ytd+
									" If you want to see the region wise business numbers, please specify";
						}else
						{
							finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for "+channel+" is Rs. "+recruitment_mtd+
									" If you want to see the region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for Region "+user_region+" is Rs "+recruitment_ytd;
						}else
						{
							finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for Region "+user_region+" is Rs "+recruitment_mtd;	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for MLI is Rs. " +recruitment_ytd+
									" If you want to see the channel wise business numbers, please specify";
						}else
						{
							finalresponse= "As of " +real_tim_timstamp+" Recruitment "+period+" for MLI is Rs. " +recruitment_mtd+
									" If you want to see the channel wise business numbers, please specify";	
						}
					}
				}
				else if("NB.Recruitment%".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI recruitment acheivement MTD: "+achiev_mtd_recruitment+" % YTD "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
								" If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" recruitment acheivement MTD: "+achiev_mtd_recruitment+" % YTD "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
								" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone " +userzone+" recruitment acheivement MTD: "+achiev_mtd_recruitment+"% YTD "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
								" If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" recruitment acheivement MTD: "+achiev_mtd_recruitment+" % YTD "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
								" If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= channel+" channel recruitment acheivement "+period+" : "+achiev_ytd_recruitment+" % till "+real_tim_timstamp+
									" If you want to see the zone/region wise business numbers please specify";
						}else
						{
							finalresponse= channel+" channel recruitment acheivement "+period+" : "+achiev_mtd_recruitment+" % till "+real_tim_timstamp+
									" If you want to see the zone/region wise business numbers please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "Zone "+userzone+" recruitment acheivement " +period+" : "+achiev_ytd_recruitment+" % till " +real_tim_timstamp+
									" If you want to see the region wise business numbers please specify";
						}
						else
						{
							finalresponse= "Zone "+userzone+" recruitment acheivement " +period+" : "+achiev_mtd_recruitment+" % till " +real_tim_timstamp+
									" If you want to see the region wise business numbers please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= "Region "+user_region+" recruitment acheivement " +period+" : "+achiev_ytd_recruitment+" % till " +real_tim_timstamp+
									" If you want to see the region wise business numbers please specify";
						}else
						{
							finalresponse= "Region "+user_region+" recruitment acheivement " +period+" : "+achiev_mtd_recruitment+" % till " +real_tim_timstamp+
									" If you want to see the region wise business numbers please specify";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "MLI recruitment acheivement " +period+" : "+achiev_ytd_recruitment+" % till " +real_tim_timstamp+
									" If you want to see the channel wise business numbers please specify";
						}else
						{
							finalresponse= "MLI recruitment acheivement " +period+" : "+achiev_mtd_recruitment+" % till " +real_tim_timstamp+
									" If you want to see the channel wise business numbers please specify";	
						}
					}
				}
				else if("NB.GROWTHAPLADGIFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
								rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								rpev_applied_adj_ifyp_mtd+" Cr  of Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
								+ " If you want to see the Channell wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
								rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+ userzone+" has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
								rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+ user_region+ " has witnessed applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
								rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								rpev_applied_adj_ifyp_mtd+" Cr of Applied Adj IFYP as compared to " + applied_adj_ifyp_mtd+ " Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region " +user_region+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Region " +user_region+" has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "MLI has witnessed Applied Business growth of " +grth_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									rpev_applied_adj_ifyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_ytd+ " Cr today "
									+ " If you want to see the Channel wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "MLI has witnessed Applied Business growth of " +grth_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									rpev_applied_adj_ifyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_adj_ifyp_mtd+ " Cr today "
									+ " If you want to see the Channel wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("NB.GROWTHAPLAFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed applied Business growth of "+grth_applied_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_applied_afyp_ytd+ " Cr of Applied AFYP as compared to " +applied_afyp_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_applied_afyp_mtd+" Cr  of Applied AFYP as compared to " +applied_afyp_mtd+ " Cr today."
								+ " If you want to see the Channel wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed applied Business growth of "+grth_applied_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_applied_afyp_ytd+ " Cr of Applied AFYP as compared to " +applied_afyp_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_applied_afyp_mtd+" Cr  of Applied AFYP as compared to " +applied_afyp_mtd+ " Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+ userzone+" has witnessed applied Business growth of "+grth_applied_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_applied_afyp_ytd+ " Cr of Applied AFYP as compared to " +applied_afyp_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_applied_afyp_mtd+" Cr  of Applied AFYP as compared to " +applied_afyp_mtd+ " Cr today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+ user_region+ " has witnessed applied Business growth of "+grth_applied_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_applied_afyp_ytd+ " Cr of Applied AFYP as compared to " +applied_afyp_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_applied_afyp_mtd+" Cr  of Applied AFYP as compared to " +applied_afyp_mtd+ " Cr today.";

					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	
						if("YTD".equalsIgnoreCase(period)){
							finalresponse= channel+" has witnessed applied Business growth of " +grth_applied_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_afyp_ytd+ " Cr of Applied AFYP as compared to " +applied_afyp_ytd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed applied Business growth of " +grth_applied_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_afyp_mtd+ " Cr of Applied AFYP as compared to " +applied_afyp_mtd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed Applied Business growth of " +grth_applied_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_afyp_ytd+ " Cr of Applied AFYP as compared to " +applied_afyp_ytd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed Applied Business growth of " +grth_applied_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_afyp_mtd+ " Cr of Applied AFYP as compared to " +applied_afyp_mtd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region " +user_region+" has witnessed Applied Business growth of " +grth_applied_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_afyp_ytd+ " Cr of Applied AFYP as compared to " +applied_afyp_ytd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Region " +user_region+" has witnessed Applied Business growth of " +grth_applied_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_afyp_mtd+ " Cr of Applied AFYP as compared to " +applied_afyp_mtd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "MLI has witnessed Applied Business growth of " +grth_applied_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_afyp_ytd+ " Cr of Applied Adj IFYP as compared to " +applied_afyp_ytd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "MLI has witnessed Applied Business growth of " +grth_applied_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_afyp_mtd+ " Cr of Applied Adj IFYP as compared to " +applied_afyp_mtd+ " Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("NB.GROWTHAPLCASES".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed applied Business growth of "+grth_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_applied_cases_ytd+ " Cr of Applied Cases as compared to " +applied_cases_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_applied_cases_mtd+" of Applied Cases as compared to " +applied_cases_mtd+ " Cr today."
								+ " If you want to see the Channel wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed applied Business growth of "+grth_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_applied_cases_ytd+ " Cr of Applied Cases as compared to " +applied_cases_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_applied_cases_mtd+" of Applied Cases as compared to " +applied_cases_mtd+ " Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+ userzone+" "+channel+" has witnessed applied Business growth of "+grth_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_applied_cases_ytd+ " Cr of Applied Cases as compared to " +applied_cases_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_applied_cases_mtd+" of Applied Cases as compared to " +applied_cases_mtd+ " Cr today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+ user_region+" "+channel+ " has witnessed applied Business growth of "+grth_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_applied_cases_ytd+ " Cr of Applied Cases as compared to " +applied_cases_ytd+ " Cr today MTD business Growth of "+ 
								grth_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_applied_cases_mtd+" of Applied Cases as compared to " +applied_cases_mtd+ " Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";					            
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= channel+" has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Zone "+userzone+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region " +user_region+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Region " +user_region+" "+channel+" has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "MLI has witnessed applied Business growth of " +grth_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_cases_ytd+ " of Applied Cases as compared to " +applied_cases_ytd+ " today "
									+ " If you want to see the Channel wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "MLI has witnessed applied Business growth of " +grth_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_applied_cases_mtd+ " of Applied Cases as compared to " +applied_cases_mtd+ " today "
									+ " If you want to see the Channel wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("NB.GROWTHCASESIZE".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed case size growth of "+grth_case_size_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_case_size_afyp_ytd+ "K of Case Size as compared to " +case_size_afyp_ytd_growth+ "K today MTD business Growth of "+ 
								grth_case_size_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_case_size_afyp_mtd+" of Case Size as compared to " +case_size_afyp_mtd+ "K today."
								+ " If you want to see the Channel wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed case size growth of "+grth_case_size_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_case_size_afyp_ytd+ "K of Case Size as compared to " +case_size_afyp_ytd_growth+ "K today MTD business Growth of "+ 
								grth_case_size_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_case_size_afyp_mtd+" of Case Size as compared to " +case_size_afyp_mtd+ "K today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed case size growth of "+grth_case_size_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_case_size_afyp_ytd+ "K of Case Size as compared to " +case_size_afyp_ytd_growth+ "K today MTD business Growth of "+ 
								grth_case_size_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_case_size_afyp_mtd+" of Case Size as compared to " +case_size_afyp_mtd+ "K today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region"+user_region+" MLI has witnessed case size growth of "+grth_case_size_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_case_size_afyp_ytd+ "K of Case Size as compared to " +case_size_afyp_ytd_growth+ "K today MTD business Growth of "+ 
								grth_case_size_afyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_case_size_afyp_mtd+" of Case Size as compared to " +case_size_afyp_mtd+ "K today.";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed case size growth of " +grth_case_size_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_case_size_afyp_ytd+ " of cases size as compared to " +case_size_afyp_ytd_growth+ "K today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed case size growth of " +grth_case_size_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_case_size_afyp_mtd+ " of cases size as compared to " +case_size_afyp_mtd_growth+ "K today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed case size growth of " +grth_case_size_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_case_size_afyp_ytd+ " K of cases size as compared to " +case_size_afyp_ytd_growth+ "K today "
									+ " If you want to see the region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed case size growth of " +grth_case_size_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_case_size_afyp_mtd+ " K of cases size as compared to " +case_size_afyp_mtd_growth+ "K today "
									+ " If you want to see the region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region"+user_region+" has witnessed case size growth of " +grth_case_size_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_case_size_afyp_ytd+ " of cases size as compared to " +case_size_afyp_ytd_growth+ "K today ";
						}else
						{
							finalresponse= "Region"+user_region+" has witnessed case size growth of " +grth_case_size_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_case_size_afyp_mtd+ " of cases size as compared to " +case_size_afyp_mtd_growth+ "K today ";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed case size growth of " +grth_case_size_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_case_size_afyp_ytd+ " of cases size as compared to " +case_size_afyp_ytd_growth+ "K today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed case size growth of " +grth_case_size_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_case_size_afyp_mtd+ " of cases size as compared to " +case_size_afyp_mtd_growth+ "K today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("NB.GROWTHLPCADJMFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed paid business growth of "+grth_lpc_paid_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_paid_adj_mfyp_ytd+ "Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_paid_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_paid_adj_mfyp_mtd+"Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today."
								+ " If you want to see the Channel wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed paid business growth of "+grth_lpc_paid_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_paid_adj_mfyp_ytd+ "Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_paid_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_paid_adj_mfyp_mtd+"Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed paid business growth of "+grth_lpc_paid_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_paid_adj_mfyp_ytd+ "Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_paid_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_paid_adj_mfyp_mtd+"Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" has witnessed paid business growth of "+grth_lpc_paid_adj_mfyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_paid_adj_mfyp_ytd+ "Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_paid_adj_mfyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_paid_adj_mfyp_mtd+"Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed paid business growth of " +grth_lpc_paid_adj_mfyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_ytd+ " of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed paid business growth of " +grth_lpc_paid_adj_mfyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_mtd+ " of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed paid business growth of " +grth_lpc_paid_adj_mfyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_ytd+ " of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed paid business growth of " +grth_lpc_paid_adj_mfyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_mtd+ " of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region "+channel+" has witnessed paid business growth of " +grth_lpc_paid_adj_mfyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_ytd+ "Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Region "+channel+" has witnessed paid business growth of " +grth_lpc_paid_adj_mfyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_mtd+ "Cr of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed paid business growth of " +grth_lpc_paid_adj_mfyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_ytd+ " of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed paid business growth of " +grth_lpc_paid_adj_mfyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_mtd+ " of LPC paid Adj MFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("NB.GROWTHLPCAPLADJIFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
								+ " If you want to see the Channel wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of "+grth_lpc_applied_adj_ifyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_adj_ifyp_ytd+ "Cr LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_adj_ifyp_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_applied_adj_ifyp_mtd+"Cr of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_applied_adj_ifyp_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_adj_ifyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_adj_ifyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_adj_ifyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_paid_adj_mfyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_ytd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_paid_adj_mfyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_adj_mfyp_mtd+ " of LPC applied Adj IFYP as compared to " +lpc_paid_adj_mfyp_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("NB.GROWTHLPCAPLAFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed LPC applied business growth of "+grth_lpc_applied_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_afyp_ytd+ "Cr LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_afyp_mtd+ " % on YTD basis, last year same month we have clocked "+
								prev_lpc_applied_afyp_mtd+"Cr of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed LPC applied business growth of "+grth_lpc_applied_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_afyp_ytd+ "Cr LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_afyp_mtd+ " % on YTD basis, last year same month we have clocked "+
								prev_lpc_applied_afyp_mtd+"Cr of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of "+grth_lpc_applied_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_afyp_ytd+ "Cr LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_afyp_mtd+ " % on YTD basis, last year same month we have clocked "+
								prev_lpc_applied_afyp_mtd+"Cr of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" has witnessed LPC applied business growth of "+grth_lpc_applied_afyp_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_afyp_ytd+ "Cr LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_afyp_mtd+ " % on YTD basis, last year same month we have clocked "+
								prev_lpc_applied_afyp_mtd+"Cr of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_afyp_ytd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_ytd+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_afyp_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_afyp_mtd+ " of LPC applied AFYP as compared to " +curr_lpc_applied_afyp_mtd+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("NB.GROWTHLPCAPLCASES".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" MLI has witnessed LPC applied business growth of "+grth_lpc_applied_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_applied_cases_ytd+ " LPC applied cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_applied_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_applied_cases_mtd+" of LPC applied cases as compared to " +lpc_applied_cases_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Zone "+userzone+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Region "+channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
					else
					{
						if("YTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_cases_ytd+ " of LPC applied Cases as compared to " +lpc_applied_cases_ytd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= channel+" has witnessed LPC applied business growth of " +grth_lpc_applied_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_applied_cases_mtd+ " of LPC applied Cases as compared to " +lpc_applied_cases_mtd_growth+ "Cr today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";	
						}
					}
				}
				else if("GROWTHLPCPAIDCASES".equalsIgnoreCase(action))
				{        
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed LPC paid cases growth of "+grth_lpc_paid_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_paid_cases_ytd+ " LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_paid_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_paid_cases_mtd+" of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed LPC paid cases growth of "+grth_lpc_paid_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_paid_cases_ytd+ " LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_paid_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_paid_cases_mtd+" of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed LPC paid cases growth of "+grth_lpc_paid_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_paid_cases_ytd+ " LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_paid_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_paid_cases_mtd+" of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" has witnessed LPC paid cases growth of "+grth_lpc_paid_cases_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_lpc_paid_cases_ytd+ " LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ "Cr today MTD business Growth of "+ 
								grth_lpc_paid_cases_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_lpc_paid_cases_mtd+" of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";

						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(channel))
						{
							finalresponse= "Zone "+userzone+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Zone "+userzone+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region "+channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Region "+channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
					else
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_cases_mtd+ " of LPC paid cases as compared to " +lpc_paid_cases_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= channel+" has witnessed LPC paid cases growth of " +grth_lpc_paid_cases_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_lpc_paid_cases_ytd+ " of LPC paid cases as compared to " +lpc_paid_cases_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}

					}
				}
				else if("NB.GROWTHPAIDCASES".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
								grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
								grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
								grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" has witnessed paid cases growth of "+grth_lst_yr_inforced_cnt_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today MTD business Growth of "+ 
								grth_lst_yr_inforced_cnt_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_year_inforced_cnt_mtd+" of paid cases as compared to " +mtd_inforced_cnt+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}

					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Zone "+userzone+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}

					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region "+channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= "Region "+channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}

					}
					else
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_year_inforced_cnt_mtd+ " of paid cases as compared to " +mtd_inforced_cnt+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= channel+" has witnessed paid cases growth of " +grth_lst_yr_inforced_cnt_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_year_inforced_cnt_ytd+ " of paid cases as compared to " +ytd_inforced_cnt+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
				}
				else if("NB.GROWTHRECRUITMENT".equalsIgnoreCase(action))
				{		 
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
								grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
								grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
								grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
								grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today.";

					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";

						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region "+channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Region "+channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
					else
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else{
							finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
				}
				else if("NB.MODEMIX".equalsIgnoreCase(action))
				{		 
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "MLI has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
								grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= channel+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
								grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
								+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Zone "+userzone+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
								grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today."
								+ " If you want to see region wise business numbers, please specIfy the same";
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= "Region "+user_region+" has witnessed recruitment growth of "+grth_recruitment_ytd+" % on YTD basis, last year same time we had clocked "+
								prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today MTD business Growth of "+ 
								grth_recruitment_mtd+ " % on MTD basis, last year same month we have clocked "+
								prev_recruitment_mtd+" recruitments as compared to " +recruitment_mtd_growth+ " today.";

					}else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{	
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
						else
						{
							finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= "Zone "+userzone+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";

						}else
						{
							finalresponse= "Zone "+userzone+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= "Region "+channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else
						{
							finalresponse= "Region "+channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
					else
					{
						if("MTD".equalsIgnoreCase(period))
						{
							finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_mtd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_mtd+ " recruitments as compared to " +recruitment_mtd_growth+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}else{
							finalresponse= channel+" has witnessed recruitment growth of " +grth_recruitment_ytd+" % on "+period+" basis, last year same time we had clocked "+
									prev_recruitment_ytd+ " recruitments as compared to " +recruitment_ytd+ " today "
									+ " If you want to see the Zone/region wise business numbers, please specIfy the same";
						}
					}
				}
				else if("NB.Achievement".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse= " MLI MTD acheivement for Adj MFYP is "+achiev_mtd_adj_mfyp+" % , Paid case acheivement is "+achiev_mtd_paid_case+" % MTD acheivement for Adj MFYP is "+
						mtd_adj_afyp_act+" Cr against "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+" till "+real_tim_timstamp+" At YTD level MLI has achieved "+
						achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFPY & "+achiev_ytd_paid_case+" % for Paid Cases , YTD acheivement for Adj MFYP is "+ytd_adj_mfyp_act+" Cr against "+ytd_adj_mfyp_pln+
						" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+" till "+real_tim_timstamp+" If you want to see the channel wise business numbers, please specify ";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="At MTD level "+channel+" has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+"At YTD level "+channel+" has achieved "+achiev_ytd_adj_mfyp+
						" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Our YTD plan is "+ytd_adj_mfyp_pln+
						" for Adj MFYP and till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
						" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="At MTD level "+userzone+" zone has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+"At YTD level "+userzone+" zone has achieved "+achiev_ytd_adj_mfyp+
						" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Our YTD plan is "+ytd_adj_mfyp_pln+
						" for Adj MFYP and till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
						" If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="At MTD level "+user_region+" region has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+"At YTD level "+user_region+" region has achieved "+achiev_ytd_adj_mfyp+
						" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Our YTD plan is "+ytd_adj_mfyp_pln+
						" for Adj MFYP and till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
						" If you want to see the zone/region wise business numbers, please specify";	
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="At MTD level "+channel+" has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+
						" If you want to see the zone/region wise business numbers, please specify";
					}
						else
						{
							finalresponse="At YTD level "+channel+" has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+ytd_adj_mfyp_pln+
							" till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
							" If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="At MTD level "+userzone+" zone has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+
						" If you want to see the region wise business numbers, please specify";	
					}
						else
						{
							finalresponse="At YTD level "+userzone+" zone has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+ytd_adj_mfyp_pln+
									" till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
									" If you want to see the region wise business numbers, please specify";		
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="At MTD level "+user_region+" region has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+
						"";
					}
						else
						{
							finalresponse="At YTD level "+user_region+" region has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+ytd_adj_mfyp_pln+
							" till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
							"";	
						}
					}
					else
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="At MTD level MLI has achieved "+achiev_mtd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_mtd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+mtd_adj_afyp_act+
						" till "+real_tim_timstamp+" We have achieved "+mtd_adj_afyp_pln+" Cr & "+mtd_paid_case_act+" paid cases against "+mtd_paid_case_pln+
						" If you want to see the channel wise business numbers, please specify";
					}
						else
						{
							finalresponse="At YTD level MLI has achieved "+achiev_ytd_adj_mfyp+" % of Management Plan for Adj MFYP & "+achiev_ytd_paid_case+" % of Paid Cases, Monthly plan for Adj MFYP is "+ytd_adj_mfyp_pln+
							" till "+real_tim_timstamp+" We have achieved "+ytd_adj_mfyp_act+" Cr & "+ytd_paid_case_act+" paid cases against "+ytd_paid_case_pln+
							" If you want to see the channel wise business numbers, please specify";	
						}
					}
				}
				else if("NB.ProductMix".equalsIgnoreCase(action))
				{
							if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
					      finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"%, protection: "+
					     protec_penet_mtd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
					     "%. YTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
					     protec_penet_ytd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_ytd_pol_cnt+"% , par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
					     " If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for" +channel+ "is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"%, protection: "+
							     protec_penet_mtd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_mtd_pol_cnt+"%, par: "+par_penet_mtd_pol_cnt+"% , Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
							     "%. YTD product mix ratio on Adj MFYP for "+channel+" is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
							     protec_penet_ytd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_ytd_pol_cnt+"%, par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
						         " If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for" +userzone+ " zone is ULIP: "+ul_penet_mtd_adj_mfyp+"%, Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"%, protection: "+
							     protec_penet_mtd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_mtd_pol_cnt+"%, par: "+par_penet_mtd_pol_cnt+"%, Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
							     "%. YTD product mix ratio on Adj MFYP for "+userzone+" zone is ULIP: "+ul_penet_ytd_adj_mfyp+"%, Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"%, protection: "+
							     protec_penet_ytd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_ytd_pol_cnt+"%, par: "+par_penet_ytd_pol_cnt+"%, Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
						         " If you want to see the region wise business numbers, please specify";	
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for" +user_region+ " region is ULIP: "+ul_penet_mtd_adj_mfyp+"%, Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"% , protection: "+
							     protec_penet_mtd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_mtd_pol_cnt+"% , par: "+par_penet_mtd_pol_cnt+"%, Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
							     "%. YTD product mix ratio on Adj MFYP for "+user_region+" region is ULIP: "+ul_penet_ytd_adj_mfyp+"%, Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
							     protec_penet_ytd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_ytd_pol_cnt+"%, par: "+par_penet_ytd_pol_cnt+"%, Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+"";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="As of "+real_tim_timstamp+" "+period+" product mix ratio on Adj MFYP for" +channel+ "is ULIP: "+ul_penet_mtd_adj_mfyp+"% , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
							     protec_penet_mtd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_mtd_pol_cnt+"%, par: "+par_penet_mtd_pol_cnt+"%, Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
							     "% If you want to see the zone/region wise business numbers, please specify";	
					}
						else
						{
							finalresponse="As of "+real_tim_timstamp+" "+period+" product mix ratio on Adj MFYP for" +channel+ "is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
									protec_penet_ytd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_ytd_pol_cnt+"%, par: "+par_penet_ytd_pol_cnt+"%, Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
								     "% If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="As of "+real_tim_timstamp+" "+period+" product mix ratio on Adj MFYP for" +userzone+ " zone is ULIP: "+ul_penet_mtd_adj_mfyp+"%, Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
							     protec_penet_mtd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_mtd_pol_cnt+"%, par: "+par_penet_mtd_pol_cnt+"%, Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
					             "% If you want to see the region wise business numbers, please specify";
						}
						else
						{
							finalresponse="As of "+real_tim_timstamp+" "+period+" product mix ratio on Adj MFYP for" +userzone+ " zone is ULIP: "+ul_penet_ytd_adj_mfyp+"%, Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"%, protection: "+
									protec_penet_ytd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_ytd_pol_cnt+"%, par: "+par_penet_ytd_pol_cnt+"%, Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
						             "% If you want to see the region wise business numbers, please specify";	
						}
				}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="As of "+real_tim_timstamp+" "+period+" product mix ratio on Adj MFYP for" +user_region+ " region is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"%, protection: "+
							     protec_penet_mtd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+"%, Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+" %";
				}
						else
						{
							finalresponse="As of "+real_tim_timstamp+" "+period+" product mix ratio on Adj MFYP for" +user_region+ " region is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"% , protection: "+
									protec_penet_ytd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+"%, Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+"%";	
						}
					}
					else
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="As of "+real_tim_timstamp+" "+period+" product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+"% & Non-Par: "+nonpar_penet_mtd_adj_mfyp+"%, protection: "+
							     protec_penet_mtd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_mtd_pol_cnt+"%, par: "+par_penet_mtd_pol_cnt+"%, Non-par: "+nonpar_penet_mtd_pol_cnt+"% & protection: "+protec_penet_mtd_pol_cnt+
							     "% If you want to see the channel wise business numbers, please specify";
					}
						else
						{
							finalresponse="As of "+real_tim_timstamp+" "+period+" product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_ytd_adj_mfyp+"% , Par: "+par_penet_ytd_adj_mfyp+"% & Non-Par: "+nonpar_penet_ytd_adj_mfyp+"%, protection: "+
									protec_penet_ytd_adj_mfyp+"%. paid case ratio: ULIP: "+ul_penet_ytd_pol_cnt+"%, par: "+par_penet_ytd_pol_cnt+"% , Non-par: "+nonpar_penet_ytd_pol_cnt+"% & protection: "+protec_penet_ytd_pol_cnt+
								     "% If you want to see the channel wise business numbers, please specify";	
						}
					}
				}
				else if("NB.ProductMixADJMFYP".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
					      finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
					       protec_penet_mtd_adj_mfyp+ " %. YTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+" % , protection: "+
					       protec_penet_ytd_adj_mfyp+" %. If you want to see the channel wise business numbers, please specify";	
				    }
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
					      finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +channel+ " is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
					       protec_penet_mtd_adj_mfyp+ " %. YTD product mix ratio on Adj MFYP for " +channel+ " is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+" % , protection: "+
					       protec_penet_ytd_adj_mfyp+" %. If you want to see the zone/region wise business numbers, please specify";	
				    }
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
					      finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +userzone+ " zone is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
					       protec_penet_mtd_adj_mfyp+ " %. YTD product mix ratio on Adj MFYP for " +userzone+ " zone is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+" % , protection: "+
					       protec_penet_ytd_adj_mfyp+" %. If you want to see the region wise business numbers, please specify";	
				    }
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
					      finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_region+ " region is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
					       protec_penet_mtd_adj_mfyp+ " %. YTD product mix ratio on Adj MFYP for " +user_region+ " region is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+" % , protection: "+
					       protec_penet_ytd_adj_mfyp+" %. If you want to see the channel wise business numbers, please specify";	
				    }
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
					      finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +channel+ " is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
					       protec_penet_mtd_adj_mfyp+ " %. If you want to see the zone/region wise business numbers, please specify"; 	
				    }
						else
						{
							finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +channel+ " is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+" % , protection: "+
								       protec_penet_ytd_adj_mfyp+ " %. If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +userzone+ " zone is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
							       protec_penet_mtd_adj_mfyp+ " %. If you want to see the region wise business numbers, please specify";	
					}
						else
						{
							finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +userzone+ " zone is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+" % , protection: "+
								       protec_penet_ytd_adj_mfyp+ " %. If you want to see the region wise business numbers, please specify";		
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for " +user_region+ " region is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
							       protec_penet_mtd_adj_mfyp+ " %.";	
					}
						else
						{
							finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for " +user_region+ " region is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+" % , protection: "+
								       protec_penet_ytd_adj_mfyp+ " %.";		
						}
					}
					else
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_mtd_adj_mfyp+" % , Par: "+par_penet_mtd_adj_mfyp+" % & Non-Par: "+nonpar_penet_mtd_adj_mfyp+" % , protection: "+
							       protec_penet_mtd_adj_mfyp+ " %. If you want to see the channel wise business numbers, please specify";	
					}
						else
						{
							finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Adj MFYP for MLI is ULIP: "+ul_penet_ytd_adj_mfyp+" % , Par: "+par_penet_ytd_adj_mfyp+" % & Non-Par: "+nonpar_penet_ytd_adj_mfyp+" % , protection: "+
								       protec_penet_ytd_adj_mfyp+ " %. If you want to see the channel wise business numbers, please specify";		
						}
					}
				}
				else if("NB.Productmixpaidcase".equalsIgnoreCase(action))
				{
					if("MLI".equalsIgnoreCase(channel))
					{channel="";}
					if("Monthly".equalsIgnoreCase(period))
					{period="";}
					if("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						 finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for MLI is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
								       " %. YTD product mix ratio on Paid Cases for MLI is ULIP:  "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
									   " If you want to see the channel wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						 finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+channel+" is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
								       " %. YTD product mix ratio on Paid Cases for "+channel+" is ULIP:  "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
									   " %. If you want to see the zone/region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						 finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+userzone+" zone is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
								       " %. YTD product mix ratio on Paid Cases for "+userzone+" zone is ULIP:  "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
									   " %. If you want to see the region wise business numbers, please specify";
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && "".equalsIgnoreCase(period))
					{
						 finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+user_region+" region is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
								       " %. YTD product mix ratio on Paid Cases for "+user_region+" region is ULIP:  "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
									   " % ";
					}
					else if(!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						 finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+channel+" is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
								       " %. If you want to see the zone/region wise business numbers, please specify";
					}
						else
						{
							finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for "+channel+" is ULIP: "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
								       " %. If you want to see the zone/region wise business numbers, please specify";	
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						 finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+userzone+" zone is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
								       " %. If you want to see the region wise business numbers, please specify";
					}
						else
						{
							 finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for "+userzone+" zone is ULIP: "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
								       " %. If you want to see the region wise business numbers, please specify";
						}
					}
					else if(!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region) && !"".equalsIgnoreCase(period))
					{
						if("MTD".equalsIgnoreCase(period))
						{
						 finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for "+user_region+" region is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
								       " %.";
					}
						else
						{
							 finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for "+user_region+" region is ULIP: "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
								       " %.";	
						}
					}
					else
					{
						if("MTD".equalsIgnoreCase(period))
						{
						finalresponse="As of "+real_tim_timstamp+" MTD product mix ratio on Paid Cases for MLI is ULIP: "+ul_penet_mtd_pol_cnt+" % , par: "+par_penet_mtd_pol_cnt+" % , Non-par: "+nonpar_penet_mtd_pol_cnt+" % & protection: "+protec_penet_mtd_pol_cnt+
							       " %. If you want to see the channel wise business numbers, please specify";	
					    }
						else
						{
							finalresponse="As of "+real_tim_timstamp+" YTD product mix ratio on Paid Cases for MLI is ULIP: "+ul_penet_ytd_pol_cnt+" % , par: "+par_penet_ytd_pol_cnt+" % , Non-par: "+nonpar_penet_ytd_pol_cnt+" % & protection: "+protec_penet_ytd_pol_cnt+
								       " %. If you want to see the channel wise business numbers, please specify";		
						}
					}
					
				}
				else
				{
					finalresponse="Something gets wrong between action & channel";
				}
				speech=finalresponse;
				System.out.println("FinalResponse:- "+speech);
			}
			catch(Exception e)
			{
			   System.out.println("Something went wrong in Bot Logic");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception In Outer Catch"+ex);
		}
		
		InnerButton innerButton1 = new InnerButton();
		innerButton1.setText("Axis");
		innerButton1.setPostback("PostBack");
				//For Second button
		InnerButton innerButton2 = new InnerButton();
		innerButton2.setText("Agency");
		innerButton2.setPostback("CallBack");
				
		InnerButton innerButton3 = new InnerButton();
		innerButton3.setText("Banca");
		innerButton3.setPostback("PostBack");
				
		InnerButton innerButton4 = new InnerButton();
		innerButton4.setText("CAT");
		innerButton4.setPostback("PostBack");
				
		innerbuttonlist.add(innerButton1);
		innerbuttonlist.add(innerButton2);
		innerbuttonlist.add(innerButton3);
		innerbuttonlist.add(innerButton4);
		fb.setButtons(innerbuttonlist);
		fb.setTitle("MLIChatBot");
		fb.setPlatform("API.AI");
		fb.setType("Chatbot");
		fb.setImageUrl("BOT");
		innerData.setFacebook(fb);
		WebhookResponse responseObj = new WebhookResponse(speech, speech, innerData);
		System.out.println("End : Response"+ speech);
		return responseObj;
		
	}
public String convertToCamelCase(String channel){

		final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the character following
		// to be capitalized

		StringBuilder sb = new StringBuilder();
		boolean capNext = true;

		for (char c : channel.toCharArray()) {
			c = (capNext)
					? Character.toUpperCase(c)
							: Character.toLowerCase(c);
					sb.append(c);
					capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed

		}
		return sb.toString();
	}
}
