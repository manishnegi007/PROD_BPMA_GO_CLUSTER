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
//--------------Mongo Dependencies--------------------------
// import java.net.UnknownHostException;
// import com.mongodb.BasicDBObject;
// import com.mongodb.DB;
// import com.mongodb.DBCollection;
// import com.mongodb.DBCursor;
// import com.mongodb.MongoClient;
// import com.mongodb.MongoException;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Component
public class APIConsumerService {
	
	public WebhookResponse getWipDataAll(String action, String channel, String period, String productType, String planType)
	{
// 		--------------START MONGO DB-------------------------------
// 	try {
// 		MongoClient mongo = new MongoClient("mongodb://MLI:max@1234@ds147842.mlab.com", 47842);
// 		System.out.println(mongo);
// 		DB db = mongo.getDB("mlibot");
// 		DBCollection table = db.getCollection("test");
// 		BasicDBObject document = new BasicDBObject();
// 		document.put("name", "ABC");
// 		document.put("age", 30);
// 		document.put("createdDate", new Date());
// 		table.insert(document);
// 	     } 
// 		catch (UnknownHostException ex) {
// 		System.out.println(ex);
//     		} catch (MongoException ex) {
// 		System.out.println(ex);
//     	}
// 		--------------END MONGO DB-------------------------------

		List<InnerButton> innerbuttonlist = new ArrayList<InnerButton>();
		Facebook fb = new Facebook();
		InnerData innerData= new InnerData();
		//InnerButton innerButton = new InnerButton();
		
		if("MLI".equalsIgnoreCase(channel)||"CAT".equalsIgnoreCase(channel))
		{
			channel=channel.toUpperCase();
		}
		else
		{
			channel=convertToCamelCase(channel);

		}
		
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
					}else{
						segment = "paid,wip,applied";
						serviceChannel = "Axis Bank";
					}
				}else{
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
					}else{
						segment="paid";
						serviceChannel = "Axis Bank";
					}
				}else{
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
					}else{
						segment="wip";
						serviceChannel = "Axis Bank";
					}
				}else{
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
					}else{
						segment="applied";
						serviceChannel = "Axis Bank";
					}
				}else{
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
					{result.append(output);}
					conn.disconnect();
					br.close();
					System.out.println("External API Call : END");
				}else{
					System.out.println("Unable to call External API :- WIP, APPLIED, ENFORCE");
				}
			}else
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
				    System.out.println("We are in Exception in to Close the writer object");
				}
				int apiResponseCode2 = conn.getResponseCode();
				if(apiResponseCode2 == 200)
				{
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					while ((output = br.readLine()) != null) 
					{result.append(output);}
					conn.disconnect();
					br.close();
				}else{
					System.out.println("Unable to connect External API");
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
				double ytd_adj_mfyp1=0;
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
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
				LocalDateTime now = LocalDateTime.now();
				for(int i=0;i<1;i++){
					try{
						real_tim_timstamp = object.getJSONObject("payload").getJSONObject("enforceData").get("real_tim_timstamp").toString();
						if(real_tim_timstamp!=null){break;}
					}catch(Exception ex){}
					try{
						real_tim_timstamp = object.getJSONObject("payload").getJSONObject("appliedData").get("real_tim_timstamp").toString();
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
				try{
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
					ytd_adj_mfyp1 = Double.parseDouble(object.getJSONObject("payload").getJSONObject("enforceData").get("ytd_adj_mfyp").toString());
				}catch(Exception ex){}
				String ytd_adj_mfyp = df.format(ytd_adj_mfyp1);
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
				sum4=sum4+ho_wip_count+go_wip_count+it_wip_count+fin_wip_count+misc_wip_count+welcome_wip_count;
				String convertsum4  =  df1.format(sum4);

				if("NUMBERS".equalsIgnoreCase(action))
				{
					if("MONTHLY".equalsIgnoreCase(period) ||"MTD".equalsIgnoreCase(period) ||"MONTH".equalsIgnoreCase(period))
					{
						finalresponse="As of "+real_tim_timstamp+
								", the Business update for "+channel+ " is :\n"
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
						finalresponse="As of "+real_tim_timstamp+
								", the Business update for "+channel+" is : \n Adj MFYP FTD:"+dailyAdjustMFYP+" Cr, \n\n"
								+"Adj MFYP MTD: " +mtdAdjustMFYP+" Cr, \n\n"
								+"Adj MFYP YTD : "+ytd_adj_mfyp+" Cr, \n\n"
								+"Applied AFYP FTD: " +dailyAppliedAFYP+" Cr, \n\n"
								+"Applied AFYP MTD: " +mtdAppliedAFYP+" Cr, \n\n"
								+"Applied AFYP YTD: "+ytd_applied_afyp+" Cr, \n\n"
								+"WIP AFYP: " +convertsum3+" Cr.";
					}
					else 
					{
						finalresponse="As of "+real_tim_timstamp+
								", the Business update for "+channel+" is : \n Adj MFYP FTD:"+dailyAdjustMFYP+" Cr, \n\n"
								+"Adj MFYP MTD: " +mtdAdjustMFYP+" Cr, \n\n"
								+"Adj MFYP YTD : "+ytd_adj_mfyp+" Cr, \n\n"
								+"Applied AFYP FTD: " +dailyAppliedAFYP+" Cr, \n\n"
								+"Applied AFYP MTD: " +mtdAppliedAFYP+" Cr, \n\n"
								+"Applied AFYP YTD: "+ytd_applied_afyp+" Cr, \n\n"
								+"WIP AFYP: " +convertsum3+" Cr.";
						if("MLI".equalsIgnoreCase(channel) || "".equalsIgnoreCase(channel))
						{
							finalresponse = finalresponse+" Do you want to see the Data Channel Wise like :\n\n Agency, Axis Bank, Banca, Cat";
						}
					}
				}
				else if("AdjMFYP".equalsIgnoreCase(action))
				{
					if("MONTHLY".equalsIgnoreCase(period) ||"MTD".equalsIgnoreCase(period) ||"MONTH".equalsIgnoreCase(period))
					{
						finalresponse="As of "+real_tim_timstamp+" Paid AdjMFYP Business MTD for "+channel+
								" is : "+mtdAdjustMFYP+" Cr";
					}
					else if(!"".equalsIgnoreCase(channel))
					{
						finalresponse="As of "+real_tim_timstamp+"  Paid AdjMFYP Business for "+channel+ "is :"+
							     " FTD : " +dailyAdjustMFYP+" Cr,"
								+" MTD : " +mtdAdjustMFYP+" Cr"
								+" YTD : " +ytd_adj_mfyp+" Cr";
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
					if(!"".equalsIgnoreCase(channel))
					{
					finalresponse="WIP for "+channel+" is AFYP :" +convertsum3+"Cr. and Policies"+convertsum4+", "
								+"\n\n HO WIP AFYP :"+hoWIPAFYP+"Cr. and Policies"+ho_wip_count+", "
								+"\n\n GO WIP AFYP :"+goWIPAFYP+"Cr. and Policies "+go_wip_count+", "
								+"\n\n IT WIP AFYP :"+itWIPAFYP+"Cr. and Policies"+it_wip_count+", "
								+"\n\n FIN WIP AFYP :"+finWIPAFYP+"Cr. and Policies"+fin_wip_count+", "
								+"\n\n MISC WIP AFYP :"+miscWIPAFYP+"Cr. and Policies"+misc_wip_count+", "
								+"\n\n WELCOME WIP AFYP :"+welcomeWIPAFYP+"Cr. and Policies "+welcome_wip_count+"";
					}
					else
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
						finalresponse="As of "+real_tim_timstamp+" Applied Business AFYP MTD For "+channel+
								": "+mtdAppliedAFYP+" Cr "+"";
					}
					else if(!"".equalsIgnoreCase(channel))
					{
						finalresponse="As of "+real_tim_timstamp+" Applied AFYP For "+channel+"  is: "
								+ " FTD : " +dailyAppliedAFYP+" Cr"
								+", MTD : " +mtdAppliedAFYP+" Cr"
								+", YTD : " +ytd_applied_afyp+" Cr";
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

					/*if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period) || "MONTH".equalsIgnoreCase(period)){
						finalresponse = channel+" has witnessed paid business growth of "+grth_paid_adj_mfyp
								+"% on MTD basis, \n\n last month we had clocked "+adj_mfyp_lst_mn+
								"Cr of Adj MFYP as compared to "+mtd_inforced_adj_mfyp+" today";
					}*/
					
						finalresponse = channel+" has witnessed paid Business growth of "+grth_ovr_lst_yr_paid
								+"% on YTD basis, \n\n last year same time we had clocked "+adj_mfyp_sam_ytd_lst_yr+
								"Cr of Adj MFYP as compared to "+ytd_inforced_adj_mfyp+" today";

					
				}
				else if("Achievement".equalsIgnoreCase(action))
				{
					finalresponse="At MTD level "+channel+" has achieved "+achiev_mtd_adj_mfyp+"% of Management Plan, Our monthly plan is "
							+pln_mtd_basis_adj_mfyp+" and till "+real_tim_timstamp+" We have achieved "+mtd_inforced_adj_mfyp_achi+" Cr, At YTD level "
							+channel+" has achieved "+achiev_ytd_adj_mfyp+"% of Management Plan, Our yearly plan is "
							+pln_ytd_basis_adj_mfyp+" and till "+real_tim_timstamp+" We have achieved "+ytd_inforced_adj_mfyp_achi+" Cr";
				}
				else if("Penetration".equalsIgnoreCase(action))
				{
					if("Monthly".equalsIgnoreCase(period) || "".equalsIgnoreCase(period) || "MTD".equalsIgnoreCase(period)||"MONTH".equalsIgnoreCase(period))
					{
						if("ULIP".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid Business AFYP MTD and "+ul_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis";
						}
						else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid Business AFYP MTD and "+trad_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_mtd_afyp+" % of "+mtd_inforced_afyp
									+" Cr of paid Business AFYP MTD and "+protec_penet_mtd_pol_cnt+" % of "+mtd_inforced_count+" Policies"
									+ " issued on MTD basis";
						}
					}
					else
					{
						if("ULIP".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+ul_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid Business AFYP YTD and "+ul_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else if("TRAD".equalsIgnoreCase(productType))
						{
							finalresponse=channel+" "+productType+" Penetration is "+trad_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid Business AFYP YTD and "+trad_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
						}
						else
						{
							finalresponse=channel+" "+productType+" Penetration is "+protec_penet_ytd_afyp+" % of "+ytd_inforced_afyp+
									" Cr of paid Business AFYP YTD and "+protec_penet_ytd_pol_cnt+" % of "+ytd_inforced_count+" Policies"
									+ " issued on YTD basis";
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
