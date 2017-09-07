package service;
import java.util.ArrayList; import java.util.List; import java.io.BufferedReader; import java.io.InputStreamReader;
import java.io.OutputStreamWriter; import java.net.HttpURLConnection; import java.net.InetSocketAddress; import java.net.Proxy;
import java.net.URL; import java.text.DecimalFormat; import java.time.LocalDateTime; import java.time.format.DateTimeFormatter;
import java.util.Date; import java.util.ResourceBundle; import javax.net.ssl.HttpsURLConnection; 
import org.springframework.stereotype.Component; import org.json.JSONObject; import common.Commons; import common.XTrustProvider;
import hello.WebhookResponse; import hello.InnerButton; import hello.Facebook; import hello.InnerData; import messageimpl.Achievement;
import messageimpl.AdjMFYP;  import messageimpl.Applied; import messageimpl.AppliedADJIFYP; import messageimpl.Appliedcases;
import messageimpl.CaseSizePercentage; import messageimpl.Growth; import messageimpl.GrowthCaseSize; import messageimpl.GrowthLPCADJMFYP;
import messageimpl.GrowthLPCAPLADJIFYP; import messageimpl.GrowthLPCAPLAFYP; import messageimpl.GrowthLPCAPLCases;
import messageimpl.GrowthLPCPaidCases; import messageimpl.GrowthPaidcases; import messageimpl.GrowthRecruitment;
import messageimpl.Lpcaplcases; import messageimpl.Lpcappadjafyp; import messageimpl.LpcAPPAdJIFYP;
import messageimpl.LpcPAIDADJMFYP; import messageimpl.LpcPAIDCASES; import messageimpl.ModeMix; import messageimpl.NBAdjMFYP;
import messageimpl.NBApplied; import messageimpl.NBCaseSize; import messageimpl.NBGROWTHAPLADGIFYP; import messageimpl.NBGROWTHAPLAFYP;
import messageimpl.NBGROWTHAPLCASES; import messageimpl.NBGrowth; import messageimpl.NbAchievement; import messageimpl.Numbers;
import messageimpl.PaidCases; import messageimpl.Penetration; import messageimpl.ProductMix; import messageimpl.ProductMixADJMFYP;
import messageimpl.ProductMixPaidCase; import messageimpl.Recruitment; import messageimpl.Recruitmentpercentage;
import messageimpl.WIP; import messageimpl.WIPYES;

@Component
public class APIConsumerService {
	
