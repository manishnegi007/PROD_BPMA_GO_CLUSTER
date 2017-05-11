package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
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
		public WebhookResponse getWipDataAll(String action, String channel)
	{
		    ResourceBundle res = ResourceBundle.getBundle("errorMessages");
			String output = new String();
			StringBuilder result = new StringBuilder();	
			String DevMode = "N";
			HttpURLConnection conn = null;
			String speech="";
			//WebhookResponse response = new WebhookResponse();
			try 
			{
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
				requestdata.append("	    \"segment\": \"paid,wip,applied\",	");
				requestdata.append("	    \"channel\": \"Agency\"	");
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
					System.out.println(result.toString());
					JSONObject object = new JSONObject(result.toString());
					
					String dailyAdjustMFYP = object.getJSONObject("payload").getJSONObject("enforceData").get("daily_adj_mfyp")+"";
					String mtdAdjustMFYP = object.getJSONObject("payload").getJSONObject("enforceData").get("mtd_adj_mfyp")+"";
					String dailyAppliedAFYP = object.getJSONObject("payload").getJSONObject("appliedData").get("daily_applied_afyp")+"";
					String mtdAppliedAFYP = object.getJSONObject("payload").getJSONObject("appliedData").get("mtd_applied_afyp")+"";
					
					double sum = 0;
					
					double wipAFYP = Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("wip_afyp").toString());
					double hoWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("ho_wip_afyp").toString());
					double goWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("go_wip_afyp").toString());
					double itWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("it_wip_afyp").toString());
					double finWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("fin_wip_afyp").toString());
					double miscWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("misc_wip_afyp").toString());
					double welcomeWIPAFYP =Double.parseDouble(object.getJSONObject("payload").getJSONObject("wipData").get("welcome_wip_afyp").toString());
					
					sum = sum+wipAFYP+hoWIPAFYP+goWIPAFYP+itWIPAFYP+finWIPAFYP+miscWIPAFYP+welcomeWIPAFYP;
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
//					System.out.println(dtf.format(now));
					
					String finalresponse="Monthly OverAll Business as of "+dtf.format(now)+
							", is Paid : Adj MFYP FTD is "+dailyAdjustMFYP+" Cr,"
							+" Adj MFYP YTD is: "+mtdAdjustMFYP+" Cr ||"
							+"  Applied Data: "
							+ "				Applied AFYP FTD is: " +dailyAppliedAFYP+" Cr"
							+", Applied AFYP MTD is: " +mtdAppliedAFYP+" Cr"
							+ " || WIP Data"
							+      " WIP AFYP is: " +sum+" Cr";
					//response.setSpeech(finalresponse);
					//response.setDisplayText(finalresponse);
					speech=finalresponse;
					
				}

			}catch(Exception ex)
			{
				System.out.println("Exception>>>>>>>>>>>>"+ex);
			}
			WebhookResponse responseObj = new WebhookResponse(speech, speech);
			System.out.println("*******"+responseObj);
			return responseObj;
		}

}

