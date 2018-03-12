package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import utils.HttpUrlConnection_GetDetails;
@Service
public class Achievement 
{
	public String idStatusCall(String ssoId)
	{
		String user_ssoid="";
		JSONObject getUserDetailObject=null;
		String user_channel="", user_sub_channel="", user_designation_desc="", user_getzone="", user_region,
				user_circle="", user_clusters="", user_go="", user_cmo="", user_amo="";
		String segment="SSO_VALIDATION";
		HttpUrlConnection_GetDetails getdetail = new HttpUrlConnection_GetDetails();
		String getdetailresult = getdetail.getUserDetail(segment, ssoId);
		try{
		   getUserDetailObject = new JSONObject(getdetailresult);
		}catch(Exception ex)
		{
			System.out.println("Exception Occoured in Method IdStatusCall while parsing json request");
		}
		try {
			user_ssoid = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("ssoid")+"";
		} catch (Exception ex) {
			user_ssoid = "";
		}
		try {
			user_channel = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("channel")+"";
		} catch (Exception ex) {
			user_channel = "";
		}
		try {
			user_sub_channel = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("sub_channel")+"";
		} catch (Exception ex) {
			user_sub_channel = "";
		}
		try {
			user_designation_desc = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("designation_desc")+"";
		} catch (Exception ex) {
			user_designation_desc = "";
		}
		try {
			user_getzone = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("zone") + "";
		} catch (Exception ex) {
			user_getzone = "";
		}
		try{
			user_region = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("region")+"";
		}catch (Exception ex) {
			user_region = "";
		}
		try{
			user_circle = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("circle")+"";
		} catch (Exception ex) {
			user_circle = "";
		}
		try {
			user_clusters = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("clusters")+"";
		} catch (Exception ex) {
			user_clusters = "";
		}
		try {
			user_go = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("go")+"";
		} catch (Exception ex) {
			user_go = "";
		}
		try {
			user_cmo = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("cmo")+"";
		} catch (Exception ex) {
			user_cmo = "";
		}
		try {
			user_amo = getUserDetailObject.getJSONObject("payload").getJSONObject("ssovalidation").get("amo")+"";
		} catch (Exception ex) {
			user_amo = "";
		}
		String response = achievementCall(user_channel, user_sub_channel, user_getzone, user_region,
				user_circle, user_clusters, user_go, user_cmo, user_amo);
		return response;
	}
	
	public String achievementCall(String serviceChannel, String user_sub_channel, String user_getzone, String user_region,
			String user_circle, String user_clusters,String user_go, String user_cmo, String user_amo)
	{
		String segment="Achievement";
		String DevMode = "N";
		String planType="";
		StringBuilder requestdata = new StringBuilder();
		StringBuilder result = new StringBuilder();
		String output = new String();
		ResourceBundle res = ResourceBundle.getBundle("errorMessages");
		HttpURLConnection conn = null;
		try
		{
			if("Achievement".equalsIgnoreCase(segment))
			{
				XTrustProvider trustProvider = new XTrustProvider();
				trustProvider.install();
				String user_designation_desc="";
				String serviceurl = res.getString("servicegetUserDetail");
				URL url = new URL(serviceurl);
				if(DevMode!=null && !"".equalsIgnoreCase(DevMode) && "Y".equalsIgnoreCase(DevMode))
				{
					Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
					conn = (HttpURLConnection) url.openConnection(proxy);
				}else{
					conn = (HttpURLConnection) url.openConnection();
				}
				UUID uniqueId = UUID.randomUUID();
				HttpsURLConnection.setFollowRedirects(true);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				requestdata.append("	{	");
				requestdata.append("	  \"header\": {	");
				requestdata.append("	    \"correlationId\": \""+uniqueId+"\",	");
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
				requestdata.append("	    \"zone\": \""+user_getzone+"\",");
				requestdata.append("	    \"region\": \""+user_region+"\",");
				requestdata.append("	    \"circle\": \""+user_circle+"\",");
				requestdata.append("	    \"cluster\": \""+user_clusters+"\",");
				requestdata.append("	    \"go\": \""+user_go+"\",");
				requestdata.append("	    \"cmo\": \""+user_cmo+"\",");
				requestdata.append("	    \"amo\": \""+user_amo+"\",");
				requestdata.append("	    \"planType\": \""+planType+"\"");
				requestdata.append("	  }	");
				requestdata.append("	}	");
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
				}
				else
				{
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
					while ((output = br.readLine()) != null) {
						result.append(output);
					}
					conn.disconnect();
					br.close();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Excpetion Occoured in Achivement Call"+e);
		}
		return result.toString();
	}
}