	public WebhookResponse getWipDataAll(String action, String channel, String period, String productType, String planType,
				String user_ssoid, String user_sub_channel, String user_designation_desc, 
				String userzone, String user_region, String user_circle, String user_clusters, String user_go, 
				String user_cmo, String user_amo, String sessionId, String source)
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
						String  mtdAdjustMFYP="", dailyAppliedAFYP="", mtdAppliedAFYP="", ytd_applied_afyp="", mtd_adj_mfyp="",
						daily_adj_mfyp="", ytd_applied_adj_ifyp="", mtd_applied_adj_ifyp="",mtd_applied_count="",
						ytd_applied_count="", daily_applied_count="", daily_inforced_count="", dailyAdjustMFYP="", ytd_adj_mfyp="";
	//				        --------------------ModeMix----------------------------------------------
				                String annual_adj_mfyp_mtd="", semi_annual_adj_mfyp_mtd="", quarterly_adj_mfyp_mtd="", monthly_adj_mfyp_mtd="",single_adj_mfyp_mtd="",annual_adj_mfyp_ytd="",semi_annual_adj_mfyp_ytd="",
							    quarterly_adj_mfyp_ytd="", monthly_adj_mfyp_ytd="", single_adj_mfyp_ytd="";
				
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
				String [] regex = segment.split(",");
				for(int i=0; i<regex.length; i++)
				{
					String split= regex[i];
					String splitIntent=split.toUpperCase();
					switch(splitIntent)
					{
					case "PAID":
					{
						try	{
							dailyAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("daily_adj_mfyp").toString());
						}
						catch(Exception ex)	{}
						dailyAdjustMFYP =df.format(dailyAdjustMFYP1);
						try	{
							mtd_inforced_afyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("mtd_inforced_afyp").toString());
						}
						catch(Exception ex)	{}
						String mtd_inforced_afyp_enforce =df.format(mtd_inforced_afyp1);
						try
						{
							mtdAdjustMFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("paid").get("mtd_adj_mfyp").toString());
						}catch(Exception ex){}
						mtdAdjustMFYP = df.format(mtdAdjustMFYP1);
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
						ytd_adj_mfyp = df.format(ytd_adj_mfyp1);

					}
					break;
					case "APPLIED":
					{
						try{
							dailyAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("daily_applied_afyp").toString());
						}catch(Exception e){}
						dailyAppliedAFYP = df.format(dailyAppliedAFYP1);
						try{
							mtdAppliedAFYP1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("mtd_applied_afyp").toString());
						}catch(Exception e){}
						mtdAppliedAFYP = df.format(mtdAppliedAFYP1);
						try{
							ytd_applied_afyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("ytd_applied_afyp").toString());
						}catch(Exception e){}
						ytd_applied_afyp = df.format(ytd_applied_afyp1);
						try{
							mtd_adj_mfyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("mtd_adj_mfyp").toString());
						}catch(Exception e){}
						mtd_adj_mfyp = df.format(mtd_adj_mfyp1);
						try{
							daily_adj_mfyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("daily_adj_mfyp").toString());
						}catch(Exception e){}
						daily_adj_mfyp = df.format(daily_adj_mfyp1);
						try{
							ytd_applied_adj_ifyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("ytd_applied_adj_ifyp").toString());
						}catch(Exception e){}
						ytd_applied_adj_ifyp = df.format(ytd_applied_adj_ifyp1);
						try{
							mtd_applied_adj_ifyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("mtd_applied_adj_ifyp").toString());
						}catch(Exception e){}
						mtd_applied_adj_ifyp = df.format(mtd_applied_adj_ifyp1);
						try{
							mtd_applied_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("mtd_applied_count").toString());
						}catch(Exception e){}
						mtd_applied_count = df.format(mtd_applied_count1);
						try{
							ytd_applied_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("ytd_applied_count").toString());
						}catch(Exception e){}
						ytd_applied_count = df.format(ytd_applied_count1);
						try{
							daily_applied_count1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("daily_applied_count").toString());
						}catch(Exception e){}
						daily_applied_count = df.format(daily_applied_count1);
						try{
							daily_inforced_count_aaplied = Double.parseDouble(object.getJSONObject("payload").getJSONObject("applied").get("daily_inforced_count").toString());
						}catch(Exception e){}
						daily_inforced_count = df.format(daily_inforced_count_aaplied);
					}
					break;
					case "WIP":
					{
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
					}
					break;
					case "PENETRATION":
					{
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
					}
					break;
					case "GROWTH":
					{
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
					}
					break;
					case "ACHIEVEMENT":
					{
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
					}
					break;
					case "CASE_SIZE":
					{
						try{
							case_size_afyp_mtd = (object.getJSONObject("payload").getJSONObject("casesize").get("case_size_afyp_mtd").toString());
						}catch(Exception e){}
						try{
							case_size_afyp_ytd = (object.getJSONObject("payload").getJSONObject("casesize").get("case_size_afyp_ytd").toString());
						}catch(Exception e){}
					}
					break;
					case "LPC_PERFORMANCE":
					{
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
					}
					break;
					case "MODE_MIX":
					{
						try{
							annual_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("modemix").get("annual_adj_mfyp_mtd").toString());
						}catch(Exception e){}
						try{
							semi_annual_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("modemix").get("semi_annual_adj_mfyp_mtd").toString());
						}catch(Exception e){}
						try{
							quarterly_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("modemix").get("quarterly_adj_mfyp_mtd").toString());
						}catch(Exception e){}
						try{
							monthly_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("modemix").get("monthly_adj_mfyp_mtd").toString());
						}catch(Exception e){}
						try{
							single_adj_mfyp_mtd = (object.getJSONObject("payload").getJSONObject("modemix").get("single_adj_mfyp_mtd").toString());
						}catch(Exception e){}
						try{
							annual_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("modemix").get("annual_adj_mfyp_ytd").toString());
						}catch(Exception e){}
						try{
							semi_annual_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("modemix").get("semi_annual_adj_mfyp_ytd").toString());
						}catch(Exception e){}
						try{
							quarterly_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("modemix").get("quarterly_adj_mfyp_ytd").toString());
						}catch(Exception e){}
						try{
							monthly_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("modemix").get("monthly_adj_mfyp_ytd").toString());
						}catch(Exception e){}
						try{
							single_adj_mfyp_ytd = (object.getJSONObject("payload").getJSONObject("modemix").get("single_adj_mfyp_ytd").toString());
						}catch(Exception e){}
					}
					break;
					case "REC":
					{
						try{
							recruitment_mtd = (object.getJSONObject("payload").getJSONObject("rec").get("recruitment_mtd").toString());
						}catch(Exception e){}
						try{
							recruitment_ytd = (object.getJSONObject("payload").getJSONObject("rec").get("recruitment_ytd").toString());
						}catch(Exception e){}
					}
					break;
					default :
						finalresponse="No Action Matched";
					}
				}

				sum3 = sum+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
				sum = sum+wipAFYP+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
				String convertsum  =  df.format(sum);
				String convertsum3  =  df.format(sum3);
				sum4=sum4+ho_wip_count+go_wip_count+it_wip_count+fin_wip_count+misc_wip_count+welcome_wip_count;
				String convertsum4  =  df1.format(sum4);

				String intent=action.toUpperCase();

				switch(intent)
				{
				case "NUMBERS":
				{
					finalresponse=Numbers.numberIntent(period, source, msgChannel, mtdAdjustMFYP, mtdAppliedAFYP, 
							convertsum3, real_tim_timstamp, serviceChannel, user_sub_channel, dailyAdjustMFYP, 
							ytd_adj_mfyp, dailyAppliedAFYP, ytd_applied_afyp);
				}
				break;
				case "ADJMFYP":
				{
					finalresponse=AdjMFYP.adjMFYPIntent(period, source, msgChannel, mtdAdjustMFYP, real_tim_timstamp, 
							serviceChannel, dailyAdjustMFYP, ytd_adj_mfyp);
				}
				break;
				case "WIP":
				{
					finalresponse=WIP.wipIntent(serviceChannel, msgChannel, convertsum4, convertsum3);
				}
				break;
				case "WIP.YES":
				{
					finalresponse=WIPYES.wipyesIntent(serviceChannel, msgChannel, convertsum3, convertsum4, hoWIPAFYP, 
							ho_wip_count, goWIPAFYP, go_wip_count, itWIPAFYP, it_wip_count, finWIPAFYP, fin_wip_count,	
							miscWIPAFYP, misc_wip_count, welcomeWIPAFYP, welcome_wip_count);
				}
				break;
				case "APPLIED":
				{
					finalresponse=Applied.appliedIntent(period, source, msgChannel, mtdAppliedAFYP, real_tim_timstamp, 
							serviceChannel, dailyAppliedAFYP, ytd_applied_afyp);
				}
				break;
				case "GROWTH":
				{
					finalresponse=Growth.growthIntent(source, msgChannel, grth_ovr_lst_yr_paid, adj_mfyp_sam_ytd_lst_yr, 
							ytd_inforced_adj_mfyp_achi);
				}
				break;
				case "ACHIEVEMENT":
				{
					finalresponse=Achievement.achievementIntent(source, msgChannel, achiev_mtd_adj_mfyp, mtd_adj_mfyp_pln,
							mtd_adj_mfyp_act, achiev_ytd_adj_mfyp, ytd_adj_mfyp_pln, ytd_adj_mfyp_act, real_tim_timstamp);
				}
				break;
				case "PENETRATION":
				{
					finalresponse=Penetration.penetrationIntent(period, productType, source, msgChannel, ul_penet_mtd_afyp, 
							mtd_inforced_afyp, ul_penet_mtd_pol_cnt, mtd_inforced_count, trad_penet_mtd_afyp, 
							trad_penet_mtd_pol_cnt, protec_penet_mtd_afyp,	protec_penet_mtd_pol_cnt, 
							ul_penet_ytd_afyp, ytd_inforced_afyp, ul_penet_ytd_pol_cnt,	ytd_inforced_count, trad_penet_ytd_afyp, 
							trad_penet_ytd_pol_cnt, protec_penet_ytd_afyp, protec_penet_ytd_pol_cnt);
				}
				break;
				/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
				//				Sprint-2.2
				/*-----*/
				case "NB.PAIDCASES":
				{
					finalresponse=PaidCases.paidCasesIntent(serviceChannel, period, user_circle, user_region, userzone, 
							real_tim_timstamp, mtd_inforced_count, ytd_inforced_count, daily_inforced_count1);
				}
				break;
				case "NB.APPLIED":
				{
					finalresponse=NBApplied.nbAppliedIntent(serviceChannel, period, user_circle, user_region, userzone,
							real_tim_timstamp, mtdAppliedAFYP, ytd_applied_afyp, daily_inforced_count);
				}
				break;
				case "NB.ADJMFYP":
				{
					finalresponse=NBAdjMFYP.nbAdjMFYPIntent(serviceChannel, period, user_circle, user_region, userzone, 
							real_tim_timstamp, mtdAdjustMFYP, ytd_adj_mfyp, mtd_adj_mfyp, daily_adj_mfyp);
				}
				break;
				case "NB.CASESIZE":
				{
					finalresponse=NBCaseSize.nbCaseSizeIntent(serviceChannel, period, user_circle, user_region, userzone, 
							real_tim_timstamp, case_size_afyp_mtd, case_size_afyp_ytd);
				}
				break;
				case "NB.APPLIEDADJIFYP":
				{
					finalresponse= AppliedADJIFYP.appliedAdjIfydIntent(serviceChannel, period, user_region, user_circle,
							userzone, real_tim_timstamp, mtd_applied_adj_ifyp, ytd_applied_adj_ifyp);
				}
				break;
				case "NB.APPLIEDCASES":
				{
					finalresponse= Appliedcases.appliedCasesIntent(serviceChannel, period, user_region, user_circle,
							userzone, real_tim_timstamp, mtd_applied_count, ytd_applied_count, daily_applied_count);
				}
				break;
				case "NB.LPCAPPADJIFYP":
				{
					finalresponse= LpcAPPAdJIFYP.lpcAppAdjIfypIntent(serviceChannel, period, user_region, user_circle,
							userzone, real_tim_timstamp, lpc_applied_adj_ifyp_mtd, lpc_applied_adj_ifyp_ytd);
				}
				break;
				case "NB.LPCAPPADJAFYP":
				{
					finalresponse=Lpcappadjafyp.lpcAppAdjAfypIntent(serviceChannel, period, user_region, user_circle,
							userzone, real_tim_timstamp, lpc_applied_afyp_mtd, lpc_applied_afyp_ytd);
				}
				break;
				case "NB.LPCAPLCASES":
				{
					finalresponse=Lpcaplcases.lpcAplCasesIntent(serviceChannel, period, user_region, user_circle,
							userzone, real_tim_timstamp, lpc_applied_cases_mtd, lpc_applied_cases_ytd);
				}
				break;
				case "NB.LPCPAIDADJMFYP":
				{
					finalresponse=LpcPAIDADJMFYP.lpcPaidAdjMfypIntent(serviceChannel, period, user_region, user_circle,
							userzone, real_tim_timstamp, lpc_paid_adj_mfyp_mtd, lpc_paid_adj_mfyp_ytd);
				}
				break;
				case "NB.LPCPAIDCASES":
				{
					finalresponse=LpcPAIDCASES.lpcPaidCasesIntent(serviceChannel, period, user_region, user_circle,
							userzone, real_tim_timstamp, lpc_paid_cases_mtd, lpc_paid_cases_ytd);
				}
				break;
				case "NB.CASESIZE%":
				{
					finalresponse=CaseSizePercentage.caseSizePercentageIntent(serviceChannel, period, user_region, 
							user_circle, userzone, real_tim_timstamp, achiev_mtd_case_active_mtd, achiev_mtd_case_active_ytd);
				}
				break;
				case "NB.GROWTH":
				{
					finalresponse=NBGrowth.growthIntent(serviceChannel, period, user_region, user_circle, 
							userzone, real_tim_timstamp, prev_year_adj_mfyp_ytd, grth_lst_yr_sm_adj_mfyp_ytd, 
							ytd_inforced_adj_mfyp, grth_lst_yr_sm_adj_mfyp_mtd, prev_year_adj_mfyp_mtd, 
							mtd_inforced_adj_mfyp);
				}
				break;
				case "NB.RECRUITMENT":
				{
					finalresponse=Recruitment.recruitmentIntent(serviceChannel, period, user_region, user_circle,
							userzone, real_tim_timstamp, recruitment_mtd, recruitment_ytd);
				}
				break;
				case "NB.RECRUITMENT%":
				{
					finalresponse=Recruitmentpercentage.recruitmentPercentageIntent(serviceChannel, period, user_region, 
							user_circle, userzone, real_tim_timstamp, achiev_mtd_recruitment, achiev_ytd_recruitment);
				}
				break;
				case "NB.GROWTHAPLADGIFYP":
				{
					finalresponse=NBGROWTHAPLADGIFYP.nbGROWTHAPLADGIFYPIntent(serviceChannel, period, user_circle, 
							user_region, userzone, grth_applied_adj_ifyp_ytd, rpev_applied_adj_ifyp_ytd, 
							applied_adj_ifyp_ytd, grth_applied_adj_ifyp_mtd, 
							rpev_applied_adj_ifyp_mtd, applied_adj_ifyp_mtd);
				}
				break;
				case "NB.GROWTHAPLAFYP":
				{
					finalresponse=NBGROWTHAPLAFYP.nbGROWTHAPLAFYPIntent(serviceChannel, period, user_circle, user_region, 
							userzone, prev_applied_afyp_ytd, grth_applied_afyp_ytd, grth_applied_afyp_mtd, 
							applied_afyp_ytd, prev_applied_afyp_mtd, applied_afyp_mtd);
				}
				break;
				case "NB.GROWTHAPLCASES":
				{
					finalresponse=NBGROWTHAPLCASES.nbGROWTHAPLCASESIntent(serviceChannel, period, user_circle, 
							user_region, userzone, prev_applied_cases_ytd, grth_applied_cases_ytd, grth_applied_cases_mtd, 
							applied_cases_ytd, prev_applied_cases_mtd, applied_cases_mtd);
				}
				break;
				case "NB.GROWTHCASESIZE":
				{
					finalresponse=GrowthCaseSize.growthCaseSizeIntent(serviceChannel, period, userzone, user_region, 
							real_tim_timstamp, user_circle, grth_case_size_afyp_ytd, prev_case_size_afyp_ytd,
							case_size_afyp_ytd_growth, grth_case_size_afyp_mtd, prev_case_size_afyp_mtd, 
							case_size_afyp_mtd, case_size_afyp_mtd_growth);
				}
				break;
				case "NB.GROWTHLPCADJMFYP":
				{
					finalresponse=GrowthLPCADJMFYP.growthLPCADJMFYPIntent(serviceChannel, period, userzone, user_region,
							real_tim_timstamp, user_circle,	grth_lpc_paid_adj_mfyp_ytd, prev_lpc_paid_adj_mfyp_ytd, 
							lpc_paid_adj_mfyp_ytd_growth, grth_lpc_paid_adj_mfyp_mtd, prev_lpc_paid_adj_mfyp_mtd, 
							lpc_paid_adj_mfyp_mtd_growth);
				}
				break;
				case "NB.GROWTHLPCAPLADJIFYP":
				{
					finalresponse=GrowthLPCAPLADJIFYP.growthLPCAPLADJIFYPIntent(serviceChannel, period, userzone, user_region,
							real_tim_timstamp, user_circle, grth_lpc_applied_adj_ifyp_ytd, prev_lpc_applied_adj_ifyp_ytd, 
							lpc_applied_adj_ifyp_ytd_growth, grth_lpc_applied_adj_ifyp_mtd,	prev_lpc_applied_adj_ifyp_mtd, 
							lpc_applied_adj_ifyp_mtd_growth, lpc_paid_adj_mfyp_ytd_growth, lpc_paid_adj_mfyp_mtd_growth, 
							grth_lpc_paid_adj_mfyp_ytd, prev_lpc_paid_adj_mfyp_ytd, prev_lpc_paid_adj_mfyp_mtd, 
							grth_lpc_paid_adj_mfyp_mtd);
				}
				break;
				case "NB.GROWTHLPCAPLAFYP":
				{
					finalresponse=GrowthLPCAPLAFYP.growthLPCAPLAFYPIntent(serviceChannel, period, userzone, user_region,
							real_tim_timstamp, user_circle, grth_lpc_applied_afyp_ytd, prev_lpc_applied_afyp_ytd, 
							curr_lpc_applied_afyp_ytd, grth_lpc_applied_afyp_mtd, prev_lpc_applied_afyp_mtd, 
							curr_lpc_applied_afyp_mtd);
				}
				break;
				case "NB.GROWTHLPCAPLCASES":
				{
					finalresponse=GrowthLPCAPLCases.growthLPCAPLCasesIntent(serviceChannel, period, userzone, user_region, 
							real_tim_timstamp, user_circle, grth_lpc_applied_cases_ytd, prev_lpc_applied_cases_ytd, 
							lpc_applied_cases_ytd_growth, grth_lpc_applied_cases_mtd, prev_lpc_applied_cases_mtd,
							lpc_applied_cases_mtd_growth);
				}
				break;
				case "GROWTHLPCPAIDCASES":
				{ 
					finalresponse=GrowthLPCPaidCases.growthLPCPaidcasesIntent(serviceChannel, period, userzone, user_region, 
							real_tim_timstamp, user_circle, grth_lpc_paid_cases_ytd, prev_lpc_paid_cases_ytd, 
							lpc_paid_cases_ytd_growth, grth_lpc_paid_cases_mtd, prev_lpc_paid_cases_mtd, 
							lpc_paid_cases_mtd_growth);
				}
				break;
				case "NB.GROWTHPAIDCASES":
				{
					finalresponse=GrowthPaidcases.growthPaidcasesIntent(serviceChannel, period, userzone, user_region, 
							real_tim_timstamp, grth_lst_yr_inforced_cnt_ytd, prev_year_inforced_cnt_ytd, 
							ytd_inforced_cnt, grth_lst_yr_inforced_cnt_mtd, prev_year_inforced_cnt_mtd, mtd_inforced_cnt,
							user_circle);
				}
				break;
				case "NB.GROWTHRECRUITMENT":
				{
					finalresponse=GrowthRecruitment.growthRecruitmentIntent(serviceChannel, period, userzone, user_region,
							real_tim_timstamp, grth_recruitment_ytd, prev_recruitment_ytd, recruitment_ytd_growth,
							grth_recruitment_mtd, prev_recruitment_mtd, recruitment_mtd_growth, recruitment_ytd_growth,
							user_circle);
				}
				break;
				case "NB.MODEMIX":
				{
					finalresponse=ModeMix.modeMixIntent(channel, period,  userzone,  user_region,  real_tim_timstamp,
							 annual_adj_mfyp_mtd, semi_annual_adj_mfyp_mtd,  quarterly_adj_mfyp_mtd, monthly_adj_mfyp_mtd,
							 single_adj_mfyp_mtd, annual_adj_mfyp_ytd,  semi_annual_adj_mfyp_ytd, quarterly_adj_mfyp_ytd,
							 monthly_adj_mfyp_ytd, single_adj_mfyp_ytd, user_circle);
				}
				break;
				case "NB.ACHIEVEMENT":

				{
					finalresponse=NbAchievement.achievementIntent(serviceChannel, period, userzone, user_region, 
							real_tim_timstamp, achiev_mtd_adj_mfyp, achiev_mtd_paid_case, mtd_adj_afyp_act,
							mtd_adj_afyp_pln, mtd_paid_case_act, mtd_paid_case_pln, achiev_ytd_adj_mfyp, 
							achiev_ytd_paid_case, ytd_adj_mfyp_act, ytd_adj_mfyp_pln, ytd_paid_case_act, 
							ytd_paid_case_pln, user_circle);

				}
				break;
				case "NB.PRODUCTMIX":
				{
					finalresponse=ProductMix.productMixIntent(serviceChannel, period, userzone, user_region, real_tim_timstamp,
							ul_penet_mtd_adj_mfyp, par_penet_mtd_adj_mfyp, nonpar_penet_mtd_adj_mfyp, 
							protec_penet_mtd_adj_mfyp, ul_penet_mtd_pol_cnt, par_penet_mtd_pol_cnt, 
							nonpar_penet_mtd_pol_cnt, protec_penet_mtd_pol_cnt, protec_penet_ytd_adj_mfyp, 
							ul_penet_ytd_adj_mfyp, par_penet_ytd_adj_mfyp, nonpar_penet_ytd_adj_mfyp, 
							ul_penet_ytd_pol_cnt, par_penet_ytd_pol_cnt, nonpar_penet_ytd_pol_cnt, 
							protec_penet_ytd_pol_cnt, user_circle);
				}
				break;
				case "NB.PRODUCTMIXADJMFYP":

				{
					finalresponse=ProductMixADJMFYP.productMixADJMFYPIntent(serviceChannel, period, userzone, user_region, 
							real_tim_timstamp, ul_penet_mtd_adj_mfyp, par_penet_mtd_adj_mfyp, nonpar_penet_mtd_adj_mfyp, 
							protec_penet_mtd_adj_mfyp, ul_penet_ytd_adj_mfyp, par_penet_ytd_adj_mfyp, 
							nonpar_penet_ytd_adj_mfyp, protec_penet_ytd_adj_mfyp, user_circle);
				}
				break;
				case "NB.PRODUCTMIXPAIDCASE":
				{
					finalresponse=ProductMixPaidCase.productMixPaidCaseIntent(serviceChannel, period, userzone, user_region, 
							real_tim_timstamp, ul_penet_mtd_pol_cnt, par_penet_mtd_pol_cnt,
							ul_penet_ytd_pol_cnt, par_penet_ytd_pol_cnt, nonpar_penet_mtd_pol_cnt,nonpar_penet_ytd_pol_cnt, 
							protec_penet_mtd_pol_cnt, protec_penet_ytd_pol_cnt,user_circle);
				}
				break;
				default :
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
